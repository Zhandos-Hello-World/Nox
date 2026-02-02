package io.bz.nox.features.users.presentation.main

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import io.bz.domain.model.File
import io.bz.domain.model.user.ProfilePhoto
import io.bz.domain.model.user.User
import io.bz.domain.model.user.UserStatus
import io.bz.domain.model.user.UserType
import io.bz.domain.model.user.Usernames
import io.bz.domain.state.UserState
import io.bz.nox.theme.NoxTheme
import io.bz.nox.features.CommonScreenFlows
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun UsersMainScreenStarter(
    modifier: Modifier,
    flow: (CommonScreenFlows) -> Unit,
) {
    val viewModel: UsersMainViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(
        Unit
    ) {
        viewModel.getUsers()
    }


    UsersMainDataScreen(
        modifier = modifier,
        state,
        loadImages = viewModel::loadAvatar,
    )
//    when (val uiState = state) {
//
//
//    }
}


@Composable
private fun UsersMainDataScreen(
    modifier: Modifier,
    users: Map<Long, User>,
    loadImages: (ProfilePhoto) -> Unit,
) {
    LaunchedEffect(users) {
        users.values.forEach { it.profilePhoto?.let(loadImages) }
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(users.values.toList()) {
            UserItem(user = it)
        }
    }
}

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        UserAvatar(
            profilePhoto = user.profilePhoto,
            size = 48.dp,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${user.firstName} ${user.lastName}".trim(),
                style = MaterialTheme.typography.titleMedium
            )

            user.usernames?.activeUsernames?.firstOrNull()?.let { username ->
                Text(
                    text = "@$username",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = when (user.status) {
                    is UserStatus.Online -> "online"
                    is UserStatus.Offline -> "offline"
                    is UserStatus.Recently -> "recently"
                    is UserStatus.LastWeek -> "last week"
                    is UserStatus.LastMonth -> "last month"
                    else -> "unknown"
                }, style = MaterialTheme.typography.bodySmall, color = Color.Gray
            )
        }
    }
}


@Composable
fun UserAvatar(
    profilePhoto: ProfilePhoto?,
    size: Dp = 48.dp,
) {

    Box(
        modifier = Modifier.size(size).clip(
            RoundedCornerShape(12.dp),
        ),
        contentAlignment = Alignment.Center,

    ) {
        if (profilePhoto == null) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(size / 2),
            )
        } else {
            val path = profilePhoto.small.local.path
            if (path.isEmpty()) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    modifier = Modifier.size(size / 2),
                    tint = Color.Red,
                )
            } else {
                CoilImage(
                    imageModel = { path },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    ),
                    modifier = Modifier.fillMaxSize(),
                )
            }

        }
    }
}


@Preview
@Composable
private fun UsersMainDataScreenPreview() {
    val user = User(
        id = 7986866164,
        firstName = "Seva",
        lastName = "",
        usernames = Usernames(
            activeUsernames = listOf("seva2025"),
            disabledUsernames = emptyList(),
            editableUsername = "seva2025",
            collectibleUsernames = emptyList()
        ),
        phoneNumber = "",
        status = UserStatus.Recently(false),
        profilePhoto = ProfilePhoto(
            id = 5231303968376877703,
            small = File(
                id = 5148,
                size = 0,
                expectedSize = 0,
                local = File.LocalFile(
                    path = "",
                    canBeDownloaded = true,
                    canBeDeleted = false,
                    isDownloadingActive = false,
                    isDownloadingCompleted = false,
                    downloadOffset = 0,
                    downloadedPrefixSize = 0,
                    downloadedSize = 0,
                ),
                remote = File.RemoteFile(
                    id = "AQADAgADh_IxGz5TmUgACAIAA_TnDdwBAAMOFMwX27WgnTgE",
                    uniqueId = "AQADh_IxGz5TmUgAAQ",
                    isUploadingActive = false,
                    isUploadingCompleted = true,
                    uploadedSize = 0,
                ),
            ),
            big = File(
                id = 5149, size = 0, expectedSize = 0, local = File.LocalFile(
                    path = "",
                    canBeDownloaded = true,
                    canBeDeleted = false,
                    isDownloadingActive = false,
                    isDownloadingCompleted = false,
                    downloadOffset = 0L,
                    downloadedPrefixSize = 0L,
                    downloadedSize = 0L
                ), remote = File.RemoteFile(
                    id = "AQADAgADh_IxGz5TmUgACAMAA_TnDdwBAAMOFMwX27WgnTgE",
                    uniqueId = "AQADh_IxGz5TmUgB",
                    isUploadingActive = false,
                    isUploadingCompleted = true,
                    uploadedSize = 0,
                )
            ),
            minithumbnail = null,
            hasAnimation = false,
            isPersonal = false,
        ),
        accentColorId = 4,
        backgroundCustomEmojiId = 0,
        upgradedGiftColors = null,
        profileAccentColorId = -1,
        profileBackgroundCustomEmojiId = 0,
        emojiStatus = null,
        isContact = false,
        isMutualContact = false,
        isCloseFriend = false,
        verificationStatus = null,
        isPremium = false,
        isSupport = false,
        restrictionInfo = null,
        activeStoryState = null,
        restrictsNewChats = false,
        paidMessageStarCount = 0,
        haveAccess = true,
        type = UserType.Regular,
        languageCode = "en",
        addedToAttachmentMenu = false,
    )
    val mapUser = Pair(user.id, user)
    NoxTheme {
        UsersMainDataScreen(
            modifier = Modifier.fillMaxSize(), users = mapOf(
                mapUser, mapUser, mapUser, mapUser, mapUser, mapUser
            ), loadImages = {})
    }
}