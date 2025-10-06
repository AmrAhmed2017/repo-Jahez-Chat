package com.jahez.jahezchat.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jahez.jahezchat.ui.theme.CaptionTypography

@Composable
fun ChatInfo(
    modifier: Modifier = Modifier,
    imageURL: String,
    currentTime: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageURL,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )

        VerticalSpacer(6.dp)

        Text(
            text = currentTime,
            style = CaptionTypography,
        )
    }
}

@Preview
@Composable
fun ChatInfoPreview() {
    ChatInfo(
        imageURL = "https://avatar.iran.liara.run/public/3",
        currentTime = "12:00 PM"
    )
}