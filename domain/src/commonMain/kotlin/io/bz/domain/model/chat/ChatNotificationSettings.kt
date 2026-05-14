package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatNotificationSettings(
    @SerialName("use_default_mute_for")
    val useDefaultMuteFor: Boolean,

    @SerialName("mute_for")
    val muteFor: Int,

    @SerialName("use_default_sound")
    val useDefaultSound: Boolean,

    @SerialName("sound_id")
    val soundId: Long,

    @SerialName("use_default_show_preview")
    val useDefaultShowPreview: Boolean,

    @SerialName("show_preview")
    val showPreview: Boolean,

    @SerialName("use_default_mute_stories")
    val useDefaultMuteStories: Boolean,

    @SerialName("mute_stories")
    val muteStories: Boolean,

    @SerialName("use_default_story_sound")
    val useDefaultStorySound: Boolean,

    @SerialName("story_sound_id")
    val storySoundId: Long,

    @SerialName("use_default_show_story_poster")
    val useDefaultShowStoryPoster: Boolean,

    @SerialName("show_story_poster")
    val showStoryPoster: Boolean,

    @SerialName("use_default_disable_pinned_message_notifications")
    val useDefaultDisablePinnedMessageNotifications: Boolean,

    @SerialName("disable_pinned_message_notifications")
    val disablePinnedMessageNotifications: Boolean,

    @SerialName("use_default_disable_mention_notifications")
    val useDefaultDisableMentionNotifications: Boolean,

    @SerialName("disable_mention_notifications")
    val disableMentionNotifications: Boolean
)