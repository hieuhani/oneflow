package vn.periscope.cms.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.cms.core.content.CreateContentService
import vn.periscope.cms.core.content.DeleteContentService
import vn.periscope.cms.core.content.GetContentsService
import vn.periscope.cms.core.content.UpdateContentService
import vn.periscope.cms.core.resource.CrudResourceService
import vn.periscope.cms.ports.content.CreateContentUseCase
import vn.periscope.cms.ports.content.DeleteContentUseCase
import vn.periscope.cms.ports.content.GetContentsUseCase
import vn.periscope.cms.ports.content.UpdateContentUseCase
import vn.periscope.cms.ports.resource.CrudResourceUseCase
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

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

    single {
        DeleteContentService(
            transactionService = get(),
            deleteContentEntryPort = get(),
        )
    } binds arrayOf(
        DeleteContentUseCase::class,
    )

    single {
        CrudResourceService<TaxonomyEntry, Long>(
            transactionService = get(),
            crudResourceEntryPort = get(),
        )
    } binds arrayOf(
        CrudResourceUseCase::class,
    )
}