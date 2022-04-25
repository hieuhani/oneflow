package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PriceListEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PriceListEntity>(PriceListTable)

    val nid by PriceListTable.nid
    val businessId by PriceListTable.businessId
    val type by PriceListTable.type
    val name by PriceListTable.name
    val currencySymbol by PriceListTable.currencySymbol
    val currencyIso by PriceListTable.currencyIso
    val status by PriceListTable.status
    val pricePolicyId by PriceListTable.pricePolicyId
    val createdAt by PriceListTable.createdAt
    val updatedAt by PriceListTable.updatedAt

}

