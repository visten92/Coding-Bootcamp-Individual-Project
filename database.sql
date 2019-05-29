-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: individual
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `receiver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('admin','level1_a','hello world','2019-01-17 20:58:42',89),('level1_a','level1_a','hello world','2019-01-17 20:58:55',90),('level1_b','level1_a','hello world','2019-01-17 20:59:20',91),('level2_b','level1_a','hello world','2019-01-17 20:59:37',92),('level3_a','level1_a','hello world','2019-01-17 21:18:42',93),('level3_b','level1_a','hello world','2019-01-17 21:19:02',94),('level4_a','level1_a','hello world','2019-01-17 21:19:23',95),('level4_b','level1_a','hello world','2019-01-17 21:19:56',96),('admin','level1_a','hello world','2019-01-17 21:20:42',97),('level1_a','level1_b','hello world','2019-01-17 21:21:09',98),('level1_a','level1_b','hello world','2019-01-17 21:21:41',99),('level1_a','level2_a','hello world','2019-01-17 21:22:26',100),('level2_a','level2_a','hello world','2019-01-17 21:23:09',101),('level2_b','level2_a','hello world','2019-01-17 21:23:22',102),('level3_a','level2_a','hello world','2019-01-17 21:23:31',103),('level3_b','level2_a','hello world','2019-01-17 21:23:44',104),('level4_a','level2_a','hello world','2019-01-17 21:23:52',105),('level4_b','level2_a','hello world','2019-01-17 21:24:07',106),('admin','level2_a','hello world','2019-01-17 21:24:14',107),('level1_a','level2_b','hello world','2019-01-17 21:24:43',108),('level1_b','level2_b','hello world','2019-01-17 21:24:50',109),('level2_a','level2_b','hello world','2019-01-17 21:24:56',110),('level2_b','level2_b','hello world','2019-01-17 21:25:03',111),('level3_a','level2_b','hello world','2019-01-17 21:25:09',112),('level4_a','level2_b','hello world','2019-01-17 21:25:29',113),('level1_a','level3_a','hello world','2019-01-17 21:26:46',114),('level1_b','level3_a','hello world','2019-01-17 21:26:52',115),('level2_a','level3_a','hello world','2019-01-17 21:26:56',116),('level2_b','level3_a','hello world','2019-01-17 21:27:03',117),('level3_a','level3_a','hello world','2019-01-17 21:27:18',118),('level3_b','level3_a','hello world','2019-01-17 21:27:40',119),('level4_a','level3_a','hello world','2019-01-17 21:27:46',120),('level4_b','level3_a','hello world','2019-01-17 21:27:52',121),('admin','level3_a','hello world','2019-01-17 21:27:58',122),('level1_a','level3_b','hello world','2019-01-17 21:28:38',123),('level1_b','level3_b','hello world','2019-01-17 21:28:43',124),('level2_b','level3_b','hello world','2019-01-17 21:28:54',125),('level3_a','level3_b','hello world','2019-01-17 21:29:00',126),('level3_b','level3_b','hello world','2019-01-17 21:29:06',127),('level4_a','level3_b','hello world','2019-01-17 21:29:12',128),('level4_b','level3_b','hello world','2019-01-17 21:29:20',129),('admin','level3_b','hello world','2019-01-17 21:29:25',130),('level1_a','level4_a','hello world','2019-01-17 21:29:42',131),('level1_b','level4_a','hello world','2019-01-17 21:29:46',132),('level2_a','level4_a','hello world','2019-01-17 21:29:51',133),('level2_b','level4_a','hello world','2019-01-17 21:30:12',134),('level3_a','level4_a','hello world','2019-01-17 21:30:17',135),('level3_b','level4_a','hello world','2019-01-17 21:30:23',136),('level4_a','level4_a','hello world','2019-01-17 21:30:30',137),('level4_b','level4_a','hello world','2019-01-17 21:30:36',138),('admin','level4_a','hello world','2019-01-17 21:30:42',139),('level1_a','level4_b','hello world','2019-01-17 21:31:49',140),('level2_a','level4_b','hello world','2019-01-17 21:31:54',141),('level1_b','level4_b','hello world','2019-01-17 21:32:22',142),('level2_b','level4_b','hello world','2019-01-17 21:32:53',143),('level3_a','level4_b','hello world','2019-01-17 21:32:58',144),('level3_b','level4_b','hello world','2019-01-17 21:33:03',145),('level4_a','level4_b','hello world','2019-01-17 21:33:08',146),('level4_b','level4_b','hello world','2019-01-17 21:33:12',147),('admin','level4_b','hello world','2019-01-17 21:33:22',148),('level1_a','admin','hello world','2019-01-17 21:33:46',149),('level1_b','admin','hello world','2019-01-17 21:33:52',150),('level2_a','admin','hello world','2019-01-17 21:33:55',151),('level2_b','admin','hello world','2019-01-17 21:34:16',152),('level3_a','admin','hello world','2019-01-17 21:34:20',153),('level3_b','admin','hello world','2019-01-17 21:34:27',154),('level4_a','admin','hello world','2019-01-17 21:34:33',155),('level4_b','admin','hello world','2019-01-17 21:34:38',156),('admin','admin','hello world','2019-01-17 21:34:42',157),('skatiafsdfsd','blax','setsefs','2019-01-26 18:36:27',160);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `first_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nickname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  `password` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_adress_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Stefanos','Nikolopoulos','admin','admin',5,'admin',1),('Stelios','Giannakopoulos','level1_b','level1_b',1,'1234',3),('mixalis','Kostopoulos','level2_a','level2_a',2,'1234',4),('Katerina','Aleksiou','level2_b','level2_b',2,'1234',5),('Antreas','Kougianos','level3_a','level3_a',3,'1234',6),('Nikos','Manesis','level3_b','level3_b',3,'1234',7),('Nikolaos','Mpelias','level4_a','level4_a',4,'1234',8),('Giorgos','Pasparakis','level4_b','level4_b',4,'1234',9);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-29 21:14:08
