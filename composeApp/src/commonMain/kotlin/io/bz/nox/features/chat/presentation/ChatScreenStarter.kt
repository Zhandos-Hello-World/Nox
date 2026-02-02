package io.bz.nox.features.chat.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.bz.domain.model.chat.ChatModel
import io.bz.nox.features.CommonScreenFlows
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreenStarter(
    modifier: Modifier = Modifier,
    model: ChatModel,
    flow: (CommonScreenFlows) -> Unit,
) {
    val viewModel: ChatViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMessages(model.id)
    }

    ChatScreen(
        ChatUiModel(
            messages = state,
            chat = model,
        ),
        modifier = modifier,
        onSendChatClickListener = {},
    )
}