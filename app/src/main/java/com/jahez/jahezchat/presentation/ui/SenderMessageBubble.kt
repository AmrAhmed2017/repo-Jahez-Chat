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
fun SenderMessageBubble(
    modifier: Modifier = Modifier,
    message: ChatUIMessage
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 0.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .padding(12.dp)
                .weight(1f, false)
                .wrapContentWidth()
        ) {
            Text(
                message.text,
                color = MaterialTheme.colorScheme.primary,
                style = Body1Typography
            )
        }

        HorizontalSpacer(12.dp)

        ChatInfo(
            imageURL = message.avatarUrl,
            currentTime = message.time
        )
    }
}