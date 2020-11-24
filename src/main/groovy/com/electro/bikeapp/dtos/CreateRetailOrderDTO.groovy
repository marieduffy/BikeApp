package com.electro.bikeapp.dtos

import java.time.OffsetDateTime

class CreateRetailOrderDTO {

    String orderStatus

    String customerName

    String paymentMethod

    Float paymentAmount

    String shippingMethod

    String shippingAddress

    String[] itemIds

}
