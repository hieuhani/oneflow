package vn.periscope.adapters.persistence.productattribute

import vn.periscope.ports.product.out.CreateProductAttributeEntryPoint
import vn.periscope.ports.product.out.DeleteProductAttributeEntryPoint
import vn.periscope.ports.product.out.GetProductAttributeEntryPoint
import vn.periscope.ports.product.out.UpdateProductAttributeEntryPoint
import vn.periscope.ports.product.models.ProductAttributeEntry
import kotlin.streams.toList

class ProductAttributePersistenceAdapter(
    private val productAttributeRepository: ProductAttributeRepository,
) : GetProductAttributeEntryPoint,
    CreateProductAttributeEntryPoint,
    UpdateProductAttributeEntryPoint,
    DeleteProductAttributeEntryPoint {

    override fun findById(id: Long): ProductAttributeEntry {
        return productAttributeRepository.get(id).toEntry()
    }

    override fun findByProductId(id: Long): List<ProductAttributeEntry> {
        return ProductAttributeRepository.getByProductId(id).stream().map { it.toEntry() }.toList()
    }

    override fun create(productAttributeEntry: ProductAttributeEntry): ProductAttributeEntry {
        return productAttributeRepository.create(productAttributeEntry).toEntry()
    }

    override fun update(id: Long, productAttributeEntry: ProductAttributeEntry): ProductAttributeEntry {
        return productAttributeRepository.update(id, productAttributeEntry).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return productAttributeRepository.delete(id)
    }
}