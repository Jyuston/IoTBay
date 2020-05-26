INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, ACCOUNT_TYPE, PASSWORD)
VALUES
    ('U-00000001', 'john.smith@gmail.com', 'John', 'Smith', 'C', 'password123'),
    ('U-00000002', 'odio@sed.ca','Nehru','Rivers', 'C', 'password123'),
    ('U-00000003', 'Sed@iderat.edu','Craig','Estrada', 'C', 'password123'),
    ('U-00000004', 'risus.quis@tortorat.org','Judah','Erickson', 'C', 'password123'),
    ('U-00000005', 'Nullam@augue.org','Hakeem','Townsend', 'C', 'password123'),
    ('U-00000006', 'consectetuer.adipiscing@Donecnibh.org','Leroy','Hoffman', 'C', 'password123'),
    ('U-00000007', 'ante.Nunc@vestibulum.com','Zachery','Acosta', 'C', 'password123'),
    ('U-00000008', 'dis.parturient.montes@malesuadafames.edu','Upton','Phelps', 'C', 'password123'),
    ('U-00000009', 'elementum.sem@utnullaCras.net','Barrett','Lewis', 'C', 'password123'),
    ('U-00000010','vulputate.velit@quisaccumsanconvallis.com','Stuart','Kramer', 'C', 'password123'),
    ('U-00000011', 'egestas.Duis.ac@Morbinon.edu','Tyler','Owens', 'C', 'password123'),
    ('U-00000012', 'nicholsonK@iotbay.com', 'Kirby','Nicholson', 'S', 'jSev*UkN1DJr'),
    ('U-00000013', 'farleyS@iotbay.com', 'Lenore','Farley', 'S', 'ilOh%@hQ&$LZ'),
    ('U-00000014', 'talleyS@iotbay.com', 'Stephanie','Talley', 'S', 'jAupTO!0DnHt')
;

INSERT INTO CUSTOMERS (USER_ID, CONTACT_NUMBER, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, IS_ANONYMOUS)
VALUES
    ('U-00000001', '0438278971', '200 Wonder St', '', 'Maroubra', '2035', 'NSW', 'false'),
    ('U-00000002', '0438278972', '70 Axel Pde', '', 'Kensington', '2033' , 'NSW', 'false'),
    ('U-00000003', '0438278973', '60 Lovely St', '', 'Melbourne' , '2035', 'VIC', 'false'),
    ('U-00000004', '0438278974', '83 Oak St', '', 'Brisbane' , '3045', 'QLD', 'false'),
    ('U-00000005', '0438278975', '61 Markey St', '', 'Darwin' , '6504', 'NT', 'false'),
    ('U-00000006', '0438278976', '84 Small Ln', '', 'Hobart' , '9345', 'TAS', 'false'),
    ('U-00000007', '0438278977', '61 Anzac Pde', '', 'Perth' , '5678', 'WA', 'false'),
    ('U-00000008', '0438278978', '2 Capital St', '', 'Canberra' , '8458', 'ACT', 'false'),
    ('U-00000009', '0438278979', '54 Wonky St', '', 'Adelaide' , '4431', 'SA', 'false'),
    ('U-00000010', '0438278980', '45 Wonder St', '', 'Maroubra' , '2035', 'NSW', 'false'),
    ('U-00000011', '0438278981', '3/46', 'Wonder St', 'Maroubra', '2035', 'NSW', 'false')
;

INSERT INTO STAFF (USER_ID, IS_ADMIN)
VALUES
    ('U-00000012', 'false'),
    ('U-00000013', 'false'),
    ('U-00000014', 'false')
;

INSERT INTO PAYMENT_INFORMATION (USER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR)
VALUES
    ('U-00000001', '5244 8746 5799 2270','832', '04', '21'),
    ('U-00000002', '536462 6527332747','858', '04', '22'),
    ('U-00000003', '5588298188999519','863', '06', '23'),
    ('U-00000004', '5301127140287588','287', '07', '20'),
    ('U-00000005', '5315328102180159','509', '11', '20'),
    ('U-00000006', '547 25471 14513 607','771', '01', '20'),
    ('U-00000007', '5483 5394 8183 4028','174', '04', '20'),
    ('U-00000008', '550 84054 11825 588','184', '03', '20'),
    ('U-00000009', '549151 905253 2532','837', '04', '20'),
    ('U-00000010', '5477537779451487','817', '02', '20'),
    ('U-00000011','523 09152 33392 109','908', '04', '21')
;

/* Inserting Tst Product and Order data */

INSERT INTO PRODUCT_CATEGORIES (PRODUCT_CATEGORY, ARCHIVED)
VALUES
    ('Resistors', false),
    ('Capacitors', false)
;

INSERT INTO PRODUCTS (PRODUCT_ID, PRODUCT_NAME, STOCK, PRODUCT_PRICE, PRODUCT_CATEGORY, PRODUCT_DESCRIPTION, ARCHIVED)
VALUES
    ('P-0000001', '0.5 Ohm Resistor', 10, 0.20, 'Resistors', 'This is a 0.5 ohn resistor', false),
    ('P-0000002', '0.5 micro farahs capacitor', 20, 0.50, 'Capacitors', 'This is a micro farahs capacitor', false),
    ('P-0000003', '0.7 Ohm Resistor', 10, 0.70, 'Resistors', 'This is a 0.7 ohm resistor', false),
    ('P-0000004', '0.5 force sensitive resistor sensor', 25, 8.50, 'Resistor', 'This is a force sensitive resistor with a round, 0.5" diameter, sensing area.', false),
    ('P-0000005', '2.5 micro farahs capacitor', 20, 1.50, 'Capacitors', 'This is a micro farahs capacitor', false),
    ('P-0000006', '1/4W resistor kit', 30, 12.00, 'Resistors', 'Resistor kit comes with 25 each of 20 different resistor values ', false),
    ('P-0000007', 'ceramic capacitor', 22, 3.50, 'Capacitors', 'This is a ceramic capacitor', false),
    ('P-0000008', 'tantalum capacitor', 20, 4.00 'Capacitors', 'This is a tantalum capacitor', false),
    ('P-0000010', 'plastic film capacitor', 20, 1.50, 'Capacitors', 'This is a plastic film capacitor', false),
    ('P-0000010', 'aluminium capacitor', 30, 5.50, 'Capacitors', 'This is a aluminium capacitor', false),
    ('P-0000011', 'electrolytic capacitor', 40, 6.00, 'Capacitors', 'This is a electrolytic capacitor', false),
    ('P-0000012', 'carbon film resistor', 20, 0.50, 'Resistors', 'This is a carbon film resistor', false),
    ('P-0000013', 'metal film resistor', 10, 1.50, 'Resistors', 'This is a metal film resistor', false),
    ('P-0000014', 'wirewound resistor', 20, 4.50, 'Resistors', 'This is a wirewound resistor', false),
    ('P-0000015', 'power resistor', 50, 9.50, 'Resistors', 'This is a power resistor', false),
    ('P-0000016', 'load resistor', 90, 10.00, 'Resistors', 'This is a load resistor', false),
    ('P-0000017', 'voltage led load resistor', 20, 1.50, 'Resistors', 'This is a voltage led load resistor', false),
    ('P-0000018', 'high temp electrolytic capacitors', 80, 7.00, 'Capacitors', 'This is a high temp capacitor', false),
    ('P-0000019', 'microfarad run capacitor', 20, 1.50, 'Capacitors', 'This is a microfarad run capacitor', false),
    ('P-0000020', 'plastic capacitor', 10, 0.50, 'Capacitors', 'This is a plastic capacitor', false)
    
;

INSERT INTO ORDERS (ORDER_ID, ORDER_DATE_TIME, ORDER_SHIPPING_ADDRESS, ORDER_TOTAL, USER_ID, TRACKING_ID, CARD_NUMBER)
VALUES
    ('O-0000001', '2020-01-01 00:00:05', '21 Shipping Ave--Waterloo-2017-NSW', 23.99, 'U-00000002', '00000000000', '5244 8746 5799 2270'),
    ('O-0000002', '2020-01-02 00:00:05', '30 Shipping Ave--Waterloo-3105-QLD', 28.00, 'U-00000002', '00000000000', '5244 8746 5799 2271'),
    
;

INSERT INTO ORDER_LINE (ORDER_ID, PRODUCT_ID, QUANTITY_ORDERED, PRODUCT_PRICE)
VALUES
    ('O-0000001', 'P-0000001', 5, 0.20),
    ('O-0000001', 'P-0000002', 8, 0.50),
    ('O-0000001', 'P-0000003', 10, 0.70),
    ('O-0000001', 'P-0000004', 2, 1.50),
    ('O-0000002', 'P-0000001', 1, 0.20),
    ('O-0000002', 'P-0000002', 2, 0.50),
    ('O-0000002', 'P-0000003', 5, 0.70),
    ('O-0000002', 'P-0000004', 2, 1.50)
;

/* Insert Dashboard Data */

INSERT INTO REPORTS (REPORT_ID, REPORT_NAME, REPORT_DESCRIPTION, REPORT_START_DATE, REPORT_END_DATE)
VALUES
    ('R-0000001', 'January 2020 Sales Report', 'Total sales for January', '2020-01-01 00:00:00', '2020-01-31 23:59:59'),
    ('R-0000002', '02 2020 Sales Report', 'Feb Sales report', '2020-02-01 00:00:00', '2020-02-29 23:59:59')
;
