package com.electro.bikeapp.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = 'order_retails')
class RetailOrderDomain {
    @Id
    @Column(name = 'customer_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long bikeId

    @Column(name = 'order_id')
    Long orderId

    @Column(name = 'order_date')
    OffsetDateTime orderDate

    @Column(name = 'shipping_method')
    String shipMethod

    @Column(name = 'sales_price')
    Float priceAmount

    @Column(name = 'shipping_address')
    String shippingAddress
}
