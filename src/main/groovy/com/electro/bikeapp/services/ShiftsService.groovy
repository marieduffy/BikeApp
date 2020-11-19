package com.electro.bikeapp.services

import com.electro.bikeapp.domains.ShiftsDomain
import com.electro.bikeapp.dtos.RequestsDTO
import com.electro.bikeapp.dtos.ShiftsDTO
import com.electro.bikeapp.repositories.ShiftsRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Time
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.temporal.ChronoField
import java.time.OffsetTime

@SuppressWarnings(['UnnecessaryElseStatement'])
@Slf4j
@Service
class ShiftsService {

    @Autowired
    ShiftsRepository shiftsRepository

    OffsetDateTime clockIn (long employeeId) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        if (currentEmployee.isPresent()) {
            OffsetDateTime currentTime = OffsetDateTime.now()
            currentEmployee.get().timeIn = currentTime
            shiftsRepository.save(currentEmployee.get())
            return currentTime
        }
        else {
            log.error("Employee with ID: $employeeId does not exist")
        }
    }

    OffsetDateTime clockOut (long employeeId) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        if (currentEmployee.isPresent()) {
            OffsetDateTime currentTime = OffsetDateTime.now() //current time is being set to the current time
            currentEmployee.get().timeOut = currentTime
            shiftsRepository.save(currentEmployee.get())
            return currentTime
        }
        else {
            log.error("Employee with ID: $employeeId does not exist")
        }
    }

    OffsetTime totalDayTime (long employeeId) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        if (currentEmployee.isPresent()) {
            OffsetDateTime timeIn = currentEmployee.get().timeIn
            OffsetDateTime timeOut = currentEmployee.get().timeOut
            long totalSeconds = timeOut.getLong(ChronoField.SECOND_OF_DAY) - timeIn.getLong(ChronoField.SECOND_OF_DAY)
            int seconds = totalSeconds
            int sixty = 60
            int sec = seconds % sixty
            int hr = seconds / sixty
            int min = hr % sixty
            hr = hr / sixty
            Time format = new Time(hr, min, sec) // this will format hr, min, sec to hr:min:sec
            String time = format.toString() + '-08:00' //add on offset so it can pass to OffsetTime
            OffsetTime total = OffsetTime.parse(time)
            currentEmployee.get().totalDayHours = total
            shiftsRepository.save(currentEmployee.get())
            return total
        }
        else {
            log.error("Employee with ID: $employeeId does not exist")
        }
    }

    void timeSheet (long employeeId) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        if (currentEmployee.isPresent()) {
            LocalDate today = LocalDate.now()
            Date date = java.sql.Date.valueOf(today)
            currentEmployee.get().todaysDate = date
            log.info("Today is: $date")
            log.info("Employee $employeeId clocked in at: " + currentEmployee.get().timeIn)
            log.info('They clocked out at: ' + currentEmployee.get().timeOut)
            log.info('Working a total time of: ' + currentEmployee.get().totalDayHours)
            shiftsRepository.save(currentEmployee.get())
        }
        else {
            log.error("Employee with ID: $employeeId does not exist")
        }
    }

    // TODO: LILI
    //actually this is not done because I need to add one more things
    void getShiftChart (ShiftsDTO shiftsDTO) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(shiftsDTO.employeeId)
        if (currentEmployee.isPresent()) {
            currentEmployee.get().daysWorking = shiftsDTO.daysWorking
            currentEmployee.get().enterTimes = shiftsDTO.enterTimes
            currentEmployee.get().exitTimes = shiftsDTO.exitTimes
            currentEmployee.get().totalWeeklyHours = shiftsDTO.totalWeeklyHours
            shiftsRepository.save(currentEmployee.get())
        }
        else {
            log.error("Employee with ID: $shiftsDTO.employeeId does not exist")
        }
    }

    void requestTimeOff (RequestsDTO requestsDTO) {
        Optional<ShiftsDomain> currentEmployee = shiftsRepository.findByEmployeeId(requestsDTO.employeeId)
        if (currentEmployee.isPresent()) {
            currentEmployee.get().requestMessage = requestsDTO.requestMessage
            shiftsRepository.save(currentEmployee.get())
        }
        else {
            log.error("Employee with ID: $requestsDTO.employeeId does not exist")
        }
    }

}
