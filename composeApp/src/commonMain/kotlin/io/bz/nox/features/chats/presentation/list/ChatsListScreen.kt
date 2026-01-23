package io.bz.nox.features.chats.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.MessageContent
import io.bz.domain.state.ChatState
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.features.users.presentation.main.UserAvatar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatsListScreenStarter(
    modifier: Modifier = Modifier,
    flow: (CommonScreenFlows) -> Unit,
) {
    val viewModel: ChatsListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val uiState = state) {
        is ChatState.Data -> {
            ChatsListScreen(
                modifier = modifier,
                chatState = uiState,
                onGetAll = {
                    viewModel.getChats()
                },
            )
        }

        ChatState.None -> {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun ChatsListScreen(
    modifier: Modifier = Modifier,
    chatState: ChatState.Data,
    onGetAll: () -> Unit,
) {
    Column(modifier) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onGetAll.invoke()
            },
            content = {
                Text(
                    text = "GET ALL"
                )
            },
        )
        LazyColumn {
            items(chatState.chats.values.toList()) {
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    chat = it,
                )
            }
        }
    }
}

@Composable
fun ChatItem(
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
                path = path, size = 48.dp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = fullName.toString(), style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))

                when (val contentMessage = chat.lastMessage?.content) {
                    is MessageContent.MessageText -> {
                        Text(
                            text = contentMessage.text.text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    is MessageContent.UnSupportedContent -> {
                        Text(
                            text = contentMessage.name,
                            color = Color.Red,
                        )
                    }

                    null -> {

                    }
                }
            }

        }


        Spacer(Modifier.height(12.dp))

        HorizontalDivider(
            thickness = 0.5.dp,
        )
    }
}


@Preview
@Composable
private fun ChatListScreenPreview() {
//    TelegaTheme {
//        val mockChat = Chat(
//            id = 123456789L,
//
//            type = ChatType.PRIVATE,
//            title = "Test Chat",
//            photo = null,
//            accentColorId = 5,
//            backgroundCustomEmojiId = 0L,
//            upgradedGiftColors = null,
//            profileAccentColorId = 3,
//            profileBackgroundCustomEmojiId = 0L,
//            permissions = ChatPermissions(
//                permissions = mutableSetOf()
//            ),
//
//            lastMessage = Message(
//                id = 1001L,
//                sender = Message.MessageSender.FromUser(
//                    userId = 987654321L,
//                ),
//
//                chatId = 123456789L,
//
//                sendingState = null,
//                schedulingState = null,
//
//                isOutgoing = false,
//                isPinned = false,
//                isFromOffline = false,
//                canBeSaved = true,
//                hasTimestampedMedia = false,
//                isChannelPost = false,
//                isPaidStarSuggestedPost = false,
//                isPaidTonSuggestedPost = false,
//                containsUnreadMention = true,
//
//                date = 1_706_000_000,
//                editDate = 0,
//
//                forwardInfo = null,
//                importInfo = null,
//                interactionInfo = null,
//
//                unreadReactions = emptyList(),
//                factCheck = null,
//                suggestedPostInfo = null,
//                replyTo = null,
//                topic = null,
//
//                selfDestructType = null,
//                selfDestructInSeconds = 0.0,
//                autoDeleteInSeconds = 0.0,
//
//                viaBotUserId = 0L,
//                senderBusinessBotUserId = 0L,
//                senderBoostCount = 0,
//                paidMessageStarCount = 0L,
//
//                authorSignature = null,
//                mediaAlbumId = 0L,
//                effectId = 0L,
//
//                restrictionInfo = null,
//
//                content = MessageContent.MessageText(
//                    text = FormattedText(
//                        text = "Hello 👋 This is a fully mocked message", entities = emptyList()
//                    ),
//                    linkPreviewOptions = null,
//                    linkPreview = null,
//                ),
//                replyMarkup = null,
//            ),
//
//            positions = arrayOf(
//                ChatPosition(
//                    list = ChatList.Main,
//                    order = 100L,
//                    isPinned = false,
//                    source = null,
//                )
//            ),
//
//            chatLists = arrayOf(
//                ChatList.Main
//            ),
//
//            messageSenderId = Message.MessageSender.FromUser(
//                userId = 987654321L
//            ),
//
//            hasProtectedContent = false,
//            isTranslatable = true,
//            isMarkedAsUnread = false,
//            viewAsTopics = false,
//            hasScheduledMessages = false,
//
//            canBeDeletedOnlyForSelf = true,
//            canBeDeletedForAllUsers = false,
//            canBeReported = true,
//
//            defaultDisableNotification = false,
//
//            unreadCount = 2,
//            lastReadInboxMessageId = 553L,
//            lastReadOutboxMessageId = 550L,
//            unreadMentionCount = 1,
//            unreadReactionCount = 0,
//
//            notificationSettings = ChatNotificationSettings(
//                useDefaultMuteFor = true,
//                muteFor = 0,
//                useDefaultSound = true,
//                soundId = 0L,
//                useDefaultShowPreview = true,
//                showPreview = true,
//                useDefaultMuteStories = true,
//                muteStories = false,
//                useDefaultStorySound = true,
//                storySoundId = 0L,
//                useDefaultShowStoryPoster = true,
//                showStoryPoster = true,
//                useDefaultDisablePinnedMessageNotifications = true,
//                disablePinnedMessageNotifications = false,
//                useDefaultDisableMentionNotifications = true,
//                disableMentionNotifications = false
//            ),
//
//            availableReactions = ChatAvailableReactions.ChatAvailableReactionsAll(
//                maxReactionCount = 3
//            ),
//
//            messageAutoDeleteTime = 0,
//
//            emojiStatus = null,
//
//            replyMarkupMessageId = 0L,
//
//            clientData = "mock_chat_data"
//        )
//
//        ChatsListScreen(
//            chatState = ChatState.Data(listOf(mockChat, mockChat),
//                lastMessages = emptyList(),
//                emptyMap()),
//            onGetAll = {}
//        )
//    }
}