package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.RetailOrderDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RetailOrderRepository extends JpaRepository<RetailOrderDomain, Long> {

    Optional<RetailOrderDomain> findByOrderId(long id)

}
