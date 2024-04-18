-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: libreria_pinguinera
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area_genero`
--

DROP TABLE IF EXISTS `area_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area_genero` (
  `titulo_publicacion` varchar(45) NOT NULL,
  `Area_generocol` varchar(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`,`Area_generocol`),
  CONSTRAINT `fk_Area_genero_1` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`Titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_genero`
--

LOCK TABLES `area_genero` WRITE;
/*!40000 ALTER TABLE `area_genero` DISABLE KEYS */;
INSERT INTO `area_genero` VALUES ('1984','Ciencia ficción'),('Cien años de soledad','Realismo mágico'),('Crimen y castigo','Novela psicológica'),('Don Quijote de la Mancha','Sátira'),('Drácula','Terror'),('El código Da Vinci','Misterio'),('El Hobbit','Fantasía'),('El principito','Fábula'),('El señor de los anillos','Fantasía'),('Harry Potter y la piedra filosofal','Fantasía'),('La sombra del viento','Misterio'),('Las aventuras de Tom Sawyer','Aventura'),('Los juegos del hambre','Distopía'),('Matar a un ruiseñor','Drama'),('Orgullo y prejuicio','Romance');
/*!40000 ALTER TABLE `area_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edad_sugerida`
--

DROP TABLE IF EXISTS `edad_sugerida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edad_sugerida` (
  `titulo_publicacion` varchar(45) NOT NULL,
  `edad` varchar(45) NOT NULL,
  PRIMARY KEY (`titulo_publicacion`,`edad`),
  CONSTRAINT `fk_edad_sugerida_1` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`Titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edad_sugerida`
--

LOCK TABLES `edad_sugerida` WRITE;
/*!40000 ALTER TABLE `edad_sugerida` DISABLE KEYS */;
INSERT INTO `edad_sugerida` VALUES ('1984','Adultos'),('Cien años de soledad','Adultos'),('Crimen y castigo','Adultos'),('Don Quijote de la Mancha','Adultos'),('Drácula','Adultos'),('El código Da Vinci','Adultos'),('El Hobbit','Niños'),('El principito','Niños'),('El señor de los anillos','Adultos'),('Harry Potter y la piedra filosofal','Niños'),('La sombra del viento','Adultos'),('Las aventuras de Tom Sawyer','Niños'),('Los juegos del hambre','Jóvenes adultos'),('Matar a un ruiseñor','Jóvenes adultos'),('Orgullo y prejuicio','Adultos');
/*!40000 ALTER TABLE `edad_sugerida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `Nombre` varchar(45) DEFAULT NULL,
  `Contrasena` varchar(45) DEFAULT NULL,
  `Correo` varchar(45) NOT NULL,
  `Rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('John Doe','contrasennasegura123456','administrador@pingu.com.co','ADMINISTRADOR'),('Elkin Blanco','eblanco@pingu.com.co','contrasena1234','asistente'),('Luis Molina','qwer1234','luis.molina@pingu.com.co','asistente');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `idPrestamo` int NOT NULL AUTO_INCREMENT,
  `Fecha_prestamo` date DEFAULT NULL,
  `Fecha_devolucion` date DEFAULT NULL,
  `Estado` varchar(100) DEFAULT NULL,
  `correo_usuario` varchar(100) DEFAULT NULL,
  `titulo_publicacion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `fk_correo_usuario_idx` (`correo_usuario`),
  KEY `fk_titulo_publicacion_idx` (`titulo_publicacion`),
  CONSTRAINT `fk_correo_usuario` FOREIGN KEY (`correo_usuario`) REFERENCES `usuario` (`Correo`),
  CONSTRAINT `fk_titulo_publicacion` FOREIGN KEY (`titulo_publicacion`) REFERENCES `publicacion` (`Titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (2,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(3,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(4,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(5,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(6,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(7,'2024-04-18','2024-04-20','Finalizado','d','El código Da Vinci'),(8,'2024-04-18','2024-04-20','Finalizado','d','El principito'),(9,'2024-04-18','2024-04-20','Finalizado','d','Microeconomía Aplicada'),(10,'2024-04-18','2024-04-25','Finalizado','d','Microeconomía Aplicada'),(11,'2024-04-18','2024-04-25','Finalizado','d','La sombra del viento'),(12,'2024-04-18','2024-04-30','Finalizado','d','El principito'),(13,'2024-04-18','2024-04-20','Finalizado','d','Microeconomía Aplicada'),(14,'2024-04-18','2024-04-20','Finalizado','d','Cien años de soledad'),(15,'2024-04-18','2024-04-20','Finalizado','d','Don Quijote de la Mancha'),(16,'2024-04-18','2024-04-25','Finalizado','d','El Hobbit'),(17,'2024-04-18','2024-04-25','Finalizado','d','El señor de los anillos'),(20,'2024-04-18','2024-04-25','Finalizado','aplata@mail.com','Microeconomía Aplicada'),(21,'2024-04-18','2024-04-25','Finalizado','aplata@mail.com','1984');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicacion` (
  `Titulo` varchar(45) NOT NULL,
  `tipo_publicacion` varchar(45) DEFAULT NULL,
  `autor` varchar(45) DEFAULT NULL,
  `num_paginas` int DEFAULT NULL,
  `cant_ejemplares` int DEFAULT NULL,
  `cant_prestados` int DEFAULT NULL,
  `cant_disponible` int DEFAULT NULL,
  PRIMARY KEY (`Titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacion`
--

LOCK TABLES `publicacion` WRITE;
/*!40000 ALTER TABLE `publicacion` DISABLE KEYS */;
INSERT INTO `publicacion` VALUES ('1984','Novela','George Orwell',250,60,2,58),('Cien años de soledad','Novela','Gabriel García Márquez',500,80,0,80),('Crimen y castigo','Novela','Fyodor Dostoevsky',600,100,0,100),('Don Quijote de la Mancha','Novela','Miguel de Cervantes',800,120,0,120),('Drácula','Novela','Bram Stoker',450,85,0,85),('El código Da Vinci','Novela','Dan Brown',600,90,0,90),('El Hobbit','Novela','J.R.R. Tolkien',350,70,0,70),('El principito','Novela','Antoine de Saint-Exupéry',150,40,0,40),('El señor de los anillos','Novela','J.R.R. Tolkien',1000,50,1,49),('Harry Potter y la piedra filosofal','Novela','J.K. Rowling',300,100,0,100),('La sombra del viento','Novela','Carlos Ruiz Zafón',350,75,0,75),('Las aventuras de Tom Sawyer','Novela','Mark Twain',200,55,0,55),('Los juegos del hambre','Novela','Suzanne Collins',400,80,0,80),('Matar a un ruiseñor','Novela','Harper Lee',320,65,0,65),('Microeconomía Aplicada','libro','asfg',456,7,2,5),('Orgullo y prejuicio','Novela','Jane Austen',400,70,0,70);
/*!40000 ALTER TABLE `publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Correo` varchar(100) NOT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Contrasena` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('afsdg','kgf','saf'),('aplata@mail.com','Anderson Plata','plata1234'),('d','d','d'),('ecartman@mail.com','Eric Cartman','019283'),('f','f','f'),('t','t','t');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-18 18:00:28
