package com.electro.bikeapp.repositories

import com.electro.bikeapp.domains.RepairTicketDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepairTicketRepository extends JpaRepository<RepairTicketDomain, Long> {

}
