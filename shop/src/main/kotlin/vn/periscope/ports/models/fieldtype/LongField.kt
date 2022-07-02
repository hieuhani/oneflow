package vn.periscope.ports.models.fieldtype

open class LongField(
    val value: Long
) : Comparable<LongField> {

    override fun compareTo(other: LongField): Int {
        TODO("Not yet implemented")
    }
}