package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Sequence

object ProductIdSequence {
    val sequence = Sequence(
        name = "product_id_seq",
        startWith = 1,
        incrementBy = 1,
        minValue = 1,
        maxValue = 9223372036854775807,
        cycle = false,
        cache = 1
    )
}