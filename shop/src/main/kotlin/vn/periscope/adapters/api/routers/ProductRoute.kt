package vn.periscope.adapters.api.routers

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import vn.periscope.ports.GetProductUseCase

class ProductRoute(application: Application) {

    private val getProductUseCase by inject<GetProductUseCase>
}