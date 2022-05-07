package vn.periscope.ports.models

import vn.periscope.share.statics.VariantStatus

data class VariantEntry(
    val id: Long,
    val name: String,
    val sku: String,
    val barcode: String? = "",
    val qrcode: String? = "",
    val status: VariantStatus? = VariantStatus.DELETED,
    val prices: List<VariantPriceEntry>,
    val attribute: List<VariantAttributeEntry>,
)
