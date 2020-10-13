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

            retailOrder.orderId = newRetailOrderParametersArray[i].orderId
            retailOrder.orderStatus = newRetailOrderParametersArray[i].orderStatus
            retailOrder.customerId = newRetailOrderParametersArray[i].customerId
            retailOrder.orderDate = newRetailOrderParametersArray[i].orderDate
            retailOrder.shipMethod = newRetailOrderParametersArray[i].shipMethod
            retailOrder.priceAmount = newRetailOrderParametersArray[i].priceAmount
            retailOrder.shippingAddress = newRetailOrderParametersArray[i].shippingAddress

            // save the retail order to database
            retailOrderRepository.save(retailOrder)
        }
    }

    RetailOrderDomain getRetailOrder(long id) {
        return retailOrderRepository.findById(id) as RetailOrderDomain
    }

    void setRetailOrder(long id) {
        // retailOrderRepository
    }
}