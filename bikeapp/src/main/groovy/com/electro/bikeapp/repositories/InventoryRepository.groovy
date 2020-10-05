package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.BikeDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @author jbyerline
 * @creationDate 09/12/2020
 * This Repository interfaces with the bike_inventory Table
 */
@Repository
interface InventoryRepository extends JpaRepository<BikeDomain, Long> {

    // SQL Query to say, if a JSON parameter is NULL, ignore it, otherwise, return the items from the database
    // that meet the given parameters. Normally, you won't need to hardcode a query like this
    @Query("SELECT b FROM BikeDomain b WHERE (:name is null or b.bikeDisplayName = :name) and (:make is null or b.make = :make) and (:color is null or b.color = :color) and (:lessThanCost is null or b.retailCost <= :lessThanCost) and (:greaterThanCost is null or b.retailCost >= :greaterThanCost)")
    // This method returns a list of bikes. It accepts the name, make, color, lessThanCost, and greaterThanCost
    List<BikeDomain> allParamSearch(@Param("name") String name, @Param("make") String make, @Param("color") String color, @Param("lessThanCost") Float lessThanCost, @Param("greaterThanCost") Float greaterThanCost);

}
