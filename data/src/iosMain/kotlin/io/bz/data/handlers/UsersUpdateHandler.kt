package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.data.lib.tdJson
import io.bz.domain.model.user.User
import io.bz.domain.model.user.UserStatus
import io.bz.domain.model.user.UserType
import io.bz.domain.stores.UserStore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@SerialName("updateUser")
data class UpdateUser(
    @SerialName("user") val user: User? = null,
)
val jsonFormatter = Json {
    classDiscriminator = "@type" // Глобальный дискриминатор
    ignoreUnknownKeys = true     // Чтобы не падать на новых полях
    encodeDefaults = true
}

class UsersUpdateHandler(
    private val userStore: UserStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        val json = wrapper.jsonResponse ?: return false
        try {
            if (json.contains("updateUser")) {

                val update = jsonFormatter.decodeFromString<UpdateUser>(json)
                update.user?.let { userStore.updateUser(it) }
                return true
            }
        } catch (e: Exception) {
            println("Mapping error: ${e.message}")
        }
        return false
    }

    suspend fun readyTestUser() {
        userStore.updateUser(
            user = User(
                id = 123456789L,
                firstName = "Zhandos",
                lastName = "Baimurat",
                usernames = null,
                phoneNumber = "+77001234567",
                status = UserStatus.Online(3243), // пример
                profilePhoto = null,
                accentColorId = 5,
                backgroundCustomEmojiId = 0L,
                upgradedGiftColors = null,
                profileAccentColorId = 7,
                profileBackgroundCustomEmojiId = 0L,
                emojiStatus = null,
                isContact = true,
                isMutualContact = true,
                isCloseFriend = false,
                verificationStatus = null,
                isPremium = true,
                isSupport = false,
                restrictionInfo = null,
                activeStoryState = null,
                restrictsNewChats = false,
                paidMessageStarCount = 0L,
                haveAccess = true,
                type = UserType.Regular, // пример
                languageCode = "ru",
                addedToAttachmentMenu = false
            )

        )
    }
}