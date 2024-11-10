package devcoop.hyunjoon.selfcounter.domain.paylog.repository

import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import org.springframework.data.jpa.repository.JpaRepository

interface PayLogRepository : JpaRepository<PayLog, Int> {

}