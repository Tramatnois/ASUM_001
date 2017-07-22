-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: ASUM_GmbH
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `characteristic_group_tab`
--

DROP TABLE IF EXISTS `characteristic_group_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characteristic_group_tab` (
  `idcharacteristic_group` int(11) NOT NULL,
  `groupnumber` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcharacteristic_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characteristic_group_tab`
--

LOCK TABLES `characteristic_group_tab` WRITE;
/*!40000 ALTER TABLE `characteristic_group_tab` DISABLE KEYS */;
INSERT INTO `characteristic_group_tab` VALUES (1,1,'Begutachtung der Lagerbedingung'),(2,2,'Begutachtung der Ladehilfsmittel'),(3,3,'Begutachtung des Regalsystems');
/*!40000 ALTER TABLE `characteristic_group_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characteristic_tab`
--

DROP TABLE IF EXISTS `characteristic_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characteristic_tab` (
  `idcharacteristic` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inspectionplan_id` int(11) NOT NULL,
  `characteristic_type_id` int(11) NOT NULL,
  `characteristic_group_id` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcharacteristic`),
  KEY `inspectionplan_id_idx` (`inspectionplan_id`),
  KEY `characteristic_type_id_idx` (`characteristic_type_id`),
  KEY `characteristic_group_id_idx` (`characteristic_group_id`),
  CONSTRAINT `fk_characteristic_chargroup` FOREIGN KEY (`characteristic_group_id`) REFERENCES `characteristic_group_tab` (`idcharacteristic_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_characteristic_chartype` FOREIGN KEY (`characteristic_type_id`) REFERENCES `characteristic_type_tab` (`idcharacteristic_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_characteristic_inspplan` FOREIGN KEY (`inspectionplan_id`) REFERENCES `inspectionplan_tab` (`idinspectionplan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characteristic_tab`
--

LOCK TABLES `characteristic_tab` WRITE;
/*!40000 ALTER TABLE `characteristic_tab` DISABLE KEYS */;
INSERT INTO `characteristic_tab` VALUES (1,'Entspricht der Aufbau des Regals den Herstellerangaben?',1,1,1,0),(2,'Ist eine ausreichende Beleuchtung der Lagerfläche gewährleistet? (Tageslichteinfall, Ausreichende Beleuchtungseinheiten)',1,2,1,1),(3,'Sind Ladehilfsmittel beschädigt? (abstehende Palettenteile, defekte Gitterboxen)',1,1,2,1),(4,'Sind die Ladehilfsmittel ordentlich beladen? (überstehende Kartonage, überladene Ladungsträger)',1,1,2,2),(5,'Schäden durch Stoßeinwirkung an Teilen der Konstruktion? (\r,Diagonalstrebe/Horizontalstrebe,Rahmenprofil/Holm,\rDurchschubsicherung/Tiefensteg)',1,1,3,1),(6,'Ist an jeder Stütze ein Anfahrschutz vorhanden?',1,1,3,2);
/*!40000 ALTER TABLE `characteristic_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characteristic_type_tab`
--

DROP TABLE IF EXISTS `characteristic_type_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characteristic_type_tab` (
  `idcharacteristic_type` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcharacteristic_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characteristic_type_tab`
--

LOCK TABLES `characteristic_type_tab` WRITE;
/*!40000 ALTER TABLE `characteristic_type_tab` DISABLE KEYS */;
INSERT INTO `characteristic_type_tab` VALUES (1,'checkbox_single'),(2,'single_results');
/*!40000 ALTER TABLE `characteristic_type_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_tab`
--

DROP TABLE IF EXISTS `customer_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_tab` (
  `idcustomer` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `zipcode` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `contactperson` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_tab`
--

LOCK TABLES `customer_tab` WRITE;
/*!40000 ALTER TABLE `customer_tab` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection_comment_tab`
--

DROP TABLE IF EXISTS `inspection_comment_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspection_comment_tab` (
  `idinspection_comment` int(11) NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `inspection_id` int(11) NOT NULL,
  PRIMARY KEY (`idinspection_comment`),
  KEY `inspection_id_idx` (`inspection_id`),
  CONSTRAINT `fk_inspcomment_inspresult` FOREIGN KEY (`inspection_id`) REFERENCES `inspection_result_tab` (`idinspection_result`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection_comment_tab`
--

LOCK TABLES `inspection_comment_tab` WRITE;
/*!40000 ALTER TABLE `inspection_comment_tab` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspection_comment_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection_operation_tab`
--

DROP TABLE IF EXISTS `inspection_operation_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspection_operation_tab` (
  `idinspection_operation` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `inspector_id` int(11) NOT NULL,
  `inspectionplan_id` int(11) NOT NULL,
  PRIMARY KEY (`idinspection_operation`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `inspectionplan_id_idx` (`inspectionplan_id`),
  KEY `fk_inspector-idinspector_idx` (`inspector_id`),
  CONSTRAINT `fk_inspop_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer_tab` (`idcustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inspop_inspector` FOREIGN KEY (`inspector_id`) REFERENCES `inspector_tab` (`idinspector`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inspop_inspplan` FOREIGN KEY (`inspectionplan_id`) REFERENCES `inspectionplan_tab` (`idinspectionplan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection_operation_tab`
--

LOCK TABLES `inspection_operation_tab` WRITE;
/*!40000 ALTER TABLE `inspection_operation_tab` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspection_operation_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection_result_tab`
--

DROP TABLE IF EXISTS `inspection_result_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspection_result_tab` (
  `idinspection_result` int(11) NOT NULL,
  `result` varchar(45) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `inspection_operation_id` int(11) NOT NULL,
  `characteristic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idinspection_result`),
  KEY `inspection_operation_id_idx` (`inspection_operation_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `characteristic_id_idx` (`characteristic_id`),
  CONSTRAINT `fk_inspresult_characteristic` FOREIGN KEY (`characteristic_id`) REFERENCES `characteristic_tab` (`idcharacteristic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inspresult_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer_tab` (`idcustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inspresult_inspoperation` FOREIGN KEY (`inspection_operation_id`) REFERENCES `inspection_operation_tab` (`idinspection_operation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection_result_tab`
--

LOCK TABLES `inspection_result_tab` WRITE;
/*!40000 ALTER TABLE `inspection_result_tab` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspection_result_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspectionplan_tab`
--

DROP TABLE IF EXISTS `inspectionplan_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspectionplan_tab` (
  `idinspectionplan` int(11) NOT NULL,
  `norm` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idinspectionplan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspectionplan_tab`
--

LOCK TABLES `inspectionplan_tab` WRITE;
/*!40000 ALTER TABLE `inspectionplan_tab` DISABLE KEYS */;
INSERT INTO `inspectionplan_tab` VALUES (1,'DIN EN 15635','Regalprüfung nach DIN EN 15635');
/*!40000 ALTER TABLE `inspectionplan_tab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspector_tab`
--

DROP TABLE IF EXISTS `inspector_tab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inspector_tab` (
  `idinspector` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idinspector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspector_tab`
--

LOCK TABLES `inspector_tab` WRITE;
/*!40000 ALTER TABLE `inspector_tab` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspector_tab` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-22 18:56:51
