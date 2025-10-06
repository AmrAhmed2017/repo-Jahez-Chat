package com.jahez.jahezchat.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.jahezchat.ui.theme.Body1Typography

@Composable
fun ReceiverMessageBubble(
    modifier: Modifier = Modifier,
    message: ChatUIMessage
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChatInfo(
            imageURL = message.avatarUrl,
            currentTime = message.time
        )

        HorizontalSpacer(12.dp)

        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .padding(12.dp)
                .weight(1f, false)
                .wrapContentWidth()
        ) {
            Text(
                message.message,
                color = MaterialTheme.colorScheme.tertiary,
                style = Body1Typography
            )
        }
    }
}