package com.electro.bikeapp.controllers

import com.electro.bikeapp.dtos.ChangePasswordDTO
import com.electro.bikeapp.dtos.LoginCredentialsDTO
import com.electro.bikeapp.services.AccountService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@Slf4j
class AccountController {

    @Autowired
    AccountService accountService

    /**
     * POST - login user
     * @requestBody JSON user login credentials
     * @param LoginCredentialsDTO
     * @return boolean
     */
    @PostMapping(value = '/userLogin', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    boolean login(@RequestBody LoginCredentialsDTO loginCredentials) {
        log.info 'Verifying Login Credentials'
        log.info(loginCredentials.password)
        accountService.verifyCredentials(loginCredentials)
    }

    /**
     * POST - Change Password
     * @requestBody JSON new login credentials
     * @param ChangePasswordDTO
     * @return void
     */
    @PatchMapping(value = '/account/changePassword', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void changePassword(@RequestBody ChangePasswordDTO changePassCredentials) {
        log.info 'Changing Password'
        accountService.changePassword(changePassCredentials)
    }

    //TODO: Lili /account/changeEmail

    //TODO: Lili /account/timeSheet

    //TODO: Lili /account/shiftChart
}
