INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, ACCOUNT_TYPE)
VALUES
    ('U-00000001', 'john.smith@gmail.com', 'John', 'Smith', 'C'),
    ('U-00000002', 'odio@sed.ca','Nehru','Rivers', 'C'),
    ('U-00000003', 'Sed@iderat.edu','Craig','Estrada', 'C'),
    ('U-00000004', 'risus.quis@tortorat.org','Judah','Erickson', 'C'),
    ('U-00000005', 'Nullam@augue.org','Hakeem','Townsend', 'C'),
    ('U-00000006', 'consectetuer.adipiscing@Donecnibh.org','Leroy','Hoffman', 'C'),
    ('U-00000007', 'ante.Nunc@vestibulum.com','Zachery','Acosta', 'C'),
    ('U-00000008', 'dis.parturient.montes@malesuadafames.edu','Upton','Phelps', 'C'),
    ('U-00000009', 'elementum.sem@utnullaCras.net','Barrett','Lewis', 'C'),
    ('U-00000010','vulputate.velit@quisaccumsanconvallis.com','Stuart','Kramer', 'C'),
    ('U-00000011', 'egestas.Duis.ac@Morbinon.edu','Tyler','Owens', 'C'),
    ('U-00000012', 'nicholsonK@iotbay.com', 'Kirby','Nicholson', 'S'),
    ('U-00000013', 'farleyS@iotbay.com', 'Lenore','Farley', 'S'),
    ('U-00000014', 'talleyS@iotbay.com', 'Stephanie','Talley', 'S')
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

INSERT INTO STAFF (USER_ID, PASSWORD, IS_ADMIN)
VALUES
    ('U-00000012' , 'jSev*UkN1DJr', 'false'),
    ('U-00000013' , 'ilOh%@hQ&$LZ', 'false'),
    ('U-00000014' , 'jAupTO!0DnHt', 'false')
;