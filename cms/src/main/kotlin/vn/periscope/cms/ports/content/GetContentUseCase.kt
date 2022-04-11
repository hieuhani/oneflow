package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface GetContentUseCase {
    suspend fun getContentById(contentId: Long): ContentEntry
}