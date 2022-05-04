package vn.periscope.core.domain

import kotlinx.datetime.Instant
import vn.periscope.ports.models.BrandEntry
import  kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.BrandEntity

class Brand(
    id: Long = 0,
    private var name: String,
    private var shortName: String,
    private var logoId: Long = 0,
    private var countryId: Long = 0,
    createdAt: Instant,
    updatedAt: Instant,
    state: DomainState = DomainState.NONE,
) : BaseDomain<Long>(
    id,
    state,
    createdAt,
    updatedAt
) {

    companion object {
        fun create(entry: BrandEntry): Brand {
            return Brand(
                name = entry.name,
                shortName = entry.shortName,
                logoId = entry.logoId,
                countryId = entry.countryId,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now(),
                state = DomainState.CREATE
            )
        }

        fun reconstruct(entity: BrandEntity): Brand {
            return Brand(
                id = entity.id,
                name = entity.name,
                shortName = entity.shortName,
                logoId = entity.logoId,
                countryId = entity.countryId,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    fun changeName(name: String) {
        if (this.name.equals(name, false)) return
        this.name = name
        modify()
    }

    fun changeShortName(shortName: String) {
        if (this.shortName.equals(shortName, false)) return
        this.shortName = shortName
        modify()
    }

    fun changeLogoId(logoId: Long) {
        if (this.logoId == logoId) return
        this.logoId = logoId
        modify()
    }

    fun changeCountryId(countryId: Long) {
        if (this.countryId == countryId) return
        this.countryId = countryId
        modify()
    }

    private fun modify() {
        this.updatedAt = Clock.System.now()
        this.state = DomainState.UPDATE
    }

    fun getName(): String {
        return name;
    }

}