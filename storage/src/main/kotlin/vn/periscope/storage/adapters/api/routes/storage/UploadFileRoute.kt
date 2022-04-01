package vn.periscope.storage.adapters.api.routes.storage

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import vn.periscope.storage.extentions.inject
import vn.periscope.storage.ports.storage.UploadFileUseCase
import java.nio.file.Path

class UploadFileRoute(application: Application) {
    private val uploadFileUseCase by inject<UploadFileUseCase>()
    init {
        application.routing {
            route("/storage/upload") {
                post {
                    val multipartData = call.receiveMultipart()
                    multipartData.forEachPart { part ->
                        when (part) {
                            is PartData.FormItem -> {
                                when (part.name) {
                                    "name" -> {

                                    }
                                }
                            }
                            is PartData.FileItem -> {
                                part.streamProvider().use { inputStream ->
                                    uploadFileUseCase.upload(Path.of(""), inputStream)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}