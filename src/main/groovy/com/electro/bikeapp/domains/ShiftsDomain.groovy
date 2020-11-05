package com.electro.bikeapp.domains

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Entity
@Table(name = 'shifts_and_schedules')
class ShiftsDomain {
    @Id
    @Column(name = 'employee_id')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long employeeId

    @Column(name = 'name')
    String employeeName

    @Column(name = 'todays_date')
    OffsetDateTime todaysDate

    @Column(name = 'time_in')
    OffsetDateTime timeIn

    @Column(name = 'time_out')
    OffsetDateTime timeOut

    @Column(name = 'total_day_hours')
    Double totalDayHours

    @Column(name = 'days_off_work')
    String daysOffWork

    @Column(name = 'start_work_hours')
    String startWorkHours

    @Column(name = 'end_work_hours')
    String endWorkHours

    @Column(name = 'total_weekly_hours')
    Float totalWeeklyHours

    @Column(name = 'request_days_off')
    String requestMessage
}
