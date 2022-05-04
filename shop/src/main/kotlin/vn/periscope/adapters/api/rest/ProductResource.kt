package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateProductRequest
import vn.periscope.adapters.api.rest.vm.request.UpdateProductRequest
import vn.periscope.adapters.api.rest.vm.response.ProductAttributeResponse
import vn.periscope.adapters.api.rest.vm.response.ProductResponse
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Product
import vn.periscope.extentions.inject
import vn.periscope.ports.*
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.ProductEntry
import kotlin.streams.toList

class ProductResource(application: Application) {
    private val getProductUseCase by inject<GetProductUseCase>()
    private val createProductUseCase by inject<CreateProductUseCase>()
    private val updateContentUseCase by inject<UpdateProductUseCase>()
    private val deleteContentUseCase by inject<DeleteProductUseCase>()
    private val filterAndSearchProductUseCase by inject<FilterAndSearchProductUseCase>()

    init {
        application.routing {
            route("/api/v1") {
                get("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val product = getProductUseCase.findById(businessId, id)
                    call.respond(HttpStatusCode.OK, toProductResponse(product))
                }

                post("/products") {
                    val request: CreateProductRequest = call.receive()
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val productEntry = toProductEntryForCreate(request)
                    val product = createProductUseCase.create(businessId, productEntry)
                    call.respond(HttpStatusCode.Created, toProductResponse(product))
                }

                put("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateProductRequest = call.receive()
                    val product = getProductUseCase.findById(businessId, id)
                    val productEntry = product.toEntry()
                    toProductEntryForUpdate(request, productEntry)
                    updateContentUseCase.update(productEntry, product)
                    call.respond(HttpStatusCode.OK, toProductResponse(product))
                }

                delete("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    deleteContentUseCase.delete(id, businessId)
                    call.respond(HttpStatusCode.OK)
                }

                get("/products") {
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val products = filterAndSearchProductUseCase.filterAndSearch(businessId)
                    call.respond(HttpStatusCode.OK, products.stream().map { toProductResponse(it) }.toList())
                }
            }
        }
    }

    private fun toProductEntryForCreate(request: CreateProductRequest): ProductEntry {
        val attributeEntries = toAttributeEntryForCreate(request.attributes)
        return ProductEntry(
            type = request.type,
            name = request.name,
            photoId = request.photoId ?: 0,
            brandId = request.brandId ?: 0,
            industryId = request.industryId ?: 0,
            categoryIds = request.categoryIds ?: setOf(),
            attributes = attributeEntries,
        )
    }

    private fun toAttributeEntryForCreate(requests: List<CreateProductRequest.CreateAttributeRequest>?): List<AttributeEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val entries = mutableListOf<AttributeEntry>()
        for (request in requests) {
            val entry = AttributeEntry(
                name = request.name,
                values = request.values
            )
            entries.add(entry)
        }
        return entries
    }

    private fun toProductResponse(product: Product): ProductResponse {
        val attributeResponses = product.attributes.stream().map { toAttributeResponse(it) }.toList()
        return ProductResponse(
            id = product.id,
            type = product.type,
            name = product.name,
            photoId = product.photoId,
            brandId = product.brandId,
            industryId = product.industryId,
            categoryIds = product.categoryIds,
            attributes = attributeResponses,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }


    private fun toAttributeResponse(attribute: Attribute) = ProductAttributeResponse(
        id = attribute.id,
        name = attribute.name,
        values = attribute.values,
        createdAt = attribute.createdAt,
        updatedAt = attribute.updatedAt,
    )


    private fun toProductEntryForUpdate(request: UpdateProductRequest, productEntry: ProductEntry) {
        productEntry.name = request.name
        productEntry.brandId = request.brandId ?: 0
        productEntry.brandId = request.industryId ?: 0
        productEntry.categoryIds = request.categoryIds ?: setOf()
        val attributeEntries = toAttributeEntryForUpdate(request.attributes)
        productEntry.attributes = attributeEntries
    }

    private fun toAttributeEntryForUpdate(requests: List<UpdateProductRequest.UpdateAttributeRequest>?): List<AttributeEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val entries = mutableListOf<AttributeEntry>()
        for (request in requests) {
            val entry = AttributeEntry(
                id = request.id,
                name = request.name,
                values = request.values
            )
            entries.add(entry)
        }
        return entries
    }
}