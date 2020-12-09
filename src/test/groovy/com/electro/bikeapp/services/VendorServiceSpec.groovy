package com.electro.bikeapp.services

import com.electro.bikeapp.domains.VendorOrderDomain
import com.electro.bikeapp.dtos.VendorOrderDTO
import com.electro.bikeapp.repositories.VendorOrderRepository
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
        given:'A new vendor order'
        VendorOrderDTO v = new VendorOrderDTO(
                vendorId:243,
                vendorName:'Wheels & Deals',
                quantityOrdered:25,
                purchasePrice:50.00,
                orderBreakdown:'wheels, deals, and more wheels'
        )
        VendorOrderDTO[] vendorParams = new VendorOrderDTO[1]
        vendorParams[0] = v

        when:'A vendor order status is updated'
        vendorOrderService.createVendorOrder(vendorParams)

        then:'verify the order was created'
        Optional<VendorOrderDomain> vend = vendorOrderRepository.findTopByOrderByVendorOrderIdDesc()
        assert vend.get().vendorId == 243
        assert vend.get().vendorName == 'Wheels & Deals'
        assert vend.get().quantityOrdered == 25
        assert vend.get().purchasePrice == 50.00
        assert vend.get().orderBreakdown == 'wheels, deals, and more wheels'

//        then:'Verify the order has been updated'
//        Optional<VendorOrderDomain> vend = vendorOrderRepository.findByVendorOrderId(1)
//        assert vend.get().vendorOrderStatus == 'Shipped'
    }

}
