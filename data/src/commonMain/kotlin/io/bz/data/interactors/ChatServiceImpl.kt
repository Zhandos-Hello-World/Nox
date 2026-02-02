package io.bz.data.interactors

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.chat.ChatIntent
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.FileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.logger.Logger

class ChatServiceImpl(
    private val repository: ChatRepository,
    private val fileRepository: FileRepository,
    private val coroutineScope: CoroutineScope,
) : ChatService {
    private val downloadingFiles = mutableSetOf<Int>()

    override val state: StateFlow<LinkedHashMap<Long, ChatModel>> = combine(
        repository.allChats,
        fileRepository.files.map { files -> files.associateBy { it.id } }
    ) { chats, filesMap ->
        val newMap = LinkedHashMap<Long, ChatModel>()

        chats.forEach { chat ->
            val photoFile = chat.photo?.small
            val updatedFile = filesMap[photoFile?.id] // Проверяем, есть ли свежие данные о файле

            // Если файл найден в репозитории файлов, обновляем статус в модели
            val chatModel = if (updatedFile != null) {
                chat.copy(photo = chat.photo?.copy(small = updatedFile))
            } else {
                chat
            }


            // Логика запуска загрузки:
            // Проверяем актуальное состояние файла
            val fileToDownload = updatedFile ?: photoFile
            if (fileToDownload != null && !fileToDownload.local.isDownloadingCompleted) {
                triggerDownload(fileToDownload.id)
            }

            newMap[chat.id] = chatModel
        }
        newMap
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = linkedMapOf()
    )

    override suspend fun <T> sendIntent(intent: ChatIntent): DomainResult<T> {
        return when (intent) {
            is ChatIntent.LoadChats -> repository.loadChats(
                chatListType = intent.type,
                limit = intent.limit,
            ) as DomainResult<T>

            is ChatIntent.LoadChatHistory -> repository.loadChatHistory(
                chatId = intent.chatId,
                fromMessageId = intent.fromMessageId,
                offset = intent.offset,
                limit = intent.limit,
                onlyLocal = intent.onlyLocal,
            ) as DomainResult<T>

            is ChatIntent.LoadDirectMessagesChatTopics -> TODO("IMPLEMENT")
        }
    }

    private fun triggerDownload(fileId: Int) {
        if (downloadingFiles.contains(fileId)) return

        downloadingFiles.add(fileId)
        coroutineScope.launch {
            fileRepository.donwloadFile(fileId, 1, 0, 0, false)
        }
    }

}