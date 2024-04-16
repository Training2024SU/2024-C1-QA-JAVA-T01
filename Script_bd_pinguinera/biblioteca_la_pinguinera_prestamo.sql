-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca_la_pinguinera
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `idPrestamo` int NOT NULL,
  `Fecha_prestamo` date DEFAULT NULL,
  `Fecha_devolucion` date DEFAULT NULL,
  `Estado` varchar(45) DEFAULT NULL,
  `Usuario_correo` varchar(100) NOT NULL,
  `Publicacion_Titulo` varchar(100) NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `fk_Prestamo_Usuario1_idx` (`Usuario_correo`),
  KEY `fk_Prestamo_Publicacion1_idx` (`Publicacion_Titulo`),
  CONSTRAINT `fk_Prestamo_Publicacion1` FOREIGN KEY (`Publicacion_Titulo`) REFERENCES `publicacion` (`Titulo`),
  CONSTRAINT `fk_Prestamo_Usuario1` FOREIGN KEY (`Usuario_correo`) REFERENCES `usuario` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1126,'2024-03-10','2024-03-18','SOLICITADO','nuevousuario@example.com.co','Azul'),(112233,'2024-04-12','2024-04-15','SOLICITADO','correoreal@gmail.com','El coronel no tiene quien le escriba'),(112246,'2024-01-25','2024-02-15','SOLICITADO','correoreal@gmail.com','Azul');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 14:19:27
