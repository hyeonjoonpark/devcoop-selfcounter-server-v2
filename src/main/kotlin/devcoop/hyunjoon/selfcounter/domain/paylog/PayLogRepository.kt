package devcoop.hyunjoon.selfcounter.domain.paylog

import org.springframework.data.jpa.repository.JpaRepository

interface PayLogRepository : JpaRepository<PayLog, Int> {

}