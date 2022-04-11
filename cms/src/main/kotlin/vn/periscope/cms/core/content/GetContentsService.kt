package vn.periscope.cms.core.content

import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.output.GetContentsEntryPort
import vn.periscope.cms.ports.content.GetContentsUseCase
import vn.periscope.cms.ports.content.models.ContentEntry

class GetContentsService(
    private val transactionService: TransactionService,
    private val getContentsEntryPort: GetContentsEntryPort,
): GetContentsUseCase {
    override suspend fun getContents(): List<ContentEntry> = transactionService.transaction {
        getContentsEntryPort.findAll()
    }
}