-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Pinguin_Library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Pinguin_Library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Pinguin_Library` DEFAULT CHARACTER SET utf8 ;
USE `Pinguin_Library` ;

-- -----------------------------------------------------
-- Table `Pinguin_Library`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pinguin_Library`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`author` (
  `id` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pinguin_Library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`book` (
  `book_id` VARCHAR(100) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  `quantity_loaned` INT NOT NULL,
  `quantity_available` INT AS  (quantity - quantity_loaned),
  `category` VARCHAR(45) NOT NULL,
  `author_id` VARCHAR(100) NOT NULL,
  INDEX `FK_AuthorB_idx` (`author_id` ASC) VISIBLE,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `FK_AuthorB`
    FOREIGN KEY (`author_id`)
    REFERENCES `Pinguin_Library`.`author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pinguin_Library`.`novel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`novel` (
  `novel_id` VARCHAR(100) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `recommend_age` INT NOT NULL,
  `quantity` INT NOT NULL,
  `quantity_loaned` INT NOT NULL,
  `quantity_available` INT AS  (quantity - quantity_loaned),
  `gender` VARCHAR(45) NOT NULL,
  `author_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`novel_id`),
  INDEX `FK_AuthorN_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `FK_AuthorN`
    FOREIGN KEY (`author_id`)
    REFERENCES `Pinguin_Library`.`author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Pinguin_Library`.`book_loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`book_loan` (
  `book_loan_id` VARCHAR(100) NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `book_id` VARCHAR(100) NOT NULL,
  `loan_date` DATE NULL,
  `return_date` DATE NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`book_loan_id`),
  INDEX `FK_BookItem_idx` (`book_id` ASC) VISIBLE,
  INDEX `FK_UserLoan_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_BookItem`
    FOREIGN KEY (`book_id`)
    REFERENCES `Pinguin_Library`.`book` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_UserLoan`
    FOREIGN KEY (`user_id`)
    REFERENCES `Pinguin_Library`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pinguin_Library`.`novel_loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pinguin_Library`.`novel_loan` (
  `novel_loan_id` VARCHAR(100) NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `novel_id` VARCHAR(100) NOT NULL,
  `loan_date` DATE NULL,
  `return_date` DATE  NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`novel_loan_id`),
  INDEX `FK_UserNovel_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_NovelId_idx` (`novel_id` ASC) VISIBLE,
  CONSTRAINT `FK_UserNovel`
    FOREIGN KEY (`user_id`)
    REFERENCES `Pinguin_Library`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_NovelId`
    FOREIGN KEY (`novel_id`)
    REFERENCES `Pinguin_Library`.`novel` (`novel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
