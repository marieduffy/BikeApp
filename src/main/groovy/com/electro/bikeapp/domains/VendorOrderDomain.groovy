package com.electro.bikeapp.domains

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenerationTime

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.OffsetDateTime


@Entity
@Table(name = 'order_vendors')
class VendorOrderDomain {

   // public static final GenerationType VALUE = GenerationType.AUTO

    @Id
    @Column(name = 'vendor_order_id')
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer vendorOrderId

    @Column(name = 'vendor_id')
    Integer vendorId

    @Column(name = 'vendor_name')
    String vendorName

    @Column(name = 'vendor_order_status')
    String vendorOrderStatus

    @CreationTimestamp
    @Column(name = 'vendor_order_date')
    OffsetDateTime vendorOrderDate

    @Column(name = 'vendor_receive_date')
    OffsetDateTime vendorReceiveDate

    @Column(name = 'quantity_ordered')
    Integer quantityOrdered

    @Column(name = 'quantity_received')
    Integer quantityReceived

    @Column(name = 'purchase_price')
    Float purchasePrice

    @Column(name = 'order_breakdown')
    String orderBreakdown
}
