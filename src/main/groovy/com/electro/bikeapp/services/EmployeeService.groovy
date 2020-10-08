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
            employee.employeeId = employeeInfoParams[i].employeeId
            employee.employeeName = employeeInfoParams[i].employeeName
            employee.address = employeeInfoParams[i].address
            employee.social = employeeInfoParams[i].social
            employee.position = employeeInfoParams[i].position
            employee.salary = employeeInfoParams[i].salary
            employee.username = employeeInfoParams[i].username
            employee.passWord = employeeInfoParams[i].passWord
            //save employee to the database
            employeeAccountRepository.save(employee)
        }
    }

    /**
     * Update employee in system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void updateEmployee(AddEmployeeDTO[] employeeUpdateParams){
        //get employee by their ID number
        //how to choose the field that we want to update?
        if(employeeUpdateParams[0].passWord != null){

        }
    }

    /**
     * Delete employee from system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void deleteEmployee(String username){
        //get employee by ID number
        //remove their entry into the array of employees
        // or remove their EmployeeDomain() object???
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
        employee.isDeleted = true
        //employeeAccountRepository.delete(employee)
    }
}
