package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Product
import vn.periscope.ports.CreateAttributeUseCase
import vn.periscope.ports.CreateProductUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.CreateProductEntryPort
import vn.periscope.ports.out.GetProductEntryPort

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort,
    private val getProductEntryPort: GetProductEntryPort,
    private val createAttributeUseCase: CreateAttributeUseCase
) : CreateProductUseCase {

    override suspend fun create(businessId: Long, entry: ProductEntry): Product = transactionService.transaction {
        val product = initProduct(entry)
        createProductEntryPort.insert(businessId, product)
        return@transaction product
    }

    private fun initProduct(entry: ProductEntry): Product {
        val attributes = initAttribute(entry.attributes)
        val id = getProductEntryPort.getNextSeriesId()
        return Product(
            id = id,
            type = entry.type,
            name = entry.name,
            photoId = entry.photoId,
            brandId = entry.brandId,
            industryId = entry.industryId,
            categoryIds = entry.categoryIds,
            attributes = attributes,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
        )
    }

    private fun initAttribute(entries: List<AttributeEntry>?): List<Attribute> {
        if (entries.isNullOrEmpty()) return listOf()
        return createAttributeUseCase.create(entries)
    }
}