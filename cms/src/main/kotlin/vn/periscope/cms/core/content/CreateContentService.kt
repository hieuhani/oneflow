package vn.periscope.cms.core.content

import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.CreateContentEntryPort
import vn.periscope.cms.ports.content.CreateContentUseCase
import vn.periscope.cms.ports.content.models.ContentEntry

class CreateContentService(
    private val transactionService: TransactionService,
    private val createContentEntryPort: CreateContentEntryPort,
): CreateContentUseCase {
    override suspend fun createContent(content: ContentEntry) = transactionService.transaction {
        createContentEntryPort.create(content)
    }
}