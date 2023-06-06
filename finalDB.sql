CREATE DATABASE  IF NOT EXISTS `c_cats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `c_cats`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: c_cats
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` int NOT NULL,
  `street_number` varchar(10) NOT NULL,
  `street_name` varchar(20) NOT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'216','S 32nd Ave',NULL,'Yakima','WA','98902'),(2,'1501','N Alder St',NULL,'Ellensburg','WA','98926'),(3,'1401','N Alder St','432','Ellensburg','WA','98926');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_lines`
--

DROP TABLE IF EXISTS `order_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_lines` (
  `order_id` int NOT NULL,
  `order_line_num` int NOT NULL,
  `part_id` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `prebuilt_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`order_line_num`),
  KEY `order_lines_fk_parts_idx` (`part_id`),
  KEY `order_lines_fk_prebuilt_idx` (`prebuilt_name`),
  CONSTRAINT `order_lines_fk_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_lines_fk_parts` FOREIGN KEY (`part_id`) REFERENCES `part` (`part_id`),
  CONSTRAINT `order_lines_fk_prebuilt` FOREIGN KEY (`prebuilt_name`) REFERENCES `prebuilt` (`comp_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_lines`
--

LOCK TABLES `order_lines` WRITE;
/*!40000 ALTER TABLE `order_lines` DISABLE KEYS */;
INSERT INTO `order_lines` VALUES (1,1,0,1,NULL),(1,2,1,1,NULL),(1,3,NULL,NULL,'first rig'),(2,1,0,1,NULL),(2,2,7,1,NULL);
/*!40000 ALTER TABLE `order_lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL,
  `cust_id` varchar(6) NOT NULL,
  `card_type` varchar(45) NOT NULL,
  `exp_date` varchar(7) NOT NULL,
  `card_num` varchar(16) NOT NULL,
  `card_sec_code` varchar(3) NOT NULL,
  `order_date` date NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_fk_customer_users_idx` (`cust_id`),
  CONSTRAINT `orders_fk_users` FOREIGN KEY (`cust_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'C00001','VISA','07/2023','1234123412341234','123','2023-06-01'),(2,'C00001','DISCOVER','09/2034','4321432143214321','123','2023-06-01');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part` (
  `part_id` int NOT NULL,
  `part_name` varchar(45) NOT NULL,
  `part_desc` varchar(255) NOT NULL,
  `part_cat` varchar(45) NOT NULL,
  `part_price` decimal(13,2) NOT NULL,
  PRIMARY KEY (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (0,'HX1200','1200 Watt 80 PLUS® PLATINUM Certified Fully Modular PSU from Corsair','power_supply',275.80),(1,'HX1000','1000 Watt 80 PLUS® PLATINUM Certified Fully Modular PSU from Corsair','power_supply',254.99),(2,'HX 800','800 Watt 80 PLUS® PLATINUM Certified Fully Modular PSU from Corsair ','power_supply',221.98),(3,'Z790 Aorus Xtreme','Motherboard from Gigabyte','motherboard',799.99),(4,'MAG B660M Mortar WIFI','Motherboard from MSI','motherboard',173.23),(5,'ROG Strix B660-I Gaming WIFI','Motherboard from Asus ','motherboard',288.98),(6,'RTX 4090','Flagship GPU from NVIDIA','gpu',1599.99),(7,'RTX 4080','High-end GPU from NVIDIA','gpu',1199.99),(8,'RTX 4060','Budget GPU from NVIDIA','gpu',449.99),(9,'Radeon 7900 XTX','Flagship GPU from AMD','gpu',999.99),(10,'Radeon 7900 XT','High-end GPU from AMD','gpu',899.99),(11,'Radeon 6600 XT','Budget GPU from AMD','gpu',379.99),(12,'Ryzen 7 7800X3D','High performance CPU from AMD','cpu',449.99),(13,'Ryzen 5 7600X','Mid-range CPU from AMD','cpu',219.99),(14,'Core i9-13900K','High performance CPU from Intel','cpu',569.97),(15,'Core i5-13600K','Mid-range CPU from Intel','cpu',299.99),(16,'Trident Z5 Neo RGB DDR5-6000','32 GB of DDR5 Ram from G.Skill','ram',134.99),(17,'T-Force Xtreem ARGB DDR4-3600','16 GB of DDR4 Ram from TeamGroup','ram',99.99),(18,'Vengeance RGB Pro DDR4-3200','32 GB of DDR4 Ram form Corsair','ram',64.99),(19,'Samsung 990 Pro','2 TB M.2 NVMe SSD from Samsung','storage',189.99),(20,'Patriot Viper VPR400','1 TB M.2 NVMe SSD from Patriot','storage',74.99),(21,'Seagate BarraCuda','4 TB SATA HDD from Seagate','storage',85.99),(22,'Western Digital Blue Deskop','500 GB SATA HDD from Western Digital','storage',34.99),(23,'Fractal Design North','Compact ATX case from Fractal Design','case',169.99),(24,'Lian Li Lancool 216','Budget ATX case from Lian Li','case',109.99),(25,'NZXT H7 Flow','Airflow-focused case from NZXT','case',129.99),(26,'Radeon 7600XT','Budget GPU from AMD','gpu',450.99),(27,'Arc 750','new gpu from intel','gpu',199.99);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_inventory`
--

DROP TABLE IF EXISTS `part_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part_inventory` (
  `part_id` int NOT NULL,
  `qty` int NOT NULL,
  `pending_qty` int NOT NULL,
  PRIMARY KEY (`part_id`),
  CONSTRAINT `inventory_fk_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_inventory`
--

LOCK TABLES `part_inventory` WRITE;
/*!40000 ALTER TABLE `part_inventory` DISABLE KEYS */;
INSERT INTO `part_inventory` VALUES (0,18,0),(1,5,0),(2,8,0),(3,10,0),(4,6,0),(5,8,0),(6,7,0),(7,7,0),(8,4,0),(9,5,0),(10,9,0),(11,8,0),(12,5,0),(13,5,0),(14,3,0),(15,5,0),(16,5,0),(17,3,0),(18,5,0),(19,4,0),(20,4,0),(21,7,0),(22,5,0),(23,5,0),(24,4,0),(25,4,0),(26,6,0),(27,4,0);
/*!40000 ALTER TABLE `part_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prebuilt`
--

DROP TABLE IF EXISTS `prebuilt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prebuilt` (
  `comp_name` varchar(45) NOT NULL,
  `motherboard_id` int NOT NULL,
  `ps_id` int NOT NULL,
  `gpu_id` int NOT NULL,
  `cpu_id` int NOT NULL,
  `ram_id` int NOT NULL,
  `storage_id` int NOT NULL,
  `case_id` int NOT NULL,
  `price` decimal(13,2) NOT NULL,
  PRIMARY KEY (`comp_name`),
  KEY `prebuilt_fk_pspart_idx` (`ps_id`),
  KEY `prebuilt_fk_gpupart_idx` (`gpu_id`),
  KEY `prebuilt_fk__idx` (`cpu_id`),
  KEY `prebuilt_fk_rampart_idx` (`ram_id`),
  KEY `prebuilt_fk_storagepart_idx` (`storage_id`),
  KEY `prebuilt_fk_casepart_idx` (`case_id`),
  KEY `prebuilt_fk_part1_idx` (`motherboard_id`,`ps_id`,`gpu_id`,`cpu_id`,`ram_id`,`storage_id`,`case_id`),
  CONSTRAINT `prebuilt_fk_partcase` FOREIGN KEY (`case_id`) REFERENCES `part` (`part_id`),
  CONSTRAINT `prebuilt_fk_partcpu` FOREIGN KEY (`cpu_id`) REFERENCES `part` (`part_id`),
  CONSTRAINT `prebuilt_fk_partgpu` FOREIGN KEY (`gpu_id`) REFERENCES `part` (`part_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prebuilt_fk_partmb` FOREIGN KEY (`motherboard_id`) REFERENCES `part` (`part_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prebuilt_fk_partps` FOREIGN KEY (`ps_id`) REFERENCES `part` (`part_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prebuilt_fk_partram` FOREIGN KEY (`ram_id`) REFERENCES `part` (`part_id`),
  CONSTRAINT `prebuilt_fk_partstorage` FOREIGN KEY (`storage_id`) REFERENCES `part` (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prebuilt`
--

LOCK TABLES `prebuilt` WRITE;
/*!40000 ALTER TABLE `prebuilt` DISABLE KEYS */;
INSERT INTO `prebuilt` VALUES ('first rig',4,0,9,12,17,21,25,1999.99);
/*!40000 ALTER TABLE `prebuilt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_orders`
--

DROP TABLE IF EXISTS `repair_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_orders` (
  `cust_id` varchar(6) NOT NULL,
  `rep_order_id` int NOT NULL,
  `repair_time` datetime NOT NULL,
  `problem` varchar(255) NOT NULL,
  PRIMARY KEY (`cust_id`,`rep_order_id`),
  CONSTRAINT `rep_orders_fk_users` FOREIGN KEY (`cust_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_orders`
--

LOCK TABLES `repair_orders` WRITE;
/*!40000 ALTER TABLE `repair_orders` DISABLE KEYS */;
INSERT INTO `repair_orders` VALUES ('C00001',0,'2023-04-29 01:00:00','broken gpu fan'),('C00002',1,'2023-06-06 02:30:00','My video output is getting artifacts when playing TF2, i think my gpu is dying :c');
/*!40000 ALTER TABLE `repair_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(6) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `f_name` varchar(45) NOT NULL,
  `l_name` varchar(45) NOT NULL,
  `address_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `cust_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('00002','variable','pass','last','user',1),('C00001','jmike','abc123','Michael','Jackson',1),('C00002','Jmoore','test','Jason','Moore',3),('E00001','nmill','itsmillertime','Nathan ','Miller',1),('E00002','Jrob','Jake2020','Jake','Robinson',2),('E00003','bqual','123apple','Brayden','Qualman',1),('M00004','ksam','kateisgreat','Kate','Samuleson',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-06 10:39:44
