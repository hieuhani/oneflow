package vn.periscope.cms.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.cms.core.content.GetContentsService
import vn.periscope.cms.ports.content.GetContentsUseCase

val coreModule = module(createdAtStart = true) {
    single {
        GetContentsService(
            transactionService = get(),
            getContentsEntryPort = get(),
        )
    } binds arrayOf(
        GetContentsUseCase::class,
    )
}