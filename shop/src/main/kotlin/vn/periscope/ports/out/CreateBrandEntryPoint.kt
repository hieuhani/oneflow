package vn.periscope.ports.out

import vn.periscope.core.domain.Brand

interface CreateBrandEntryPoint {
    fun create(brand: Brand): Brand
}