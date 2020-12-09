package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.VendorOrderDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendorOrderRepository extends JpaRepository<VendorOrderDomain, Long> {

    Optional<VendorOrderDomain> findByVendorOrderId(Integer vendorOrderId)
    Optional<VendorOrderDomain> findTopByOrderByVendorOrderIdDesc()

}
