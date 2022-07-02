package vn.periscope.adapters.api.rest

import io.ktor.server.application.*
import io.ktor.server.routing.*
import vn.periscope.extentions.inject
import vn.periscope.ports.*

class PricePolicyResource(application: Application) {
    private val getPricePolicyUseCase by inject<GetPricePolicyUseCase>()
    private val createPricePolicyUseCase by inject<CreatePricePolicyUseCase>()
    private val updatePricePolicyUseCase by inject<UpdatePricePolicyUseCase>()
    private val deleteIndustryUseCase by inject<DeletePricePolicyUseCase>()
    private val findPricePolicyUseCase by inject<FindPricePolicyUseCase>()


    init {
        application.routing {
            route("/api/v1") {
                get("/pricelist/{id}") {
                    //TODO something
                }

                post("/industries") {
                    //TODO something
                }

                put("/industries/{id}") {
                    //TODO something
                }

                delete("/industries/{id}") {
                    //TODO something
                }

                get("/industries") {
                    //TODO something
                }
            }
        }
    }
}