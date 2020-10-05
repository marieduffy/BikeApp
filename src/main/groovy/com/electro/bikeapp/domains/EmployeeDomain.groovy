package com.electro.bikeapp.domains

import javax.persistence.*

@Entity
@Table(name = 'employee_accounts')
class EmployeeDomain {
    @Id
    @Column(name = 'employee_id')
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long employeeId

    @Column(name = 'name')
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
