package io.bz.nox.features.auth.presentation.phone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhoneViewModel(
    private val authService: AuthService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PhoneUIState("", "kz"))
    val uiState = _uiState.asStateFlow()


    fun phoneNumberChanged(value: String) {
        _uiState.update { it.copy(phoneNumber = value) }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.SendPhone(_uiState.value.phoneNumber)
            )
        }
    }
}