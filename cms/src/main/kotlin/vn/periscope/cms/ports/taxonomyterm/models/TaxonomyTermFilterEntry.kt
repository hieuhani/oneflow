package vn.periscope.cms.ports.taxonomyterm.models

import vn.periscope.cms.ports.resource.models.FilterEntry

data class TaxonomyTermFilterEntry(
    val taxonomyId: Long?,
    val parentId: Long?,
    val machineName: String?,
    override val limit: Int,
    override val offset: Int,
) : FilterEntry(limit = limit, offset = offset)