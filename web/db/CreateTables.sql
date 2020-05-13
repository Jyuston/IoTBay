CREATE TABLE Account (
    "userID" VARCHAR(50),
    "userEmail" VARCHAR(50),
    "userFName" VARCHAR(20),
    "userLName" VARCHAR(20),
    "accountType" VARCHAR(1),
    PRIMARY KEY (userID)
);

CREATE TABLE ORDER {
	"orderID" VARCHAR(50), 
	"orderDateTime" VARCHAR(19),
	"orderShippingAddress" VARCHAR(50),
	"orderTotal" INTEGER(50),
	"userID" VARCHAR(50),
	"trackingID" VARCHAR(30),
	"cardNumber" VARCHAR(16),
	PRIMARY KEY (orderID),
	FOREIGN KEY (userID),
	FOREIGN KEY (cardNumber)
};

CREATE TABLE ORDERLINE {
	"orderID" VARCHAR(50), 
	"productID" VARCHAR(50),
	"quantityOrdered" VARCHAR(255),
	"productPrice" DECIMAL(999),
	FOREIGN KEY (orderID),
	FOREIGN KEY(productID)
};

CREATE TABLE PRODUCT {
	"productID" VARCHAR(50),
	"productName" VARCHAR(255),
	"productStock" VARCHAR(255),
	"productPrice" DECIMAL(999),
	"productCategory" VARCHAR(100),
	"archived" VARCHAR(1),
     PRIMARY KEY(productID)
     };
