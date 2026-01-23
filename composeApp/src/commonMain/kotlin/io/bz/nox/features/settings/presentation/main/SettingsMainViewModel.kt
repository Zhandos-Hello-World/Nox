package io.bz.nox.features.settings.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.launch

class SettingsMainViewModel(private val authService: AuthService) : ViewModel() {
    val state = authService.state

    fun logout() {
        viewModelScope.launch {
            authService.sendIntent(AuthIntent.SendLogout)
        }
    }

    fun close() {
        viewModelScope.launch {
            authService.sendIntent(AuthIntent.Close)
        }
    }
}