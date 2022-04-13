package vn.periscope.cms.core

import org.koin.core.qualifier.named
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
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry
import vn.periscope.cms.ports.resource.CrudResourceUseCase
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

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

    // ContentType
    single(named("ContentTypeResourceService")) {
        CrudResourceService<ContentTypeEntry, Long>(
            transactionService = get(),
            crudResourceEntryPort = get(named("ContentTypePersistenceAdapter")),
        )
    } binds arrayOf(
        CrudResourceUseCase::class,
    )

    // TaxonomyTerm
    single(named("TaxonomyTermResourceService")) {
        CrudResourceService<TaxonomyTermEntry, Long>(
            transactionService = get(),
            crudResourceEntryPort = get(named("TaxonomyTermPersistenceAdapter")),
        )
    } binds arrayOf(
        CrudResourceUseCase::class,
    )

    // ContentTypeField
    single(named("ContentTypeFieldResourceService")) {
        CrudResourceService<TaxonomyTermEntry, Long>(
            transactionService = get(),
            crudResourceEntryPort = get(named("ContentTypeFieldPersistenceAdapter")),
        )
    } binds arrayOf(
        CrudResourceUseCase::class,
    )

    // ContentFieldValue
    single(named("ContentFieldValueResourceService")) {
        CrudResourceService<ContentFieldValueEntry, Long>(
            transactionService = get(),
            crudResourceEntryPort = get(named("ContentFieldValuePersistenceAdapter")),
        )
    } binds arrayOf(
        CrudResourceUseCase::class,
    )
}