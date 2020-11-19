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

@SuppressWarnings(['UnnecessaryElseStatement'])
@Slf4j
@Service
class AccountService {

    @Autowired
    AccountRepository accountRepository

    StringEncryption stringEncryption = new StringEncryption()

    boolean verifyCredentials (LoginCredentialsDTO loginCredentialsDTO) {
        EmployeeDomain employeeDomain = accountRepository.findByUsername(loginCredentialsDTO.username)
        if (employeeDomain != null) {
            String testEncryptedPassword = stringEncryption.encode(loginCredentialsDTO.password)
            if (testEncryptedPassword == employeeDomain.encryptedPassword) {
                log.info('Login successful')
                return true
            }
            else {
                log.info('Wrong password')
                return false
            }
        }
        else {
            log.info('Invalid username')
            return false
        }
    }

    void changePassword (ChangePasswordDTO changePasswordDTO) {
        // Find employee by username
        Optional<EmployeeDomain> currentEmployee = accountRepository.findByUsername(changePasswordDTO.username)
        // If given password matches
        if (currentEmployee.isPresent()) {
            if (stringEncryption.encode(changePasswordDTO.currentPassword) == currentEmployee.get().encryptedPassword) {
                currentEmployee.get().encryptedPassword = stringEncryption.encode(changePasswordDTO.newPassword)
                accountRepository.save(currentEmployee.get())
            }
            // Else log error
            else {
                log.error('Wrong password entered')
            }
        }
        else {
            log.error('User not found :(')
        }
    }

    void changeEmail (ChangeEmailDTO changeEmailDTO) {
        Optional<EmployeeDomain> currentEmployee = accountRepository.findByUsername(changeEmailDTO.username)
       // println (currentEmployee.get().email)
        if (currentEmployee.isPresent()) {
            if (changeEmailDTO.currentEmail == currentEmployee.get().email) {
                currentEmployee.get().email = changeEmailDTO.newEmail
                accountRepository.save(currentEmployee.get())
            }
            else {
                log.error('Invalid current email')
            }
        }
        else {
            log.error('User not found :/')
        }
    }

}
