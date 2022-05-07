package vn.periscope.adapters.api.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.rest.vm.request.CreateIndustryRequest
import vn.periscope.adapters.api.rest.vm.request.UpdateIndustryRequest
import vn.periscope.adapters.api.rest.vm.response.IndustryResponse
import vn.periscope.extentions.inject
import vn.periscope.ports.*
import vn.periscope.ports.models.IndustryEntry
import kotlin.streams.toList

class IndustryResource(application: Application) {
    private val getIndustryUseCase by inject<GetIndustryUseCase>()
    private val createIndustryUseCase by inject<CreateIndustryUseCase>()
    private val updateIndustryUseCase by inject<UpdateIndustryUseCase>()
    private val deleteIndustryUseCase by inject<DeleteIndustryUseCase>()
    private val findIndustryUseCase by inject<FindIndustryUseCase>()


    init {
        application.routing {
            route("/api/v1") {
                get("/industries/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val industry = getIndustryUseCase.findById(businessId, id)
                    call.respond(HttpStatusCode.OK, IndustryResponse.fromIndustry(industry))
                }

                post("/industries") {
                    val request: CreateIndustryRequest = call.receive()
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val entry = IndustryEntry(
                        name = request.name,
                        machineName = request.machineName
                    )
                    val industry = createIndustryUseCase.create(businessId, entry)
                    call.respond(HttpStatusCode.Created, IndustryResponse.fromIndustry(industry))
                }

                put("/industries/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val request: UpdateIndustryRequest = call.receive()
                    val entry = IndustryEntry(
                        name = request.name,
                        machineName = request.machineName
                    )
                    val industry = updateIndustryUseCase.update(businessId, id, entry)
                    call.respond(HttpStatusCode.OK, IndustryResponse.fromIndustry(industry))
                }

                delete("/industries/{id}") {
                    val id = call.longParameter("id")
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    deleteIndustryUseCase.delete(businessId, id)
                    call.respond(HttpStatusCode.OK)
                }

                get("/industries") {
                    val businessId = call.longHeader(BUSINESS_ID_HEADER)
                    val industries = findIndustryUseCase.find(businessId)
                    call.respond(
                        HttpStatusCode.OK,
                        industries.stream().map { IndustryResponse.fromIndustry(it) }.toList()
                    )
                }
            }
        }
    }

}