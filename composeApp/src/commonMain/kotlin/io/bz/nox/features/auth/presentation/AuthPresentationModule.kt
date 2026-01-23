package io.bz.nox.features.auth.presentation

import io.bz.nox.features.auth.presentation.email.EmailViewModel
import io.bz.nox.features.auth.presentation.password.PasswordVerifyViewModel
import io.bz.nox.features.auth.presentation.phone.PhoneViewModel
import io.bz.nox.features.auth.presentation.sms.SmsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authPresentationModule = module {

    viewModel {
        AuthFlowViewModel(
            authService = get(),
        )
    }

    viewModel {
        PhoneViewModel(
            authService = get()
        )
    }

    viewModel {
        SmsViewModel(
            authService = get()
        )
    }

    viewModel {
        PasswordVerifyViewModel(
            authService = get()
        )
    }

    viewModel {
        EmailViewModel(
            authService = get()
        )
    }
}