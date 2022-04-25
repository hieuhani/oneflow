package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateProductRequest
import vn.periscope.adapters.api.rest.vm.request.GalleryRequest
import vn.periscope.adapters.api.rest.vm.request.ProductAttributeRequest
import vn.periscope.adapters.api.rest.vm.response.GalleryResponse
import vn.periscope.adapters.api.rest.vm.response.ProductAttributeResponse
import vn.periscope.adapters.api.rest.vm.response.ProductResponse
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.ProductAttribute
import vn.periscope.extentions.inject
import vn.periscope.ports.product.*
import vn.periscope.ports.product.models.GalleryEntry
import vn.periscope.ports.product.models.ProductAttributeEntry
import vn.periscope.ports.product.models.ProductEntry
import kotlin.streams.toList

class ProductResource(application: Application) {
    private val getProductUseCase by inject<GetProductUseCase>()
    private val createProductUseCase by inject<CreateProductUseCase>()
    private val updateContentUseCase by inject<UpdateProductUseCase>()
    private val deleteContentUseCase by inject<DeleteProductUseCase>()
    private val filterProductUseCase by inject<FilterProductUseCase>()
    private val searchProductUseCase by inject<SearchProductUseCase>()

    init {
        application.routing {
            route("/api/v1") {
//                get("/products/{id}") {
//                    val id = call.longParameter("id")
//                    val businessID = call.businessIdHeader()
//                    val product = getProductUseCase.findById(id)
//                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
//                }

                post("/products") {
                    val request: CreateProductRequest = call.receive()
                    val galleryEntries = buildGalleryEntry(request.galleries)
                    val attributeEntries = buildAttributeEntry(request.attributes)
                    val productEntry = ProductEntry.init {
                        businessId = call.businessIdHeader()
                        taxonomy = request.taxonomy
                        managementMethodology = request.managementMethodology
                        name = request.name
                        brandId = request.brandId
                        industryId = request.industryId
                        categoryIds = request.categoryIds
                        galleries = galleryEntries
                        attributes = attributeEntries

                    }
                    val product = createProductUseCase.create(productEntry)
                    call.respond(HttpStatusCode.Created, toProductResponse(product))
                }

//                put("/products/{id}") {
//                    val id = call.longParameter("id")
//                    val request: CreateContentRequestDto = call.receive()
//                    val content = updateContentUseCase.updateContent(id, request.toDomainModel(null))
//                    call.respond(ContentResponseDto.fromDomainModel(content))
//                }
//
//                delete("/products/{id}") {
//                    val id = call.longParameter("id")
//                    val status = filterProductUseCase.deleteContent(id)
//                    call.respond(status)
//                }
//
//                get("/products/filters") {
//                    val id = call.parameters["id"]?.toLong() ?: throw RuntimeException("Id")
//                    val contents = getProductUseCase.findById(id)
//                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
//                }
//
//                get("/products/searches") {
//                    val id = call.parameters["id"]?.toLong() ?: throw RuntimeException("Id")
//                    val contents = getProductUseCase.findById(id)
//                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
//                }
            }
        }
    }

    private fun buildGalleryEntry(requests: List<GalleryRequest>?): List<GalleryEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val iterator = requests.iterator()
        val entries = listOf<GalleryEntry>()
        iterator.forEach {
            val galleryEntry = GalleryEntry.init {
                storeId = it.storeId
                default = it.default
                position = it.position
            }
            entries.toMutableList().add(galleryEntry)
        }
        return entries
    }

    private fun buildAttributeEntry(requests: List<ProductAttributeRequest>?): List<ProductAttributeEntry> {
        if (requests.isNullOrEmpty()) return emptyList()
        val iterator = requests.iterator()
        val entries = listOf<ProductAttributeEntry>()
        iterator.forEach {
            val productAttributeEntry = ProductAttributeEntry.init {
                name = it.name
                values = it.values
            }
            entries.toMutableList().add(productAttributeEntry)
        }
        return entries
    }

    private fun toProductResponse(product: Product): ProductResponse {
        val galleryResponses = product.galleries.stream()
            .map { toGalleryResponse(it) }
            .toList()
        val attributeResponses = product.attributes.stream()
            .map { toAttributeResponse(it) }
            .toList()
        return ProductResponse.init {
            id = product.id
            taxonomy = product.taxonomy
            managementMethodology = product.managementMethodology
            name = product.name
            brandId = product.brandId
            industryId = product.industryId
            categoryIds = setOf()
            galleries = galleryResponses
            attributes = attributeResponses
            createdAt = product.createdAt
            updatedAt = product.updatedAt
        }
    }

    private fun toGalleryResponse(gallery: Gallery): GalleryResponse {
        return GalleryResponse.init {
            id = gallery.id
            storeId = gallery.storeId
            default = gallery.default
            position = gallery.position
            createdAt = gallery.createdAt
            updatedAt = gallery.updatedAt
        }
    }

    private fun toAttributeResponse(attribute: ProductAttribute): ProductAttributeResponse {
        return ProductAttributeResponse.init {
            id = attribute.id
            name = attribute.name
            values = attribute.values
            createdAt = attribute.createdAt
            updatedAt = attribute.updatedAt
        }
    }
}