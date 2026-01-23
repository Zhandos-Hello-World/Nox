package io.bz.nox.features.users.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.features.users.presentation.main.UsersMainScreenStarter

@Composable
fun UsersFlow(
    modifier: Modifier = Modifier,
    flow: (CommonScreenFlows) -> Unit,
) {
    UsersMainScreenStarter(modifier, flow)
}