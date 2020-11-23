package com.electro.bikeapp.services

import com.electro.bikeapp.domains.RetailOrderDomain
import com.electro.bikeapp.dtos.CreateRetailOrderDTO
import com.electro.bikeapp.repositories.RetailOrderRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class RetailOrderService {

    @Autowired
    RetailOrderRepository retailOrderRepository

    void createRetailOrder(CreateRetailOrderDTO[] newRetailOrderParametersArray) {
        // loop through JSON array of new retail orders
        for (int i = 0; i < newRetailOrderParametersArray.size(); i++) {
            // For each retail order,
            // make a new instance of type retail order
            RetailOrderDomain retailOrder = new RetailOrderDomain()

            retailOrder.orderStatus = newRetailOrderParametersArray[i].orderStatus
            retailOrder.customerName = newRetailOrderParametersArray[i].customerName
            retailOrder.paymentMethod = newRetailOrderParametersArray[i].paymentMethod
            retailOrder.paymentAmount = newRetailOrderParametersArray[i].paymentAmount
            retailOrder.shipMethod = newRetailOrderParametersArray[i].shippingMethod
            retailOrder.shippingAddress = newRetailOrderParametersArray[i].shippingAddress

            // save the retail order to database
            retailOrderRepository.save(retailOrder)
        }
    }

    String getRetailOrder(long id) {
        return retailOrderRepository.findByOrderId(id).get().orderStatus
    }

    void updateRetailOrderStatus(long id, String status) {
        Optional<RetailOrderDomain> retailOrder = retailOrderRepository.findByOrderId(id)
        if (retailOrder.isPresent()) {
            retailOrder.get().orderStatus = status
            retailOrderRepository.save(retailOrder.get())
        }
        else {
            log.error("ID: $id is invalid")
        }
    }

}
