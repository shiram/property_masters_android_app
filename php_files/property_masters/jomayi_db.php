<?php

include 'db_connect.php';


$query = 'CREATE DATABASE IF NOT EXISTS jomayi_db';

if($db->query($query) == TRUE){
	echo 'Database created Successfully';

//create tables.

include 'config.php';

	mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


	$query = 'CREATE TABLE IF NOT EXISTS users(
	             id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             firstname           VARCHAR(55)       NOT NULL,
				 lastname            VARCHAR(55)       NOT NULL,
				 email               VARCHAR(55)       NOT NULL,
				 phone               VARCHAR(55)       NOT NULL,
				 user_password       VARCHAR(255)      NOT NULL,
				 access_level        TINYINT NOT NULL DEFAULT 0,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Users table successfully.';
	 }else{
	 	echo 'Server Error while Creating Users Table:

	 	 '. $db->error;
	 }

	$query = 'CREATE TABLE IF NOT EXISTS estates(
	             estate_id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
				 id             INTEGER UNSIGNED    NOT NULL,
	             estate_name           VARCHAR(55)       NOT NULL,
	             estate_price          VARCHAR(55)       NOT NULL,
				 estate_description    MEDIUMTEXT       NOT NULL,
				 estate_category    VARCHAR(55)       NOT NULL,
	             estate_image           VARCHAR(255)     NOT NULL,
	             estate_country    VARCHAR(55)       NOT NULL,   
				 estate_city    VARCHAR(55)       NOT NULL,  
				 estate_address_one    VARCHAR(55)       NOT NULL,
				 estate_address_two        VARCHAR(255)     NOT NULL,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(estate_id),
				 FOREIGN KEY(id) REFERENCES users(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Estate Table successfully.';
	 }else{
	 	echo 'Server Error while Creating Estate Table:

	 	 '. $db->error;
	 }


	 	$query = 'CREATE TABLE IF NOT EXISTS appointments(
	             appointment_id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
				 estate_id             INTEGER UNSIGNED    NOT NULL,
	             names           VARCHAR(255)       NOT NULL,
	             phone          VARCHAR(55)       NOT NULL,
				 email    VARCHAR(55)       NOT NULL,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(appointment_id),
				 FOREIGN KEY(estate_id) REFERENCES estates(estate_id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Estate appointments successfully.';
	 }else{
	 	echo 'Server Error while Creating appointments Table:

	 	 '. $db->error;
	 }
	 
	 }else{
		 echo 'DATABASE NOT CREATED';
	 } 
?>