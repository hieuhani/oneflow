package vn.periscope.ports.price.models

data class PricePolicy(
    val id: Long,
    val businessId: Long,
    val type: PricePolicyType,
    val status: PricePolicyStatus,
    val code: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
) {
    enum class PricePolicyStatus {
        ACTIVE, INACTIVE
    }

    enum class PricePolicyType {
        PURCHASE, SALES
    }

}


