package vn.periscope.cms.ports.contenttypefield.models

import vn.periscope.cms.ports.resource.models.FilterEntry

class ContentTypeFieldFilterEntry(
    val contentTypeId: Long?,
    override val limit: Int,
    override val offset: Int,
) : FilterEntry(limit = limit, offset = offset)