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

    @Column(name = 'pay_rate')
    Float payRate

    @Column(name = 'payroll_type')
    String payrollType

    @Column(name = 'username')
    String username

    @Column(name = 'email')
    String email

    @Column(name = 'encrypted_password')
    String encrypted_password

    @Column(name = 'privilege_level')
    String privilegeLevel

    @Column(name = 'is_deleted')
    Boolean isDeleted


}
