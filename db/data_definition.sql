CREATE SCHEMA `bank_xyz`;

USE bank_xyz;

/* ********************************************************************************************************* */
/* ********************************************************************************************************* */

CREATE TABLE `bank_card` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UQ` (`type`)
);

CREATE TABLE `loan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UQ` (`type`)
);

CREATE TABLE `bank_transaction` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UQ` (`type`)
);

CREATE TABLE `customer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `national_id` CHAR(14) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `mobile` CHAR(11) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `mailing_address` VARCHAR(500) NOT NULL,
  `account_number` CHAR(8) NOT NULL,
  `balance` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `national_id_UQ` (`national_id`),
  UNIQUE KEY `mobile_UQ` (`mobile`),
  UNIQUE KEY `email_UQ` (`email`),
  UNIQUE KEY `account_number_UQ` (`account_number`)
);

CREATE TABLE `transaction` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT UNSIGNED NOT NULL,
  `bank_transaction_type_id` INT UNSIGNED NOT NULL,
  `amount` FLOAT UNSIGNED NOT NULL,
  `occurred_on` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  FOREIGN KEY (`bank_transaction_type_id`) REFERENCES `bank_transaction` (`id`)
);

CREATE TABLE `issued_bank_card` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT UNSIGNED NOT NULL,
  `bank_card_type_id` INT UNSIGNED NOT NULL,
  `date_of_issuance` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  FOREIGN KEY (`bank_card_type_id`) REFERENCES `bank_card` (`id`)
);

CREATE TABLE `borrowed_loan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT UNSIGNED NOT NULL,
  `loan_type_id` INT UNSIGNED NOT NULL,
  `amount` FLOAT UNSIGNED NOT NULL,
  `borrowing_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  FOREIGN KEY (`loan_type_id`) REFERENCES `loan` (`id`)
);


