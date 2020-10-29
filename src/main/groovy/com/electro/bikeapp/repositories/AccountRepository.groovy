package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.EmployeeDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends JpaRepository<EmployeeDomain, Long> {

    EmployeeDomain findByUsername(String username)
    EmployeeDomain findByPosition(String position)
}