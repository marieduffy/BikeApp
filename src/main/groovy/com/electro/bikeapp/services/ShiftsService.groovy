package com.electro.bikeapp.services

import com.electro.bikeapp.domains.ShiftsDomain
import com.electro.bikeapp.repositories.ShiftsRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.sql.Time
import java.text.Format
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.temporal.ChronoField;
import java.time.OffsetTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Slf4j
@Service
class ShiftsService {

    @Autowired
    ShiftsRepository shiftsRepository

    OffsetDateTime clockIn (long employeeId){
        try {
            ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
            OffsetDateTime currentTime = OffsetDateTime.now()
            currentEmployee.timeIn = currentTime
            shiftsRepository.save(currentEmployee)
            log.info("Employee $employeeId has clocked in")
            return currentTime
        } catch(NullPointerException exception){ //if that employeeId doesn't exist then print to console
            log.error("There is no employee with that id $employeeId")
        }

    }

    OffsetDateTime clockOut (long employeeId){
        try {
            ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
            OffsetDateTime currentTime = OffsetDateTime.now() //current time is being set to the current time
            currentEmployee.timeOut = currentTime
            shiftsRepository.save(currentEmployee)
            log.info("Employee $employeeId has clocked in")
            return currentTime
        } catch(NullPointerException exception){ //if that employeeId doesn't exist then print to console
            log.error("There is no employee with that id $employeeId")
        }

    }

    OffsetTime totalDayTime (long employeeId) {
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        OffsetDateTime timeIn = currentEmployee.timeIn
        OffsetDateTime timeOut = currentEmployee.timeOut
        long totalSeconds = timeOut.getLong(ChronoField.SECOND_OF_DAY) - timeIn.getLong(ChronoField.SECOND_OF_DAY)
        int seconds = totalSeconds
        int sec = seconds % 60
        int hr = seconds / 60
        int min = hr % 60
        hr = hr / 60
        Time format = new Time(hr, min, sec) // this will format hr, min, sec to hr:min:sec
        String time = format.toString() + "-08:00" //add on offset so it can pass to OffsetTime
        OffsetTime total = OffsetTime.parse(time)
        currentEmployee.totalDayHours = total
        shiftsRepository.save(currentEmployee)
        return total

    }
    
}
