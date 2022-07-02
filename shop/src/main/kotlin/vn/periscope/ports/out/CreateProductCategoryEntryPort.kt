package vn.periscope.ports.out

interface CreateProductCategoryEntryPort {
    fun create(productId: Long, categoryId: Long)
}