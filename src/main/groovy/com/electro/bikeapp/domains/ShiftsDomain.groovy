package com.electro.bikeapp.domains

import org.hibernate.annotations.CreationTimestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.sql.Timestamp
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

    @CreationTimestamp
    @Column(name = 'todays_date')
    OffsetDateTime todaysDate

    @Column(name = 'time_in')
    Timestamp timeIn

    @Column(name = 'time_out')
    Timestamp timeOut

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

    @Column(name = 'request_message')
    String requestMessage
}
