package io.bz.nox.features.auth.presentation.sms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SmsViewModel(
    private val authService: AuthService,
) : ViewModel() {
    private val _timerSeconds = MutableStateFlow(120)
    val timerSeconds = _timerSeconds.asStateFlow()

    // Показывать ли кнопку "Отправить SMS"
    private val _canResendCode = MutableStateFlow(false)
    val canResendCode = _canResendCode.asStateFlow()

    private var timerJob: Job? = null

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

    fun onWaitCodeReceived(timeoutSeconds: Int) {
        if (timeoutSeconds <= 0) {
            _canResendCode.value = true
            return
        }

        _canResendCode.value = false
        _timerSeconds.value = timeoutSeconds

        // Запускаем таймер
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timerSeconds.value > 0) {
                delay(1000)
                _timerSeconds.value -= 1
            }
            _canResendCode.value = true // Время вышло, показываем кнопку
        }
    }

    fun resendCodeViaSms() {
        viewModelScope.launch {
            authService.sendIntent(AuthIntent.ResendCode)
        }
    }
}