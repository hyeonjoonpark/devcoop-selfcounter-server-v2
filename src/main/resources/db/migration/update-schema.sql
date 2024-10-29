ALTER TABLE receipt_item
DROP
FOREIGN KEY FK9pfwh8ualce7kj9srgv0hcme6;

ALTER TABLE receipt_item
DROP
FOREIGN KEY FKj76issu1h4ri95eg535qq1510;

ALTER TABLE occount_items
    ADD item_id BIGINT NULL;

ALTER TABLE common_user
    ADD refresh_token VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_code VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_email VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_finger_print VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_name VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_number VARCHAR(255) NOT NULL;

ALTER TABLE common_user
    ADD user_password VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_pin VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_point INT NULL;

ALTER TABLE common_user
    ADD PRIMARY KEY (user_number);

ALTER TABLE occount_items
    ADD PRIMARY KEY (item_id);

DROP TABLE common_investment;

DROP TABLE common_oneTimeToken;

DROP TABLE common_student;

DROP TABLE occount_chargeLog;

DROP TABLE occount_inventory;

DROP TABLE occount_inventorySnapshots;

DROP TABLE occount_itemScans;

DROP TABLE occount_kioskReceipts;

DROP TABLE occount_payLog;

DROP TABLE occount_receipt;

DROP TABLE oring_agenda;

DROP TABLE oring_conference;

DROP TABLE oring_vote;

DROP TABLE receipt_item;

ALTER TABLE occount_items
DROP
COLUMN itemCategory;

ALTER TABLE occount_items
DROP
COLUMN itemCode;

ALTER TABLE occount_items
DROP
COLUMN itemExplain;

ALTER TABLE occount_items
DROP
COLUMN itemId;

ALTER TABLE occount_items
DROP
COLUMN itemName;

ALTER TABLE occount_items
DROP
COLUMN itemPrice;

ALTER TABLE occount_items
DROP
COLUMN itemQuantity;

ALTER TABLE occount_items
DROP
COLUMN receipt_id;

ALTER TABLE occount_items
DROP
COLUMN event;

ALTER TABLE occount_items
DROP
COLUMN item_category;

ALTER TABLE occount_items
DROP
COLUMN item_code;

ALTER TABLE occount_items
DROP
COLUMN item_explain;

ALTER TABLE occount_items
DROP
COLUMN item_image;

ALTER TABLE common_user
DROP
COLUMN `role`;

ALTER TABLE common_user
DROP
COLUMN userAddress;

ALTER TABLE common_user
DROP
COLUMN userCiNumber;

ALTER TABLE common_user
DROP
COLUMN userCode;

ALTER TABLE common_user
DROP
COLUMN userEmail;

ALTER TABLE common_user
DROP
COLUMN userFingerPrint;

ALTER TABLE common_user
DROP
COLUMN userName;

ALTER TABLE common_user
DROP
COLUMN userNumber;

ALTER TABLE common_user
DROP
COLUMN userPassword;

ALTER TABLE common_user
DROP
COLUMN userPhone;

ALTER TABLE common_user
DROP
COLUMN userPin;

ALTER TABLE common_user
DROP
COLUMN userPoint;

ALTER TABLE common_user
DROP
COLUMN roles;

ALTER TABLE occount_kiosk_receipts
DROP
COLUMN user_code;

ALTER TABLE occount_kiosk_receipts
DROP
COLUMN event_type;

ALTER TABLE occount_kiosk_receipts
DROP
COLUMN sale_type;

ALTER TABLE occount_items
    ADD event VARCHAR(255) NULL;

ALTER TABLE occount_kiosk_receipts
    ADD event_type VARCHAR(255) NULL;

ALTER TABLE occount_pay_log
DROP
COLUMN event_type;

ALTER TABLE occount_pay_log
    ADD event_type VARCHAR(255) NULL;

ALTER TABLE occount_items
    ADD item_category VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NULL;

ALTER TABLE occount_items
    ADD item_code VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'None' NULL;

ALTER TABLE occount_items
    ADD item_explain TEXT NULL;

ALTER TABLE occount_items
    ADD item_image VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NULL;

ALTER TABLE common_user
    ADD roles VARCHAR(255) NULL;

ALTER TABLE occount_kiosk_receipts
    ADD sale_type VARCHAR(255) NULL;

ALTER TABLE occount_pay_log
    MODIFY user_code VARCHAR (255) NULL;
ALTER TABLE receipt_item
    DROP FOREIGN KEY FK9pfwh8ualce7kj9srgv0hcme6;

ALTER TABLE receipt_item
    DROP FOREIGN KEY FKj76issu1h4ri95eg535qq1510;

ALTER TABLE occount_items
    ADD item_id BIGINT NULL;

ALTER TABLE common_user
    ADD refresh_token VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_code VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_email VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_finger_print VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_name VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_number VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_password VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_pin VARCHAR(255) NULL;

ALTER TABLE common_user
    ADD user_point INT NULL;

ALTER TABLE common_user
    ADD PRIMARY KEY (user_number);

ALTER TABLE occount_items
    ADD PRIMARY KEY (item_id);

DROP TABLE common_investment;

DROP TABLE common_oneTimeToken;

DROP TABLE common_student;

DROP TABLE occount_chargeLog;

DROP TABLE occount_inventory;

DROP TABLE occount_inventorySnapshots;

DROP TABLE occount_itemScans;

DROP TABLE occount_kioskReceipts;

DROP TABLE occount_payLog;

DROP TABLE occount_receipt;

DROP TABLE oring_agenda;

DROP TABLE oring_conference;

DROP TABLE oring_vote;

DROP TABLE receipt_item;

ALTER TABLE occount_items
    DROP COLUMN itemCategory;

ALTER TABLE occount_items
    DROP COLUMN itemCode;

ALTER TABLE occount_items
    DROP COLUMN itemExplain;

ALTER TABLE occount_items
    DROP COLUMN itemId;

ALTER TABLE occount_items
    DROP COLUMN itemName;

ALTER TABLE occount_items
    DROP COLUMN itemPrice;

ALTER TABLE occount_items
    DROP COLUMN itemQuantity;

ALTER TABLE occount_items
    DROP COLUMN receipt_id;

ALTER TABLE occount_items
    DROP COLUMN event;

ALTER TABLE occount_items
    DROP COLUMN item_category;

ALTER TABLE occount_items
    DROP COLUMN item_code;

ALTER TABLE occount_items
    DROP COLUMN item_explain;

ALTER TABLE occount_items
    DROP COLUMN item_image;

ALTER TABLE common_user
    DROP COLUMN `role`;

ALTER TABLE common_user
    DROP COLUMN userAddress;

ALTER TABLE common_user
    DROP COLUMN userCiNumber;

ALTER TABLE common_user
    DROP COLUMN userCode;

ALTER TABLE common_user
    DROP COLUMN userEmail;

ALTER TABLE common_user
    DROP COLUMN userFingerPrint;

ALTER TABLE common_user
    DROP COLUMN userName;

ALTER TABLE common_user
    DROP COLUMN userNumber;

ALTER TABLE common_user
    DROP COLUMN userPassword;

ALTER TABLE common_user
    DROP COLUMN userPhone;

ALTER TABLE common_user
    DROP COLUMN userPin;

ALTER TABLE common_user
    DROP COLUMN userPoint;

ALTER TABLE common_user
    DROP COLUMN roles;

ALTER TABLE occount_kiosk_receipts
    DROP COLUMN user_code;

ALTER TABLE occount_kiosk_receipts
    DROP COLUMN event_type;

ALTER TABLE occount_kiosk_receipts
    DROP COLUMN sale_type;

ALTER TABLE occount_items
    ADD event VARCHAR(255) NULL;

ALTER TABLE occount_kiosk_receipts
    ADD event_type VARCHAR(255) NULL;

ALTER TABLE occount_pay_log
    DROP COLUMN event_type;

ALTER TABLE occount_pay_log
    ADD event_type VARCHAR(255) NULL;

ALTER TABLE occount_items
    ADD item_category VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NULL;

ALTER TABLE occount_items
    ADD item_code VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'None' NULL;

ALTER TABLE occount_items
    ADD item_explain TEXT NULL;

ALTER TABLE occount_items
    ADD item_image VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' NULL;

ALTER TABLE common_user
    ADD roles VARCHAR(255) NULL;

ALTER TABLE occount_kiosk_receipts
    ADD sale_type VARCHAR(255) NULL;

ALTER TABLE occount_pay_log
    MODIFY user_code VARCHAR(255) NULL;