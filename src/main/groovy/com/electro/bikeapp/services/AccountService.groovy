package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.ChangePasswordDTO
import com.electro.bikeapp.dtos.ChangeEmailDTO
import com.electro.bikeapp.dtos.LoginCredentialsDTO
import com.electro.bikeapp.repositories.AccountRepository
import com.electro.bikeapp.utils.StringEncryption
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class AccountService {

    @Autowired
    AccountRepository accountRepository

    StringEncryption stringEncryption = new StringEncryption()

    boolean verifyCredentials(LoginCredentialsDTO loginCredentialsDTO){

        EmployeeDomain employeeDomain = accountRepository.findByUsername(loginCredentialsDTO.username)
        if(employeeDomain != null){
            String encrypted_password = stringEncryption.encrypt(loginCredentialsDTO.password)
            if(encrypted_password == employeeDomain.encrypted_password){
                log.info("Login successful")
                return true
            }
            else{
                log.info("Wrong password")
                return false
            }
        }
        else{
            log.info("Invalid username")
            return false
        }
    }

    void changePassword(ChangePasswordDTO changePasswordDTO){
        // Find employee by username
        EmployeeDomain currentEmployee = accountRepository.findByUsername(changePasswordDTO.userName)
        // If given password matches
        if(changePasswordDTO.currentPassword == currentEmployee.password){
            currentEmployee.password = changePasswordDTO.newPassword
            accountRepository.save(currentEmployee)
        }
        // Else log error
        else{
            log.error("Wrong password entered")
        }
    }

    void changeEmail(ChangeEmailDTO changeEmailDTO){
        EmployeeDomain currentEmployee = accountRepository.findByUsername(changeEmailDTO.userName)
        if(changeEmailDTO.currentEmail == currentEmployee.email){
            currentEmployee.email = changeEmailDTO.newEmail
            accountRepository.save(currentEmployee)
        }
        else{
            log.error("You have already used this email")
        }
    }
}
