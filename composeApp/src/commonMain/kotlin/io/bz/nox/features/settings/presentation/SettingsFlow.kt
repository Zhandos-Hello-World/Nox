package io.bz.nox.features.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.features.settings.presentation.main.SettingsMainScreenStarter

@Composable
fun SettingsFlow(
    modifier: Modifier = Modifier,
    flow: (CommonScreenFlows) -> Unit,
) {
    SettingsMainScreenStarter(modifier, flow)
}