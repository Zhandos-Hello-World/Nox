package io.bz.nox.features.chat.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.MessageContent
import io.bz.nox.features.chats.presentation.list.UserAvatar
import io.bz.nox.theme.PurpleGrey80

@Composable
fun ChatScreen(
    model: ChatUiModel,
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(model.messages.size) {
        if (model.messages.isNotEmpty()) {
            listState.animateScrollToItem(model.messages.lastIndex)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        UserToolbar(
            modifier = Modifier.fillMaxWidth(),
            chat = model.chat,
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(model.messages) { item ->
                ChatItem(item)
            }
        }

        ChatBox(
            onSendChatClickListener = onSendChatClickListener, modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ChatItem(message: Message) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {

        Box(
            modifier = Modifier.align(
                if (message.isOutgoing) Alignment.End else Alignment.Start
            ).clip(
                RoundedCornerShape(
                    topStart = 48f,
                    topEnd = 48f,
                    bottomStart = if (message.isOutgoing) 48f else 0f,
                    bottomEnd = if (message.isOutgoing) 0f else 48f
                )
            ).background(PurpleGrey80).padding(16.dp)
        ) {
            when (val content = message.content) {
                is MessageContent.MessageText -> {
                    Text(
                        text = content.text.text,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

                is MessageContent.UnSupportedContent -> {
                    Text(
                        text = content.name,
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                MessageContent.MessageContactRegistered -> {
                    Text(
                        text = "Joined Telegram",
                        color = Color.Cyan,
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBox(
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier,
) {
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
    Row(modifier = modifier.padding(16.dp)) {
        TextField(
            value = chatBoxValue,
            onValueChange = { newText ->
                chatBoxValue = newText
            },
            modifier = Modifier.weight(1f).padding(4.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Type something")
            })
        IconButton(
            onClick = {
                val msg = chatBoxValue.text
                if (msg.isBlank()) return@IconButton
                onSendChatClickListener(chatBoxValue.text)
                chatBoxValue = TextFieldValue("")
            },
            modifier = Modifier.clip(CircleShape).background(color = PurpleGrey80)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
        }
    }
}


@Composable
fun UserToolbar(
    modifier: Modifier = Modifier,
    chat: ChatModel,
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var path: String? = chat.photo?.small?.local?.path
            var fullName: String? = chat.title

            UserAvatar(
                path = path,
                size = 48.dp,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = fullName.toString(),
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

        }


        Spacer(Modifier.height(12.dp))

        HorizontalDivider(
            thickness = 0.5.dp,
        )
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun ChatScreenPreview() {
//    NoxTheme {
//        ChatScreen(
//            model = ChatUiModel(
//                messages = listOf(
//                    ChatUiModel.Message(
//                        "Hi Tree, How you doing?",
//                        ChatUiModel.Author("0", "Branch")
//                    ),
//                    ChatUiModel.Message(
//                        "Hi Branch, good. You?",
//                        ChatUiModel.Author("-1", "Tree"))
//                ),
//                addressee = ChatUiModel.Author("0", "Branch")
//            ),
//            onSendChatClickListener = {},
//            modifier = Modifier
//        )
//    }
//}