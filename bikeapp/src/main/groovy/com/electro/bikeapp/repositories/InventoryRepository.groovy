package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.BikeDomain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author jbyerline
 * @creationDate 09/12/2020
 * This Repository interfaces with the bike_inventory Table
 */
@Repository
interface InventoryRepository extends CrudRepository<BikeDomain, Long> {

    List<BikeDomain> findAll()
}
