package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.Bike
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by jeb3796 on 08/03/2020.
 */
@Repository
interface BikeInventoryRepository extends CrudRepository<Bike, Long> {

    List<Bike> findAll()
}
