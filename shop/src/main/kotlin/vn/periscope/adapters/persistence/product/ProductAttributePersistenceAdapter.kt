package vn.periscope.adapters.persistence.product

import vn.periscope.ports.product.CreateProductAttributeEntryPoint
import vn.periscope.ports.product.DeleteProductAttributeEntryPoint
import vn.periscope.ports.product.GetProductAttributeEntryPoint
import vn.periscope.ports.product.UpdateProductAttributeEntryPoint
import vn.periscope.ports.product.models.Product
import vn.periscope.ports.product.models.ProductAttribute

class ProductAttributePersistenceAdapter(
    private val productAttributeRepository: ProductAttributeRepository,
) : GetProductAttributeEntryPoint,
    CreateProductAttributeEntryPoint,
    UpdateProductAttributeEntryPoint,
    DeleteProductAttributeEntryPoint {

    override fun findById(id: Long): Product {
        return productAttributeRepository.get(id).toEntry()
    }

    override fun findByProductId(id: Long): Product {
        return productAttributeRepository.getByProductId(id)
    }

    override fun create(productAttribute: ProductAttribute): ProductAttribute {
        return productAttributeRepository.create(productAttribute).toEntry()
    }

    override fun update(id: Long, productAttribute: ProductAttribute): ProductAttribute {
        return productAttributeRepository.update(id, productAttribute).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return productAttributeRepository.delete(id)
    }
}