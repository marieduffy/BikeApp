package com.electro.bikeapp.services

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.repositories.InventoryRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

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
                searchParams.bikeDisplayName,
                searchParams.make,
                searchParams.color,
                searchParams.lessThanCost,
                searchParams.greaterThanCost)

        return searchedBikeList
    }
}


