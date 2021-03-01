-- Delete database if it exists, start from scratch each time 
-- Good for new development
DROP DATABASE IF EXISTS HotelDB;

-- Create database
CREATE DATABASE HotelDB;

-- Use the correct database for the table creation
USE HotelDB;

-- Create table RoomType 
CREATE TABLE `RoomType` (
	`RoomTypeID` INT PRIMARY KEY AUTO_INCREMENT,
    `RoomType` VARCHAR(15) NOT NULL,
    `ADA` VARCHAR(3) NOT NULL,
    `StandardOccupancy` TINYINT NOT NULL,
    `MaxOccupancy` TINYINT NOT NULL,
    `BasePrice` DECIMAL(5,2) NOT NULL,
    `ExtraPerson` DECIMAL(4,2) NOT NULL
);

-- Create table Address 
CREATE TABLE `Address` (
	`AddressId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `Address` VARCHAR(50) NOT NULL,
    `City` VARCHAR(30) NOT NULL,
    `State` VARCHAR(2) NOT NULL,
    `Zip` VARCHAR(5) NOT NULL
);

-- Create table Amenities
CREATE TABLE `Amenities` (
	`AmenitiesID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `AmenitiesName` VARCHAR(35) NOT NULL
);

-- Create table Room
CREATE TABLE `Room` (
	`RoomNumber` INT PRIMARY KEY NOT NULL,
    `RoomTypeId` INT NOT NULL,
    FOREIGN KEY fk_RoomTypeId (`RoomTypeId`)
		REFERENCES RoomType (`RoomTypeId`)
);

-- Create bridge table RoomAmenities
CREATE TABLE `RoomAmenities` (
	`RoomNumber` INT NOT NULL,
    `AmenitiesID` INT NOT NULL,
    PRIMARY KEY `pk_RoomAmenities` (`RoomNumber`, `AmenitiesID`),
    FOREIGN KEY `fk_RoomAmenities_Room` (`RoomNumber`)
		REFERENCES `Room`(`RoomNumber`),
	FOREIGN KEY `fk_RoomAmenities_Amenities` (`AmenitiesID`)
		REFERENCES `Amenities`(`AmenitiesID`)
);

-- Create table Guest
CREATE TABLE `Guest` (
	`GuestID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `AddressId` INT NOT NULL,
    `Phone` VARCHAR(15) NOT NULL,
    FOREIGN KEY `AddressId` (`AddressId`)
		REFERENCES `Address` (`AddressId`)
);

-- Create table Reservation
CREATE TABLE `Reservation` (
	`ReservationID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `GuestId` INT NOT NULL,
    `Adults` TINYINT NOT NULL,
    `Children` TINYINT NOT NULL,
    `StartDate` DATE NOT NULL,
    `EndDate` DATE NOT NULL,
    `TotalCost` DECIMAL(6,2),
    FOREIGN KEY `GuestId` (`GuestId`)
		REFERENCES `Guest` (`GuestId`)
);

-- Create bridge table RoomReservation
CREATE TABLE `RoomReservation` (
	`RoomNumber` INT NOT NULL,
    `ReservationId` INT NOT NULL,
    PRIMARY KEY `pk_RoomReservation` (`RoomNumber`, `ReservationId`),
    FOREIGN KEY `fk_RoomReservation_Room` (`RoomNumber`)
		REFERENCES `Room`(`RoomNumber`),
	FOREIGN KEY `fk_RoomReservation_Reservation` (`ReservationId`)
		REFERENCES `Reservation`(`ReservationId`)
);

