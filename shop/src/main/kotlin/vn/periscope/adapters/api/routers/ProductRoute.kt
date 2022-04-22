package vn.periscope.adapters.api.routers

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.adapters.api.routers.vm.CreateProductRequest
import vn.periscope.extentions.inject
import vn.periscope.ports.product.*

class ProductRoute(application: Application) {
    private val getProductUseCase by inject<GetProductUseCase>()
    private val createProductUseCase by inject<CreateProductUseCase>()
    private val updateContentUseCase by inject<UpdateProductUseCase>()
    private val deleteContentUseCase by inject<DeleteProductUseCase>()
    private val filterProductUseCase by inject<FilterProductUseCase>()
    private val searchProductUseCase by inject<SearchProductUseCase>()

    init {
        application.routing {
            route("/api/v1") {
                get("/products/{id}") {
                    val id = call.parameters["id"]?.toLong() ?: throw RuntimeException("Id")
                    val contents = getProductUseCase.findById(id)
                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
                }

                post("/products") {
                    val requestBody: CreateProductRequest = call.receive()
                    val requestHeader = call.
                    val content = createProductUseCase.create(request.toDomainModel(principal!!.userId))
                    call.respond(ContentResponseDto.fromDomainModel(content))
                }

                put("/products/{id}") {
                    val id = call.longParameter("id")
                    val request: CreateContentRequestDto = call.receive()
                    val content = updateContentUseCase.updateContent(id, request.toDomainModel(null))
                    call.respond(ContentResponseDto.fromDomainModel(content))
                }

                delete("/products/{id}") {
                    val id = call.longParameter("id")
                    val status = filterProductUseCase.deleteContent(id)
                    call.respond(status)
                }

                get("/products/filters") {
                    val id = call.parameters["id"]?.toLong() ?: throw RuntimeException("Id")
                    val contents = getProductUseCase.findById(id)
                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
                }

                get("/products/searches") {
                    val id = call.parameters["id"]?.toLong() ?: throw RuntimeException("Id")
                    val contents = getProductUseCase.findById(id)
                    call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
                }
            }
        }
    }
}