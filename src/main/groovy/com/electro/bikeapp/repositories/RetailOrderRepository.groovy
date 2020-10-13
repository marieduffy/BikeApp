package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.RetailOrderDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RetailOrderRepository extends JpaRepository<RetailOrderDomain, Long> {
    // createRetailOrder()
    // getAllRetailOrders()
    // getRetailOrder(long id)
    // updateRetailOrder(long id)
    // deleteRetailOrder(long id)

    RetailOrderDomain findByOrderId(long id)
}
