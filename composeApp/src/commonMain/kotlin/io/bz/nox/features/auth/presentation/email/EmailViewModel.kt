package io.bz.nox.features.auth.presentation.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailViewModel(
    private val authService: AuthService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailUIState(""))
    val uiState = _uiState.asStateFlow()


    fun onEmailValueChanged(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.SendEmail(
                    email = _uiState.value.email,
                )
            )
        }
    }
}