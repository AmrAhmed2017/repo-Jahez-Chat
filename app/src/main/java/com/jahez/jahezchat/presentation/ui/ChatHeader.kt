package com.jahez.jahezchat.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jahez.systemdesign.R
import com.jahez.jahezchat.ui.theme.HeadingTypography


@Composable
fun ChatHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCallClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Chat Name should retrieved from BE at prod
        Text(
            text = stringResource(R.string.chat_name),
            style = HeadingTypography
        )

        Surface(
            modifier = Modifier
                .align(Alignment.CenterStart).size(32.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.CenterEnd).size(32.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            IconButton(
                onClick = onCallClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
@Preview
fun ChatHeaderPreview() {
    ChatHeader(
        onBackClick = {},
        onCallClick = {}
    )
}