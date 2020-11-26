package com.electro.bikeapp.controllers

import com.electro.bikeapp.dtos.CreateRetailOrderDTO
import com.electro.bikeapp.services.RetailOrderService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class RetailOrderController {

    @Autowired
    RetailOrderService retailOrderService

    /**
     * POST - create retail order
     * @requestBody JSON order details
     * @param CreateRetailOrderDTO
     * @return boolean
     */
    @PostMapping(value = '/order/create', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void createRetailOrder(@RequestBody CreateRetailOrderDTO[] newRetailOrderParameters) {
        log.info 'Creating Retail Order'
        retailOrderService.createRetailOrder(newRetailOrderParameters)
    }

    /**
     * GET - get retail order
     * @param orderId
     * @return RetailOrderDomain
     */
    @GetMapping(value = '/order/status/{orderId}', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    String getRetailOrder(@PathVariable Long orderId) {
        log.info 'Getting Retail Order'
        retailOrderService.getRetailOrder(orderId)
    }

    /**
     * GET - update retail order
     * @param orderId
     * @return void
     */
    @GetMapping(value = '/order/status/set/{orderId}/{status}', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void updateRetailOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        log.info 'Updating Retail Order'
        retailOrderService.updateRetailOrderStatus(orderId, status)
    }

}
