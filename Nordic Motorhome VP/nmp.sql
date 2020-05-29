DROP DATABASE IF EXISTS nmr;
CREATE DATABASE nmr; 

USE nmr;


DROP TABLE IF EXISTS models;
CREATE TABLE models
(
    model		VARCHAR(45)		NOT NULL	PRIMARY KEY,
	brand_name 	VARCHAR(45)		NOT NULL
);

DROP TABLE IF EXISTS types;
CREATE TABLE types
(
	type 		VARCHAR(45)		NOT NULL 	PRIMARY 	KEY UNIQUE,
    beds		INT				NOT NULL
);

DROP TABLE IF EXISTS motorhomes;
CREATE TABLE motorhomes 
(
	id					INT									NOT NULL	PRIMARY KEY 	AUTO_INCREMENT,
    model				VARCHAR(45)							NOT NULL,
    type				VARCHAR(45)							NOT NULL,
    mileage				INT									NOT NULL,
    price_per_day		FLOAT								NOT NULL,
    registration_number	VARCHAR(10)							NOT NULL	UNIQUE,
	FOREIGN KEY (model)
		REFERENCES models (model),
	FOREIGN KEY (type)
		REFERENCES types (type)
);

DROP TABLE IF EXISTS zip_codes;
CREATE TABLE zip_codes
(
	zip				VARCHAR(10)		NOT NULL		PRIMARY KEY,
    city			VARCHAR(45)		NOT NULL,
    country			VARCHAR(45)		NOT NULL
);

DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses
(
	id					INT				NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    street_name			VARCHAR(45)		NOT NULL,
    street_number		VARCHAR(5)		NOT NULL,
    apartment_number	VARCHAR(10),
    zip_code			VARCHAR(10)		NOT NULL,
    FOREIGN KEY (zip_code)
		REFERENCES zip_codes (zip)
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers
(
	id					INT				NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    first_name			VARCHAR(45)		NOT NULL,
    last_name			VARCHAR(45)		NOT NULL,
    phone_number		VARCHAR(15)		NOT NULL,
    address_id			INT				NOT NULL,
	birth_date			DATE			NOT NULL,
    drivers_license		VARCHAR(20)		NOT NULL,
    FOREIGN KEY (address_id)
		REFERENCES addresses (id)
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations 
(
	id					INT			NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
	motorhome_id		INT			NOT NULL, 
    customer_id			INT			NOT NULL, 
	payment_details		VARCHAR(45)	NOT NULL,
    start_date			DATETIME	NOT NULL, 
    end_date			DATETIME	NOT NULL, 
    distance_to_pickup	INT			NOT NULL,
    season				VARCHAR(45) NOT NULL,
    total_price 		FLOAT		NOT NULL,
    FOREIGN KEY (motorhome_id)
		REFERENCES motorhomes (id),
    FOREIGN KEY (customer_id)
		REFERENCES customers (id)
);

DROP TABLE IF EXISTS accessories;
CREATE TABLE accessories
(
	id			INT				NOT NULL	PRIMARY KEY		AUTO_INCREMENT,
    accessory	VARCHAR(45)		NOT NULL
);

DROP TABLE IF EXISTS reserved_accessories;
CREATE TABLE reserved_accessories 
(
	reservation_id	INT		NOT NULL,
    accessory_id	INT		NOT NULL,
    FOREIGN KEY (reservation_id)
		REFERENCES reservations (id)
        ON DELETE cascade,
    FOREIGN KEY (accessory_id)
		REFERENCES accessories (id)
);

INSERT INTO zip_codes VALUES
('4180','Sorø','Denmark'),
('2000','Frederiksberg','Denmark'),
('2605','Brøndby','Denmark'),
('3460','Birkerød','Denmark'),
('2880','Bagsværd','Denmark'),
('2100','Østerbro','Denmark'),
('3520','Farum','Denmark'),
('4171','Glumsø','Denmark'),
('0918','Københavns Pakkecenter','Denmark');

INSERT INTO addresses VALUES
(1, 'Falskvej', '13', NULL, '4180'),
(2, 'Storvej', '26', '3. th', '4171'),
(3, 'Mellemvej', '6', '5. tv', '2605'),
(4, 'Frederiksberg Bredegade', '13b', '337', '2000'),
(5, 'Vejgade', '69', NULL, '3460'),
(6, 'Viborggade', '35', NULL, '2100'),
(7, 'Industrivej', '1', NULL, '0918'),
(8, 'Farum Gydevej', '120', NULL, '3520');

INSERT INTO customers VALUES 
(1, 'Michael', 'Berko','+45 28 05 09 50', 7, '1998-05-28', '8490324'),
(2, 'Rasmus', 'Falk-Jensen','+45 74 01 29 12', 2, '1997-06-28', '7489324'),
(3, 'Ammad', 'Azhar','+45 87 01 39 53', 3, '1996-07-28', '8490234'),
(4, "Julius", "Christoffersen", "+45 48 02 10 23", 5, "1997-05-05", "4234234"); 

INSERT INTO customers VALUES
(5, 'Danny', 'Baker', '420420420', 5, '2001-05-20', '7i767r86e786'),
(6, 'John', 'Wick', '234876192', 8, '2005-10-07', '326vigdf7w6e'),
(7, 'Postmand', 'Per', '01110000', 7, '1981-09-16', 'f7vgw6e978ad'),
(8, 'Byggemand', 'Bob', '12345678', 3, '1983-04-26', 'bkadsfi76ds'),
(9, 'Freddy', 'Kreuger', '678123830', 1, '1977-11-26', 'g8uvw7362dg'),
(10, 'John', 'Smith', '20274527', 2, '1999-04-23', 'f976faa90hu'),
(11, 'Carl', 'Jensen', '58584657', 6, '2007-01-15', 'sd78f02nofd'),
(12, 'Boris', 'Johnson', '555695833', 4, '1990-07-28', 'ag7g83jkahds');

INSERT INTO models VALUES
("430 LE", "Malibu"),
("490 LE", "Malibu"),
("A 68", "Sunlight"),
("T 67", "Sunlight"),
("Cliff XV 640", "Malibu"),
("Masterline 780", "Hymer"),
("Exis 580", "Hymer"),
("361", "Caroda"),
("Ducato 14", "Fiat"),
("447", "Carado"),
("Pla Plasy", "Fiat"),
("500", "Hobby");

INSERT INTO types VALUES
("3P Standard", 3),
("4P Standard", 4),
("4P Luksus", 4),
("5P Standard", 5),
("5P Luksus", 5),
("6P Standard", 6),
("6P Luksus", 6),
("8P Standard", 8);

INSERT INTO motorhomes VALUES
(1, "430 LE", 			"4P Standard", 	237582, 549.5, "JE 78 019"),
(2, "490 LE", 			"4P Luksus", 	139590, 749.5, "MU 84 109"),
(3, "A 68", 			"6P Luksus", 	287190, 899.5, "JU 09 124"),
(4, "T 67", 			"5P Standard", 	97831, 	599.5, "IU 89 521"),
(5, "Cliff XV 640", 	"4P Standard", 	320941, 499.5, "IQ 88 063"),
(6, "Masterline 780", 	"5P Standard", 	238573, 649.5, "PF 98 016"),
(7, "Exis 580", 		"4P Standard", 	328934, 549.5, "BJ 01 208"),
(8, "361", 				"6P Standard", 	271947, 849.5, "VN 08 019"),
(9, "Ducato 14", 		"4P Standard", 	489102, 499.5, "BP 47 378"),
(10, "447", 			"4P Standard", 	3847, 	599.5, "JD 14 881"),
(11, "Pla Plasy", 		"5P Luksus", 	77593, 	689.5, "AB 34 965"),
(12, "500", 			"3P Standard", 	146947, 399.5, "AV 31 848"),
(13, "430 LE", 			"8P Standard", 	147048, 999.9, "UQ 28 123"),
(14, "Exis 580", 		"4P Standard", 	328934, 549.5, "BJ 11 208"),
(15, "490 LE", 			"4P Standard", 	239490, 549.5, "QR 98 203"),
(16, "A 68", 			"5P Standard", 	948903, 699.5, "JO 22 088"),
(17, "T 67", 			"3P Standard", 	103243, 349.5, "KO 09 530"),
(18, "Cliff XV 640", 	"6P Standard", 	323941, 749.5, "IO 18 001"),
(19, "Masterline 780", 	"8P Standard", 	238573, 949.5, "PL 84 028"),
(20, "Exis 580", 		"4P Luksus", 	493024, 749.5, "YR 42 017"),
(21, "361", 			"6P Standard", 	435522, 649.5, "IY 10 185"),
(22, "Ducato 14", 		"4P Standard", 	438925, 549.5, "CO 29 842"),
(23, "447", 			"4P Standard", 	384712,	599.5, "DP 69 420"),
(24, "Pla Plasy", 		"5P Luksus", 	775931, 689.5, "CB 10 135"),
(25, "500", 			"4P Standard", 	646947, 499.5, "VA 88 848"),
(26, "430 LE", 			"8P Standard", 	9999,	999.9, "AL 99 999"),
(27, "490 LE", 			"6P Luksus", 	423452, 749.5, "MU 70 420"),
(28, "A 68", 			"6P Standard", 	245322, 699.5, "JU 88 124"),
(29, "T 67", 			"5P Standard", 	123242, 629.5, "IU 09 521"),
(30, "Cliff XV 640", 	"4P Standard", 	320941, 529.5, "MA 88 063"),
(31, "Masterline 780", 	"5P Standard", 	238573, 629.5, "KJ 98 016"),
(32, "Exis 580", 		"4P Standard", 	328934, 549.5, "PV 98 208");

INSERT INTO accessories VALUES
(1, 'Autostol'),
(2, 'Cykelholder'),
(3, 'Sengetøj'),
(4, "Fortelt"),
(5, "Klapbord"),
(6, "Klapstol"),
(7, "Borddug"),
(8, "Badehåndklæde");

INSERT INTO reservations VALUES
(1, 1, 1, 'Dankort', '2020-05-20 20:40:14', '2020-05-24 20:00:10', 200, 'Mellemsæson', 2997.4),
(2, 2, 3, "Dankort", "2020-01-02 16:30:00", "2020-01-09 16:30:00", 0, "Lavsæson", 5246.5),
(3, 4, 5, "Dankort", "2020-02-20 10:00:00", "2020-02-28 12:00:00", 40, "Lavsæson", 5423.5),
(4, 5, 6, "Dankort", "2020-03-15 23:00:00", "2020-03-20 12:00:00", 172, "Lavsæson", 3367.15),
(5, 6, 7, "Dankort", "2020-07-01 11:00:00", "2020-07-25 18:00:00", 374, "Højsæson", 26241.8),
(6, 7, 8, "Dankort", "2020-06-01 12:00:00", "2020-06-03 11:00:00", 0, "Højsæson", 1758.4 );

INSERT INTO reserved_accessories VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 3),
(2, 5),
(2, 6),
(2, 6),
(5, 8),
(5, 8);

