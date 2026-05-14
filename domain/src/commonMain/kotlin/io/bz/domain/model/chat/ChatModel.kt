package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatModel(
    @SerialName("id") val id: Long,
    @SerialName("type") val type: ChatType,
    @SerialName("title") val title: String,
    @SerialName("photo") val photo: ChatPhotoInfo? = null,
    @SerialName("accent_color_id") val accentColorId: Int,
    @SerialName("background_custom_emoji_id") val backgroundCustomEmojiId: Long,
    @SerialName("profile_accent_color_id") val profileAccentColorId: Int,
    @SerialName("profile_background_custom_emoji_id") val profileBackgroundCustomEmojiId: Long,
    @SerialName("permissions") val permissions: ChatPermissions,
    @SerialName("last_message") val lastMessage: Message? = null,
    @SerialName("upgraded_gift_colors") val upgradedGiftColors: UpgradedGiftColors? = null,

    // Используй List вместо Array для лучшей совместимости в KMP
    @SerialName("positions") val positions: List<ChatPosition> = emptyList(),

    @SerialName("chat_lists") val chatListTypes: List<ChatListType> = emptyList(),

    @SerialName("message_sender_id") val messageSenderId: Message.MessageSender? = null,

    @SerialName("has_protected_content") val hasProtectedContent: Boolean,

    @SerialName("is_translatable") val isTranslatable: Boolean,

    @SerialName("is_marked_as_unread") val isMarkedAsUnread: Boolean,

    @SerialName("view_as_topics") val viewAsTopics: Boolean,

    @SerialName("has_scheduled_messages") val hasScheduledMessages: Boolean,

    @SerialName("can_be_deleted_only_for_self") val canBeDeletedOnlyForSelf: Boolean,

    @SerialName("can_be_deleted_for_all_users") val canBeDeletedForAllUsers: Boolean,

    @SerialName("can_be_reported") val canBeReported: Boolean,

    @SerialName("default_disable_notification") val defaultDisableNotification: Boolean,

    @SerialName("unread_count") val unreadCount: Int,

    @SerialName("last_read_inbox_message_id") val lastReadInboxMessageId: Long,

    @SerialName("last_read_outbox_message_id") val lastReadOutboxMessageId: Long,

    @SerialName("unread_mention_count") val unreadMentionCount: Int,

    @SerialName("unread_reaction_count") val unreadReactionCount: Int,

    @SerialName("notification_settings") val notificationSettings: ChatNotificationSettings,

    @SerialName("available_reactions") val availableReactions: ChatAvailableReactions,

    @SerialName("message_auto_delete_time") val messageAutoDeleteTime: Int,

//    @SerialName("emoji_status") val emojiStatus: EmojiStatus? = null,

//    @SerialName("background") val background: ChatBackground? = null,

//    @SerialName("theme") val theme: ChatTheme? = null,

    @SerialName("action_bar") val actionBar: ChatActionBar? = null,

    @SerialName("business_bot_manage_bar") val businessBotManageBar: BusinessBotManageBar? = null,

    @SerialName("video_chat") val videoChat: VideoChat,

    @SerialName("pending_join_requests") val pendingJoinRequests: ChatJoinRequestsInfo? = null,

    @SerialName("reply_markup_message_id") val replyMarkupMessageId: Long,

    @SerialName("client_data") val clientData: String = ""
)