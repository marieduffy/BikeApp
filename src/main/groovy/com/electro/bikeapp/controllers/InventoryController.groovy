package com.electro.bikeapp.controllers

import com.electro.bikeapp.domains.BikeDomain
import com.electro.bikeapp.dtos.AddProductDTO
import com.electro.bikeapp.dtos.SearchInventoryDTO
import com.electro.bikeapp.services.InventoryService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class InventoryController {

    @Autowired
    InventoryService inventoryService

    /**
     * POST - receive list of bikes with given parameters
     * @requestBody JSON search inventory parameters
     * @param SearchInventoryDTO
     * @return List of bikes with given parameters
     */
    // Declare API POST type. set URL endpoint, and set media type to JSON
    @PostMapping(value = '/inventory/search', produces = MediaType.APPLICATION_JSON_VALUE)
    // Set a HTTP response status by default
    @ResponseStatus(HttpStatus.OK)
    // Create method of given parameters and specify the return type.
    // Note: the return value returns a JSON array of BikeDomain Objects, this is what will be sent to frontend
    // The method accepts a JSON request body of type SearchInventoryDTO (and all of its members)
    List<BikeDomain> searchInventory(@RequestBody SearchInventoryDTO searchParameters) {
        // Helpful log statement to make sure call went through
        log.info 'Getting bike inventory'
        log.info (searchParameters.bikeColor)
        // Call the search inventory method from the service class, pass it the JSON request body sent in
        inventoryService.searchInventory(searchParameters)
    }

    /**
     * POST - add a new bike to the inventory
     * @requestBody JSON array of new bike parameters
     * @param AddProductDTO[]
     * @return void
     */
    @PostMapping(value = '/inventory/addProduct', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void addProduct(@RequestBody AddProductDTO[] newProductParameters) {
        log.info 'Adding new bike to inventory'
        inventoryService.addProduct(newProductParameters)
    }

    /**
     * POST - remove a list of bikes from the inventory
     * @requestBody JSON array of bike hashs
     * @param String[]
     * @return void
     */
    @PostMapping(value = '/inventory/removeProduct', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void addProduct(@RequestBody String[] hashList) {
        log.info 'Removing bike from inventory'
        inventoryService.removeProduct(hashList)
    }

}
