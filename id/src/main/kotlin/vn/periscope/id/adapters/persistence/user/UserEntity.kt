package vn.periscope.id.adapters.persistence.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.id.ports.user.models.UserEntry

data class UserEntity(
    val id: Long,
    val firstName: String,
    val lastName: String? = null,
    val email: String,
    val emailVerified: Boolean,
    val password: String,
) {
    companion object
}

internal fun UserEntity.toSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[UserTable.firstName] = firstName
    it[UserTable.lastName] = lastName
    it[UserTable.email] = email
    it[UserTable.emailVerified] = emailVerified
    it[UserTable.password] = password
}

internal fun UserEntry.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[UserTable.firstName] = firstName
    it[UserTable.lastName] = lastName
    it[UserTable.email] = email
    it[UserTable.emailVerified] = emailVerified
    it[UserTable.password] = password
}

internal fun UserEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = UserEntity(
    id = resultRow[UserTable.id].value,
    firstName = resultRow[UserTable.firstName],
    lastName = resultRow[UserTable.lastName],
    email = resultRow[UserTable.email],
    emailVerified = resultRow[UserTable.emailVerified],
    password = resultRow[UserTable.password],
)



internal fun UserEntity.toEntry() = UserEntry(
    id,
    firstName,
    lastName,
    email,
    emailVerified,
    password
)