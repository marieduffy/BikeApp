package com.electro.bikeapp.controllers

import com.electro.bikeapp.domains.RepairTicketDomain
import com.electro.bikeapp.services.RepairTicketService
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
class RepairTicketController {

    @Autowired
    RepairTicketService repairTicketService

    /**
     * POST - create repair ticket
     * @requestBody JSON order details
     * @param CreateRepairTicketDTO
     * @return boolean
     */
    @PostMapping(value = '/repair/create', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void createRepairTicket(@RequestBody RepairTicketDomain newTicketParameters) {
        log.info 'Creating Repair Ticket'
        repairTicketService.createRepairTicket(newTicketParameters)
    }

}
