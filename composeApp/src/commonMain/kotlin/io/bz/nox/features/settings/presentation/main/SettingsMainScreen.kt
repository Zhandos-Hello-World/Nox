package io.bz.nox.features.settings.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.bz.domain.state.AuthState
import io.bz.nox.theme.NoxTheme
import io.bz.nox.features.CommonScreenFlows
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SettingsMainScreenStarter(
    modifier: Modifier,
    flow: (CommonScreenFlows) -> Unit
) {
    val viewModel: SettingsMainViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        AuthState.LoggingOut -> viewModel.close()
        AuthState.Closing -> Unit
        AuthState.Closed -> {
            flow.invoke(CommonScreenFlows.Auth)
        }
        else -> Unit
    }

    SettingsMainScreen(
        modifier = modifier,
        onClick = {
            viewModel.logout()
        },
    )
}


@Composable
private fun SettingsMainScreen(
    modifier: Modifier,
    onClick: () -> Unit,
) {

    Column(modifier = modifier.background(Color.Red)) {

        Text(text = "Settings")

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
            content = {
                Text("Logging out")
            },
        )

    }
}

@Preview
@Composable
private fun SettingsMainScreenPreview() {
    NoxTheme {
        SettingsMainScreen(
            modifier = Modifier.fillMaxSize(),
            onClick = { },
        )
    }
}