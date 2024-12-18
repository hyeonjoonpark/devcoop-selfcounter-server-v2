package devcoop.hyunjoon.selfcounter.domain.item

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import jakarta.persistence.*
import java.time.LocalDate
import org.hibernate.annotations.DynamicUpdate

@Entity @Table(name = "occount_items") @DynamicUpdate
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "itemId")
    val itemId: Int = 0,

    @Column(name = "itemCode", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'None'") var itemCode: String,
    @Column(name = "itemName") var itemName: String,
    @Column(name = "itemExplain", columnDefinition = "text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci") var itemExplain: String,
    @Column(name = "itemPrice", nullable = false, columnDefinition = "int DEFAULT 0") var itemPrice: Int,
    @Column(name = "itemCategory", length = 45, columnDefinition = "varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ''") var itemCategory: String,
    @Column(name = "itemQuantity", nullable = false, columnDefinition = "int DEFAULT 0") var itemQuantity: Int,
    @Column(name = "event") @Enumerated(value = EnumType.STRING) var event: EventType = EventType.NONE,
    @Column(name = "eventStartDate") var eventStartDate: LocalDate = LocalDate.now(),
    @Column(name = "eventEndDate") var eventEndDate: LocalDate = LocalDate.now(),
    @Column(name = "itemImage", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ''") var itemImage: String,
)
