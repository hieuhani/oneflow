package vn.periscope.adapters.persistence

import kotlinx.datetime.Instant
import vn.periscope.adapters.persistence.entity.BrandEntity
import vn.periscope.adapters.persistence.repository.BrandRepository
import vn.periscope.core.domain.Brand
import vn.periscope.ports.models.BrandEntry
import vn.periscope.ports.out.*
import kotlin.streams.toList

class BrandPersistence(
    private val brandRepository: BrandRepository
) : CreateBrandEntryPort, GetBrandEntryPort, UpdateBrandEntryPort, DeleteBrandEntryPort, FindBrandEntryPort {

    override fun create(businessId: Long, entry: BrandEntry): Brand {
        val entity = brandRepository.insert(businessId, entry)
        return toBrand(entity)
    }

    override fun getById(businessId: Long, id: Long): Brand {
        val entity =
            brandRepository.getById(businessId, id) ?: throw Error("Update brand failure, not found brand id=$id")
        return toBrand(entity)
    }

    override fun update(businessId: Long, id: Long, entry: BrandEntry): Brand {
        val entity = brandRepository.update(businessId, id, entry)
        return toBrand(entity)
    }

    override fun delete(businessId: Long, id: Long) {
        brandRepository.delete(businessId, id)
    }

    override fun find(businessId: Long): List<Brand> {
        val entities = brandRepository.find(businessId)
        return entities.stream().map { toBrand(it) }.toList()
    }

    private fun toBrand(entity: BrandEntity) = Brand(
        id = entity.id.value,
        name = entity.name,
        logoId = entity.logoId,
        countryId = entity.countryId,
        createdAt = Instant.fromEpochMilliseconds(entity.createdAt.toEpochMilli()),
        updatedAt = Instant.fromEpochMilliseconds(entity.updatedAt.toEpochMilli())
    )
}