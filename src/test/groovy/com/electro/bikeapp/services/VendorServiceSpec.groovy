package com.electro.bikeapp.services

import com.electro.bikeapp.domains.VendorOrderDomain
import com.electro.bikeapp.repositories.VendorOrderRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class VendorServiceSpec extends Specification {

    @Autowired
    VendorOrderRepository vendorOrderRepository

    @Autowired
    VendorOrderService vendorOrderService

    void createAVendorOrder() {
        given: "A new vendor order"
        VendorOrderDomain v = new VendorOrderDomain(
                vendorId: 243,
                vendorName: 'Wheels & Deals',
                quantityOrdered: 25,
                purchasePrice: 50.00,
                orderBreakdown: 'wheels, deals, and more wheels'
        )
        vendorOrderRepository.save(v)

        when: "A vendor order status is updated"
        vendorOrderService.updateVendorOrderStatus(1,'Shipped')

        then: "Verify the order has been updated"
        Optional<VendorOrderDomain> vend = vendorOrderRepository.findByVendorOrderId(1)
        assert vend.get().vendorOrderStatus == 'Shipped'

    }
}
