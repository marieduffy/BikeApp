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

@Slf4j
@Service

class ShiftsService {

    @Autowired
    ShiftsRepository shiftsRepository

    OffsetDateTime clockIn (long employeeId) {
        try {
            ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
            OffsetDateTime currentTime = OffsetDateTime.now()
            currentEmployee.timeIn = currentTime
            shiftsRepository.save(currentEmployee)
            return currentTime
        } catch (NullPointerException nullPointerException) { //if that employeeId doesn't exist then print to console
            log.error("There is no employee with that id $employeeId")
        }
    }

    OffsetDateTime clockOut (long employeeId) {
        try {
            ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
            OffsetDateTime currentTime = OffsetDateTime.now() //current time is being set to the current time
            currentEmployee.timeOut = currentTime
            shiftsRepository.save(currentEmployee)
            return currentTime
        } catch (NullPointerException nullPointerException) { //if that employeeId doesn't exist then print to console
            log.error("There is no employee with that id $employeeId")
        }
    }

    OffsetTime totalDayTime (long employeeId) {
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        OffsetDateTime timeIn = currentEmployee.timeIn
        OffsetDateTime timeOut = currentEmployee.timeOut
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
        currentEmployee.totalDayHours = total
        shiftsRepository.save(currentEmployee)
        total
    }

    void timeSheet (long employeeId) {
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        LocalDate today = LocalDate.now()
        java.util.Date date = java.sql.Date.valueOf(today)
        currentEmployee.todaysDate = date
        log.info("Today is: $date")
        log.info("Employee $employeeId clocked in at: " + currentEmployee.timeIn)
        log.info("They clocked out at: " + currentEmployee.timeOut)
        log.info("Working a total time of: " + currentEmployee.totalDayHours)
        shiftsRepository.save(currentEmployee)
    }

    int[] parseString (ShiftsDTO shiftsDTO){

    }

    void getShiftChart (ShiftsDTO shiftsDTO) {
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(shiftsDTO.employeeId)
        currentEmployee.daysWorking = shiftsDTO.daysWorking
        currentEmployee.enterTimes = shiftsDTO.enterTimes
        currentEmployee.exitTimes = shiftsDTO.exitTimes
        currentEmployee.totalWeeklyHours = shiftsDTO.totalWeeklyHours
        shiftsRepository.save(currentEmployee)

    }

    void requestTimeOff (RequestsDTO requestsDTO){
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(requestsDTO.employeeId)
        currentEmployee.requestMessage = requestsDTO.requestMessage
        shiftsRepository.save(currentEmployee)
    }

}
