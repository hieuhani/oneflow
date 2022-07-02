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
import vn.periscope.ports.models.audit.CreatedAt
import vn.periscope.ports.models.audit.UpdatedAt
import vn.periscope.ports.models.id.LongId
import vn.periscope.ports.models.id.UUIDId
import kotlin.streams.toList

class ProductRoute(application: Application) {
    private val getProductUseCase by inject<GetProductUseCase>()
    private val createProductUseCase by inject<CreateProductUseCase>()
    private val updateContentUseCase by inject<UpdateProductUseCase>()
    private val deleteContentUseCase by inject<DeleteProductUseCase>()
    private val findProductUseCase by inject<FindProductUseCase>()

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
                    val product = ProductEntry(
                        id = LongId.default(),
                        type = request.type,
                        name = ProductEntry.Name.of(request.name),
                        photo = ProductEntry.Photo.of(request.photoId),
                        brand = ProductEntry.Brand.of(request.brandId),
                        industry = ProductEntry.Industry.of(request.industryId),
                        categories = ProductEntry.CategoryCollection.of(request.categoryIds),
                        price = ProductEntry.Price.of(request.price),
                        createdAt = CreatedAt.now(),
                        updatedAt = UpdatedAt.now(),
                    )
                    createProductUseCase.create(businessId, product)
                    call.respond(HttpStatusCode.Created, toProductResponse(product))
                }

                put("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateProductRequest = call.receive()
                    val product = updateContentUseCase.update(businessId, id, request.toEntry())
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
                    val products = findProductUseCase.filterAndSearch(businessId)
                    call.respond(HttpStatusCode.OK, products.stream().map { toProductResponse(it) }.toList())
                }
            }
        }
    }

    private fun toProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            type = product.type,
            name = product.name,
            photoId = product.photoId,
            brandId = product.brandId,
            industryId = product.industryId,
            categoryIds = product.categoryIds,
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
}