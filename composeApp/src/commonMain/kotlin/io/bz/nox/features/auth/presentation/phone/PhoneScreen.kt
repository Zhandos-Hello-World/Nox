package io.bz.nox.features.auth.presentation.phone

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
fun PhoneScreenStarter(
    modifier: Modifier,
) {
    val viewModel: PhoneViewModel = koinViewModel<PhoneViewModel>()

    val state by viewModel.uiState.collectAsState()

    PhoneScreen(
        modifier = modifier,
        state = state,
        onValueChanged = viewModel::phoneNumberChanged,
        onNextClicked = {
            viewModel.onNextClicked()
        },
    )

}


@Composable
private fun PhoneScreen(
    modifier: Modifier,
    state: PhoneUIState,
    onValueChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
) {

    Column(modifier = modifier) {

        Text(text = "Авторизация")


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phoneNumber,
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
        PhoneScreen(
            modifier = Modifier.fillMaxSize(),
            state = PhoneUIState("", ""),
            onValueChanged = { },
            onNextClicked = { },
        )
    }
}