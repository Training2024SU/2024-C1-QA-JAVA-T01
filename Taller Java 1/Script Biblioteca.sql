-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`Empleado` (
  `idEmpleado` VARCHAR(100) NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Contraseña` VARCHAR(100) NULL,
  `Correo` VARCHAR(100) NULL,
  `Rol` VARCHAR(100) NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`Usuario` (
  `Correo` VARCHAR(100) NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Contraseña` VARCHAR(100) NULL,
  PRIMARY KEY (`Correo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`Publicacion` (
  `Titulo` VARCHAR(100) NOT NULL,
  `tipo_publicacion` VARCHAR(100) NULL,
  `autor` VARCHAR(100) NULL,
  `num_paginas` INT NULL,
  `cant_ejemplares` INT NULL,
  `cant_prestados` INT NULL,
  `cant_disponible` INT as ('cant_ejemplares' - 'cant_prestados'),
  PRIMARY KEY (`Titulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`Prestamo` (
  `idPrestamo` VARCHAR(100) NOT NULL,
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
    REFERENCES `biblioteca`.`Usuario` (`Correo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_titulo_publicacion`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`Area_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`Area_genero` (
  `titulo_publicacion` VARCHAR(100) NOT NULL,
  `Area_generocol` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `Area_generocol`),
  CONSTRAINT `fk_Area_genero_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca`.`edad_sugerida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`edad_sugerida` (
  `titulo_publicacion` VARCHAR(100) NOT NULL,
  `edad` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `edad`),
  CONSTRAINT `fk_edad_sugerida_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca`.`Publicacion` (`Titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
