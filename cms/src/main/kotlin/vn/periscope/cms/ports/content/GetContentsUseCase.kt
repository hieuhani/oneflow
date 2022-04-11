package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface GetContentsUseCase {
    suspend fun getContents(): List<ContentEntry>
}