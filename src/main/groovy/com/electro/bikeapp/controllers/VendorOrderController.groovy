package com.electro.bikeapp.controllers

import com.electro.bikeapp.dtos.VendorOrderDTO
import com.electro.bikeapp.services.VendorOrderService
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired

@RestController
@Slf4j

class VendorOrderController {

    @Autowired
    VendorOrderService vendorOrderService

    /**
     * POST - create an order to a vendor
     * @requestBody JSON order info
     * @param VendorOrderDTO[]
     * @return void
     */
    @PostMapping(value = 'vendorOrder/create', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void createVendorOrder(@RequestBody VendorOrderDTO[] vendorOrderParameters){
        log.info('Creating a new order from a vendor')
        vendorOrderService.createVendorOrder(vendorOrderParameters)
    }

    //TODO: update vendor preference status: i.e. preferred, okay, not preferred
    /**
     * PATCH - update vendor preference status
     * @requestBody JSON VendorOrderDTO[]
     * @param
     * @return void
     */
}
