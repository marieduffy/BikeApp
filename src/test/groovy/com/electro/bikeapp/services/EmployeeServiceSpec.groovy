package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.UpdateEmployeeDTO
import com.electro.bikeapp.repositories.AccountRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
@SuppressWarnings(['MethodReturnTypeRequired', 'NoDef', 'VariableTypeRequired'])
class EmployeeServiceSpec extends Specification {

    @Autowired
    EmployeeService employeeService

    @Autowired
    AccountRepository accountRepository

    def 'exampleJunitTest'() {
        given:'A variable'
        int x = 3

        when:'A second variable is declared'
        int y = 3 * 1

        then:'Assert they\'re equal'
        assert x == y
    }

    void deleteAnEmployee() {
        given:'A username'
        String testUsername = 'bhfsbghsbgh'

        when:'Deleting an non existing employee'
        employeeService.deleteEmployee(testUsername)

        then:'Verify thrown error'
        def e = thrown(NotFoundException)
        assert e.message == 'username:' + testUsername + ' not found'
    }

    void deleteAnNonExistingEmployee() {
        given:'A new employee'
        EmployeeDomain e = new EmployeeDomain(
                employeeName:'Jacob',
                address:'123 Main St.',
                social:'123-456-789',
                position:'Boss',
                payRate:22.45,
                payrollType:'Salary',
                username:'testUser123',
                email:'myEmail@email.com',
                encryptedPassword:'password',
                privilegeLevel:'admin',
                isDeleted:'false'
        )
        accountRepository.save(e)

        when:'An employee is deleted'
        employeeService.deleteEmployee('testUser123')

        then:'Verify they\'re deleted'
        Optional<EmployeeDomain> emp = accountRepository.findByUsername('testUser123')
        assert emp.get().isDeleted == true

        cleanup:
        accountRepository.delete(e)
    }

    void updateAnEmployee() {
        given:'A new employee'
        EmployeeDomain e = new EmployeeDomain(
                employeeName:'Jacob',
                address:'123 Main St.',
                social:'123-456-789',
                position:'Boss',
                payRate:22.45,
                payrollType:'Salary',
                username:'testUser123',
                email:'myEmail@email.com',
                encryptedPassword:'password',
                privilegeLevel:'admin',
                isDeleted:'false'
        )
        accountRepository.save(e)

        UpdateEmployeeDTO updateEmployeeDTO = new UpdateEmployeeDTO(
                employeeName:'Lili',
                address:'999 Apple Dr',
                position:'Bookkeeper',
                payRate:10.99,
                username:'testUser123',
                password:'PassWord',
                email:'myEmail@gmail.com',
                payrollType:'Hourly',
                privilegeLevel:'ADMIN'
        )

        when:'An employee is updated'
        employeeService.updateEmployee(updateEmployeeDTO, 'testUser123')

        then:'Verify the employee has been updated'
        Optional<EmployeeDomain> emp = accountRepository.findByUsername('testUser123')
        assert emp.get().employeeName == 'Lili'
        assert emp.get().address == '999 Apple Dr'

        cleanup:
        accountRepository.delete(emp.get())
    }

}
