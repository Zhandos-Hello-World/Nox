package io.bz.domain.model.chat

data class ChatNotificationSettings(
    val useDefaultMuteFor: Boolean,
    val muteFor: Int,
    val useDefaultSound: Boolean,
    val soundId: Long,
    val useDefaultShowPreview: Boolean,
    val showPreview: Boolean,
    val useDefaultMuteStories: Boolean,
    val muteStories: Boolean,
    val useDefaultStorySound: Boolean,
    val storySoundId: Long,
    val useDefaultShowStoryPoster: Boolean,
    val showStoryPoster: Boolean,
    val useDefaultDisablePinnedMessageNotifications: Boolean,
    val disablePinnedMessageNotifications: Boolean,
    val useDefaultDisableMentionNotifications: Boolean,
    val disableMentionNotifications: Boolean,
)