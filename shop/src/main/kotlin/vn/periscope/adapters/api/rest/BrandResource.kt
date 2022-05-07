package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateBrandRequest
import vn.periscope.adapters.api.rest.vm.request.UpdateBrandRequest
import vn.periscope.adapters.api.rest.vm.response.BrandResponse
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
                get("/brands/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val brand = getBrandUseCase.getById(businessId, id)
                    call.respond(HttpStatusCode.OK, BrandResponse.fromBrand(brand))
                }

                post("/brands") {
                    val request: CreateBrandRequest = call.receive()
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val entry = BrandEntry(
                        name = request.name,
                        logoId = request.logoId ?: 0,
                        countryId = request.countryId ?: 0
                    )
                    val brand = createBrandUseCase.create(businessId, entry)
                    call.respond(HttpStatusCode.Created, BrandResponse.fromBrand(brand))
                }

                put("/brands/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateBrandRequest = call.receive()
                    val entry = BrandEntry(
                        name = request.name,
                        logoId = request.logoId ?: 0,
                        countryId = request.countryId ?: 0
                    )
                    val brand = updateBrandUseCase.update(businessId, id, entry)
                    call.respond(HttpStatusCode.OK, BrandResponse.fromBrand(brand))
                }

                delete("/brands/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    deleteBrandUseCase.delete(id, businessId)
                    call.respond(HttpStatusCode.OK)
                }

                get("/brands") {
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val brands = findBrandUseCase.find(businessId)
                    val brandResponses = brands.stream().map { BrandResponse.fromBrand(it) }.toList()
                    call.respond(HttpStatusCode.OK, brandResponses)
                }
            }
        }
    }
}