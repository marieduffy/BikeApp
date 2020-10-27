package com.electro.bikeapp.services

import com.electro.bikeapp.domains.ShiftsDomain
import com.electro.bikeapp.dtos.TimesDTO
import com.electro.bikeapp.repositories.ShiftsRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.OffsetDateTime

@Slf4j
@Service
class ShiftsService {

    @Autowired
    ShiftsRepository shiftsRepository

    OffsetDateTime clockIn (long employeeId){
        try {
            ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(employeeId)
            OffsetDateTime currentTime = OffsetDateTime.now() //current time is being set to the current time
            currentEmployee.timeIn = currentTime
            shiftsRepository.save(currentEmployee)
            log.info('the employee has clocked in')
            return currentTime
        } catch(NullPointerException exception){ //if that employeeId doesn't exist then print to console
            log.error("there is no employee with that id $employeeId")
        }

    }

    //this should save their clock out time
//    OffsetDateTime clockOutTime (TimesDTO timesDTO){
//        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(timesDTO.employeeId)
//        currentEmployee.timeOut = timesDTO.clockOut
//        shiftsRepository.save(currentEmployee)
//        log.info('the employee has clocked out')
//        return timesDTO.clockOut
//    }

    //this should return how much they worked in a day
//    void totalDayTime (TimesDTO timesDTO){ //still needs working on
//        ShiftsDomain currentEmployee = shiftsRepository.findByEmployeeId(timesDTO.employeeId)
//        OffsetDateTime timeIN = currentEmployee.timeIn
//        OffsetDateTime timeOUT = currentEmployee.timeOut
//        OffsetDateTime totalTime = timeOUT.minusHours(timeIN.getHour())
//        log.info('total time $totalTime')
//    }

}
