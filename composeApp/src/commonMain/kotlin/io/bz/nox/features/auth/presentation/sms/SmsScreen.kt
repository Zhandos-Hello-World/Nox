package io.bz.nox.features.auth.presentation.sms

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
import androidx.compose.ui.tooling.preview.Preview
import io.bz.nox.theme.NoxTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SmsScreenStarter(
    modifier: Modifier,
) {
    val viewModel: SmsViewModel = koinViewModel<SmsViewModel>()

    val state by viewModel.uiState.collectAsState()

    SmsScreen(
        modifier = modifier,
        state = state,
        onValueChanged = viewModel::onCodeChanged,
        onNextClicked = {
            viewModel.onNextClicked()
        },
    )

}


@Composable
private fun SmsScreen(
    modifier: Modifier,
    state: SmsUIState,
    onValueChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
) {

    Column(modifier = modifier) {

        Text(text = "Напишите sms код")


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.code,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextClicked,
            content = {
                Text(text = "Продолжить")
            },
        )

    }
}

@Preview
@Composable
private fun SmsScreenPreview() {
    NoxTheme {
        SmsScreen(
            modifier = Modifier.fillMaxSize(),
            state = SmsUIState("32"),
            onValueChanged = { },
            onNextClicked = { },
        )
    }
}