package vn.periscope.id.core.session

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.NewSessionUseCase
import vn.periscope.id.ports.auth.PasswordService
import vn.periscope.id.ports.session.CreateSessionEntryPort
import vn.periscope.id.ports.session.models.NewSessionPayload
import vn.periscope.id.ports.session.models.SessionEntry

class NewSessionService(
    private val createSessionEntryPort: CreateSessionEntryPort,
    private val passwordService: PasswordService,
    private val transactionService: TransactionService,
) : NewSessionUseCase {
    override suspend fun newSession(payload: NewSessionPayload) = transactionService.transaction {
        createSessionEntryPort.createSession(
            SessionEntry(
                userId = payload.userId,
                issuedAt = payload.issuedAt,
                expiresAt = payload.issuedAt.plus(DateTimePeriod(months = 1), TimeZone.currentSystemDefault()),
                refreshToken = passwordService.encrypt(payload.rawRefreshToken)
            )
        )
    }
}