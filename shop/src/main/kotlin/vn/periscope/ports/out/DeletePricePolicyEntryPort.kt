package vn.periscope.ports.out

interface DeletePricePolicyEntryPort {
    fun delete(id: Long): Boolean
}