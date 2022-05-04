package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateBrandRequest
import vn.periscope.adapters.api.rest.vm.request.CreateProductRequest
import vn.periscope.adapters.api.rest.vm.request.UpdateProductRequest
import vn.periscope.extentions.inject
import vn.periscope.ports.*
import vn.periscope.ports.models.BrandEntry
import kotlin.streams.toList

class BrandResource(application: Application) {
    private val getBrandUseCase by inject<GetBrandUseCase>()
    private val createBrandUseCase by inject<CreateBrandUseCase>()
    private val updateBrandUseCase by inject<UpdateBrandUseCase>()
    private val deleteBrandUseCase by inject<DeleteBrandUseCase>()
    private val findBrandUseCase by inject<FindBrandUseCase>()

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
                    val request: CreateBrandRequest = call.receive()
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val entry = BrandEntry(
                        name = request.name,
                        shortName = request.shortName,
                        logoId = request.logoId ?: 0,
                        countryId = request.countryId ?: 0

                    )
                    val brand = createBrandUseCase.create(businessId, entry)
                    call.respond(HttpStatusCode.Created, toProductResponse(product))
                }

                put("/products/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateProductRequest = call.receive()
                    val product = getProductUseCase.findById(businessId, id)
                    val productEntry = product.toEntry()
                    toProductEntryForUpdate(request, productEntry)
                    updateBrandUseCase.update(productEntry, product)
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

    private

}