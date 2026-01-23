package io.bz.nox.features.auth.presentation.sms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SmsViewModel(
    private val authService: AuthService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SmsUIState(""))
    val uiState = _uiState.asStateFlow()

    fun onCodeChanged(value: String) {
        _uiState.update { it.copy(code = value) }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.SendCode(
                    code = _uiState.value.code,
                )
            )
        }
    }
}