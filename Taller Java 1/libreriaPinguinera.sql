-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Empleado` (
  `idEmpleado` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Contraseña` VARCHAR(45) NULL,
  `Correo` VARCHAR(45) NULL,
  `Rol` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `Correo` VARCHAR(100) NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Contraseña` VARCHAR(100) NULL,
  PRIMARY KEY (`Correo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Publicacion` (
  `Titulo` VARCHAR(45) NOT NULL,
  `tipo_publicacion` VARCHAR(45) NULL,
  `autor` VARCHAR(45) NULL,
  `num_paginas` INT NULL,
  `cant_ejemplares` INT NULL,
  `cant_prestados` INT NULL,
  `cant_disponible` INT AS (`cant_ejemplares`-`cant_prestados`),
  PRIMARY KEY (`Titulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Prestamo` (
  `idPrestamo`  VARCHAR(100) NOT NULL,
  `Fecha_prestamo` DATE NULL,
  `Fecha_devolucion` DATE NULL,
  `Estado` VARCHAR(100) NULL,
  `correo_usuario` VARCHAR(100) NULL,
  `titulo_publicacion` VARCHAR(100) NULL,
  PRIMARY KEY (`idPrestamo`),
  INDEX `fk_correo_usuario_idx` (`correo_usuario` ASC) VISIBLE,
  INDEX `fk_titulo_publicacion_idx` (`titulo_publicacion` ASC) VISIBLE,
  CONSTRAINT `fk_correo_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `mydb`.`Usuario` (`Correo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_titulo_publicacion`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `mydb`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Area_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Area_genero` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `Area_generocol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `Area_generocol`),
  CONSTRAINT `fk_Area_genero_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `mydb`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`edad_sugerida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`edad_sugerida` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `edad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `edad`),
  CONSTRAINT `fk_edad_sugerida_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `mydb`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
