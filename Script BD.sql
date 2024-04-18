-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `la_pinguinera_biblioteca` DEFAULT CHARACTER SET utf8mb3 ;

USE `la_pinguinera_biblioteca` ;


-- -----------------------------------------------------
-- Table `la_pinguinera_biblioteca`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `la_pinguinera_biblioteca`.`libro` (
  `tituloLibro` VARCHAR(100) NOT NULL,
  `autor` VARCHAR(150) NOT NULL,
  `areaConocimiento` VARCHAR(100) NOT NULL,
  `numeroPaginas` INT NOT NULL,
  `cantidadEjemplares` INT NOT NULL,
  `cantidadPrestados` INT NOT NULL,
  PRIMARY KEY (`tituloLibro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `la_pinguinera_biblioteca`.`novela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `la_pinguinera_biblioteca`.`novela` (
  `tituloNovela` VARCHAR(100) NOT NULL,
  `autor` VARCHAR(150) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `edadLecturaSugerida` VARCHAR(45) NOT NULL,
  `cantidadEjemplares` INT NOT NULL,
  `cantidadPrestados` INT NOT NULL,
  PRIMARY KEY (`tituloNovela`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `la_pinguinera_biblioteca`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `la_pinguinera_biblioteca`.`usuario` (
  `correoUsuario` VARCHAR(150) NOT NULL,
  `nombreUsuario` VARCHAR(150) NOT NULL,
  `contrasenaUsuario` VARCHAR(30) NOT NULL,
  `rolUsuario` ENUM('ADMINISTRADOR', 'ASISTENTE', 'LECTOR') NOT NULL,
  PRIMARY KEY (`correoUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `la_pinguinera_biblioteca`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `la_pinguinera_biblioteca`.`prestamo` (
  `idPrestamo` INT NOT NULL AUTO_INCREMENT,
  `correoUsuario` VARCHAR(150) NOT NULL,
  `tipoPublicacion` ENUM('LIBRO', 'NOVELA') NOT NULL,
  `fechaPrestamo` DATE NOT NULL,
  `fechaDevolucion` DATE NULL DEFAULT NULL,
  `estadoPrestamo` ENUM('SOLICITADO', 'REALIZADO', 'FINALIZADO') NOT NULL,
  `tituloLibro` VARCHAR(100) NOT NULL,
  `tituloNovela` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  UNIQUE INDEX `correoUsuario_UNIQUE` (`correoUsuario` ASC) VISIBLE,
  INDEX `titulo_idx` (`tituloLibro` ASC) VISIBLE,
  INDEX `tituloNovela_idx` (`tituloNovela` ASC) VISIBLE,
  CONSTRAINT `fk_prestamo_usuario`
    FOREIGN KEY (`correoUsuario`)
    REFERENCES `la_pinguinera_biblioteca`.`usuario` (`correoUsuario`),
  CONSTRAINT `tituloLibro`
    FOREIGN KEY (`tituloLibro`)
    REFERENCES `la_pinguinera_biblioteca`.`libro` (`tituloLibro`),
  CONSTRAINT `tituloNovela`
    FOREIGN KEY (`tituloNovela`)
    REFERENCES `la_pinguinera_biblioteca`.`novela` (`tituloNovela`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
