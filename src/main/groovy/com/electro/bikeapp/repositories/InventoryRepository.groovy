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
    @Query('SELECT b FROM BikeDomain b WHERE (:bikeColor is null or b.color = :bikeColor) and (:priceMin is null or b.retailCost >= :priceMin) and (:priceMax is null or b.retailCost <= :priceMax) and (:inStock is null or b.inStock = :inStock) and (:condition is null or b.condition = :condition) and (:make is null or b.make = :make)')
    // This method returns a list of bikes. It accepts the name, make, color, lessThanCost, and greaterThanCost
    List<BikeDomain> allParamSearch(@Param('bikeColor') String bikeColor,
                                    @Param('priceMin') BigDecimal priceMin,
                                    @Param('priceMax') BigDecimal priceMax,
                                    @Param('inStock') Boolean inStock,
                                    @Param('condition') String condition,
                                    @Param('make') String make);

}
