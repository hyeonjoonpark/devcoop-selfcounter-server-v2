package devcoop.hyunjoon.selfcounter.global.utils

class ApiPath {
    companion object {
        private const val COUNTER_BASE_API_URL = "/kiosk/v2"
        const val COUNTER_USER_API_URL = "$COUNTER_BASE_API_URL/user"
        const val COUNTER_ITEM_API_URL = "$COUNTER_BASE_API_URL/item"
        const val COUNTER_PAYMENT_API_URL = "$COUNTER_BASE_API_URL/payment"
        const val COUNTER_PAYLOG_API_URL = "$COUNTER_BASE_API_URL/paylog"
        const val COUNTER_RECEIPT_API_URL = "$COUNTER_BASE_API_URL/receipt"
        const val COUNTER_INVENTORY_API_URL = "$COUNTER_BASE_API_URL/inventory"

        const val COUNTER_TRANSACTION_API_URL = "$COUNTER_BASE_API_URL/transaction"
    }
}