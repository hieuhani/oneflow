package vn.periscope.ports.models.id

data class LongId(
    val value: Long
) {
    companion object {
        fun default() = LongId(0)

        fun of(value: Long) = LongId(value)
    }
}