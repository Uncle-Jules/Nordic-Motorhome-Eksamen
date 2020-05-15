DROP DATABASE IF EXISTS nmr;
CREATE DATABASE nmr; 

USE nmr;


DROP TABLE IF EXISTS models;
CREATE TABLE models
(
    model		VARCHAR(45)		NOT NULL	PRIMARY KEY,
	brand_name 	VARCHAR(45)		NOT NULL	UNIQUE
);
DROP TABLE IF EXISTS accessories;
CREATE TABLE accessories
(
	id			INT				NOT NULL	PRIMARY KEY		AUTO_INCREMENT,
    accessory	VARCHAR(45)		NOT NULL
);

DROP TABLE IF EXISTS motorhome;
CREATE TABLE motorhome 
(
	id					INT									NOT NULL	PRIMARY KEY 	AUTO_INCREMENT,
    model				VARCHAR(45)							NOT NULL,
    odometer			INT									NOT NULL,
    price_per_day		INT									NOT NULL,
    registration_number	VARCHAR(10)							NOT NULL	UNIQUE,
	FOREIGN KEY (model)
		REFERENCES models (model)
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
    phone_number		VARCHAR(45)		NOT NULL,
    address_id			INT				NOT NULL,
	birth_date			DATE			NOT NULL,
    payment_details		VARCHAR(45)		NOT NULL,
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
    start_date			DATETIME	NOT NULL, 
    end_date			DATETIME	NOT NULL, 
    distance_to_pickup	INT			NOT NULL,
	accessory_id 		INT			NOT NULL,
    FOREIGN KEY (motorhome_id)
		REFERENCES motorhome (id),
    FOREIGN KEY (customer_id)
		REFERENCES customers (id),
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
(1, 'Rasmus', 'Falk','+45 22 22 22 22', 1, '2020-05-28', "credit card", 'XMP-232'),
(2, 'Rasmusiiii', 'Falk','+45 22 22 22 22', 1, '2020-05-28', "credit card", 'XMP-232')
