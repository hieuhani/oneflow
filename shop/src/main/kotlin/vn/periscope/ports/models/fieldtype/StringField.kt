package vn.periscope.ports.models.fieldtype

open class StringField(
    val value: String,
) : Comparable<StringField> {

    override fun compareTo(other: StringField): Int {
        return this.value.compareTo(other.value)
    }

    fun lowercase(): StringField {
        return StringField(value.lowercase())
    }

    fun uppercase(): StringField {
        return StringField(value.uppercase())
    }

    fun equalWith(other: String): Boolean {
        return this.value == other
    }
}
