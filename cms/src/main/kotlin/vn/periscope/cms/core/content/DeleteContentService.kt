package vn.periscope.cms.core.content

import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.DeleteContentUseCase
import vn.periscope.cms.ports.content.output.DeleteContentEntryPort

class DeleteContentService(
    private val transactionService: TransactionService,
    private val deleteContentEntryPort: DeleteContentEntryPort,
): DeleteContentUseCase {
    override suspend fun deleteContent(id: Long) = transactionService.transaction {
        deleteContentEntryPort.delete(id)
    }
}