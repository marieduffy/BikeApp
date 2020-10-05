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

    @Autowired
    InventoryRepository bikeInventoryRepository

    List<BikeDomain> searchInventory(SearchInventoryDTO searchParams){

        List<BikeDomain> modelList = bikeInventoryRepository.findByAllParam(searchParams.bikeDisplayName, searchParams.color)

        modelList
    }
}


