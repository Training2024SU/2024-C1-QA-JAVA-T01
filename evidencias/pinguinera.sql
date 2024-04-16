-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema biblioteca_pinguinera_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca_pinguinera_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca_pinguinera_db` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca_pinguinera_db` ;

-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Empleado` (
  `idEmpleado` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `contrasena` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `rol` VARCHAR(45) NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Usuario` (
  `correo` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(100) NULL,
  `contrasena` VARCHAR(100) NULL,
  PRIMARY KEY (`correo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Autor` (
  `id` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Publicacion` (
  `titulo` VARCHAR(45) NOT NULL,
  `tipo_publicacion` VARCHAR(45) NULL,
  `id_autor` VARCHAR(15) NOT NULL,
  `num_paginas` INT NULL,
  `cant_ejemplares` INT NULL,
  `cant_prestados` INT NULL,
  `cant_disponible` INT NULL,
  PRIMARY KEY (`titulo`),
  INDEX `fk_autor_idx` (`id_autor` ASC) VISIBLE,
  CONSTRAINT `fk_autor`
    FOREIGN KEY (`id_autor`)
    REFERENCES `biblioteca_pinguinera_db`.`Autor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Prestamo` (
  `id_prestamo` VARCHAR(15),
  `fecha_prestamo` DATE NULL,
  `fecha_devolucion` DATE NULL,
  `estado` VARCHAR(100) NULL,
  `correo_usuario` VARCHAR(100) NULL,
  `titulo_publicacion` VARCHAR(100) NULL,
  PRIMARY KEY (`id_prestamo`),
  INDEX `fk_correo_usuario_idx` (`correo_usuario` ASC) VISIBLE,
  INDEX `fk_titulo_publicacion_idx` (`titulo_publicacion` ASC) VISIBLE,
  CONSTRAINT `fk_correo_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `biblioteca_pinguinera_db`.`Usuario` (`correo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE, -- Actualización en cascada
  CONSTRAINT `fk_titulo_publicacion`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`Area_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`Area_genero` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `area_generocol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `area_generocol`),
  CONSTRAINT `fk_area_genero_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca_pinguinera_db`.`edad_sugerida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_pinguinera_db`.`edad_sugerida` (
  `titulo_publicacion` VARCHAR(45) NOT NULL,
  `edad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`, `edad`),
  CONSTRAINT `fk_edad_sugerida_1`
    FOREIGN KEY (`titulo_publicacion`)
    REFERENCES `biblioteca_pinguinera_db`.`Publicacion` (`titulo`)
    ON DELETE CASCADE  -- Borrado en cascada
    ON UPDATE CASCADE  -- Actualización en cascada
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;