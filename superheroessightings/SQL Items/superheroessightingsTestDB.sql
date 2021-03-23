DROP DATABASE IF EXISTS superheroesSightingsTest;
CREATE DATABASE superheroesSightingsTest;

USE superheroesSightingsTest;

CREATE TABLE powers(
    powerId INT PRIMARY KEY AUTO_INCREMENT,
    superpower VARCHAR(50) NOT NULL
);

CREATE TABLE organizations(
    organizationId INT PRIMARY KEY AUTO_INCREMENT,
    organizationName VARCHAR(50) NOT NULL,
    organizationDescription VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    contactInfo VARCHAR(50)
);

CREATE TABLE location(
	locationId INT PRIMARY KEY AUTO_INCREMENT,
    locationName VARCHAR(50) NOT NULL,
    locationDescription VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    latitude FLOAT,
    longitude FLOAT
);

CREATE TABLE superHumans(
    superHumanId INT PRIMARY KEY AUTO_INCREMENT,
    superHumanName VARCHAR(50) NOT NULL,
    superDescription VARCHAR(256) NOT NULL,
    powerId INT NOT NULL,
    FOREIGN KEY (powerId) REFERENCES powers(powerId)
);

CREATE TABLE superHumansOrganization(
    shoId INT PRIMARY KEY AUTO_INCREMENT,
    superHumanId INT NOT NULL,
    organizationId INT NOT NULL,
    FOREIGN KEY (superHumanId) REFERENCES superHumans(superHumanId),
    FOREIGN KEY (organizationId) REFERENCES organizations(organizationId)
);

CREATE TABLE sighting(
	shlId INT PRIMARY KEY AUTO_INCREMENT,
    superHumanId INT NOT NULL,
    locationId INT NOT NULL,
    sightingDate VARCHAR(20) NOT NULL,
    FOREIGN KEY (superHumanId) REFERENCES superHumans(superHumanId),
    FOREIGN KEY (locationId) REFERENCES location(locationId)
);