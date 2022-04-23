package vn.periscope.id.core.session

import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.session.GetSessionEntryPort
import vn.periscope.id.ports.session.GetSessionUseCase
import java.util.*

class GetSessionService(
    private val getSessionEntryPort: GetSessionEntryPort,
    private val transactionService: TransactionService,
) : GetSessionUseCase {
    override suspend fun getSessionById(sessionId: String) = transactionService.transaction {
        getSessionEntryPort.findById(UUID.fromString(sessionId))
    }
}