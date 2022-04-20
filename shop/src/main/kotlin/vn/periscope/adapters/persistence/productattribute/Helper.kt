package vn.periscope.adapters.persistence.productattribute

class Helper {
    companion object
}

internal const val SEPARATOR = ","

fun splitToSet(values: String): Set<String> {
    if (values.isBlank()) {
        return emptySet()
    }
    return values.split(SEPARATOR).toSet()
}