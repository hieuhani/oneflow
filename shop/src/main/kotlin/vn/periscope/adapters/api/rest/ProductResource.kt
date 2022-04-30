package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateProductRequest
import vn.periscope.adapters.api.rest.vm.request.UpdateProductRequest
import vn.periscope.adapters.api.rest.vm.response.GalleryResponse
import vn.periscope.adapters.api.rest.vm.response.ProductAttributeResponse
import vn.periscope.adapters.api.rest.vm.response.ProductResponse
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.Attribute
import vn.periscope.extentions.inject
import vn.periscope.ports.*
import vn.periscope.ports.models.GalleryEntry
import vn.periscope.ports.models.ProductAttributeEntry
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
                    val galleryEntries = buildGalleryEntry(request.galleries)
                    val attributeEntries = buildAttributeEntry(request.attributes)
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val productEntry = ProductEntry(
                        taxonomy = request.taxonomy,
                        type = request.type,
                        name = request.name,
                        brandId = request.brandId,
                        industryId = request.industryId,
                        categoryIds = request.categoryIds,
                        galleries = galleryEntries,
                        attributes = attributeEntries,
                    )
                    val product = createProductUseCase.create(productEntry)
                    call.respond(HttpStatusCode.Created, toProductResponse(product))
                }

                put("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateProductRequest = call.receive()
                    val product = getProductUseCase.findById(businessId, id)
                    val content = updateContentUseCase.update(id, product)
                    call.respond(ContentResponseDto.fromDomainModel(content))
                }

                delete("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    deleteContentUseCase.delete(id, businessId)
                    call.respond(HttpStatusCode.OK)
                }

                get("/products") {
                    val categoryIds = call.request.queryParameters[""]



                    val contents = getProductUseCase.fi(id)
                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
                }
            }
        }
    }

    private fun buildGalleryEntry(requests: List<CreateProductRequest.CreateGalleryRequest>?): List<GalleryEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val iterator = requests.iterator()
        val entries = listOf<GalleryEntry>()
        iterator.forEach {
            val galleryEntry = GalleryEntry(
                storeId = it.storeId,
                position = it.position,
            )
            entries.toMutableList().add(galleryEntry)
        }
        return entries
    }

    private fun buildAttributeEntry(requests: List<CreateProductRequest.CreateAttributeRequest>?): List<ProductAttributeEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val iterator = requests.iterator()
        val entries = listOf<ProductAttributeEntry>()
        iterator.forEach {
            val productAttributeEntry = ProductAttributeEntry(
                name = it.name, values = it.values
            )
            entries.toMutableList().add(productAttributeEntry)
        }
        return entries
    }

    private fun toProductResponse(product: Product): ProductResponse {
        val galleryResponses = product.galleries.orEmpty().stream().map { toGalleryResponse(it) }.toList()
        val attributeResponses = product.attributes.orEmpty().stream().map { toAttributeResponse(it) }.toList()
        return ProductResponse(
            id = product.id,
            taxonomy = product.taxonomy,
            type = product.type,
            name = product.name,
            brandId = product.brandId,
            industryId = product.industryId,
            categoryIds = product.categoryIds,
            galleries = galleryResponses,
            attributes = attributeResponses,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }

    private fun toGalleryResponse(gallery: Gallery) = GalleryResponse(
        id = gallery.id,
        storeId = gallery.storeId,
        position = gallery.position,
        createdAt = gallery.createdAt,
        updatedAt = gallery.updatedAt,
    )

    private fun toAttributeResponse(attribute: Attribute) = ProductAttributeResponse(
        id = attribute.id,
        name = attribute.name,
        values = attribute.values,
        createdAt = attribute.createdAt,
        updatedAt = attribute.updatedAt,
    )
}