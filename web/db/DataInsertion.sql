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

INSERT INTO CUSTOMERS (USER_ID, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, ANNONYMOUS)
VALUES
    ('U-00000001', '200 Wonder St', '', 'Maroubra', '2035', 'NSW', 'false'),
    ('U-00000002', '70 Axel Pde', '', 'Kensington', '2033' , 'NSW', 'false'),
    ('U-00000003', '60 Lovely St', '', 'Melbourne' , '2035', 'VIC', 'false'),
    ('U-00000004', '83 Oak St', '', 'Brisban' , '3045', 'QLD', 'false'),
    ('U-00000005', '61 Markey St', '', 'Darwin' , '6504', 'NT', 'false'),
    ('U-00000006', '84 Small Ln', '', 'Hobart' , '9345', 'TAS', 'false'),
    ('U-00000007', '61 Anzac Pde', '', 'Perth' , '5678', 'WA', 'false'),
    ('U-00000008', '2 Capital St', '', 'Canberra' , '8458', 'ACT', 'false'),
    ('U-00000009', '54 Wonky St', '', 'Adelaide' , '4431', 'SA', 'false'),
    ('U-00000010', '45 Wonder St', '', 'Maroubra' , '2035', 'NSW', 'false'),
    ('U-00000011', '3/46', 'Wonder St', 'Maroubra', '2035', 'NSW', 'false')
;

INSERT INTO STAFF (USER_ID, IS_ADMIN)
VALUES
    ('U-00000012', 'false'),
    ('U-00000013', 'false'),
    ('U-00000014', 'false')
;