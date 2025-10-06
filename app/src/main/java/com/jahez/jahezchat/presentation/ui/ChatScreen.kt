package com.jahez.jahezchat.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jahez.jahezchat.R
import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.jahezchat.ui.theme.Body2Typography
import com.jahez.websocket.domain.AppThrowable
import com.jahez.websocket.util.getCurrentDay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatUIState,
    onBackClick: () -> Unit,
    onCallClick: () -> Unit,
    sendMessage: (ChatUIMessage) -> Unit,
    reConnect: () -> Unit,
    resetError: () -> Unit
) {
    var tempMessage: ChatUIMessage? = null
    val listState = rememberLazyListState()

    LaunchedEffect(state.data.size) {
        if (state.data.isNotEmpty()) {
            listState.scrollToItem(state.data.lastIndex)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        ChatHeader(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            onBackClick = onBackClick,
            onCallClick = onCallClick
        )

        VerticalSpacer(8.dp)

        Text(
            text = getCurrentDay(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = Body2Typography
        )

        VerticalSpacer(8.dp)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .padding(8.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(state.data) { msg ->
                if (msg.isMine)
                    SenderMessageBubble(message = msg)
                else
                    ReceiverMessageBubble(message = msg)
                VerticalSpacer(20.dp)
            }
        }

        ChatInput { chatMsg ->
            tempMessage = chatMsg
            sendMessage.invoke(chatMsg)
        }

        VerticalSpacer(8.dp)

        if (state.error != null) {
            ExceptionDialog(
                error = if (state.error == AppThrowable.SERVER_CONNECTION_ERROR)
                    stringResource(R.string.connection_failed_error_message)
                else stringResource(R.string.message_not_sent_error_message),
                onDismissRequest = {},
                onConfirmation = {
                    if (state.error == AppThrowable.SERVER_CONNECTION_ERROR) {
                        reConnect.invoke()
                        resetError.invoke()
                    } else {
                        tempMessage?.let {
                            sendMessage.invoke(it)
                        }
                        resetError
                    }

                }
            )
        }
    }
}




