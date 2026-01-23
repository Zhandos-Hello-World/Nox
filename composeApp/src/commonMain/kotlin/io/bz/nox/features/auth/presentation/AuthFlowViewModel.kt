package io.bz.nox.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import io.bz.nox.core.log
import kotlinx.coroutines.launch

class AuthFlowViewModel(
    private val authService: AuthService,
) : ViewModel() {
    val state = authService.state

    fun sendTDLibParameters(
        dataBaseDirectory: String,
    ) {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.SendTDLibParameters(
                    databaseDirectory = dataBaseDirectory,
                ),
            )
        }
    }

    fun close() {
        viewModelScope.launch {
            authService.sendIntent(
                AuthIntent.Close,
            )
        }
    }

    fun recreateClient() {
        viewModelScope.launch {
            authService.sendIntent(AuthIntent.RecreateClient)
        }
    }

}