/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  james
 * Created: 30-Jun-2017
 */

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FlightBookingSystem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FlightBookingSystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FlightBookingSystem` DEFAULT CHARACTER SET utf8 ;

USE `FlightBookingSystem` ;

-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Customer` (
  `CustomerId` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(10) NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `MobileNo` VARCHAR(12) NOT NULL,
  `HomePhoneNumber` VARCHAR(12) NOT NULL,
  `EmailAddress` VARCHAR(45) NOT NULL,
  `LoginName` VARCHAR(45) NOT NULL,
  `LoginPassword` VARCHAR(45) NOT NULL,
  `DateOfBirth` DATE NOT NULL,
  PRIMARY KEY (`CustomerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Booking` (
  `BookingId` INT NOT NULL AUTO_INCREMENT,
  `NoOfAdults` INT NOT NULL,
  `NoOfChildren` INT NOT NULL,
  `NoOfInfants` INT NOT NULL,
  `TicketType` VARCHAR(12) NOT NULL,
  `TotalCost` DECIMAL(4,2) NULL,
  `SeatType` VARCHAR(12) NOT NULL,
  `OutboundRouteID` INT NOT NULL,
  `OutboundDate` DATE NOT NULL,
  `ReturnRouteID` INT NULL,
  `ReturnDate` DATE NULL,
  `Customer_CustomerId` INT NOT NULL,
  PRIMARY KEY (`BookingId`),
  INDEX `fk_Booking_Customer1_idx` (`Customer_CustomerId` ASC),
  CONSTRAINT `fk_Booking_Customer1`
    FOREIGN KEY (`Customer_CustomerId`)
    REFERENCES `FlightBookingSystem`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Route` (
  `RouteId` INT NOT NULL AUTO_INCREMENT,
  `RouteName` VARCHAR(45) NOT NULL,
  `AirportFrom` CHAR(3) NOT NULL,
  `AirportTo` CHAR(3) NOT NULL,
  `AdultFare` DECIMAL(4,2) NOT NULL,
  `ChildFare` DECIMAL(4,2) NOT NULL,
  `InfantFare` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`RouteId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Aircraft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Aircraft` (
  `AircraftId` INT NOT NULL,
  `AircraftModel` VARCHAR(45) NOT NULL,
  `SeatingCapacity` INT NOT NULL,
  `LuggageCapacity` INT NOT NULL,
  `AircraftStatus` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AircraftId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Flight` (
  `FlightId` INT NOT NULL AUTO_INCREMENT,
  `FlightDate` DATE NOT NULL,
  `LeaveDateTime` DATETIME NOT NULL,
  `ArrivalDateTime` DATETIME NOT NULL,
  `FlightStatus` VARCHAR(45) NOT NULL,
  `GateNumber` INT NOT NULL,
  `Stops` INT NOT NULL,
  `Route_RouteId` INT NOT NULL,
  `Aircraft_AircraftId` INT NOT NULL,
  PRIMARY KEY (`FlightId`),
  INDEX `fk_Flight_Route1_idx` (`Route_RouteId` ASC),
  INDEX `fk_Flight_Aircraft1_idx` (`Aircraft_AircraftId` ASC),
  CONSTRAINT `fk_Flight_Route1`
    FOREIGN KEY (`Route_RouteId`)
    REFERENCES `FlightBookingSystem`.`Route` (`RouteId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Flight_Aircraft1`
    FOREIGN KEY (`Aircraft_AircraftId`)
    REFERENCES `FlightBookingSystem`.`Aircraft` (`AircraftId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Seat` (
  `SeatNo` INT NOT NULL,
  `Aircraft_AircraftId` INT NOT NULL,
  `SeatType` VARCHAR(45) NOT NULL,
  `Booking_BookingId` INT NULL,
  `SeatBooked` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`SeatNo`, `Aircraft_AircraftId`),
  INDEX `fk_Seat_Aircraft1_idx` (`Aircraft_AircraftId` ASC),
  INDEX `fk_Seat_Booking1_idx` (`Booking_BookingId` ASC),
  CONSTRAINT `fk_Seat_Aircraft1`
    FOREIGN KEY (`Aircraft_AircraftId`)
    REFERENCES `FlightBookingSystem`.`Aircraft` (`AircraftId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Seat_Booking1`
    FOREIGN KEY (`Booking_BookingId`)
    REFERENCES `FlightBookingSystem`.`Booking` (`BookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`BaggageItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`BaggageItem` (
  `BaggageItemId` INT NOT NULL AUTO_INCREMENT,
  `BaggageItemWeightKg` INT NOT NULL,
  `Booking_BookingId` INT NOT NULL,
  `Aircraft_AircraftId` INT NOT NULL,
  PRIMARY KEY (`BaggageItemId`),
  INDEX `fk_BaggageItem_Booking1_idx` (`Booking_BookingId` ASC),
  INDEX `fk_BaggageItem_Aircraft1_idx` (`Aircraft_AircraftId` ASC),
  CONSTRAINT `fk_BaggageItem_Booking1`
    FOREIGN KEY (`Booking_BookingId`)
    REFERENCES `FlightBookingSystem`.`Booking` (`BookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BaggageItem_Aircraft1`
    FOREIGN KEY (`Aircraft_AircraftId`)
    REFERENCES `FlightBookingSystem`.`Aircraft` (`AircraftId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Passenger` (
  `PassengerId` INT NOT NULL AUTO_INCREMENT,
  `PassengerName` VARCHAR(45) NOT NULL,
  `PassengerType` VARCHAR(45) NOT NULL,
  `Seat_SeatNo` INT NOT NULL,
  `Seat_Aircraft_AircraftId` INT NOT NULL,
  `BaggageItem_BaggageItemId` INT NOT NULL,
  `Booking_BookingId` INT NOT NULL,
  `Flight_FlightId` INT NOT NULL,
  PRIMARY KEY (`PassengerId`),
  INDEX `fk_Passenger_Seat1_idx` (`Seat_SeatNo` ASC, `Seat_Aircraft_AircraftId` ASC),
  INDEX `fk_Passenger_BaggageItem1_idx` (`BaggageItem_BaggageItemId` ASC),
  INDEX `fk_Passenger_Booking1_idx` (`Booking_BookingId` ASC),
  INDEX `fk_Passenger_Flight1_idx` (`Flight_FlightId` ASC),
  CONSTRAINT `fk_Passenger_Seat1`
    FOREIGN KEY (`Seat_SeatNo` , `Seat_Aircraft_AircraftId`)
    REFERENCES `FlightBookingSystem`.`Seat` (`SeatNo` , `Aircraft_AircraftId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Passenger_BaggageItem1`
    FOREIGN KEY (`BaggageItem_BaggageItemId`)
    REFERENCES `FlightBookingSystem`.`BaggageItem` (`BaggageItemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Passenger_Booking1`
    FOREIGN KEY (`Booking_BookingId`)
    REFERENCES `FlightBookingSystem`.`Booking` (`BookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Passenger_Flight1`
    FOREIGN KEY (`Flight_FlightId`)
    REFERENCES `FlightBookingSystem`.`Flight` (`FlightId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`FlightBooking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`FlightBooking` (
  `Flight_FlightId` INT NOT NULL,
  `Booking_BookingId` INT NOT NULL,
  PRIMARY KEY (`Flight_FlightId`, `Booking_BookingId`),
  INDEX `fk_Flight_has_Booking_Booking1_idx` (`Booking_BookingId` ASC),
  INDEX `fk_Flight_has_Booking_Flight1_idx` (`Flight_FlightId` ASC),
  CONSTRAINT `fk_Flight_has_Booking_Flight1`
    FOREIGN KEY (`Flight_FlightId`)
    REFERENCES `FlightBookingSystem`.`Flight` (`FlightId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Flight_has_Booking_Booking1`
    FOREIGN KEY (`Booking_BookingId`)
    REFERENCES `FlightBookingSystem`.`Booking` (`BookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `FlightBookingSystem`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FlightBookingSystem`.`Payment` (
  `PaymentId` INT NOT NULL AUTO_INCREMENT,
  `CardNumber` INT NOT NULL,
  `CardType` VARCHAR(45) NOT NULL,
  `HolderName` VARCHAR(45) NOT NULL,
  `HolderAddressLine1` VARCHAR(45) NOT NULL,
  `HolderAddressLine2` VARCHAR(45) NULL,
  `HolderPostcode` VARCHAR(12) NOT NULL,
  `HolderTownCity` VARCHAR(45) NOT NULL,
  `HolderCountyState` VARCHAR(45) NOT NULL,
  `HolderCountry` VARCHAR(45) NOT NULL,
  `ExpiryDate` DATE NOT NULL,
  `CVCNumber` VARCHAR(3) NOT NULL,
  `PaymentAmount` DECIMAL(4,2) NOT NULL,
  `Customer_CustomerId` INT NOT NULL,
  `Booking_BookingId` INT NOT NULL,
  PRIMARY KEY (`PaymentId`),
  INDEX `fk_Payment_Customer1_idx` (`Customer_CustomerId` ASC),
  INDEX `fk_Payment_Booking1_idx` (`Booking_BookingId` ASC),
  CONSTRAINT `fk_Payment_Customer1`
    FOREIGN KEY (`Customer_CustomerId`)
    REFERENCES `FlightBookingSystem`.`Customer` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_Booking1`
    FOREIGN KEY (`Booking_BookingId`)
    REFERENCES `FlightBookingSystem`.`Booking` (`BookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
