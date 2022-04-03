package vn.periscope.id.core.user

import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.user.GetUserEntryPort
import vn.periscope.id.ports.user.GetUserUseCase

class GetUserService(
    private val transactionService: TransactionService,
    private val getUserEntryPort: GetUserEntryPort,
) : GetUserUseCase {
    override suspend fun getUserById(userId: Long) = transactionService.transaction {
        getUserEntryPort.findById(userId)
    }
}