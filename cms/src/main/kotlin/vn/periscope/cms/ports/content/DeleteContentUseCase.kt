package vn.periscope.cms.ports.content

interface DeleteContentUseCase {
    suspend fun deleteContent(id: Long): Boolean
}