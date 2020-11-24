package com.electro.bikeapp.domains

import org.hibernate.annotations.CreationTimestamp

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = 'order_vendors')
class VendorOrderDomain {

    @Id
    @Column(name = 'vendor_order_id')
    Integer vendorOrderId

    @Column(name = 'vendor_id')
    Integer vendorId

    @Column(name = 'vendor_name')
    String vendorName

    @Column(name = 'vendor_address')
    String vendorAddress

    @Column(name = 'vendor_preference_status')
    String vendorPreference

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
