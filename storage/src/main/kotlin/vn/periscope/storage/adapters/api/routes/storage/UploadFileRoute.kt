package vn.periscope.storage.adapters.api.routes.storage

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.ports.auth.models.UserPrincipal
import vn.periscope.storage.adapters.api.routes.storage.dto.FileResponseDto
import vn.periscope.storage.extentions.inject
import vn.periscope.storage.ports.file.models.FileEntry
import vn.periscope.storage.ports.storage.UploadFileUseCase
import java.nio.file.Path

class UploadFileRoute(application: Application) {
    private val uploadFileUseCase by inject<UploadFileUseCase>()
    private val fileFields = arrayOf("name", "description", "serviceName", "entityType", "entityId")

    init {
        application.routing {
            authenticate {
                route("/storage/upload") {
                    post {
                        val principal = call.principal<UserPrincipal>()
                        val multipartData = call.receiveMultipart()
                        var sourceFile: PartData.FileItem? = null
                        val formData = mutableMapOf<String, String>()
                        multipartData.forEachPart { part ->
                            when (part) {
                                is PartData.FormItem -> {
                                    if (part.name != null && fileFields.contains(part.name)) {
                                        formData[part.name!!] = part.value
                                    }
                                }
                                is PartData.FileItem -> {
                                    sourceFile = part
                                }
                                else -> {
                                    // TODO: unhandled part
                                    print("unhandled part")
                                }
                            }
                        }

                        if (sourceFile == null) {
                            throw Exception("File part data is required")
                        }
                        sourceFile!!.let {
                            val name = formData["name"] ?: it.name.orEmpty()
                            val originalFileName = it.originalFileName ?: "noname"
                            val fileName = "${System.currentTimeMillis()}_${originalFileName}"
                            val contentType = it.contentType
                            val description = formData["description"].orEmpty()

                            val serviceName = formData["serviceName"] ?: "storage"
                            val entityType = formData["entityType"] ?: "UnspecifiedEntity"
                            val entityId = formData["entityId"]?.toLongOrNull() ?: 0

                            val entry = uploadFileUseCase.uploadAndCreate(
                                file = it.streamProvider(), FileEntry(
                                    name = name,
                                    contentType = contentType.toString(),
                                    fileName = originalFileName,
                                    filePath = Path.of(serviceName, entityType, entityId.toString(), fileName)
                                        .toString(),
                                    ownerId = principal!!.userId,
                                    serviceName = serviceName,
                                    description = description,
                                    entityType = entityType,
                                    entityId = entityId
                                )
                            )
                            call.respond(FileResponseDto.fromDomainModel(entry))
                        }
                    }
                }
            }
        }
    }
}