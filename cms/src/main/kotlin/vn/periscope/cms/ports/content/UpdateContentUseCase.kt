package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface UpdateContentUseCase {
    suspend fun updateContent(id: Long, content: ContentEntry): ContentEntry
}