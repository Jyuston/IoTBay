INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, CONTACT_NUMBER, ACCOUNT_TYPE, PASSWORD)
VALUES
    ('1', 'john.smith@gmail.com', 'John', 'Smith', '0438278971', 'C', 'password123'),
    ('2', 'odio@sed.ca','Nehru','Rivers', '0438278972', 'C', 'password123'),
    ('3', 'Sed@iderat.edu','Craig','Estrada', '0438278973', '0438278974', 'C', 'password123'),
    ('4', 'risus.quis@tortorat.org','Judah','Erickson', '0438278975', 'C', 'password123'),
    ('5', 'Nullam@augue.org','Hakeem','Townsend', '0493750271', 'C', 'password123'),
    ('6', 'consectetuer.adipiscing@Donecnibh.org','Leroy','Hoffman', '0438278976', 'C', 'password123'),
    ('7', 'ante.Nunc@vestibulum.com','Zachery','Acosta', '0438278977', 'C', 'password123'),
    ('8', 'dis.parturient.montes@malesuadafames.edu','Upton','Phelps', '0438278978', 'C', 'password123'),
    ('9', 'elementum.sem@utnullaCras.net','Barrett','Lewis', '0438278979', 'C', 'password123'),
    ('10','vulputate.velit@quisaccumsanconvallis.com','Stuart','Kramer', '0438278980', 'C', 'password123'),
    ('11', 'egestas.Duis.ac@Morbinon.edu','Tyler','Owens', '0438278981', '0438289530', 'C', 'password123'),
    ('12', 'nicholsonK@iotbay.com', 'Kirby','Nicholson', '0404285927', 'S', 'jSev*UkN1DJr'),
    ('13', 'farleyS@iotbay.com', 'Lenore','Farley', '04030385162', 'S', 'ilOh%@hQ&$LZ'),
    ('14', 'talleyS@iotbay.com', 'Stephanie','Talley', '0404477143', 'S', 'jAupTO!0DnHt')
;

INSERT INTO CUSTOMERS (USER_ID, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, IS_ANONYMOUS)
VALUES
    ('1', '200 Wonder St', '', 'Maroubra', '2035', 'NSW', 'false'),
    ('2', '70 Axel Pde', '', 'Kensington', '2033' , 'NSW', 'false'),
    ('3', '60 Lovely St', '', 'Melbourne' , '2035', 'VIC', 'false'),
    ('4', '83 Oak St', '', 'Brisbane' , '3045', 'QLD', 'false'),
    ('5', '61 Markey St', '', 'Darwin' , '6504', 'NT', 'false'),
    ('6', '84 Small Ln', '', 'Hobart' , '9345', 'TAS', 'false'),
    ('7', '61 Anzac Pde', '', 'Perth' , '5678', 'WA', 'false'),
    ('8', '2 Capital St', '', 'Canberra' , '8458', 'ACT', 'false'),
    ('9', '54 Wonky St', '', 'Adelaide' , '4431', 'SA', 'false'),
    ('10', '45 Wonder St', '', 'Maroubra' , '2035', 'NSW', 'false'),
    ('11', '3/46', 'Wonder St', 'Maroubra', '2035', 'NSW', 'false')
;

INSERT INTO STAFF (USER_ID, IS_ADMIN)
VALUES
    ('12', 'false'),
    ('13', 'false'),
    ('14', 'false')
;

INSERT INTO PAYMENT_INFORMATION (USER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR)
VALUES
    ('1', '5244 8746 5799 2270','832', '04', '21'),
    ('2', '536462 6527332747','858', '04', '22'),
    ('3', '5588298188999519','863', '06', '23'),
    ('4', '5301127140287588','287', '07', '20'),
    ('5', '5315328102180159','509', '11', '20'),
    ('6', '547 25471 14513 607','771', '01', '20'),
    ('7', '5483 5394 8183 4028','174', '04', '20'),
    ('8', '550 84054 11825 588','184', '03', '20'),
    ('9', '549151 905253 2532','837', '04', '20'),
    ('10', '5477537779451487','817', '02', '20'),
    ('11','523 09152 33392 109','908', '04', '21')
;

/* Inserting Tst Product and Order data */

INSERT INTO PRODUCT_CATEGORIES (PRODUCT_CATEGORY, ARCHIVED)
VALUES
    ('Resistors', false),
    ('Capacitors', false)
;

INSERT INTO PRODUCTS (PRODUCT_ID, PRODUCT_NAME, STOCK, PRODUCT_PRICE, PRODUCT_CATEGORY, PRODUCT_DESCRIPTION, ARCHIVED)
VALUES
    ('1', '0.5 Ohm Resistor', 10, 0.20, 'Resistors', 'This is a 0.5 ohn resistor', false),
    ('2', '0.5 micro farahs capacitor', 20, 0.50, 'Capacitors', 'This is a micro farahs capacitor', false),
    ('3', '0.7 Ohm Resistor', 10, 0.70, 'Resistors', 'This is a 0.7 ohm resistor', false),
    ('4', '0.5 force sensitive resistor sensor', 25, 8.50, 'Resistor', 'This is a force sensitive resistor with a round, 0.5" diameter, sensing area.', false),
    ('5', '2.5 micro farahs capacitor', 20, 1.50, 'Capacitors', 'This is a micro farahs capacitor', false),
    ('6', '1/4W resistor kit', 30, 12.00, 'Resistors', 'Resistor kit comes with 25 each of 20 different resistor values ', false),
    ('7', 'ceramic capacitor', 22, 3.50, 'Capacitors', 'This is a ceramic capacitor', false),
    ('8', 'tantalum capacitor', 20, 4.00 'Capacitors', 'This is a tantalum capacitor', false),
    ('9', 'plastic film capacitor', 20, 1.50, 'Capacitors', 'This is a plastic film capacitor', false),
    ('10', 'aluminium capacitor', 30, 5.50, 'Capacitors', 'This is a aluminium capacitor', false),
    ('11', 'electrolytic capacitor', 40, 6.00, 'Capacitors', 'This is a electrolytic capacitor', false),
    ('12', 'carbon film resistor', 20, 0.50, 'Resistors', 'This is a carbon film resistor', false),
    ('13', 'metal film resistor', 10, 1.50, 'Resistors', 'This is a metal film resistor', false),
    ('14', 'wirewound resistor', 20, 4.50, 'Resistors', 'This is a wirewound resistor', false),
    ('15', 'power resistor', 50, 9.50, 'Resistors', 'This is a power resistor', false),
    ('16', 'load resistor', 90, 10.00, 'Resistors', 'This is a load resistor', false),
    ('17', 'voltage led load resistor', 20, 1.50, 'Resistors', 'This is a voltage led load resistor', false),
    ('18', 'high temp electrolytic capacitors', 80, 7.00, 'Capacitors', 'This is a high temp capacitor', false),
    ('19', 'microfarad run capacitor', 20, 1.50, 'Capacitors', 'This is a microfarad run capacitor', false),
    ('20', 'plastic capacitor', 10, 0.50, 'Capacitors', 'This is a plastic capacitor', false)
    
;

INSERT INTO ORDERS (ORDER_ID, ORDER_DATE_TIME, ORDER_SHIPPING_ADDRESS, ORDER_TOTAL, USER_ID, TRACKING_ID, CARD_NUMBER)
VALUES
    ('1', '2020-01-01 00:00:05', '21 Shipping Ave--Waterloo-2017-NSW', 23.99, '2', '0', '5244 8746 5799 2270'),
    ('2', '2020-03-04 00:01:05', '30 Shipping Ave--Waterloo-3105-QLD', 28.00, '2', '1', '5244 8746 5799 2271'),
    ('3', '2020-06-05 00:04:05', '21 Madison Ave--Wetherill-2164-NSW', 24.00, '3', '2', '5244 8746 5799 2270'),
    ('4', '2020-09-10 00:06:05', '54 Lexington St--Gregory-2570-NSW', 23.99, '5', '3', '5588298188999519'),
    ('5', '2020-01-12 00:03:05', '66 Dakota Ave--Emerald-2174-NSW', 29.00, '6', '4', '547 25471 14513 607'),
    ('6', '2020-02-01 00:06:05', '78 Renwick St--Gledswood-2570-NSW', 30.00, '9', '5', '549151 905253 2532'),
    ('7', '2020-06-01 00:07:05', '29 Belmont Ave--Edmondson-2174-NSW', 25.99, '8', '6', '550 84054 11825 588'),
    ('8', '2020-08-01 00:04:05', '28 Lincoln Ave--Willowdale-2174-NSW', 29.99, '7', '8', '5483 5394 8183 4028'),
    ('9', '2020-02-01 00:04:05', '30 Easton St--Catherine-2570-NSW', 33.99, '4', '9', '5301127140287588')
;

INSERT INTO ORDER_LINE (ORDER_ID, PRODUCT_ID, QUANTITY_ORDERED, PRODUCT_PRICE)
VALUES
    ('1', '1', 5, 0.20),
    ('1', '2', 8, 0.50),
    ('1', '3', 10, 0.70),
    ('1', '4', 2, 1.50),
    ('2', '1', 1, 0.20),
    ('2', '2', 1, 0.50),
    ('2', '3', 4, 0.70),
    ('3', '6', 6, 12.00)
    ('3', '7', 3, 3.50)
    ('4', '3', 4, 0.70)
    ('5', '3', 2, 0.70)
    ('6', '5', 5, 1.50)
    ('6', '6', 1, 12.00)
    ('7', '7', 5, 3.50)
    ('8', '9', 6, 1.50)
    ('8', '7', 2, 3.50)
    ('9', '3', 2, 0.70)
    ('9', '4', 5, 1.50)
    
;

/* Insert Dashboard Data */

INSERT INTO REPORTS (REPORT_ID, REPORT_NAME, REPORT_DESCRIPTION, REPORT_START_DATE, REPORT_END_DATE)
VALUES
    ('1', 'January 2020 Sales Report', 'Total sales for January', '2020-01-01 00:00:00', '2020-01-31 23:59:59'),
    ('2', '02 2020 Sales Report', 'Feb Sales report', '2020-02-01 00:00:00', '2020-02-29 23:59:59')
;
