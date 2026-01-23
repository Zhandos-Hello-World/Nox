package io.bz.nox.features.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.bz.domain.state.AuthState
import io.bz.nox.core.dataBaseDirectory
import io.bz.nox.core.log
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.features.auth.presentation.email.EmailScreenStarter
import io.bz.nox.features.auth.presentation.password.PasswordVerifyScreenStarter
import io.bz.nox.features.auth.presentation.phone.PhoneScreenStarter
import io.bz.nox.features.auth.presentation.sms.SmsScreenStarter
import org.koin.compose.viewmodel.koinViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

private data object SplashScreen
private data object RoutePhone
private data object RouteSms
private data object RouteEmail
private data object RoutePassword


@Composable
fun AuthFlow(
    modifier: Modifier = Modifier,
    isSplashScreen: (Boolean) -> Unit,
    flow: (CommonScreenFlows) -> Unit,
) {
    val viewModel: AuthFlowViewModel = koinViewModel<AuthFlowViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    val backStack = remember { mutableStateListOf<Any>(SplashScreen) }

    log("AuthFlow", state.toString())
    when (state) {
        AuthState.None -> Unit
        is AuthState.WaitTDLibParameters -> viewModel.sendTDLibParameters(dataBaseDirectory())
        AuthState.WaitPhoneNumber -> backStack.add(RoutePhone)
        is AuthState.WaitCode -> backStack.add(RouteSms)
        is AuthState.WaitPassword -> backStack.add(RoutePassword)
        is AuthState.WaitEmailAddress -> backStack.add(RouteEmail)
        is AuthState.WaitEmailCode -> Unit
        is AuthState.WaitOtherDeviceConfirmation -> Unit
        is AuthState.WaitRegistration -> Unit
        is AuthState.WaitPremiumPurchase -> Unit

        AuthState.Ready -> flow.invoke(CommonScreenFlows.Settings)

        AuthState.LoggingOut -> viewModel.close()
        AuthState.Closing -> Unit
        AuthState.Closed -> {
//            viewModel.logout()
            viewModel.recreateClient()
        }
    }

    isSplashScreen(state == AuthState.None)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is SplashScreen -> NavEntry(key) {

                }

                is RoutePhone -> NavEntry(key) {
                    PhoneScreenStarter(
                        modifier = modifier,
                    )
                }

                is RouteSms -> NavEntry(key) {
                    SmsScreenStarter(
                        modifier = modifier,
                    )
                }

                is RoutePassword -> NavEntry(key) {
                    PasswordVerifyScreenStarter(
                        modifier = modifier,
                    )
                }

                is RouteEmail -> NavEntry(key) {
                    EmailScreenStarter(
                        modifier = modifier,
                    )
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        },
    )
}
