package vn.periscope.cms.core.resource

import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.resource.CrudResourceUseCase
import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.resource.models.Paging
import vn.periscope.cms.ports.resource.output.CrudResourceEntryPort

class CrudResourceService<R, ID>(
    private val transactionService: TransactionService,
    private val crudResourceEntryPort: CrudResourceEntryPort<R, ID>,
) : CrudResourceUseCase<R, ID> {
    override suspend fun create(resource: R) = transactionService.transaction {
        crudResourceEntryPort.create(resource)
    }

    override suspend fun delete(id: ID) = transactionService.transaction {
        crudResourceEntryPort.delete(id)
    }

    override suspend fun update(id: ID, resource: R) = transactionService.transaction {
        crudResourceEntryPort.update(id, resource)
    }

    override suspend fun getById(id: ID) = transactionService.transaction {
        crudResourceEntryPort.getById(id)
    }

    override suspend fun getAllResources() = transactionService.transaction {
        crudResourceEntryPort.getAllResources()
    }

    override suspend fun filter(filter: FilterEntry?): Paging<R> = transactionService.transaction {
        crudResourceEntryPort.filter(filter ?: FilterEntry.DefaultFilterEntry)
    }
}