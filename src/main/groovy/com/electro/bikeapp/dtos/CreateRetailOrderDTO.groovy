package com.electro.bikeapp.dtos

import java.time.OffsetDateTime

class CreateRetailOrderDTO {
    Long bikeId
    Long orderId
    OffsetDateTime orderDate
    String shipMethod
    Float priceAmount
    String shippingAddress
}
