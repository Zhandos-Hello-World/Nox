package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent

class AuthRepositoryImpl(): AuthRepository {
    override suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPhone(phoneNumber: String): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendsSMSCode(code: String): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPassword(password: String): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmail(email: String): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendLogout(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendClose(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun recreate(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }
}