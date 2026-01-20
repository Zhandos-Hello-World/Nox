package io.bz.domain.model.chat

data class ChatModel(
    /** Chat unique identifier. */
    val id: Long,

    /** Type of the chat. */
    val type: ChatType,

    /** Chat title. */
    val title: String,

    /** Chat photo; may be null. */
    val photo: ChatPhotoInfo?,

    /** Identifier of the accent color for message sender name, and backgrounds of chat photo, reply header, and link preview. */
    val accentColorId: Int,

    /** Identifier of a custom emoji to be shown on the reply header and link preview background for messages sent by the chat; 0 if none. */
    val backgroundCustomEmojiId: Long,

    /** Color scheme based on an upgraded gift; may be null if none. */
    val upgradedGiftColors: UpgradedGiftColors?,

    /** Identifier of the profile accent color for the chat's profile; -1 if none. */
    val profileAccentColorId: Int,

    /** Identifier of a custom emoji to be shown on the background of the chat's profile; 0 if none. */
    val profileBackgroundCustomEmojiId: Long,

    /** Actions that non-administrator chat members are allowed to take in the chat. */
    val permissions: ChatPermissions,

    /** Last message in the chat; may be null if none or unknown. */
    val lastMessage: Message?,

    /** Positions of the chat in chat lists. */
    val positions: Array<ChatPosition>,

    /** Chat lists to which the chat belongs. */
    val chatListTypes: Array<ChatListType>,

    /** Identifier of a user or chat that is selected to send messages in the chat; may be null. */
    val messageSenderId: Message.MessageSender?,

    /** Block list to which the chat is added; may be null if none. */
//    val blockList: BlockList?,

    /** True, if chat content can't be saved locally, forwarded, or copied. */
    val hasProtectedContent: Boolean,

    /** True, if translation of all messages in the chat must be suggested to the user. */
    val isTranslatable: Boolean,

    /** True, if the chat is marked as unread. */
    val isMarkedAsUnread: Boolean,

    /** True, if the chat must be shown in the "View as topics" mode. */
    val viewAsTopics: Boolean,

    /** True, if the chat has scheduled messages. */
    val hasScheduledMessages: Boolean,

    /** True, if messages can be deleted only for the current user. */
    val canBeDeletedOnlyForSelf: Boolean,

    /** True, if messages can be deleted for all users. */
    val canBeDeletedForAllUsers: Boolean,

    /** True, if the chat can be reported. */
    val canBeReported: Boolean,

    /** Default value of the disableNotification parameter. */
    val defaultDisableNotification: Boolean,

    /** Number of unread messages in the chat. */
    val unreadCount: Int,

    /** Identifier of the last read incoming message. */
    val lastReadInboxMessageId: Long,

    /** Identifier of the last read outgoing message. */
    val lastReadOutboxMessageId: Long,

    /** Number of unread messages with a mention/reply. */
    val unreadMentionCount: Int,

    /** Number of messages with unread reactions. */
    val unreadReactionCount: Int,

    /** Notification settings for the chat. */
    val notificationSettings: ChatNotificationSettings,

    /** Types of reaction available in the chat. */
    val availableReactions: ChatAvailableReactions,

    /** Message auto-delete or self-destruct timer (seconds). */
    val messageAutoDeleteTime: Int,

    /** Emoji status; may be null. */
    val emojiStatus: EmojiStatus?,

    /** Background set for the chat; may be null. */
    val background: ChatBackground?,

    /** Theme set for the chat; may be null. */
    val theme: ChatTheme?,

    /** Chat action bar; may be null. */
    val actionBar: ChatActionBar?,

    /** Business bot manage bar; may be null. */
    val businessBotManageBar: BusinessBotManageBar?,

    /** Information about video chat of the chat. */
    val videoChat: VideoChat,

    /** Information about pending join requests; may be null. */
    val pendingJoinRequests: ChatJoinRequestsInfo?,

    /** Identifier of the message with default reply markup. */
    val replyMarkupMessageId: Long,

    /** Draft message; may be null. */
//    val draftMessage: DraftMessage?,

    /** Application-specific client data. */
    val clientData: String
)
