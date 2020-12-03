package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.AddEmployeeDTO
import com.electro.bikeapp.dtos.UpdateEmployeeDTO
import com.electro.bikeapp.repositories.AccountRepository
import com.electro.bikeapp.utils.StringEncryption
import groovy.util.logging.Slf4j
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@SuppressWarnings([''])
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
    void addEmployee(AddEmployeeDTO[] employeeInfoParams) {
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
                    e.get().encryptedPassword = stringEncryption.encode(employeeInfoParams[i].password)
                    e.get().email = employeeInfoParams[i].email
                    e.get().privilegeLevel = employeeInfoParams[i].privilegeLevel
                    employeeAccountRepository.save(e.get())
                } else {
                    throw new NotFoundException(user + e.get().username + ' is already taken ')
                }
            }
            // else add employee
            else if (!e.isPresent()) {
                employee.employeeName = employeeInfoParams[i].employeeName
                employee.address = employeeInfoParams[i].address
                employee.social = employeeInfoParams[i].social
                employee.position = employeeInfoParams[i].position
                employee.payRate = employeeInfoParams[i].payRate
                employee.payrollType = employeeInfoParams[i].payrollType
                employee.username = employeeInfoParams[i].username
                employee.encryptedPassword = stringEncryption.encode(employeeInfoParams[i].password)
                employee.email = employeeInfoParams[i].email
                employee.privilegeLevel = employeeInfoParams[i].position
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
    void updateEmployee(UpdateEmployeeDTO employeeUpdateParams, String currentUsername) {
        //get employee by their username
        Optional<EmployeeDomain> employee = employeeAccountRepository.findByUsername(currentUsername)
        if (employee.isPresent()) {
            // Manager's ability to reset password
            if (employeeUpdateParams.password != null) {
                employee.get().encryptedPassword = stringEncryption.encode(employeeUpdateParams.password)
            }
            if (employeeUpdateParams.employeeName != null) {
                employee.get().employeeName = employeeUpdateParams.employeeName
            }
            if (employeeUpdateParams.address != null) {
                employee.get().address = employeeUpdateParams.address
            }
            if (employeeUpdateParams.position != null) {
                employee.get().position = employeeUpdateParams.position
            }
            if (employeeUpdateParams.payRate != null) {
                employee.get().payRate = employeeUpdateParams.payRate
            }
            if (employeeUpdateParams.payrollType != null) {
                employee.get().payrollType = employeeUpdateParams.payrollType
            }
            if (employeeUpdateParams.username != null) {
                employee.get().username = employeeUpdateParams.username
            }
            if (employeeUpdateParams.email != null) {
                employee.get().email = employeeUpdateParams.email
            }
            if (employeeUpdateParams.privilegeLevel != null) {
                employee.get().privilegeLevel = employeeUpdateParams.privilegeLevel
            }
            employeeAccountRepository.save(employee.get())
        }
        else {
            log.error("No employee found with Username: $currentUsername")
        }
    }

    /**
     * Delete employee from system
     * @param username
     * @return void
     */
    void deleteEmployee(String username) {
        //get employee by their username
        //we do not want to permanently delete them, just archive them somehow
        Optional<EmployeeDomain> employee = employeeAccountRepository.findByUsername(username)
        if (!employee.isPresent()) {
            throw new NotFoundException('username: ' + username + ' not found')
        }
        employee.get().isDeleted = true
        //save changes instead of delete
        employeeAccountRepository.save(employee.get())
    }

}
