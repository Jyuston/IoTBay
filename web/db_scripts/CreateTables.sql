DROP TABLE STAFF;
DROP TABLE CUSTOMERS;
DROP TABLE ORDER_LINE;
DROP TABLE ORDERS;
DROP TABLE PRODUCTS;
DROP TABLE PRODUCT_CATEGORIES;
DROP TABLE PAYMENT_INFORMATION;
DROP TABLE ACCOUNTS;
DROP TABLE REPORTS;

CREATE TABLE ACCOUNTS
(
    "ID"             INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "EMAIL"          VARCHAR(50),
    "F_NAME"         VARCHAR(20),
    "L_NAME"         VARCHAR(20),
    "CONTACT_NUMBER" VARCHAR(12),
    "ACCOUNT_TYPE"   CHARACTER,
    "PASSWORD"       VARCHAR(50),
    "IS_ACTIVE"      BOOLEAN
);

CREATE TABLE CUSTOMERS
(
    "ID"             INTEGER PRIMARY KEY REFERENCES ACCOUNTS(ID) ON DELETE CASCADE,
    "ADDRESS_LINE_1" VARCHAR(50),
    "ADDRESS_LINE_2" VARCHAR(50),
    "SUBURB"         VARCHAR(50),
    "STATE"          VARCHAR(3),
    "POSTCODE"       VARCHAR(4),
    "IS_ANONYMOUS"   BOOLEAN
);

CREATE TABLE STAFF
(
    "ID"       INTEGER PRIMARY KEY REFERENCES ACCOUNTS(ID) ON DELETE CASCADE,
    "IS_ADMIN" BOOLEAN
);

CREATE TABLE PAYMENT_INFORMATION
(
    "CUSTOMER_ID"  INTEGER PRIMARY KEY REFERENCES CUSTOMERS(ID) ON DELETE CASCADE,
    "CARD_NUMBER"  VARCHAR(20),
    "CVV_NUMBER"   VARCHAR(3),
    "EXPIRY_MONTH" VARCHAR(2),
    "EXPIRY_YEAR"  VARCHAR(4)
);

CREATE TABLE ORDERS
(
    "ID"               INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "CUSTOMER_ID"      INTEGER REFERENCES CUSTOMERS (ID) ON DELETE CASCADE,
    "DATE_TIME"        TIMESTAMP,
    "SHIPPING_ADDRESS" VARCHAR(255),
    "TOTAL"            DOUBLE,
    "TRACKING_ID"      VARCHAR(30),
    "CARD_NUMBER"      VARCHAR(50)
);

CREATE TABLE PRODUCT_CATEGORIES
(
    "CATEGORY" VARCHAR(50) PRIMARY KEY,
    "ARCHIVED" BOOLEAN
);

CREATE TABLE PRODUCTS
(
    "ID"          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "CATEGORY"    VARCHAR(50) REFERENCES PRODUCT_CATEGORIES (CATEGORY) ON DELETE CASCADE,
    "NAME"        VARCHAR(50),
    "DESCRIPTION" LONG VARCHAR,
    "STOCK"       INTEGER,
    "PRICE"       DOUBLE,
    "ARCHIVED"    BOOLEAN
);

CREATE TABLE ORDER_LINE
(
    "ORDER_ID"         INTEGER REFERENCES ORDERS (ID) ON DELETE CASCADE,
    "PRODUCT_ID"       INTEGER REFERENCES PRODUCTS (ID) ON DELETE CASCADE,
    "QUANTITY_ORDERED" INTEGER,
    "PRICE"            DOUBLE,
    PRIMARY KEY (ORDER_ID, PRODUCT_ID)
);

CREATE TABLE REPORTS
(
    "ID"          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "NAME"        VARCHAR(50),
    "DESCRIPTION" LONG VARCHAR,
    "START_DATE"  TIMESTAMP,
    "END_DATE"    TIMESTAMP
);