package vn.periscope.cms.ports.taxonomy.models

import vn.periscope.cms.ports.resource.models.FilterEntry

class TaxonomyFilterEntry(
    override val limit: Int,
    override val offset: Int,
) : FilterEntry(limit = limit, offset = offset)