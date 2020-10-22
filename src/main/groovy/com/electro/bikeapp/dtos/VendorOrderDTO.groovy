package com.electro.bikeapp.dtos

import java.time.OffsetDateTime

class VendorOrderDTO {

    Integer vendorOrderID

    Integer vendorId

    String vendorName

    String vendorAddress

    String vendorPreference

    String vendorOrderStatus

    OffsetDateTime vendorOrderDate

    OffsetDateTime vendorReceiveDate

    Integer quantityOrdered

    Integer quantityReceived

    Float purchasePrice

}
