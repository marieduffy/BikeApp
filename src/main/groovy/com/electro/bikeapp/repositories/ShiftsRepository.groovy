package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.ShiftsDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShiftsRepository extends JpaRepository <ShiftsDomain, Long> {

    Optional<ShiftsDomain> findByEmployeeId(long employeeId)

}
