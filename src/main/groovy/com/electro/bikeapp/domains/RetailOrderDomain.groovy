package com.electro.bikeapp.domains

import org.hibernate.annotations.CreationTimestamp

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
    @Column(name = 'order_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long orderId

    @Column(name = 'order_status')
    String orderStatus

    @Column(name = 'customer_name')
    String customerName

    @CreationTimestamp
    @Column(name = 'order_date')
    OffsetDateTime orderDate

    @Column(name = 'payment_method')
    String paymentMethod

    @Column(name = 'payment_amount')
    Float paymentAmount

    @Column(name = 'shipping_method')
    String shipMethod

    @Column(name = 'shipping_address')
    String shippingAddress

    @Column(name = 'item_ids')
    String itemIds
}
