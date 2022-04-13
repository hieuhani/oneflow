package vn.periscope.cms.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.cms.adapters.api.routes.ContentRoute
import vn.periscope.cms.adapters.api.routes.ContentTypeRoute
import vn.periscope.cms.adapters.api.routes.TaxonomyRoute
import vn.periscope.cms.adapters.api.routes.contenttypefield.ContentTypeFieldRoute
import vn.periscope.cms.adapters.api.routes.taxonomyterm.TaxonomyTermRoute
import vn.periscope.cms.adapters.configs.DatabaseConfig
import vn.periscope.cms.adapters.persistence.DatabaseConnector
import vn.periscope.cms.adapters.persistence.ExposedTransactionService
import vn.periscope.cms.adapters.persistence.content.ContentPersistenceAdapter
import vn.periscope.cms.adapters.persistence.content.ContentRepository
import vn.periscope.cms.adapters.persistence.contentfieldvalue.ContentFieldValueEntity
import vn.periscope.cms.adapters.persistence.contentfieldvalue.ContentFieldValueRepository
import vn.periscope.cms.adapters.persistence.contentfieldvalue.ContentFieldValueTable
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeEntity
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeRepository
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeTable
import vn.periscope.cms.adapters.persistence.contenttypefield.ContentTypeFieldEntity
import vn.periscope.cms.adapters.persistence.contenttypefield.ContentTypeFieldRepository
import vn.periscope.cms.adapters.persistence.contenttypefield.ContentTypeFieldTable
import vn.periscope.cms.adapters.persistence.resource.ResourcePersistenceAdapter
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.adapters.persistence.taxonomy.TaxonomyEntity
import vn.periscope.cms.adapters.persistence.taxonomy.TaxonomyRepository
import vn.periscope.cms.adapters.persistence.taxonomy.TaxonomyTable
import vn.periscope.cms.adapters.persistence.taxonomyterm.TaxonomyTermEntity
import vn.periscope.cms.adapters.persistence.taxonomyterm.TaxonomyTermRepository
import vn.periscope.cms.adapters.persistence.taxonomyterm.TaxonomyTermTable
import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.output.*
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.resource.output.CrudResourceEntryPort
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry
import vn.periscope.id.adapters.Auth0JWTService
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.JWTService
import javax.sql.DataSource

val adapterModule = module(createdAtStart = true) {
    single {
        ContentRoute(application = get())
    }

    single {
        TaxonomyRoute(application = get())
    }

    single {
        ContentTypeRoute(application = get())
    }

    single {
        TaxonomyTermRoute(application = get())
    }

    single {
        ContentTypeFieldRoute(application = get())
    }

    single {
        AppBootstrap(application = get())
    }

    single {
        DatabaseConfig(environment = get<Application>().environment)
    }

    single {
        JWTConfig(environment = get<Application>().environment)
    }

    single<JWTService> {
        val jwtConfig = get<JWTConfig>()
        Auth0JWTService(jwtConfig)
    }

    single<DataSource> {
        val hikari = get<DatabaseConfig>().hikari
        HikariDataSource(hikari)
    }

    single {
        DatabaseConnector(dataSource = get())
    }

    single<TransactionService> {
        ExposedTransactionService(dbConnector = get())
    }

    single {
        ContentRepository
    }

    single {
        ContentPersistenceAdapter(contentRepository = get())
    } binds arrayOf(
        GetContentsEntryPort::class,
        GetContentEntryPort::class,
        CreateContentEntryPort::class,
        UpdateContentEntryPort::class,
        DeleteContentEntryPort::class,
    )

    single<ResourceRepository<TaxonomyEntry, TaxonomyEntity, Long, TaxonomyTable>> {
        TaxonomyRepository
    }

    single {
        ResourcePersistenceAdapter<TaxonomyEntry, TaxonomyEntity, Long>(
            resourceRepository = get(),
        )
    } binds arrayOf(
        CrudResourceEntryPort::class,
    )

    // ContentType
    single<ResourceRepository<ContentTypeEntry, ContentTypeEntity, Long, ContentTypeTable>>(named("ContentTypeRepository")) {
        ContentTypeRepository
    }
    single(named("ContentTypePersistenceAdapter")) {
        ResourcePersistenceAdapter<ContentTypeEntry, ContentTypeEntity, Long>(
            resourceRepository = get(named("ContentTypeRepository")),
        )
    } binds arrayOf(
        CrudResourceEntryPort::class,
    )


    // TaxonomyTerm
    single<ResourceRepository<TaxonomyTermEntry, TaxonomyTermEntity, Long, TaxonomyTermTable>>(named("TaxonomyTermRepository")) {
        TaxonomyTermRepository
    }
    single(named("TaxonomyTermPersistenceAdapter")) {
        ResourcePersistenceAdapter<TaxonomyTermEntry, TaxonomyTermEntity, Long>(
            resourceRepository = get(named("TaxonomyTermRepository")),
        )
    } binds arrayOf(
        CrudResourceEntryPort::class,
    )

    // ContentTypeField
    single<ResourceRepository<ContentTypeFieldEntry, ContentTypeFieldEntity, Long, ContentTypeFieldTable>>(named("ContentTypeFieldRepository")) {
        ContentTypeFieldRepository
    }
    single(named("ContentTypeFieldPersistenceAdapter")) {
        ResourcePersistenceAdapter<ContentTypeFieldEntry, ContentTypeFieldEntity, Long>(
            resourceRepository = get(named("ContentTypeFieldRepository")),
        )
    } binds arrayOf(
        CrudResourceEntryPort::class,
    )

    // ContentFieldValue
    single<ResourceRepository<ContentFieldValueEntry, ContentFieldValueEntity, Long, ContentFieldValueTable>>(named("ContentFieldValueRepository")) {
        ContentFieldValueRepository
    }
    single(named("ContentFieldValuePersistenceAdapter")) {
        ResourcePersistenceAdapter<ContentFieldValueEntry, ContentFieldValueEntity, Long>(
            resourceRepository = get(named("ContentFieldValueRepository")),
        )
    } binds arrayOf(
        CrudResourceEntryPort::class,
    )
}