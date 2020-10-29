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


    /**
     * Add employee to system
     * @param AddEmployeeDTO[]
     * @return void
     */
    void addEmployee(AddEmployeeDTO[] employeeInfoParams){
        for(int i = 0; i< employeeInfoParams.size(); i++){
            EmployeeDomain employee = new EmployeeDomain()
            EmployeeDomain e;
            try{
                e = employeeAccountRepository.findByUsername(employeeInfoParams[i].username)
            }
            catch (NullPointerException exception){
                log.info("No employee exists yet for this username")
            }

            if(e != null && employeeInfoParams[i].username == e.username){
                log.info('Changing the employee status to isDeleted = false')
                e.isDeleted = false
                employeeAccountRepository.save(e)
            }
            else {
                employee.payrollType = employeeInfoParams[i].payrollType
                employee.employeeName = employeeInfoParams[i].employeeName
                employee.address = employeeInfoParams[i].address
                employee.social = employeeInfoParams[i].social
                employee.position = employeeInfoParams[i].position
                employee.salary = employeeInfoParams[i].salary
                employee.username = employeeInfoParams[i].username
                employee.encrypted_password = stringEncryption.encrypt(employeeInfoParams[i].password)
                employee.email = employeeInfoParams[i].email
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
    void updateEmployee(AddEmployeeDTO[] employeeUpdateParams, String username){
        //add isDeleted and findbyUsername call in repository 
        //get employee by their username
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
//        if(employeeUpdateParams[0].password != null){
//            //might not change password here
//        }
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
        if(employeeUpdateParams[0].payrollType != null){
            employee.payrollType = employeeUpdateParams[0].payrollType
        }
        employeeAccountRepository.save(employee)
    }

    /**
     * Delete employee from system
     * @param username
     * @return void
     */
    void deleteEmployee(String username){
        //get employee by their username
        //we do not want to permanently delete them, just archive them somehow
        EmployeeDomain employee = employeeAccountRepository.findByUsername(username)
        if(employee == null){
            throw new NotFoundException(username + "not found")
        }
        employee.isDeleted = true
        //employeeAccountRepository.delete(employee)
        //save changes instead of delete
        employeeAccountRepository.save(employee)
    }
}
