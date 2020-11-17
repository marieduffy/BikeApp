package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.AddEmployeeDTO
import com.electro.bikeapp.repositories.AccountRepository
import com.electro.bikeapp.utils.StringEncryption
import groovy.util.logging.Slf4j
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class EmployeeService {

    @Autowired
    AccountRepository employeeAccountRepository

    StringEncryption stringEncryption = new StringEncryption()
    String user = 'Username: '
    /**
     * Add employee to system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void addEmployee (AddEmployeeDTO[] employeeInfoParams) {
        for (int i = 0; i < employeeInfoParams.size(); i++) {
            EmployeeDomain employee = new EmployeeDomain()
            Optional<EmployeeDomain> e = employeeAccountRepository.findByUsername(employeeInfoParams[i].username)
            // If employee already exists, throw error
            if (e.isPresent()) {
                // if its the same person
                if (e.get().social == employeeInfoParams[i].social) {
                    e.get().isDeleted = false
                    e.get().payrollType = employeeInfoParams[i].payrollType
                    e.get().employeeName = employeeInfoParams[i].employeeName
                    e.get().address = employeeInfoParams[i].address
                    e.get().social = employeeInfoParams[i].social
                    e.get().position = employeeInfoParams[i].position
                    e.get().payRate = employeeInfoParams[i].payRate
                    e.get().payrollType = employeeInfoParams[i].payrollType
                    e.get().username = employeeInfoParams[i].username
                    e.get().encrypted_password = stringEncryption.encode(employeeInfoParams[i].password)
                    e.get().email = employeeInfoParams[i].email
                    e.get().privilegeLevel = employeeInfoParams[i].privilegeLevel
                    employeeAccountRepository.save(e.get())
                }
                else {
                    throw new NotFoundException(user + e.get().username + ' is already taken ')
                }
            }
            // else add employee
            else if (!e.isPresent()) {
                employee.payrollType = employeeInfoParams[i].payrollType
                employee.employeeName = employeeInfoParams[i].employeeName
                employee.address = employeeInfoParams[i].address
                employee.social = employeeInfoParams[i].social
                employee.position = employeeInfoParams[i].position
                employee.payRate = employeeInfoParams[i].payRate
                employee.payrollType = employeeInfoParams[i].payrollType
                employee.username = employeeInfoParams[i].username
                employee.encrypted_password = stringEncryption.encode(employeeInfoParams[i].password)
                employee.email = employeeInfoParams[i].email
                employee.privilegeLevel = employeeInfoParams[i].privilegeLevel
                employee.isDeleted = false

                //save employee to the database
                employeeAccountRepository.save(employee)
            }
        }
    }

    /**
     * Update employee in system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void updateEmployee (AddEmployeeDTO[] employeeUpdateParams, String username) {
        //add isDeleted and findbyUsername call in repository
        //get employee by their username
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
//        if(employeeUpdateParams[0].password != null){
//            //might not change password here
//        }
        if (employeeUpdateParams[0].employeeName != null) {
            employee.employeeName = employeeUpdateParams[0].employeeName
        }
        if (employeeUpdateParams[0].address != null) {
            employee.address = employeeUpdateParams[0].address
        }
        if (employeeUpdateParams[0].position != null) {
            employee.position = employeeUpdateParams[0].position
        }
        if (employeeUpdateParams[0].salary != null) {
            employee.salary = employeeUpdateParams[0].salary
        }
        if (employeeUpdateParams[0].username != null) {
            employee.username = employeeUpdateParams[0].username
        }
        if (employeeUpdateParams[0].payrollType != null) {
            employee.payrollType = employeeUpdateParams[0].payrollType
        }
        employeeAccountRepository.save(employee)
    }

    /**
     * Delete employee from system
     * @param username
     * @return void
     */
    void deleteEmployee (String username) {
        //get employee by their username
        //we do not want to permanently delete them, just archive them somehow
        Optional<EmployeeDomain> employee = employeeAccountRepository.findByUsername(username)
        if (!employee.isPresent()) {
            throw new NotFoundException(user + username + 'not found')
        }
        employee.get().isDeleted = true
        //save changes instead of delete
        employeeAccountRepository.save(employee.get())
    }

}
