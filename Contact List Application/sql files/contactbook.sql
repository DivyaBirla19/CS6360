/*
 *    File name: create-contact-list-data-for-MySQL.sql
 *       Author: Divya Birla
 *    Last Edit: 2020-03-17
 *  Description: This file is based on the contact list csv database
 *               It is for use in the UT-Dallas course CS-6360 project.
 */

-- Run this script directly in the MySQL server query window.
-- It will automatically create the database and all the database objects.

-- ini_set('MAX_EXECUTION_TIME', '-1');
-- If the database "contactbook" already exists, then delete it.
DROP DATABASE IF EXISTS contactbook;
-- Create the Database "contactbook"
CREATE DATABASE contactbook;


-- Set the currently active database to be "contactbook"
USE contactbook;

DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT (
  contact_id      INT NOT NULL AUTO_INCREMENT,
  first_name      VARCHAR(15) NOT NULL,
  middle_name     VARCHAR(15) ,
  last_name 	  VARCHAR(15) NOT NULL,
  CONSTRAINT pk_contact PRIMARY KEY (contact_id)
  );

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS (
  address_id 	INT NOT NULL AUTO_INCREMENT,
  contact_id		INT,
  -- AddressType	CHAR(6),
  home_address   	VARCHAR(50),
  home_city			VARCHAR(15),
  home_state		VARCHAR(20),
  home_zip			VARCHAR(5),
  work_address   	VARCHAR(50),
  work_city			VARCHAR(15),
  work_state		VARCHAR(20),
  work_zip			VARCHAR(5),
CONSTRAINT pk_address PRIMARY KEY (address_id),
CONSTRAINT fk_address_contact FOREIGN KEY (contact_id) references CONTACT(contact_id)
);

DROP TABLE IF EXISTS PHONE;
CREATE TABLE PHONE (
  phone_id         INT NOT NULL AUTO_INCREMENT,
  contact_id    	  INT,
  -- PhoneType		  CHAR(6),
  -- AreaCode        INT,
  home_phone    	 VARCHAR(15),
  cell_phone		 VARCHAR(15),
  work_phone		 VARCHAR(15),
 CONSTRAINT pk_phone PRIMARY KEY (phone_id),
  CONSTRAINT fk_phone_contact FOREIGN KEY (contact_id) references CONTACT(contact_id)
);

DROP TABLE IF EXISTS DATES;
CREATE TABLE DATES (
  date_id    INT  AUTO_INCREMENT,
  contact_id       INT,
  -- DateType  CHAR(15),
  birth_date      DATE,
  anni_date		  DATE, 	
  CONSTRAINT pk_dates PRIMARY KEY (date_id),
  CONSTRAINT fk_dates_contact FOREIGN KEY (contact_id) references CONTACT(contact_id)
);