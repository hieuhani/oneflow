package vn.periscope.cms.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.cms.core.content.CreateContentService
import vn.periscope.cms.core.content.GetContentsService
import vn.periscope.cms.core.content.UpdateContentService
import vn.periscope.cms.ports.content.CreateContentUseCase
import vn.periscope.cms.ports.content.GetContentsUseCase
import vn.periscope.cms.ports.content.UpdateContentUseCase

val coreModule = module(createdAtStart = true) {
    single {
        GetContentsService(
            transactionService = get(),
            getContentsEntryPort = get(),
        )
    } binds arrayOf(
        GetContentsUseCase::class,
    )

    single {
        CreateContentService(
            transactionService = get(),
            createContentEntryPort = get(),
        )
    } binds arrayOf(
        CreateContentUseCase::class,
    )

    single {
        UpdateContentService(
            transactionService = get(),
            updateContentEntryPort = get(),
        )
    } binds arrayOf(
        UpdateContentUseCase::class,
    )
}