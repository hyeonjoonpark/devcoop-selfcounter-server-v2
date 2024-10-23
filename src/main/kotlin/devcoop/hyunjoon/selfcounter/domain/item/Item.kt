package devcoop.hyunjoon.selfcounter.domain.item

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDate
import devcoop.hyunjoon.selfcounter.domain.receipt.KioskReceipt

@Entity
@Table(name = "occount_items")
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "itemId")
    val itemId: Long = 0L,

    @Column(name = "itemCode", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'None'") var itemCode: String,
    @Column(name = "itemName") var itemName: String,
    @Column(name = "itemExplain", columnDefinition = "text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci") var itemExplain: String,
    @Column(name = "itemPrice", nullable = false) @ColumnDefault("0") var itemPrice: Int,
    @Column(name = "itemCategory", length = 45, columnDefinition = "varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ''") var itemCategory: String,
    @Column(name = "itemQuantity", nullable = false) @ColumnDefault("0") var itemQuantity: Int,
    @Column(name = "event") @Enumerated(value = EnumType.STRING) var event: EventType = EventType.NONE,
    @Column(name = "event_start_date") var eventStartDate: LocalDate = LocalDate.now(),
    @Column(name = "event_end_date") var eventEndDate: LocalDate = LocalDate.now(),
    @Column(name = "item_image", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT ''") var itemImage: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    var receipt: KioskReceipt,
)
