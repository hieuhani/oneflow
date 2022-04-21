package vn.periscope.id.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.id.core.auth.*
import vn.periscope.id.core.session.NewSessionService
import vn.periscope.id.core.user.GetUserService
import vn.periscope.id.ports.auth.*
import vn.periscope.id.ports.user.GetUserUseCase

val coreModule = module(createdAtStart = true) {
    single {
        NewSessionService(
            createSessionEntryPort = get(),
            passwordService = get(),
            transactionService = get(),
        )
    } binds arrayOf(
        NewSessionUseCase::class,
    )

    single {
        SignInUserService(
            getUserEntryPort = get(),
            passwordService = get(),
            generateUserTokenUseCase = get(),
            newSessionUseCase = get(),
            transactionService = get(),
        )
    } binds arrayOf(
        SignInUserUseCase::class,
    )

    single {
        Argon2PasswordService()
    } binds arrayOf(
        PasswordService::class,
    )

    single {
        GenerateUserTokenService(
            jwtService = get()
        )
    } binds arrayOf(
        GenerateUserTokenUseCase::class,
    )

    single {
        SignUpUserService(
            createUserEntryPort = get(),
            getUserEntryPort = get(),
            passwordService = get(),
            generateUserTokenUseCase = get(),
            newSessionUseCase = get(),
            transactionService = get(),
        )
    } binds arrayOf(
        SignUpUserUseCase::class,
    )

    single {
        GetUserService(
            transactionService = get(),
            getUserEntryPort = get(),
        )
    } binds arrayOf(
        GetUserUseCase::class,
    )
}