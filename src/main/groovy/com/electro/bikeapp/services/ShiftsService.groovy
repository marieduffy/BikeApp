package com.electro.bikeapp.services

import com.electro.bikeapp.domains.ShiftsDomain
import com.electro.bikeapp.dtos.TimesDTO
import com.electro.bikeapp.repositories.ShiftsRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class ShiftsService {

    @Autowired
    ShiftsRepository shiftsRepository

    Date today (TimesDTO timesDTO){
        return timesDTO.todaysDate
    }

//if this does what I think it does or what I'm trying to get it do then it should set that employees clock in time and save it
    void clockInTime (TimesDTO timesDTO){
        ShiftsDomain currentEmployee = shiftsRepository.findById(timesDTO.employeeId)
        currentEmployee.timeIn = timesDTO.clockIn
        shiftsRepository.save(currentEmployee)
        log.info("the employee has clocked in")
    }

    //this should save their clock out time
    void clockOutTime (TimesDTO timesDTO){
        ShiftsDomain currentEmployee = shiftsRepository.findById(timesDTO.employeeId)
        currentEmployee.timeOut = timesDTO.clockOut
        shiftsRepository.save(currentEmployee)
        log.info("the employee has clocked out")

    }

    //this should return how much they worked in a day
    double totalDayTime (TimesDTO timesDTO){ //still needs working on
        ShiftsDomain currentEmployee = shiftsRepository.findById(timesDTO.employeeId)
        currentEmployee.totalDayHours = timesDTO.clockOut.getTime() - timesDTO.clockIn.getTime()
        double hoursWorked = currentEmployee.totalDayHours / 3600000
        shiftsRepository.save(currentEmployee)
        log.info("the total time worked has been recorded")

        return hoursWorked
    }

}
