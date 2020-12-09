package com.electro.bikeapp.services

import com.electro.bikeapp.domains.VendorOrderDomain
import com.electro.bikeapp.dtos.VendorOrderDTO
import com.electro.bikeapp.repositories.VendorOrderRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.OffsetDateTime

@Slf4j
@Service
class VendorOrderService {

    @Autowired
    VendorOrderRepository vendorOrderRepository
    float MANAGER_REQ_AMOUNT = 500.00
    float OWNER_REQ_AMOUNT = 10000.00

    /**
     * Create an order to send to a vendor
     * @param VendorOrderDTO[]
     * @return void
     */
    void createVendorOrder(VendorOrderDTO[] vendorOrderParameters) {
        //if purchasePrice is < $500, automatically save order to vendorOrderRepository
        //if purchasePrice is $500 - $9,999, system must ask for manager/owner approval
        //if purchasePrice is >= $10,000, system must ask for owner approval

        //we also need to be able to incorporate multiple items into one order
        //maybe create a string of all the items??
        VendorOrderDomain vendorOrder = new VendorOrderDomain()

        vendorOrder.vendorId = vendorOrderParameters[0].vendorId
        vendorOrder.vendorName = vendorOrderParameters[0].vendorName
        vendorOrder.vendorOrderStatus = 'Confirmed'
        vendorOrder.quantityOrdered = vendorOrderParameters[0].quantityOrdered
        vendorOrder.purchasePrice = vendorOrderParameters[0].purchasePrice
        vendorOrder.orderBreakdown = vendorOrderParameters[0].orderBreakdown.toString()
        vendorOrder.vendorOrderDate = OffsetDateTime.now()


        if (vendorOrder.purchasePrice < MANAGER_REQ_AMOUNT) {
            vendorOrderRepository.save(vendorOrder)
        }
        else if (vendorOrder.purchasePrice >= MANAGER_REQ_AMOUNT && vendorOrder.purchasePrice < OWNER_REQ_AMOUNT) {
            log.info('This order requires manager or owner approval')
            //verify login credentials for a manager or owner
        }
        else if (vendorOrder.purchasePrice >= OWNER_REQ_AMOUNT) {
            log.info('This order requires SCEB owner approval')
            //verify login credentials for the owner
        }
    }

    void updateVendorOrderStatus (Integer vendorOrderId, String status) {
        Optional<VendorOrderDomain> vendorOrder = vendorOrderRepository.findByVendorOrderId(vendorOrderId)
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        if (vendorOrder.isPresent()) {
            vendorOrder.get().vendorOrderStatus = status
            if (status == "received") {
                vendorOrder.get().vendorReceiveDate = offsetDateTime
            }
        }
        else {
            log.error("No vendor order found")
        }
    }
}
