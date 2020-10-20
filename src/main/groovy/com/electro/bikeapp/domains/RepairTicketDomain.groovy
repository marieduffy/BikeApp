package com.electro.bikeapp.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = 'ticket_repairs')
class RepairTicketDomain {
    @Id
    @Column(name = 'ticket_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long ticketId

    @Column(name = 'date_created')
    OffsetDateTime dateCreated

    @Column(name = 'first_name')
    String firstName

    @Column(name = 'last_name')
    String lastName

    @Column(name = 'address')
    String address

    @Column(name = 'phone')
    String phone

    @Column(name = 'customer_description')
    String customerDescription

    @Column(name = 'estimated_cost')
    Float estimatedCost

    @Column(name = 'service_type')
    String serviceType

    @Column(name = 'service_status')
    String serviceStatus

    @Column(name = 'repair_resolution')
    String repairResolution

    @Column(name = 'components_to_replace')
    String componentsToReplace

    @Column(name = 'vendor_of_failed_component')
    String vendorOfFailedComponent
}
