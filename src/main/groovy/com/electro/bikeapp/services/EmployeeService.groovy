package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.AddEmployeeDTO
import com.electro.bikeapp.repositories.AccountRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class EmployeeService {
    @Autowired
    AccountRepository employeeAccountRepository

    /**
     * Add employee to system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void addEmployee(AddEmployeeDTO[] employeeInfoParams){

        //Loop through JSON array of new employees
        for(int i = 0; i< employeeInfoParams.size(); i++){
            EmployeeDomain employee = new EmployeeDomain()
            employee.payrollType = employeeInfoParams[i].payrollType
            employee.employeeName = employeeInfoParams[i].employeeName
            employee.address = employeeInfoParams[i].address
            employee.social = employeeInfoParams[i].social
            employee.position = employeeInfoParams[i].position
            employee.salary = employeeInfoParams[i].salary
            employee.username = employeeInfoParams[i].username
            employee.passWord = employeeInfoParams[i].passWord
            //employee.isDeleted = false
            //save employee to the database
            employeeAccountRepository.save(employee)
        }
    }

    /**
     * Update employee in system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void updateEmployee(AddEmployeeDTO[] employeeUpdateParams, String username){

        //get employee by their username
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
        if(employeeUpdateParams[0].passWord != null){
            //might not change password here
        }
        if(employeeUpdateParams[0].employeeName != null){
            employee.employeeName = employeeUpdateParams[0].employeeName
        }
        if(employeeUpdateParams[0].address != null){
            employee.address = employeeUpdateParams[0].address
        }
        if(employeeUpdateParams[0].position != null){
            employee.position = employeeUpdateParams[0].position
        }
        if(employeeUpdateParams[0].salary != null){
            employee.salary = employeeUpdateParams[0].salary
        }
        if(employeeUpdateParams[0].username != null){
            employee.username = employeeUpdateParams[0].username
        }
    }

    /**
     * Delete employee from system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void deleteEmployee(String username){
        //get employee by their username
        //we do not want to permanently delete them, just archive them somehow
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
        //employee.isDeleted = true
        //employeeAccountRepository.delete(employee)
    }
}
