package vn.periscope.id.adapters.persistence.user

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import vn.periscope.id.ports.user.models.UserEntry

class UserRepository {
    fun create(entry: UserEntry): UserEntity {
        val userId = UserTable.insert {
            entry.toCreateSqlStatement(it)
        }[UserTable.id]
        return with(entry) {
            UserEntity(id = userId.value, firstName, lastName, email, emailVerified, password)
        }
    }

    fun findByEmail(email: String): UserEntity? {
        return UserTable.select { UserTable.email eq email }
            .map { UserEntity.fromSqlResultRow(it) }
            .firstOrNull()
    }

    fun findById(id: Long): UserEntity {
        return UserEntity.fromSqlResultRow(UserTable.select { UserTable.id eq id }.single())
    }
}