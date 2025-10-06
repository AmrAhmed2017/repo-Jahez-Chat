package com.jahez.jahezchat.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jahez.jahezchat.R
import com.jahez.jahezchat.presentation.viewModel.NewChatViewModel
import com.jahez.jahezchat.ui.theme.JahezChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JahezChatTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    val viewModel: NewChatViewModel = hiltViewModel()
                    val state by viewModel.uiState.collectAsState()

                    LaunchedEffect(key1 = Unit) {
                        viewModel.connect()
                        viewModel.getSavedMessages()
                    }
                    ChatScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onBackClick = { finish() },
                        onCallClick = {
                            Toast.makeText(this,
                                getString(R.string.calling_message), Toast.LENGTH_SHORT).show()
                        },
                        sendMessage = { msg ->
                            viewModel.send(msg)
                        },
                        reConnect = {
                            viewModel.connect()
                        },
                        resetError = {
                            viewModel.resetError()
                        }
                    )
                }
            }
        }
    }
}