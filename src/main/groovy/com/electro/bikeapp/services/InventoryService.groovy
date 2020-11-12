package com.electro.bikeapp.services

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.AddProductDTO
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.repositories.InventoryRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

import java.time.OffsetDateTime

@Slf4j
@Service
class InventoryService {

    // The repository must be autowired. The repository is the class that queried the database
    @Autowired
    InventoryRepository bikeInventoryRepository

    // This method returns a list of bikes with the given search parameters
    List<BikeDomain> searchInventory(SearchInventoryDTO searchParams){

        // We call the bikeInventoryRepository and return it's result
        List<BikeDomain> searchedBikeList = bikeInventoryRepository.allParamSearch(
                searchParams.bikeColor,
                searchParams.priceMin,
                searchParams.priceMax,
                searchParams.inStock,
                searchParams.condition,
                searchParams.make
        )

        return searchedBikeList
    }

    /**
     * Add bike to inventory
     * @param AddProductDTO[]
     * @return void
     */
    void addProduct(AddProductDTO[] newProductParametersArray){

        // Loop through JSON array of new bikes
        for(int i = 0; i < newProductParametersArray.size(); i++){
            // For each bike:
            // Make a new instance of type bike
            BikeDomain bike = new BikeDomain()
            // Set in all the parameters
            bike.bikeDisplayName = newProductParametersArray[i].bikeDisplayName
            bike.color = newProductParametersArray[i].color
            bike.make = newProductParametersArray[i].make
            bike.model = newProductParametersArray[i].model
            bike.retailCost = newProductParametersArray[i].retailCost
            bike.wholesaleCost = newProductParametersArray[i].wholesaleCost
            bike.description = newProductParametersArray[i].description
            bike.addedBy = newProductParametersArray[i].addedBy
            bike.isSold = false
            // Save the bike to the database
            bikeInventoryRepository.save(bike)
        }
    }
}


