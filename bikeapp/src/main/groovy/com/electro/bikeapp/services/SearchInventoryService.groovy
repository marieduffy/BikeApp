package com.electro.bikeapp.services

import com.electro.bikeapp.domains.Bike
import com.electro.bikeapp.repositories.BikeInventoryRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class SearchInventoryService {

    @Autowired
    BikeInventoryRepository bikeInventoryRepository

    List<Bike> getInvetoryList(){
        bikeInventoryRepository.findAll()
    }
}
