DROP DATABASE IF EXISTS nmp;
CREATE DATABASE nmp; 

USE nmp;


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
    address_id			INT				NOT NULL,
	birth_date			DATE			NOT NULL,
    payment_details		VARCHAR(45)		NOT NULL,
    driver_license		VARCHAR(20)		NOT NULL,
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


