package vn.periscope.adapters.persistence.variant

import vn.periscope.ports.variant.*
import vn.periscope.ports.variant.models.Variant

class VariantPersistenceAdapter(
    private val variantRepository: VariantRepository,
) : GetVariantEntryPoint,
    CreateVariantEntryPoint,
    UpdateVariantEntryPoint,
    DeleteVariantEntryPoint,
    FilterVariantEntryPoint,
    SearchVariantEntryPoint {
    override fun filter(): List<Variant> {
        return variantRepository.getAll().map { it.toEntry() }
    }

    override fun search(): List<Variant> {
        return variantRepository.getAll().map { it.toEntry() }
    }

    override fun findById(id: Long): Variant {
        return variantRepository.get(id).toEntry()
    }

    override fun create(variant: Variant): Variant {
        return variantRepository.create(variant).toEntry()
    }

    override fun update(id: Long, variant: Variant): Variant {
        return variantRepository.update(id, variant).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return variantRepository.delete(id)
    }
}