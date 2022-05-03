package vn.periscope.cms.ports.contenttype.models

import vn.periscope.cms.ports.resource.models.FilterEntry

data class ContentTypeFilterEntry(
    override val limit: Int,
    override val offset: Int,
) : FilterEntry(limit = limit, offset = offset)