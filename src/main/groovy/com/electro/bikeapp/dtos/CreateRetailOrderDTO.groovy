package com.electro.bikeapp.dtos

import java.time.OffsetDateTime

class CreateRetailOrderDTO {

    String orderStatus

    Long customerId

    String customerName

    OffsetDateTime orderDate

    String shipMethod

    BigDecimal priceAmount

    String shippingAddress

}
