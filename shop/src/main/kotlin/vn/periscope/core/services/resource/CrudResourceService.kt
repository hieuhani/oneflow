package vn.periscope.core.services.resource

import vn.periscope.ports.TransactionService
import vn.periscope.ports.resource.CrudResourceUseCase
import vn.periscope.ports.resource.output.CrudResourceEntryPort

class CrudResourceService<R, ID>(
    private val transactionService: TransactionService,
    private val crudResourceEntryPort: CrudResourceEntryPort<R, ID>,
): CrudResourceUseCase<R, ID> {
    override suspend fun create(resource: R) = transactionService.transaction {
        crudResourceEntryPort.create(resource)
    }

    override suspend fun delete(id: ID) = transactionService.transaction {
        crudResourceEntryPort.delete(id)
    }

    override suspend fun update(id: ID, resource: R) = transactionService.transaction {
        crudResourceEntryPort.update(id, resource)
    }

    override suspend fun findById(id: ID) = transactionService.transaction {
        crudResourceEntryPort.findById(id)
    }

    override suspend fun findAll(): List<R> {
        TODO("Not yet implemented")
    }

}