package vn.periscope.adapters.persistence

import vn.periscope.core.domain.Product
import vn.periscope.ports.out.UpdateProductEntryPort

class UpdateProductPersistence() :UpdateProductEntryPort{
    override fun update(id: Long, product: Product) {
        TODO("Not yet implemented")
    }
}