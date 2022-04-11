package vn.periscope.cms.core.content

import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.output.UpdateContentEntryPort
import vn.periscope.cms.ports.content.UpdateContentUseCase
import vn.periscope.cms.ports.content.models.ContentEntry

class UpdateContentService(
    private val transactionService: TransactionService,
    private val updateContentEntryPort: UpdateContentEntryPort,
): UpdateContentUseCase {
    override suspend fun updateContent(id: Long, content: ContentEntry) = transactionService.transaction {
        updateContentEntryPort.update(id, content)
    }
}