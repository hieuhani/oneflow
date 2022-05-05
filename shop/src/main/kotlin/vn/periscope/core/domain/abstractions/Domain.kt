package vn.periscope.core.domain.abstractions

import vn.periscope.core.domain.DomainState

interface Domain<TID> {
    val id: TID
    val state: DomainState
}