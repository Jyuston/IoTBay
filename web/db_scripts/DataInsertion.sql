INSERT INTO ACCOUNTS (EMAIL, F_NAME, L_NAME, CONTACT_NUMBER, ACCOUNT_TYPE, PASSWORD, IS_ACTIVE)
VALUES ('john.smith@gmail.com', 'John', 'Smith', '0438 278 971', 'C', 'password123', true),
       ('odio@sed.ca', 'Nehru', 'Rivers', '0438 278 972', 'C', 'password123', true),
       ('Sed@iderat.edu', 'Craig', 'Estrada', '0438 278 973', 'C', 'password123', true),
       ('risus.quis@tortorat.org', 'Judah', 'Erickson', '0438 278 975', 'C', 'password123', true),
       ('Nullam@augue.org', 'Hakeem', 'Townsend', '0493 750 271', 'C', 'password123', true),
       ('consectetuer.adipiscing@Donecnibh.org', 'Leroy', 'Hoffman', '0438 278 976', 'C', 'password123', true),
       ('ante.Nunc@vestibulum.com', 'Zachery', 'Acosta', '0438 278 977', 'C', 'password123', true),
       ('dis.parturient.montes@malesuadafames.edu', 'Upton', 'Phelps', '0438 278 978', 'C', 'password123', true),
       ('elementum.sem@utnullaCras.net', 'Barrett', 'Lewis', '0438 278 979', 'C', 'password123', true),
       ('vulputate.velit@quisaccumsanconvallis.com', 'Stuart', 'Kramer', '0438 278 980', 'C', 'password123', true),
       ('egestas.Duis.ac@Morbinon.edu', 'Tyler', 'Owens', '0438 278 981', 'C', 'password123', true),
       ('nicholsonK@iotbay.com', 'Kirby', 'Nicholson', '0404 285 927', 'S', 'testpass1', true),
       ('farleyS@iotbay.com', 'Lenore', 'Farley', '0403 038 516', 'S', 'anotherpass1', true),
       ('talleyS@iotbay.com', 'Stephanie', 'Talley', '0404 477 143', 'S', 'mypassword4321', true),
       ('admin@test.com', 'Mike', 'Wozowski', '0123 456 789', 'S', 'lol123', true)
;

INSERT INTO CUSTOMERS (ID, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, IS_ANONYMOUS)
VALUES (1, '200 Wonder St', null, 'Maroubra', '2035', 'NSW', false),
       (2, '70 Axel Pde', null, 'Kensington', '2033', 'NSW', false),
       (3, '60 Lovely St', null, 'Melbourne', '2035', 'VIC', false),
       (4, '83 Oak St', null, 'Brisbane', '3045', 'QLD', false),
       (5, '61 Markey St', null, 'Darwin', '6504', 'NT', false),
       (6, '84 Small Ln', null, 'Hobart', '9345', 'TAS', false),
       (7, '61 Anzac Pde', null, 'Perth', '5678', 'WA', false),
       (8, '2 Capital St', null, 'Canberra', '8458', 'ACT', false),
       (9, '54 Wonky St', null, 'Adelaide', '4431', 'SA', false),
       (10, '45 Wonder St', null, 'Maroubra', '2035', 'NSW', false),
       (11, '3/46', 'Wonder St', 'Maroubra', '2035', 'NSW', false)
;

INSERT INTO STAFF (ID, IS_ADMIN)
VALUES (12, true),
       (13, false),
       (14, false),
       (15, true)
;

INSERT INTO USER_ACCESS (ACCOUNT_ID, PERFORMED_ON, ACTION)
VALUES  (1, '2020-01-01 09:34:11', 'create_account'),
        (1, '2020-01-01 09:34:21', 'login'),
        (1, '2020-01-01 09:36:23', 'edit'),
        (1, '2020-01-01 09:37:21', 'logout'),
        (2, '2020-01-04 10:37:31', 'login'),
        (2, '2020-01-04 11:43:50', 'logout'),
        (3, '2020-01-03 01:23:34', 'login'),
        (3, '2020-01-03 01:43:56', 'logout'),
        (15,'2020-01-01 11:30:30', 'create_account'),
        (15,'2020-01-01 11:30:45', 'login'),
        (15,'2020-01-01 11:33:10', 'edit'),
        (15,'2020-01-01 11:45:40', 'logout'),
        (15,'2020-01-03 15:32:20', 'login'),
        (15,'2020-01-03 15:42:40', 'logout')
;

INSERT INTO PAYMENT_INFORMATION (CUSTOMER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR)
VALUES (1, '5244 8746 5799 2270', '832', '04', '2021'),
       (2, '536462 6527332747', '858', '04', '2022'),
       (3, '5588298188999519', '863', '06', '2023'),
       (4, '5301127140287588', '287', '07', '2020'),
       (5, '5315328102180159', '509', '11', '2020'),
       (6, '547 25471 14513 607', '771', '01', '2020'),
       (7, '5483 5394 8183 4028', '174', '04', '2020'),
       (8, '550 84054 11825 588', '184', '03', '2020'),
       (9, '549151 905253 2532', '837', '04', '2020'),
       (10, '5477537779451487', '817', '02', '2020'),
       (11, '523 09152 33392 109', '908', '04', '2021')
;

INSERT INTO PRODUCT_CATEGORIES (CATEGORY, ARCHIVED)
VALUES ('Resistors', false),
       ('Capacitors', false),
       ('Actuators', false),
       ('Sensors', false),
       ('Raspberry Pi', false)
;

INSERT INTO PRODUCTS (NAME, STOCK, PRICE, CATEGORY, DESCRIPTION, ARCHIVED)
VALUES ('0.5 Ohm Resistor', 10, 0.20, 'Resistors', 'This is a 0.5 ohn resistor', false),
       ('0.5 micro farahs capacitor', 20, 0.50, 'Capacitors', 'This is a micro farahs capacitor', false),
       ('0.7 Ohm Resistor', 10, 0.70, 'Resistors', 'This is a 0.7 ohm resistor', false),
       ('0.5 force sensitive resistor sensor', 25, 8.50, 'Resistors',
        'This is a force sensitive resistor with a round, 0.5" diameter, sensing area.', false),
       ('2.5 micro farahs capacitor', 20, 1.50, 'Capacitors', 'This is a micro farahs capacitor', false),
       ('1/4W resistor kit', 30, 12.00, 'Resistors', 'Resistor kit comes with 25 each of 20 different resistor values', false),
       ('ceramic capacitor', 16, 3.50, 'Capacitors', 'This is a ceramic capacitor', false),
       ('tantalum capacitor', 20, 4.00, 'Capacitors', 'This is a tantalum capacitor', false),
       ('plastic film capacitor', 20, 1.50, 'Capacitors', 'This is a plastic film capacitor', false),
       ('aluminium capacitor', 30, 5.50, 'Capacitors', 'This is a aluminium capacitor', false),
       ('electrolytic capacitor', 40, 6.00, 'Capacitors', 'This is a electrolytic capacitor', false),
       ('carbon film resistor', 20, 0.50, 'Resistors', 'This is a carbon film resistor', false),
       ('metal film resistor', 10, 1.50, 'Resistors', 'This is a metal film resistor', false),
       ('wirewound resistor', 20, 4.50, 'Resistors', 'This is a wirewound resistor', false),
       ('power resistor', 50, 9.50, 'Resistors', 'This is a power resistor', false),
       ('load resistor', 90, 10.00, 'Resistors', 'This is a load resistor', false),
       ('voltage led load resistor', 20, 1.50, 'Resistors', 'This is a voltage led load resistor', false),
       ('high temp electrolytic capacitors', 80, 7.00, 'Capacitors', 'This is a high temp capacitor', false),
       ('microfarad run capacitor', 20, 1.50, 'Capacitors', 'This is a microfarad run capacitor', false),
       ('plastic capacitor', 10, 0.50, 'Capacitors', 'This is a plastic capacitor', false),
       ('Colour sensor', 18, 12.80, 'Sensors', 'RGB color sensor', false),
       ('Light sensor', 23, 7.99, 'Sensors', 'Photon (light) sensor', false),
       ('12V 100mm Linear Actuator', 3, 24.99, 'Actuators', '12V 50-100mm Linear Actuator', false)
;

INSERT INTO ORDERS (ORDERED_ON, SHIPPING_ADDRESS, STATUS, TOTAL, CUSTOMER_ID, TRACKING_ID)
VALUES ('2020-01-01 00:00:05', '21 Shipping Ave--Waterloo-2017-NSW', 'shipped', 23.99, 2, '0'),
       ('2020-03-04 00:01:05', '30 Shipping Ave--Waterloo-3105-QLD', 'shipped', 28.00, 2, '1'),
       ('2020-06-05 00:04:05', '21 Madison Ave--Wetherill-2164-NSW', 'shipped', 24.00, 3, '2'),
       ('2020-09-10 00:06:05', '54 Lexington St--Gregory-2570-NSW', 'shipped', 23.99, 5, '3'),
       ('2020-01-12 00:03:05', '66 Dakota Ave--Emerald-2174-NSW', 'shipped', 29.00, 6, '4'),
       ('2020-02-01 00:06:05', '78 Renwick St--Gledswood-2570-NSW', 'shipped', 30.00, 9, '5'),
       ('2020-06-01 00:07:05', '29 Belmont Ave--Edmondson-2174-NSW', 'shipped', 25.99, 8, '6'),
       ('2020-08-01 00:04:05', '28 Lincoln Ave--Willowdale-2174-NSW', 'shipped', 29.99, 7, '8'),
       ('2020-02-01 00:04:05', '30 Easton St--Catherine-2570-NSW', 'shipped', 33.99, 4, '9')
;

INSERT INTO ORDER_LINE (ORDER_ID, PRODUCT_ID, QUANTITY_ORDERED, PRICE)
VALUES (1, 1, 5, 0.20),
       (1, 2, 8, 0.50),
       (1, 3, 10, 0.70),
       (1, 4, 2, 1.50),
       (1, 20, 3, 12.80),
       (1, 21, 8, 21.85),
       (2, 1, 1, 0.20),
       (2, 2, 1, 0.50),
       (2, 3, 4, 0.70),
       (3, 6, 6, 12.00),
       (3, 7, 15, 3.50),
       (4, 3, 4, 0.70),
       (5, 3, 2, 0.70),
       (6, 5, 5, 1.50),
       (6, 6, 1, 12.00),
       (7, 7, 5, 3.50),
       (8, 9, 6, 1.50),
       (8, 7, 2, 3.50),
       (9, 3, 2, 0.70),
       (9, 4, 5, 1.50),
       (9, 20, 3, 12.80)
;

INSERT INTO REPORTS (NAME, DESCRIPTION, START_DATE, END_DATE)
VALUES ('January 2020 Sales Report', 'Total sales for January', '2020-01-01 00:00:00', '2020-01-31 23:59:59'),
       ('Q1 2020 Sales', 'Sales report for Q1 of 2020', '2020-01-01 00:00:00', '2020-03-31 23:59:59'),
       ('2019 Sales', 'Sales report for 2019', '2019-01-01 00:00:00', '2019-12-31 23:59:59'),
       ('2020 Sales', 'Sales report for 2020', '2020-01-01 00:00:00', '2020-12-31 23:59:59')
;
