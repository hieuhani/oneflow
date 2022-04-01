package vn.periscope.id.core.auth

import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.GetUserPrincipalUseCase
import vn.periscope.id.ports.auth.models.GetUserPrincipalInput
import vn.periscope.id.ports.auth.models.UserPrincipal
import vn.periscope.id.ports.user.GetUserEntryPort

class GetUserPrincipalService(
    private val getUserEntryPort: GetUserEntryPort,
    private val transactionService: TransactionService,
) : GetUserPrincipalUseCase {
    override suspend fun getUserPrincipal(command: GetUserPrincipalInput) = transactionService.transaction {
        val user = getUserEntryPort.findById(command.userId)
        UserPrincipal(user.id!!)
    }
}