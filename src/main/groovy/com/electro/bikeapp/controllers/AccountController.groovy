package com.electro.bikeapp.controllers

import com.electro.bikeapp.dtos.ChangePasswordDTO
import com.electro.bikeapp.dtos.ChangeEmailDTO
import com.electro.bikeapp.dtos.LoginCredentialsDTO
import com.electro.bikeapp.services.AccountService
import com.electro.bikeapp.services.EmployeeService
import com.electro.bikeapp.services.ShiftsService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import java.time.OffsetDateTime


@RestController
@Slf4j
class AccountController {

    @Autowired
    AccountService accountService

    @Autowired
    EmployeeService employeeService

    @Autowired
    ShiftsService shiftsService

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
        log.info(loginCredentials.username)
        //accountService.verifyCredentials(loginCredentials)
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
    @PatchMapping(value = '/account/changeEmail', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void changeEmail(@RequestBody ChangeEmailDTO changeToNewEmail) {
        log.info 'Changing email'
        accountService.changeEmail(changeToNewEmail)
    }

    //TODO: Lili /account/timeSheet
    //time in
    @GetMapping(value = '/account/timeIn/{employeeId}', produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    OffsetDateTime clockIn(@PathVariable long employeeId) {
        log.info 'Clock in / out times are recorded'
        shiftsService.clockIn(employeeId)
    }
//
//    @PostMapping(value = '/account/timeOut', produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    OffsetDateTime clockOut(@RequestBody TimesDTO timesDTO) {
//        log.info 'Clock in / out times are recorded'
//        shiftsService.clockOutTime(timesDTO)
//    }
//
//    @PostMapping(value = '/account/totalTime', produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    void getTotalTime(@RequestBody TimesDTO timesDTO) {
//        log.info 'Clock in / out times are recorded'
//        shiftsService.totalDayTime(timesDTO)
//    }

    //TODO: Lili /account/shiftChart
//    @GetMapping(value = '/account/shiftChart', produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    boolean getShiftChart(@RequestBody LoginCredentialsDTO loginCredentials) {
//        log.info 'Verifying Login Credentials'
//        employeeService.getShiftChart(loginCredentials)
//    }
}
