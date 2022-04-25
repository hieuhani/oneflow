package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.Sequence
import org.jetbrains.exposed.sql.transactions.TransactionManager


object IdProviderRepository {
    internal fun getNextSeriesId(sequence: Sequence): Long {
        return TransactionManager.current().exec("SELECT nextval(${sequence.identifier})") { resultSet ->
            if (resultSet.next().not()) {
                throw Error("Missing nextValue in resultSet of sequence '${sequence.identifier}'")
            } else {
                return@exec resultSet.getLong(1)
            }

        } ?: throw Error("Unable to get nextValue of sequence '${sequence.identifier}'")
    }


    internal fun getNextSeriesIds(sequence: Sequence, quantity: Int): Set<Long> {
        return TransactionManager.current()
            .exec("SELECT nextval(${sequence.identifier}) from generate_series(1, ${quantity})") { resultSet ->
                if (resultSet.next().not()) {
                    throw Error("Missing nextValue in resultSet of sequence '${sequence.identifier}'")
                } else {
                    val result = setOf<Long>()
                    while (resultSet.next()) {
                        result.toMutableList().add(resultSet.getLong(1))
                    }
                    return@exec result
                }

            } ?: throw Error("Unable to get nextValue of sequence '${sequence.identifier}'")
    }

}



