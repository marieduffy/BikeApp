package com.electro.bikeapp.services

import com.electro.bikeapp.domains.VendorOrderDomain
import com.electro.bikeapp.dtos.VendorOrderDTO
import com.electro.bikeapp.repositories.AccountRepository
import com.electro.bikeapp.repositories.VendorOrderRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class VendorOrderService {

    @Autowired
    VendorOrderRepository vendorOrderRepository
    AccountRepository employeeAccountRepository

    /**
     * Create an order to send to a vendor
     * @param VendorOrderDTO[] ??
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
        vendorOrder.vendorOrderDate = vendorOrderParameters[0].vendorOrderDate
        vendorOrder.vendorReceiveDate = null
        vendorOrder.quantityOrdered = vendorOrderParameters[0].quantityOrdered
        vendorOrder.quantityReceived = null
        vendorOrder.purchasePrice = vendorOrderParameters[0].purchasePrice

        int fiveHund = 500
        int tenThou = 10000
        if (vendorOrder.purchasePrice < fiveHund) {
            vendorOrderRepository.save(vendorOrder)
        }
        else if (vendorOrder.purchasePrice >= fiveHund && vendorOrder.purchasePrice < tenThou) {
            log.info('This order requires manager or owner approval')
            //verify login credentials for a manager or owner
        }
        else if (vendorOrder.purchasePrice >= tenThou) {
            log.info('This order requires SCEB owner approval')
            //verify login credentials for the owner
        }
    }

    List<VendorOrderDomain> getCurrentVendorOrders() {
        List<VendorOrderDomain> allCurrentOrders
    }

}
