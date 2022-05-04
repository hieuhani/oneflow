package vn.periscope.core.domain

import kotlinx.datetime.Instant

open class BaseDomain<ID>(
    protected val id: ID,
    protected var state: DomainState,
    protected val createdAt: Instant,
    protected var updatedAt: Instant,
) {
    init {
        state = DomainState.NONE
    }

    
}