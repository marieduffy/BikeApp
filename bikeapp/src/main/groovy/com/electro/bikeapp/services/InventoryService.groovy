package com.electro.bikeapp.services

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.repositories.InventoryRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class InventoryService {

    @Autowired
    InventoryRepository bikeInventoryRepository

    List<BikeDomain> searchInventory(SearchInventoryDTO searchParams){
        // Get the entire inventory from the database
        List<BikeDomain> bikeInventoryList = bikeInventoryRepository.findAll()
        // Create an empty list to add valid items to
        List<BikeDomain> searchResponseList = []
        // Loop through the inventory list
        for(bike in bikeInventoryList){
            // Check if item meets search parameters
            if(bike.bikeName == searchParams.bikeName) {
                // if so, add it to list
                searchResponseList.add(bike)
            }
            else if(bike.make == searchParams.make){
                searchResponseList.add(bike)
            }
            else if(bike.color == searchParams.color){
                searchResponseList.add(bike)
            }
            else if(bike.retailCost <= searchParams.lessThanCost && bike.retailCost >= searchParams.greaterThanCost){
                searchResponseList.add(bike)
            }
        }
        // Return the response list
        searchResponseList
    }
}
