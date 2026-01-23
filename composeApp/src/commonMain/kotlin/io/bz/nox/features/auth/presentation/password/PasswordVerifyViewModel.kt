package io.bz.nox.features.auth.presentation.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PasswordVerifyViewModel(
    private val authService: AuthService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PasswordVerifyUIState(""))
    val uiState = _uiState.asStateFlow()

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.SendPassword(
                    password = _uiState.value.password,
                )
            )
        }
    }
}