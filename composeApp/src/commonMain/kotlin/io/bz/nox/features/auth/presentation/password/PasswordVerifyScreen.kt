package io.bz.nox.features.auth.presentation.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import io.bz.nox.theme.NoxTheme
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun PasswordVerifyScreenStarter(
    modifier: Modifier,
) {
    val viewModel: PasswordVerifyViewModel = koinViewModel<PasswordVerifyViewModel>()

    val state by viewModel.uiState.collectAsState()

    PasswordVerifyScreen(
        modifier = modifier,
        state = state,
        onValueChanged = viewModel::onPasswordChanged,
        onNextClicked = {
            viewModel.onNextClicked()
        },
    )

}


@Composable
private fun PasswordVerifyScreen(
    modifier: Modifier,
    state: PasswordVerifyUIState,
    onValueChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
) {

    Column(modifier = modifier) {

        Text(text = "Password")

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Button(
            modifier = Modifier.fillMaxWidth(), onClick = onNextClicked, content = {
                Text(text = "Продолжить")
            })

    }
}

@Preview
@Composable
private fun PasswordVerifyScreenPreview() {
    NoxTheme {
        PasswordVerifyScreen(
            modifier = Modifier.fillMaxSize(),
            state = PasswordVerifyUIState("dew"),
            onValueChanged = { },
            onNextClicked = { },
        )
    }
}