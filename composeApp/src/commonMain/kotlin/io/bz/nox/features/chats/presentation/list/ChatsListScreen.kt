package io.bz.nox.features.chats.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import io.bz.domain.model.FormattedText
import io.bz.domain.model.chat.ChatAvailableReactions
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.ChatNotificationSettings
import io.bz.domain.model.chat.ChatPermissions
import io.bz.domain.model.chat.ChatPosition
import io.bz.domain.model.chat.ChatType
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.MessageContent
import io.bz.domain.model.chat.VideoChat
import io.bz.domain.state.ChatState
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.theme.NoxTheme
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.emptyList
import kotlin.math.absoluteValue
import kotlin.time.Clock
import kotlin.time.Instant

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
                onGetAll = viewModel::getChats,
                onChat = { flow.invoke(CommonScreenFlows.Chat(it)) },
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
    onChat: (ChatModel) -> Unit,
) {
    Column(modifier) {
        LaunchedEffect(Unit) { onGetAll.invoke() }

        LazyColumn {
            items(
                chatState.chats.values.toList(),
                key = { it.id },
            ) {
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    chat = it,
                    onChat = onChat,
                )
            }
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    chat: ChatModel,
    onChat: (ChatModel) -> Unit,
) {
    Column(
        modifier = Modifier.clickable{
            onChat.invoke(chat)
        }
    ) {
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
                Row(
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = fullName.toString(),
                        maxLines = 1,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.weight(0.1F))

                    chat.lastMessage?.let {
                        Text(
                            maxLines = 1,
                            text = formatTdlibTime(
                                timestampSec = it.date.toLong(),
                            ).trim(),
                            
                        )
                    }


                }
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
                            text = contentMessage.rawJson.toString(),
                            color = Color.Red,
                        )
                    }
                    MessageContent.MessageContactRegistered -> {
                        Text(
                            text = "Joined Telegram",
                            color = Color.Cyan,
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
fun formatTdlibTime(
    timestampSec: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {

    val nowDate = Clock.System.now().toLocalDateTime(timeZone).date
    val dateTime = Instant.fromEpochSeconds(timestampSec).toLocalDateTime(timeZone)
    val messageDate = dateTime.date

    val daysBetween = nowDate.daysUntil(messageDate).absoluteValue

    return when {
        daysBetween == 0 -> {
            // Сегодня → HH:mm
            "${dateTime.hour.pad2()}:${dateTime.minute.pad2()}"
        }

        daysBetween == 1 -> {
            "Вчера"
        }

        daysBetween <= 2 -> {
            messageDate.dayOfWeek.toRussian()
        }

        else -> {
            "${messageDate.dayOfMonth.pad2()}." +
                    "${messageDate.monthNumber.pad2()}." +
                    messageDate.year
        }
    }
}

private fun Int.pad2(): String =
    if (this < 10) "0$this" else this.toString()
fun DayOfWeek.toRussian(): String = when (this) {
    DayOfWeek.MONDAY -> "Понедельник"
    DayOfWeek.TUESDAY -> "Вторник"
    DayOfWeek.WEDNESDAY -> "Среда"
    DayOfWeek.THURSDAY -> "Четверг"
    DayOfWeek.FRIDAY -> "Пятница"
    DayOfWeek.SATURDAY -> "Суббота"
    DayOfWeek.SUNDAY -> "Воскресенье"
}

@Composable
fun UserAvatar(
    path: String?,
    size: Dp = 48.dp,
) {

    Box(
        modifier = Modifier.size(size).clip(
            RoundedCornerShape(12.dp),
        ),
        contentAlignment = Alignment.Center,

        ) {
        when {
            path != null && path.isNotEmpty() -> {
                CoilImage(
                    imageModel = { path },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    ),
                    modifier = Modifier.fillMaxSize(),
                )
            }

            else -> {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                    modifier = Modifier.size(size / 2),
//                    tint = Color.Gray
//                )
            }
        }
    }
}
