-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bibliotecaPingu
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bibliotecaPingu
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bibliotecaPingu` DEFAULT CHARACTER SET utf8 ;
USE `bibliotecaPingu` ;

-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`usuario` (
  `correo` VARCHAR(100) NOT NULL,
  `contrasenha` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(15) NOT NULL,  
  PRIMARY KEY (`correo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`prestamo` (
  `id` VARCHAR(10) NOT NULL,
  `estado` VARCHAR(15) NOT NULL,
  `fecha_salida` DATE NOT NULL,
  `fecha_devolucion` DATE NOT NULL,
  `correo_usuario` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`, `correo_usuario`),
  INDEX `correo_usuario_idx` (`correo_usuario` ASC) VISIBLE,
  CONSTRAINT `correo_usuario`
    FOREIGN KEY (`correo_usuario`)
    REFERENCES `bibliotecaPingu`.`usuario` (`correo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`producto` (
  `titulo` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  `autor` VARCHAR(80) NOT NULL,
  `numero_pag` VARCHAR(10) NOT NULL,
  `cant_ejemplares` INT NOT NULL,
  `cant_prestados` INT NOT NULL,
  `cant_disponibles` INT AS (`cant_ejemplares` - `cant_prestados`) NOT NULL,
  PRIMARY KEY (`titulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`contenido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`contenido` (
  `id_prestamo` VARCHAR(10) NOT NULL,
  `titulo_libro` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`titulo_libro`, `id_prestamo`),
  INDEX `titulo_libro_idx` (`titulo_libro` ASC) VISIBLE,
  INDEX `id_prestamo_idx` (`id_prestamo` ASC) VISIBLE,
  CONSTRAINT `titulo_libro`
    FOREIGN KEY (`titulo_libro`)
    REFERENCES `bibliotecaPingu`.`producto` (`titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_prestamo`
    FOREIGN KEY (`id_prestamo`)
    REFERENCES `bibliotecaPingu`.`prestamo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`edad_min`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`edad_min` (
  `edad` INT NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`edad`, `titulo`),
  INDEX `titulo_idx` (`titulo` ASC) VISIBLE,
  CONSTRAINT `titulo_ti`
    FOREIGN KEY (`titulo`)
    REFERENCES `bibliotecaPingu`.`producto` (`titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bibliotecaPingu`.`area_genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaPingu`.`area_genero` (
  `titulo` VARCHAR(100) NOT NULL,
  `area_genero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`titulo`, `area_genero`),
  CONSTRAINT `fk_titulo_producto`
    FOREIGN KEY (`titulo`)
    REFERENCES `bibliotecaPingu`.`producto` (`titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

