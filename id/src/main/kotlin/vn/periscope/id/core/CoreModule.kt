package vn.periscope.id.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.id.core.auth.*
import vn.periscope.id.ports.auth.*

val coreModule = module(createdAtStart = true) {
    single {
        SignInUserService(
            getUserEntryPort = get(),
            passwordService = get(),
            generateUserTokenUseCase = get(),
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
            transactionService = get(),
        )
    } binds arrayOf(
        SignUpUserUseCase::class
    )
}