package com.electro.bikeapp.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'bike_inventory')
class Bike {
    @Id
    @Column(name = 'bike_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long employeeId

    @Column(name = 'added')
    String employeeName

    @Column(name = 'address')
    String address

    @Column(name = 'social')
    String social

    @Column(name = 'position')
    String position

    @Column(name = 'salary')
    Float salary

    @Column(name = 'payroll_type')
    String payrollType

    @Column(name = 'hours')
    Float hours

}
