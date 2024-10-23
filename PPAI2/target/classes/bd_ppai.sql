-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: bd_ppai
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bodega`
--

DROP TABLE IF EXISTS `bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bodega` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coordenadas` varchar(255) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `historia` varchar(2000) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `anio_fundacion` int DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `coordenadas_ubicacion` varchar(255) DEFAULT NULL,
  `periodo_actualizacion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `region_id` (`region_id`),
  CONSTRAINT `bodega_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodega`
--

LOCK TABLES `bodega` WRITE;
/*!40000 ALTER TABLE `bodega` DISABLE KEYS */;
INSERT INTO `bodega` VALUES (21,'Coordenadas Bodega1','DescripciÃ³n Bodega1','Historia Bodega1','Nombre Bodega1',1,94,NULL,NULL);
/*!40000 ALTER TABLE `bodega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maridaje`
--

DROP TABLE IF EXISTS `maridaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maridaje` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maridaje`
--

LOCK TABLES `maridaje` WRITE;
/*!40000 ALTER TABLE `maridaje` DISABLE KEYS */;
INSERT INTO `maridaje` VALUES (46,'DescripciÃ³n Maridaje1','Nombre Maridaje1'),(47,'DescripciÃ³n Maridaje2','Nombre Maridaje2'),(48,'DescripciÃ³n Maridaje3','Nombre Maridaje3');
/*!40000 ALTER TABLE `maridaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad_evento`
--

DROP TABLE IF EXISTS `novedad_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `novedad_evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_descuento` varchar(255) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `es_evento` tinyint(1) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `nombre_evento` varchar(255) DEFAULT NULL,
  `bodega_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bodega_id` (`bodega_id`),
  CONSTRAINT `novedad_evento_ibfk_1` FOREIGN KEY (`bodega_id`) REFERENCES `bodega` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad_evento`
--

LOCK TABLES `novedad_evento` WRITE;
/*!40000 ALTER TABLE `novedad_evento` DISABLE KEYS */;
INSERT INTO `novedad_evento` VALUES (25,'CÃ³digo Descuento1','DescripciÃ³n NovedadEvento1',1,'2024-10-22 23:36:01.356000','Nombre Evento1',NULL);
/*!40000 ALTER TABLE `novedad_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedades_eventos`
--

DROP TABLE IF EXISTS `novedades_eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `novedades_eventos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_descuento_premium` varchar(255) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `es_solo_premium` bit(1) DEFAULT NULL,
  `fecha_hora_evento` datetime(6) DEFAULT NULL,
  `nombre_evento` varchar(255) DEFAULT NULL,
  `bodega_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedades_eventos`
--

LOCK TABLES `novedades_eventos` WRITE;
/*!40000 ALTER TABLE `novedades_eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `novedades_eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (22,'Argentina');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paise`
--

DROP TABLE IF EXISTS `paise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paise`
--

LOCK TABLES `paise` WRITE;
/*!40000 ALTER TABLE `paise` DISABLE KEYS */;
/*!40000 ALTER TABLE `paise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `pais_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pais_id` (`pais_id`),
  CONSTRAINT `provincia_ibfk_1` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (47,'Mendoza',22),(48,'Cordoba',NULL);
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `pais_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel19sxflyst9no84b8c9r94ye` (`pais_id`),
  CONSTRAINT `FKel19sxflyst9no84b8c9r94ye` FOREIGN KEY (`pais_id`) REFERENCES `paises` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(100) DEFAULT NULL,
  `capital` varchar(100) DEFAULT NULL,
  `provincia_id` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `provincia_id` (`provincia_id`),
  CONSTRAINT `region_ibfk_1` FOREIGN KEY (`provincia_id`) REFERENCES `provincia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (94,NULL,NULL,47,'Ciudad','Capital'),(95,NULL,NULL,NULL,'Ciudad2','Capital1'),(96,NULL,NULL,NULL,'Ciudad3','Capital2'),(97,NULL,NULL,NULL,'Ciudad4','Capital3'),(98,NULL,NULL,NULL,'Ciudad5','Capital4'),(99,NULL,NULL,NULL,'Ciudad6','Capital5');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resena`
--

DROP TABLE IF EXISTS `resena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resena` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) DEFAULT NULL,
  `es_destacada` tinyint(1) DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `puntuacion` double DEFAULT NULL,
  `vino_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vino_id` (`vino_id`),
  CONSTRAINT `resena_ibfk_1` FOREIGN KEY (`vino_id`) REFERENCES `vino` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resena`
--

LOCK TABLES `resena` WRITE;
/*!40000 ALTER TABLE `resena` DISABLE KEYS */;
INSERT INTO `resena` VALUES (141,'Comentario Resena1',1,'2024-10-22 23:36:01.392000',4.5,137),(142,'Comentario Resena2',0,'2024-10-22 23:36:01.392000',3.5,138),(143,'Comentario Resena3',1,'2024-10-22 23:36:01.392000',4.6,139),(144,'Comentario Resena4',1,'2024-10-22 23:36:01.392000',4.7,140),(145,'Comentario Resena5',1,'2024-10-22 23:36:01.392000',4.9,141),(146,'Comentario Resena6',0,'2024-10-22 23:36:01.392000',4,142),(147,'Comentario Resena7',1,'2024-10-22 23:36:01.392000',3.9,143),(148,'Comentario Resena8',1,'2024-10-22 23:36:01.392000',5,144),(149,'Comentario Resena10',1,'2024-10-22 23:36:01.392000',3.7,146),(150,'Comentario Resena11',1,'2024-10-22 23:36:01.392000',5.4,147),(151,'Comentario Resena12',0,'2024-10-22 23:36:01.392000',3.4,148),(152,'Comentario Resena13',1,'2024-10-22 23:36:01.392000',5.2,149),(153,'Comentario Resena14',1,'2024-10-22 23:36:01.392000',5.8,150),(154,'Comentario Resena15',1,'2024-10-22 23:36:01.392000',5.7,151);
/*!40000 ALTER TABLE `resena` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_uva`
--

DROP TABLE IF EXISTS `tipo_uva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_uva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_uva`
--

LOCK TABLES `tipo_uva` WRITE;
/*!40000 ALTER TABLE `tipo_uva` DISABLE KEYS */;
INSERT INTO `tipo_uva` VALUES (46,'DescripciÃ³n TipoUva1','Nombre TipoUva1'),(47,'DescripciÃ³n TipoUva2','Nombre TipoUva2'),(48,'DescripciÃ³n TipoUva3','Nombre TipoUva3');
/*!40000 ALTER TABLE `tipo_uva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varietal`
--

DROP TABLE IF EXISTS `varietal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `varietal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `porcentaje` double DEFAULT NULL,
  `tipo_uva_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_uva_id` (`tipo_uva_id`),
  CONSTRAINT `varietal_ibfk_1` FOREIGN KEY (`tipo_uva_id`) REFERENCES `tipo_uva` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varietal`
--

LOCK TABLES `varietal` WRITE;
/*!40000 ALTER TABLE `varietal` DISABLE KEYS */;
INSERT INTO `varietal` VALUES (46,'DescripciÃ³n Varietal1',0.5,46),(47,'DescripciÃ³n Varietal2',0.5,47),(48,'DescripciÃ³n Varietal3',0.5,48);
/*!40000 ALTER TABLE `varietal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vino`
--

DROP TABLE IF EXISTS `vino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vino` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `calificacion` double DEFAULT NULL,
  `fecha_cosecha` datetime(6) DEFAULT NULL,
  `imagen_url` varbinary(255) DEFAULT NULL,
  `bodega_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bodega_id` (`bodega_id`),
  CONSTRAINT `vino_ibfk_1` FOREIGN KEY (`bodega_id`) REFERENCES `bodega` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vino`
--

LOCK TABLES `vino` WRITE;
/*!40000 ALTER TABLE `vino` DISABLE KEYS */;
INSERT INTO `vino` VALUES (137,'Nombre Vino1',200,4.5,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen1.comx',21),(138,'Nombre Vino2',100,3.5,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen2.comx',21),(139,'Nombre Vino3',300,4.6,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen3.comx',21),(140,'Nombre Vino4',400,4.7,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen4.comx',21),(141,'Nombre Vino5',500,4.9,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen5.comx',21),(142,'Nombre Vino6',600,4,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen6.comx',21),(143,'Nombre Vino7',700,3.9,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen7.comx',21),(144,'Nombre Vino8',800,5,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen8.comx',21),(145,'Nombre Vino9',900,4.4,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen9.comx',21),(146,'Nombre Vino10',300,3.7,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen10.comx',21),(147,'Nombre Vino11',450,5.4,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen11.comx',21),(148,'Nombre Vino12',550,3.4,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen12.comx',21),(149,'Nombre Vino13',650,5.2,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen13.comx',21),(150,'Nombre Vino14',750,5.8,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen14.comx',21),(151,'Nombre Vino15',950,5.7,'2024-10-22 23:36:01.392000',_binary '¬\í\0sr\0java.net.URI¬x.CžI«\0L\0stringt\0Ljava/lang/String;xpt\0http://imagen15.comx',21);
/*!40000 ALTER TABLE `vino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vino_maridaje`
--

DROP TABLE IF EXISTS `vino_maridaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vino_maridaje` (
  `vino_id` int NOT NULL,
  `maridaje_id` int NOT NULL,
  PRIMARY KEY (`vino_id`,`maridaje_id`),
  KEY `maridaje_id` (`maridaje_id`),
  CONSTRAINT `vino_maridaje_ibfk_1` FOREIGN KEY (`vino_id`) REFERENCES `vino` (`id`),
  CONSTRAINT `vino_maridaje_ibfk_2` FOREIGN KEY (`maridaje_id`) REFERENCES `maridaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vino_maridaje`
--

LOCK TABLES `vino_maridaje` WRITE;
/*!40000 ALTER TABLE `vino_maridaje` DISABLE KEYS */;
INSERT INTO `vino_maridaje` VALUES (137,46),(138,47),(139,48),(140,48),(141,48),(142,48),(143,48),(144,48),(145,48),(146,48),(147,48),(148,48),(149,48),(150,48),(151,48);
/*!40000 ALTER TABLE `vino_maridaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vino_varietal`
--

DROP TABLE IF EXISTS `vino_varietal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vino_varietal` (
  `vino_id` int NOT NULL,
  `varietal_id` int NOT NULL,
  PRIMARY KEY (`vino_id`,`varietal_id`),
  KEY `varietal_id` (`varietal_id`),
  CONSTRAINT `vino_varietal_ibfk_1` FOREIGN KEY (`vino_id`) REFERENCES `vino` (`id`),
  CONSTRAINT `vino_varietal_ibfk_2` FOREIGN KEY (`varietal_id`) REFERENCES `varietal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vino_varietal`
--

LOCK TABLES `vino_varietal` WRITE;
/*!40000 ALTER TABLE `vino_varietal` DISABLE KEYS */;
INSERT INTO `vino_varietal` VALUES (137,46),(138,46),(139,46),(140,46),(141,46),(142,46),(143,46),(144,46),(145,46),(146,46),(147,46),(148,46),(149,46),(150,46),(151,46),(137,47),(138,47),(139,47),(140,47),(141,47),(142,47),(143,47),(144,47),(145,47),(146,47),(147,47),(148,47),(149,47),(150,47),(151,47),(137,48),(138,48),(139,48),(140,48),(141,48),(142,48),(143,48),(144,48),(145,48),(146,48),(147,48),(148,48),(149,48),(150,48),(151,48);
/*!40000 ALTER TABLE `vino_varietal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-22 21:33:18
