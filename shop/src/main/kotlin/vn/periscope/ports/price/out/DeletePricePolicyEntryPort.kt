package vn.periscope.ports.price.out

interface DeletePricePolicyEntryPort {
    fun delete(id: Long): Boolean
}