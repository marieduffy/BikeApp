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

//    this should return how much they worked in a day
    void totalDayTime (long employeeId){ //still needs working on
        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
        OffsetDateTime tIN = currentEmployee.timeIn
        long timeIn = tIN.getLong(ChronoField.SECOND_OF_DAY)
        OffsetDateTime tOUT = currentEmployee.timeOut
        long timeOut = tOUT.getLong(ChronoField.SECOND_OF_DAY)
        long totalHour = timeOut - timeIn
        System.out.println(totalHour)
        int seconds = totalHour
        int p1 = seconds % 60;
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        Time time = new Time(p2, p3, p1)
        System.out.print(time)
        currentEmployee.totalDayHours = time
        shiftsRepository.save(currentEmployee)

    }

}
