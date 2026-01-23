package io.bz.nox.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.bz.nox.features.auth.presentation.AuthFlow
import io.bz.nox.features.chats.presentation.list.ChatsListScreenStarter
import io.bz.nox.features.main.presentation.MainScreen
import io.bz.nox.features.settings.presentation.SettingsFlow
import io.bz.nox.features.users.presentation.UsersFlow

sealed interface CommonScreenFlows {
    data object Auth : CommonScreenFlows
    data object Settings : CommonScreenFlows
    data object Users : CommonScreenFlows
    data object Chats : CommonScreenFlows
    data object Main : CommonScreenFlows
}


@Composable
fun CommonScreenFlowsContainer(
    modifier: Modifier = Modifier,
    isSplashScreen: (Boolean) -> Unit,
) {
    val backStack = remember { mutableStateListOf<Any>(CommonScreenFlows.Auth) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is CommonScreenFlows.Auth -> NavEntry(key) {
                    AuthFlow(
                        modifier = modifier,
                        isSplashScreen = isSplashScreen,
                        flow = {
                            backStack.add(CommonScreenFlows.Main)
                            backStack.removeAt(0)
                        },
                    )
                }

                is CommonScreenFlows.Settings -> NavEntry(key) {
                    SettingsFlow(
                        modifier = modifier,
                        flow = {
                            backStack.add(CommonScreenFlows.Auth)
                            backStack.removeAt(0)
                        },
                    )
                }

                is CommonScreenFlows.Users -> NavEntry(key) {
                    UsersFlow(
                        modifier = modifier,
                        flow = {
                            backStack.add(CommonScreenFlows.Auth)
                            backStack.removeAt(0)
                        },
                    )
                }

                is CommonScreenFlows.Chats -> NavEntry(key) {
                    ChatsListScreenStarter (
                        modifier = modifier,
                        flow = { },
                    )
                }
                is CommonScreenFlows.Main -> NavEntry(key) {
                    MainScreen(
                        modifier = modifier,
                        flow = { },
                    )
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        },
    )
}