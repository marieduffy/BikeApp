package com.electro.bikeapp.controllers

import com.electro.bikeapp.dtos.CreateRetailOrderDTO
import com.electro.bikeapp.services.RetailOrderService
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
class RetailOrderController {

    @Autowired
    RetailOrderService retailOrderService

    // TODO Thong /order/create
    /**
     * POST - create order
     * @requestBody JSON order details
     * @param CreateOrderDTO
     * @return boolean
     */
    @PostMapping(value = '/order/create', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void createOrder(@RequestBody CreateRetailOrderDTO newOrderParameters) {
        log.info 'Creating Order'
        retailOrderService.createOrder(newOrderParameters)
    }

    // TODO Thong /order/status/{orderId}

    // TODO Thong /order/status/set/{orderId}
}
