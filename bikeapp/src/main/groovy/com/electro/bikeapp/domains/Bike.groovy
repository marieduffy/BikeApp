package com.electro.bikeapp.domains

import com.electro.bikeapp.utils.OffsetDateTimePersistenceConverter
import com.electro.bikeapp.utils.UtcDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.CreationTimestamp

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = 'bike_inventory')
class Bike {
    @Id
    @Column(name = 'bike_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long bikeId

    @Column(name = 'added_by')
    String addedBy

    @CreationTimestamp
    @Column(name = 'added_date', columnDefinition = 'DATETIMEOFFSET(7)')
    @JsonSerialize(using = UtcDateSerializer)
    @Convert(converter = OffsetDateTimePersistenceConverter)
    OffsetDateTime addedDate

    @Column(name = 'make')
    String make

    @Column(name = 'model')
    String model

    @Column(name = 'color')
    String color

    @Column(name = 'wholesale_cost')
    Float wholesaleCost

    @Column(name = 'retail_cost')
    String retailCost

    @Column(name = 'description')
    String description

    @Column(name = 'sold_by')
    String soldBy

    @CreationTimestamp
    @Column(name = 'sold_date', columnDefinition = 'DATETIMEOFFSET(7)')
    @JsonSerialize(using = UtcDateSerializer)
    @Convert(converter = OffsetDateTimePersistenceConverter)
    OffsetDateTime soldDate

    @Column(name = 'is_sold')
    Boolean isSold




}
