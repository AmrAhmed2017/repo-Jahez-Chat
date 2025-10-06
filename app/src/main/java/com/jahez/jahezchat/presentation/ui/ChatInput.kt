package com.jahez.jahezchat.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jahez.jahezchat.R
import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.jahezchat.ui.theme.DateColor
import com.jahez.websocket.util.toTimeString

@Composable
fun ChatInput(
    onSendClick: (ChatUIMessage) -> Unit
) {
    val context = LocalContext.current
    var message by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .background(MaterialTheme.colorScheme.tertiary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = message,
                    onValueChange = {
                        message = it
                    },
                    placeholder = { Text(stringResource(R.string.user_prompt)) },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Transparent,
                        focusedTextColor = DateColor,
                        unfocusedTextColor = DateColor
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                )

                IconButton(onClick = {
                    if (message.isNotEmpty() && message.isNotBlank()) {
                        onSendClick.invoke(
                            ChatUIMessage(
                                text = message,
                                time = System.currentTimeMillis().toTimeString(),
                                isMine = true
                            )
                        )
                        message = ""
                    } else
                        Toast.makeText(context,
                            context.getString(R.string.empty_message_warning), Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painterResource(R.drawable.ic_send),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
