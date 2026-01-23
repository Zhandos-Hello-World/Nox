package io.bz.nox.features.auth.presentation.email

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
fun EmailScreenStarter(
    modifier: Modifier,
) {
    val viewModel: EmailViewModel = koinViewModel<EmailViewModel>()

    val state by viewModel.uiState.collectAsState()

    EmailScreen(
        modifier = modifier,
        state = state,
        onValueChanged = viewModel::onEmailValueChanged,
        onNextClicked = {
            viewModel.onNextClicked()
        },
    )

}


@Composable
private fun EmailScreen(
    modifier: Modifier,
    state: EmailUIState,
    onValueChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
) {

    Column(modifier = modifier) {

        Text(text = "Email")


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
        )


        Button(
            modifier = Modifier.fillMaxWidth(), onClick = onNextClicked, content = {
                Text(text = "Продолжить")
            })
    }
}

@Preview
@Composable
private fun PhoneScreenPreview() {
    NoxTheme {
        EmailScreen(
            modifier = Modifier.fillMaxSize(),
            state = EmailUIState(""),
            onValueChanged = { },
            onNextClicked = { })
    }
}