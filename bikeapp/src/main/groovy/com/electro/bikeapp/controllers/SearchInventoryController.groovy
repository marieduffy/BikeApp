package com.electro.bikeapp.controllers

import com.electro.bikeapp.domains.Bike
import com.electro.bikeapp.services.SearchInventoryService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class SearchInventoryController {

    @Autowired
    SearchInventoryService searchInventoryService

    /**
     * GET temp of pool
     * @param none
     * @return float temp
     */
    @GetMapping(value = '/searchInventory', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<Bike> getPoolTemp() {
        log.info 'Getting bike inventory'
        searchInventoryService.getInvetoryList()
    }
}
