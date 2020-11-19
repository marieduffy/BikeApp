package com.electro.bikeapp.services

import com.electro.bikeapp.domains.RepairTicketDomain
import com.electro.bikeapp.repositories.RepairTicketRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@SuppressWarnings([''])
@Slf4j
@Service
class RepairTicketService {

    @Autowired
    RepairTicketRepository repairTicketRepository

    void createRepairTicket(RepairTicketDomain[] newRepairTicketParametersArray) {
        // loop through JSON array of new retail orders
        for (int i = 0; i < newRepairTicketParametersArray.size(); i++) {
            // For each repair ticket,
            // make a new instance of type repair ticket
            RepairTicketDomain repairTicket = new RepairTicketDomain()

            repairTicket.dateCreated = newRepairTicketParametersArray[i].dateCreated
            repairTicket.firstName = newRepairTicketParametersArray[i].firstName
            repairTicket.lastName = newRepairTicketParametersArray[i].lastName
            repairTicket.address = newRepairTicketParametersArray[i].address
            repairTicket.phone = newRepairTicketParametersArray[i].phone
            repairTicket.customerDescription = newRepairTicketParametersArray[i].customerDescription
            repairTicket.estimatedCost = newRepairTicketParametersArray[i].estimatedCost
            repairTicket.serviceType = newRepairTicketParametersArray[i].serviceType
            repairTicket.serviceStatus = newRepairTicketParametersArray[i].serviceStatus
            repairTicket.repairResolution = newRepairTicketParametersArray[i].repairResolution
            repairTicket.componentsToReplace = newRepairTicketParametersArray[i].componentsToReplace
            repairTicket.vendorOfFailedComponent = newRepairTicketParametersArray[i].vendorOfFailedComponent

            // save the retail order to database
            repairTicketRepository.save(repairTicket)
        }
    }

}
