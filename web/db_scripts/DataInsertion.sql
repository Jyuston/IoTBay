INSERT INTO ACCOUNTS (EMAIL, F_NAME, L_NAME, CONTACT_NUMBER, ACCOUNT_TYPE, PASSWORD, IS_ACTIVE)
VALUES ('john.smith@gmail.com', 'John', 'Smith', '0438 278 971', 'C', 'password123', true),
       ('n.rivers@gmail.ca', 'Nehru', 'Rivers', '0438 278 972', 'C', 'password123', true),
       ('craig.e@gmail.com', 'Craig', 'Estrada', '0438 278 973', 'C', 'password123', true),
       ('Judahs.e@hotmail.com', 'Judah', 'Erickson', '0438 278 975', 'C', 'password123', true),
       ('Hakeem@hotmail.com', 'Hakeem', 'Townsend', '0493 750 271', 'C', 'password123', true),
       ('Leroy@hotmail.com', 'Leroy', 'Hoffman', '0438 278 976', 'C', 'password123', true),
       ('Zachery@hotmail.com', 'Zachery', 'Acosta', '0438 278 977', 'C', 'password123', true),
       ('Upton@gmail.com', 'Upton', 'Phelps', '0438 278 978', 'C', 'password123', true),
       ('barrett.lewis@gmail.net', 'Barrett', 'Lewis', '0438 278 979', 'C', 'password123', true),
       ('Stuart@gmail.com', 'Stuart', 'Kramer', '0438 278 980', 'C', 'password123', true),
       ('Tyler@Morbinon.com', 'Tyler', 'Owens', '0438 278 981', 'C', 'password123', true),
       ('nicholsonK@iotbay.com', 'Kirby', 'Nicholson', '0404 285 927', 'S', 'testpass1', true),
       ('farleyS@iotbay.com', 'Lenore', 'Farley', '0403 038 516', 'S', 'anotherpass1', true),
       ('talleyS@iotbay.com', 'Stephanie', 'Talley', '0404 477 143', 'S', 'mypassword4321', true),
       ('admin@test.com', 'Mike', 'Wozowski', '0123 456 789', 'S', 'lol123', true),
       ('davisesS@iotbay.com', 'Steven', 'Davies', '0404 285 921', 'S', 'testpass1', true),
       ('tomphsonA@iotbay.com', 'Andrew', 'Tomphson', '0404 221 664', 'S', 'testpass1', true),
       ('nicholsonK@iotbay.com', 'Kirby', 'Nicholson', '0404 285 927', 'S', 'testpass1', true),
       ('johnk@gmail.com', 'John', 'Krasinki', '0438 213 552', 'C', 'password123', true),
       ('mike.scott@yahoo.com', 'Michael', 'Scott', '0438 213 111', 'C', 'password123', true)
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
       (11, '3/46', 'Wonder St', 'Maroubra', '2035', 'NSW', false),
       (19, '54', 'Brisbane St', 'Brisbane', '3045', 'QLD', false),
       (20, '12', 'Market St', 'Sydney', '2000', 'NSW', false)
;

INSERT INTO STAFF (ID, IS_ADMIN)
VALUES (12, true),
       (13, false),
       (14, false),
       (15, true),
       (16, false),
       (17, false),
       (18, true)
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
        (15,'2020-01-03 15:42:40', 'logout'),
        (13,'2020-01-03 15:42:40', 'create_account'),
        (13,'2020-01-03 15:43:40', 'login'),
        (13,'2020-01-03 15:44:53', 'edit'),
        (13,'2020-01-03 15:46:20', 'edit'),
        (13,'2020-01-03 15:47:34', 'logout'),
        (13,'2020-01-04 05:22:10', 'login'),
        (13,'2020-01-03 06:57:43', 'logout')
;

INSERT INTO PAYMENT_INFORMATION (CUSTOMER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR)
VALUES (1, '5244 8746 5799 2270', '832', '04', '2021'),
       (2, '5244 8746 5799 2271', '858', '04', '2022'),
       (3, '5244 8746 5799 2272', '863', '06', '2023'),
       (4, '5244 8746 5799 2273', '287', '07', '2020'),
       (5, '5244 8746 5799 2274', '509', '11', '2020'),
       (6, '5244 8746 5799 2275', '771', '01', '2020'),
       (7, '5483 5394 8183 4028', '174', '04', '2020'),
       (8, '5244 8746 5799 2277', '184', '03', '2020'),
       (9, '2244 8746 5799 2270', '837', '04', '2020'),
       (10, '5244 8746 5799 2211', '817', '02', '2020'),
       (11, '5244 8746 5799 2321', '908', '04', '2021'),
       (19, '5244 8746 5799 1123', '908', '04', '2021'),
       (20, '5244 2321 5799 2270', '908', '04', '2021')
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
       ('0.5 Microfarad capacitor', 20, 0.50, 'Capacitors', 'This is a microfarad capacitor', false),
       ('0.7 Ohm Resistor', 10, 0.70, 'Resistors', 'This is a 0.7 ohm resistor', false),
       ('0.5 Force Sensitive Resistor', 25, 8.50, 'Resistors',
        'This is a force sensitive resistor with a round, 0.5" diameter, sensing area.', false),
       ('2.5 Microfarad capacitor', 20, 1.50, 'Capacitors', 'This is a microfarad capacitor', false),
       ('1/4W Resistor Kit', 30, 12.00, 'Resistors', 'Resistor kit comes with 25 each of 20 different resistor values', false),
       ('Ceramic Capacitor', 16, 3.50, 'Capacitors', 'This is a ceramic capacitor', false),
       ('Tantalum Capacitor', 20, 4.00, 'Capacitors', 'This is a tantalum capacitor', false),
       ('Plastic Film Capacitor', 20, 1.50, 'Capacitors', 'This is a plastic film capacitor', false),
       ('Aluminium Capacitor', 30, 5.50, 'Capacitors', 'This is a aluminium capacitor', false),
       ('Electrolytic Capacitor', 40, 6.00, 'Capacitors', 'This is a electrolytic capacitor', false),
       ('Carbon Film Resistor', 20, 0.50, 'Resistors', 'This is a carbon film resistor', false),
       ('Metal Film Resistor', 10, 1.50, 'Resistors', 'This is a metal film resistor', false),
       ('Wirewound Resistor', 20, 4.50, 'Resistors', 'This is a wirewound resistor', false),
       ('Power Resistor', 50, 9.50, 'Resistors', 'This is a power resistor', false),
       ('Load Resistor', 90, 10.00, 'Resistors', 'This is a load resistor', false),
       ('Voltage LED Load Resistor', 20, 1.50, 'Resistors', 'This is a voltage led load resistor', false),
       ('High Temp Electrolytic Capacitor', 80, 7.00, 'Capacitors', 'This is a high temp capacitor', false),
       ('Microfarad Run Capacitor', 20, 1.50, 'Capacitors', 'This is a microfarad run capacitor', false),
       ('Plastic Capacitor', 10, 0.50, 'Capacitors', 'This is a plastic capacitor', false),
       ('Colour Sensor', 18, 12.80, 'Sensors', 'RGB color sensor', false),
       ('Light Sensor', 23, 7.99, 'Sensors', 'Photon (light) sensor', false),
       ('12V 100mm Linear Actuator', 3, 24.99, 'Actuators', '12V 50-100mm Linear Actuator', false)
;

INSERT INTO ORDERS (ORDERED_ON, SHIPPING_ADDRESS, STATUS, TOTAL, CUSTOMER_ID, TRACKING_ID)
VALUES ('2020-01-01 00:00:05', '21 Shipping Ave--Waterloo-2017-NSW', 'approved', 23.99, 2, '0'),
       ('2020-03-04 00:01:05', '30 Shipping Ave--Waterloo-3105-QLD', 'approved', 28.00, 2, '1'),
       ('2020-06-05 00:04:05', '21 Madison Ave--Wetherill-2164-NSW', 'approved', 24.00, 3, '2'),
       ('2020-09-10 00:06:05', '54 Lexington St--Gregory-2570-NSW', 'approved', 23.99, 5, '3'),
       ('2020-01-12 00:03:05', '66 Dakota Ave--Emerald-2174-NSW', 'approved', 29.00, 6, '4'),
       ('2020-02-01 00:06:05', '78 Renwick St--Gledswood-2570-NSW', 'approved', 30.00, 9, '5'),
       ('2020-06-01 00:07:05', '29 Belmont Ave--Edmondson-2174-NSW', 'approved', 25.99, 8, '6'),
       ('2020-08-01 00:04:05', '28 Lincoln Ave--Willowdale-2174-NSW', 'approved', 29.99, 7, '8'),
       ('2020-08-01 00:04:05', '23 Columbus St--Oran-2570-NSW', 'approved', 12.00, 8, '11'),
       ('2020-09-01 00:04:05', '30 Charlton St--Harrington-2570-NSW', 'approved', 14.99, 8, '12'),
       ('2020-10-01 00:04:05', '27 Franklin St--Catherine-2570-NSW', 'approved', 42.99, 6, '13'),
       ('2020-12-01 00:04:05', '22 Emerson St--Ponds-2164-NSW', 'approved', 32.99, 7, '14'),
       ('2020-04-01 00:04:05', '30 Maddox St--Field-2570-NSW', 'approved', 33.99, 7, '15'),
       ('2020-05-01 00:04:05', '21 Renwick St--Ed-2570-NSW', 'approved', 66.99, 2, '16'),
       ('2020-06-01 00:04:05', '33 Easton St--Gregory-2570-NSW', 'approved', 88.99, 5, '17'),
       ('2020-07-01 00:04:05', '29 Easton St--Catherine-2570-NSW', 'approved', 77.99, 3, '18'),
       ('2020-03-01 00:04:05', '88 Brookfield St--Willowdale-2570-NSW', 'approved', 25.99, 4, '19'),
       ('2020-07-01 00:04:05', '55 Fairwater St--Hermitage-2570-NSW', 'approved', 35.99, 7, '20'),
       ('2020-07-01 00:04:05', '36 Mike St--Gledswood-2570-NSW', 'approved', 67.99, 4, '21'),
       ('2020-08-01 00:04:05', '69 Easton St--Cacl-2570-NSW', 'approved', 68.99, 3, '22')
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
