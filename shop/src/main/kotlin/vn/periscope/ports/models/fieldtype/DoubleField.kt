package vn.periscope.ports.models.fieldtype

open class DoubleField(
    val value: Double
) : Comparable<DoubleField> {
    override fun compareTo(other: DoubleField): Int {
        TODO("Not yet implemented")
    }
}