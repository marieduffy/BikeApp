package com.electro.bikeapp.services

import com.electro.bikeapp.repositories.BikeInventoryRepository

class SearchInventoryService {

    BikeInventoryRepository bikeInventoryRepository

    List<Bike> getInvetoryList(){
        bikeInventoryRepository.findAll()
    }
}
