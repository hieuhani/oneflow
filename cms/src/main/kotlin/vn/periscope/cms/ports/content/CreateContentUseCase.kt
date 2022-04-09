package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface CreateContentUseCase {
    suspend fun createContent(content: ContentEntry): ContentEntry
}