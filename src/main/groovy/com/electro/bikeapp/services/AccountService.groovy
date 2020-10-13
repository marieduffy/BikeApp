package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.ChangePasswordDTO
import com.electro.bikeapp.dtos.ChangeEmailDTO
import com.electro.bikeapp.dtos.LoginCredentialsDTO
import com.electro.bikeapp.repositories.AccountRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class AccountService {

    @Autowired
    AccountRepository accountRepository

    boolean verifyCredentials(LoginCredentialsDTO loginCredentialsDTO){

        loginCredentialsDTO.username

    }

    void changePassword(ChangePasswordDTO changePasswordDTO){
        EmployeeDomain currentEmployee = accountRepository.findByUsername(changePasswordDTO.userName)
        if(changePasswordDTO.currentPassword == currentEmployee.passWord){
            currentEmployee.passWord = changePasswordDTO.newPassword
            accountRepository.save(currentEmployee)
        }
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
