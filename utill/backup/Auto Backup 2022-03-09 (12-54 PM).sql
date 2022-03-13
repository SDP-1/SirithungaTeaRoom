-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: sirithunga_tea_room
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `contactNo1` varchar(10) DEFAULT NULL,
  `contactNo2` varchar(10) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `customer_cid_uindex` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (8,'SEHAN','0714688665','0772829780','wathu putha'),(9,'AJITH 1','','','Nakande malu Ajith'),(10,'AJITH 2','','','nakande roti Ajith'),(15,'PASAN','','',' ');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deletereqbill`
--

DROP TABLE IF EXISTS `deletereqbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deletereqbill` (
  `invoiceNo` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deletereqbill`
--

LOCK TABLES `deletereqbill` WRITE;
/*!40000 ALTER TABLE `deletereqbill` DISABLE KEYS */;
INSERT INTO `deletereqbill` VALUES (1041,'2022-02-27','08:05:47 PM'),(1042,'2022-02-27','09:28:25 PM');
/*!40000 ALTER TABLE `deletereqbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `code` int NOT NULL,
  `code2` int NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL,
  `printName` varchar(50) NOT NULL,
  `barCode` varchar(50) DEFAULT NULL,
  `supplier` varchar(50) DEFAULT NULL,
  `stock` decimal(10,2) DEFAULT '0.00',
  `decimal` tinyint(1) NOT NULL DEFAULT '0',
  `priceOfBuying` decimal(10,2) DEFAULT '0.00',
  `markPrice` decimal(10,2) NOT NULL DEFAULT '0.00',
  `retilPriceRatio` decimal(5,2) DEFAULT '0.00',
  `wholeSalePriceRatio` decimal(5,2) DEFAULT '0.00',
  `retailPrice` decimal(10,2) DEFAULT '0.00',
  `wholeSalePrice` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`code`,`code2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (100,0,'MORTIN 600ML','MORTIN 600ML','4792037130923','',24.00,0,0.00,665.00,0.00,0.00,660.00,0.00),(101,0,'LEMON PUFF 200G','LEMON PUFF 200G','8888101090401','MUNCHEE',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(102,0,'PASYALE ANTUREL ASAMODAGAM 375ML','PASYALE ANTUREL ASAMODAGAM 375ML','4796014750012','PASYALE',0.00,0,0.00,100.00,0.00,0.00,100.00,0.00),(102,1,'PASYALE ANTUREL ASAMODAGAM 375ML','PASYALE ANTUREL ASAMODAGAM 375ML','4796014750012','PASYALE',17.00,0,0.00,150.00,0.00,0.00,145.00,0.00),(103,0,'HIGHLAND MILK POWDER 400G','HIGHLAND MILK POWDER 400G','4792094003086','',0.00,0,0.00,470.00,0.00,0.00,465.00,0.00),(104,0,'LEMON PUFF 100G','LEMON PUFF 100G','8888101090203','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(105,0,'ORANGE NECTAR 1L','ORANGE NECTAR 1L','4792143133412','KIST',0.00,0,0.00,270.00,0.00,0.00,270.00,0.00),(106,0,'RATHMAL BABY SHOP','RATHMAL BABY SHOP','4792054006263','PANDA BABY',0.00,0,0.00,60.00,0.00,0.00,58.00,57.00),(107,0,'ORANGE CREEM BISCUITS 100G','ORANGE CREEM BISCUITS 100G','4792192847544','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(108,0,'CHOCOLATE PUFF 100G','CHOCOLATE PUFF 100G','8888101271206','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(109,0,'NESTOMALT','NESTOMALT','4792024016179','NESTLE',500.00,0,0.00,570.00,0.00,0.00,560.00,558.00),(110,0,'CHOCOLATE PUFF 200G','CHOCOLATE PUFF 200G','8888101271404','MUNCHEE',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(111,0,'CHEESE CRACKER 100G','CHEESE CRACKER 100G','8888101131203','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(112,0,'PARIPPU NO-1','PARIPPU NO-1','','',0.00,1,0.00,310.00,0.00,0.00,310.00,0.00),(112,1,'PARIPPU NO-2','PARIPPU NO-2','','',0.00,1,0.00,280.00,0.00,0.00,280.00,0.00),(113,0,'KAKULU HAL','KAKULU HAL','','',0.00,1,0.00,210.00,0.00,0.00,210.00,0.00),(113,1,'KAKULU HAL (POLISH)','KAKULU HAL (POLISH)','','',0.00,1,0.00,210.00,10.00,0.00,189.00,0.00),(113,2,'KAKULU HAL 3','KAKULU HAL 3','','',0.00,1,0.00,210.00,0.00,8.00,210.00,0.00),(114,0,'KOTHMALLI','KOTHMALLI','1111111111111','',0.00,1,0.00,450.00,0.00,0.00,445.00,0.00),(115,0,'ATLAS CR160','ATLAS CR160','4792210100262','ATLAS',23.00,0,0.00,160.00,0.00,0.00,155.00,0.00),(116,0,'SUPER CREEM CRACKER 125G','SUPER CREEM CRACKER 125G','8888101430153','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(117,0,'SUPER CREEM CRACKER 380G','SUPER CREEM CRACKER 380G','8888101430085','MUNCHEE',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(118,0,'HAWAIAN COOKIES 200G','HAWAIAN COOKIES 200G','8888101080402','MUNCHEE',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(119,0,'CHOCOLATE CREEM BUSCUITSB100G','CHOCOLATE CREEM BUSCUITSB100G','8888101270018','MUNCHEE',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(120,0,'CHOCOLATE CREEM BUSCUITSB100G','CHOCOLATE CREEM BUSCUITSB100G','4792192845366','MUNCHEE',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(121,0,'MILK SHORT CAKE 200G','MILK SHORT CAKE 200G','8888101030407','MUNCHEE',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(122,0,'TIKIRI MARIE 230G','TIKIRI MARIE 230G','4792192845809','MUNCHEE',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(123,0,'CUSTARD CREEM BISCUITS 240G','CUSTARD CREEM BISCUITS 240G','8888101275389','MUNCHEE',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(124,0,'NICE BUSCUITS 100G','NICE BUSCUITS 100G','8888101020200','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(125,0,'SUPER CREEM CRACKER 85G','SUPER CREEM CRACKER 85G','8888101430276','MUNCHEE',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(126,0,'SOYOGA BUSCUITS 80G','SOYOGA BUSCUITS 80G','8888101591281','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(127,0,'KOME RICE CRACKER 90G','KOME RICE CRACKER 90G','4792192845991','MUNCHEE',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(128,0,'HAWAIAN COOKIES 100G','HAWAIAN COOKIES 100G','8888101080204','MUNCHEE',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(129,0,'MILK SHORTIES 75G','MILK SHORTIES 75G','8888101306045','MUNCHEE',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(130,0,'SUN CRACKER 95G','SUN CRACKER 95G','8888101135454','MUNCHEE',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(131,0,'MILK SHORT CAKE 85G','MILK SHORT CAKE 85G','8888101030278','MUNCHEE',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(132,0,'CRUNCHEE CAROLS 100G','CRUNCHEE CAROLS 100G','8888101276201','MUNCHEE',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(133,0,'CUSTARD CREAM BUSCUITS 100G','CUSTARD CREAM BUSCUITS 100G','8888101275204','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(134,0,'CHOCOLATE MARIE 200G','CHOCOLATE MARIE 200G','4792192845823','MUNCHEE',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(135,0,'GINGER BUSCUITS 85G','GINGER BUSCUITS 85G','8888101570286','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(136,0,'TIKIRI MARI BUCUITS 80G','TIKIRI MARI BUCUITS 80G','8888101280208','MUNCHEE',0.00,0,0.00,50.00,0.00,0.00,50.00,0.00),(137,0,'TIFIN ORIGINAL 125G','TIFIN ORIGINAL 125G','8888101932190','MUNCHEE',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(138,0,'TIFIN ONION 125G','TIFIN ONION 125G','8888101931193','MUNCHEE',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(139,0,'SUN CRACKER 95G','SUN CRACKER 95G','8888101134457','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(140,0,'MILK SHRTIES CHOCO 70G','MILK SHRTIES CHOCO 70G','8888101629045','MUNCHEE',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(141,0,'CHOCOLATE MARIE 90G','CHOCOLATE MARIE 90G','8888101281205','MUNCHEE',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(142,0,'CHEESE CRACKER 200G','CHEESE CRACKER 200G','8888101131401','MUNCHEE',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(143,0,'MILK CREEM BUSCUITS 110G','MILK CREEM BUSCUITS 110G','4792192845274','MUNCHEE',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(144,0,'MARIE BUSCUITS 50G','MARIE BUSCUITS 50G','8888101287238','MUNCHEE',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(145,0,'CHOC SHOCK 90G','CHOC SHOCK 90G','4792192845380','MUNCHEE',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(146,0,'SNAK CRACKER 170G','SNAK CRACKER 170G','8888101610708','MUNCHEE',0.00,0,0.00,200.00,0.00,0.00,200.00,0.00),(147,0,'ONION BUSCUITS 250G','ONION BUSCUITS 250G','8888101613808','MUNCHEE',0.00,0,0.00,350.00,0.00,0.00,350.00,0.00),(148,0,'CHEESE BUTTONS BUSCUITS 215G','CHEESE BUTTONS BUSCUITS 215G','8888101611804','MUNCHEE',0.00,0,0.00,360.00,0.00,0.00,360.00,0.00),(149,0,'WAFERS LEMON CREAM 90G','WAFERS LEMON CREAM 90G','4791034072748','MALIBAN',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(150,0,'MILK SHORTCAKE 200G','MILK SHORTCAKE 200G','4791034070874','MALIBAN',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(151,0,'CREAM CRACKER 190G','CREAM CRACKER 190G','4791034042116','MALIBAN',0.00,0,0.00,110.00,0.00,0.00,110.00,0.00),(152,0,'CREAM CRACKER 125G','CREAM CRACKER 125G','4791034042611','MALIBAN',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(153,0,'CREAM CRACKER 230G','CREAM CRACKER 230G','4791034071604','MALIBAN',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(154,0,'CREAM CRACKER 500G','CREAM CRACKER 500G','4791034042215','MALIBAN',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(155,0,'REAL CHOCOLATE 100G','REAL CHOCOLATE 100G','4791034005111','MALIBAN',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(156,0,'REAL CHOCOLATE 200G','REAL CHOCOLATE 200G','4791034072366','MALIBAN',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(157,0,'REAL CHOCOLATE 400G','REAL CHOCOLATE 400G','4791034070546','MALIBAN',0.00,0,0.00,270.00,0.00,0.00,270.00,0.00),(158,0,'LEMON PUFF 100G','LEMON PUFF 100G','4791034017114','MALIBAN',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(159,0,'LEMON PUFF 200G','LEMON PUFF 200G','4791034017015','MALIBAN',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(160,0,'GINGER BISCUIT 80G','GINGER BISCUIT 80G','4791034070232','MALIBAN',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(161,0,'GINGER BISCUIT 240G','GINGER BISCUIT 240G','4791034070263','MALIBAN',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(162,0,'GINGER BISCUIT 370G','GINGER BISCUIT 370G','4791034070218','MALIBAN',0.00,0,0.00,240.00,0.00,0.00,240.00,0.00),(163,0,'CHOCOLATE PUF BISCUIT 100G','CHOCOLATE PUF BISCUIT 100G','4791034070270','MALIBAN',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(164,0,'CHOCOLATE PUF BISCUIT 200G','CHOCOLATE PUF BISCUIT 200G','4791034070287','MALIBAN',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(165,0,'WHITE CHOCOLATE PUFF 200G','WHITE CHOCOLATE PUFF 200G','4791034070096','MALIBAN',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(166,0,'GOLD MARIE 75G','GOLD MARIE 75G','4791034027410','MALIBAN',0.00,0,0.00,45.00,0.00,0.00,45.00,0.00),(167,0,'GOLD MARIE 230G','GOLD MARIE 230G','4791034072465','MALIBAN',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(168,0,'GOLD MARIE 350G','GOLD MARIE 350G','4791034071567','MALIBAN',0.00,0,0.00,200.00,0.00,0.00,200.00,0.00),(169,0,'ORANGE CREAM BUSCUIT 100G','ORANGE CREAM BUSCUIT 100G','4791034021111','MALIBAN',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(170,0,'ORANGE CREAM BUSCUIT 410G','ORANGE CREAM BUSCUIT 410G','4791034070614','MALIBAN',0.00,0,0.00,250.00,0.00,0.00,250.00,0.00),(171,0,'CUSTARD CREAM BISCUIT 100G','CUSTARD CREAM BISCUIT 100G','4791034008112','MALIBAN',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(172,0,'CUSTARD CREAM BISCUIT 200G','CUSTARD CREAM BISCUIT 200G','4791034072656','MALIBAN',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(173,0,'CUSTARD CREAM BISCUIT 410G','CUSTARD CREAM BISCUIT 410G','4791034070607','MALIBAN',0.00,0,0.00,250.00,0.00,0.00,250.00,0.00),(174,0,'LIGHT MARIE 50G','LIGHT MARIE 50G','4791034027915','MALIBAN',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(175,0,'MILK SHORTCAKE 85G','MILK SHORTCAKE 85G','4791034039215','MALIBAN',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(176,0,'REAL BRAN CRACKER 210G','REAL BRAN CRACKER 210G','4791034001519','MALIBAN',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(177,0,'HAWAIIAN COOKIES 100G','HAWAIIAN COOKIES 100G','4791034057110','MALIBAN',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(178,0,'HAWAIIAN COOKIES 200G','HAWAIIAN COOKIES 200G','4791034057011','MALIBAN',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(179,0,'NICE 100G','NICE 100G','4791034020114','MALIBAN',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(180,0,'NICE 4353G','NICE 4353G','4791034070249','MALIBAN',0.00,0,0.00,230.00,0.00,0.00,230.00,0.00),(181,0,'CRACKER THINS 190G','CRACKER THINS 190G','4791034073035','MALIBAN',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(182,0,'SPICY CRACKERS 170G','SPICY CRACKERS 170G','4791034060110','MALIBAN',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(183,0,'VEGGIE CRACKER 170G','VEGGIE CRACKER 170G','4791034072298','MALIBAN',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(184,0,'MILKY SORTIES 325G','MILKY SORTIES 325G','4791034032216','MALIBAN',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(185,0,'CHOCOLATE MARIE 300G','CHOCOLATE MARIE 300G','4791034070355','MALIBAN',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(186,0,'WAFERS VANILA CREAM 225G','WAFERS VANILA CREAM 225G','4791034071673','MALIBAN',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(187,0,'WAFERS VANILA CREAM 400G','WAFERS VANILA CREAM 400G','4791034071826','MALIBAN',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(188,0,'WAFERS CHOCOLATE CREAM 225G','WAFERS CHOCOLATE CREAM 225G','4791034071659','MALIBAN',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(189,0,'WAFERS CHOCOLATE CREAM 400G','WAFERS CHOCOLATE CREAM 400G','4791034071833','MALIBAN',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(190,0,'LEMON PUFF 400G','LEMON PUFF 400G','4791034070447','MALIBAN',0.00,0,0.00,280.00,0.00,0.00,280.00,0.00),(191,0,'KRISCO 170G','KRISCO 170G','4791034015318','MALIBAN',0.00,0,0.00,210.00,0.00,0.00,210.00,0.00),(192,0,'WHITE BORD MARKER','WHITE BORD MARKER','4796009862935','MANGO',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(193,0,'DEWENI BATHA(SUDU KAKULU) 350G','DEWENI BATHA(SUDU KAKULU) 350G','4792149097107','RAIGAM',0.00,0,0.00,165.00,0.00,0.00,165.00,0.00),(194,0,'DEWENI BATHA(RATHU KAKULU) 350G','DEWENI BATHA(RATHU KAKULU) 350G','4792149097206','RAIGAM',0.00,0,0.00,175.00,0.00,0.00,175.00,0.00),(195,0,'FAMILY MEAL PACK 335G','FAMILY MEAL PACK 335G','4792024015745','MAGGI',0.00,0,0.00,180.00,0.00,0.00,180.00,0.00),(196,0,'PAPARE KOTTU 77G','PAPARE KOTTU 77G','4792024016070','MAGGI',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(197,0,'DAIYA DEVILLED 76G','DAIYA DEVILLED 76G','4792024015769','MAGGI',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(198,0,'TOPPZ 80G','TOPPZ 80G','4792018233148','PRIMA',0.00,0,0.00,62.00,0.00,0.00,62.00,0.00),(199,0,'DAIYA DEVILLED 76G','DAIYA DEVILLED 76G','4792024015660','MAGGI',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(200,0,'KOTTU MEE 80G','KOTTU MEE 80G','4792018233131','PRIMA',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(201,0,'STELLA CHICKEN 74G','STELLA CHICKEN 74G','4792018234312','PRIMA',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(202,0,'MAGGI NOODLES 73G','MAGGI NOODLES 73G','4792024015646','MAGGI',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(203,0,'KOTTU MEE 80G','KOTTU MEE 80G','4792018233421','PRIMA',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(204,0,'HARISCHANDRA NOODLES - KAKULU 145G','HARISCHANDRA NOODLES 145G','4792083010316','HARISCHANDRA',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(205,0,'HARISCHANDRA NOODLES -KURAKKAN 400G','HARISCHANDRA NOODLES 400G','4792083010217','HARISCHANDRA ',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(206,0,'HARISCHANDRA NOODELES - SUDU 400G','HARISCHANDRA NOODELES 400G','4792083010118','HARISCHANDRA ',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(207,0,'WIJAYA NOOLDELS 400G','WIJAYA NOOLDELS 400G','4792173100088','WIJAYA',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(208,0,'SERA NOODLES 325G','SERA NOODLES 325G','4792109000666','SERA',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(209,0,'NIKADO NOODELES 400G','NIKADO NOODELES 400G','4792201007013','NIKADO',0.00,0,0.00,155.00,0.00,0.00,155.00,0.00),(210,0,'MILAN PASTA 1KG','MILAN PASTA 1KG','4792225001677','MILAN',0.00,0,0.00,380.00,0.00,0.00,380.00,0.00),(211,0,'NIKADO NOODELES 300G','NIKADO NOODELES 300G','4792201020845','NIKADO',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(212,0,'MDK HOPPER FLOUR - SUDU 1KG','MDK HOPPER FLOUR 1KG','4792101019147','MDK',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(213,0,'MDK HOPPER FLOUR -KAKULU 1KG','MDK HOPPER FLOUR 1KG','4792101019154','MDK',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(214,0,'SERA NOODELS 400G','SERA NOODELS 400G','4792109000659','SERA',0.00,0,0.00,450.00,0.00,0.00,450.00,0.00),(215,0,'HARISCHANDRA NOODLES 200G','HARISCHANDRA NOODLES 200G','4792083030451','HARISCHANDRA ',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(216,0,'HARISCHANDRA RICE NOODLES 200G','HARISCHANDRA RICE NOODLES 200G','4792083010514','HARISCHANDRA ',0.00,0,0.00,85.00,0.00,0.00,85.00,0.00),(217,0,'VARDA PASTA 400G','VARDA PASTA 400G','6291047000045','VARDA',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(218,0,'VARDA PASTA 400G','VARDA PASTA 400G','6291047000007','VARDA',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(219,0,'VARDA PASTA 400G','VARDA PASTA 400G','6291047073438','VARDA',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(220,0,'SRIPOSHA 400G','SRIPOSHA 400G','4797001101749','SRIPOSHA',0.00,0,0.00,175.00,0.00,0.00,175.00,0.00),(221,0,'SRIPOSHA 700G','SRIPOSHA 700G','4797001101787','SRIPOSHA ',0.00,0,0.00,295.00,0.00,0.00,295.00,0.00),(222,0,'MILAN PASTA 1KG','MILAN PASTA 1KG','4792225001660','MILAN',0.00,0,0.00,380.00,0.00,0.00,380.00,0.00);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `description` varchar(50) DEFAULT NULL,
  `path` text,
  UNIQUE KEY `location_description_uindex` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('larstBackUpLocation','C:\\Users\\sehan\\OneDrive\\Desktop');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `invoiceNo` int NOT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `retail` tinyint(1) DEFAULT NULL,
  `noOfItem` int DEFAULT NULL,
  `fullCost` decimal(10,2) DEFAULT NULL,
  `cash` decimal(10,2) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `Onloan` tinyint(1) DEFAULT '0',
  `discount` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`invoiceNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1000,'2022-02-22','10:01:51 AM','',1,3,216.00,500.00,284.00,0,4.00),(1002,'2022-02-23','10:17:55 AM','',1,1,58.00,500.00,442.00,0,2.00),(1005,'2022-02-23','07:31:07 PM','',1,2,116.00,116.00,0.00,0,4.00),(1006,'2022-02-23','07:33:07 PM','SEHAN',1,1,58.00,58.00,0.00,0,2.00),(1007,'2022-02-23','07:36:46 PM','DASUN',1,1,58.00,58.00,0.00,0,2.00),(1008,'2022-02-23','07:38:07 PM','',1,1,58.00,0.00,-58.00,1,2.00),(1009,'2022-02-25','01:03:33 AM','',1,1,58.00,60.00,2.00,0,2.00),(1010,'2022-02-25','01:04:59 AM','',1,1,58.00,60.00,2.00,0,2.00),(1011,'2022-02-25','09:10:31 AM','',1,1,58.00,60.00,2.00,0,2.00),(1012,'2022-02-25','09:36:56 AM','',1,5,506.00,600.00,94.00,0,14.00),(1013,'2022-02-25','09:47:26 AM','',1,3,303.00,400.00,97.00,0,7.00),(1014,'2022-02-25','09:50:21 AM','',1,2,158.00,500.00,342.00,0,2.00),(1015,'2022-02-25','10:12:57 AM','',1,3,303.00,400.00,97.00,0,7.00),(1016,'2022-02-25','10:17:47 AM','',1,3,303.00,500.00,197.00,0,7.00),(1017,'2022-02-25','10:19:37 AM','',1,2,158.00,500.00,342.00,0,2.00),(1018,'2022-02-25','10:27:33 AM','',1,3,303.00,500.00,197.00,0,7.00),(1019,'2022-02-25','10:34:11 AM','',1,3,303.00,500.00,197.00,0,7.00),(1020,'2022-02-25','10:36:54 AM','',1,4,448.00,500.00,52.00,0,12.00),(1021,'2022-02-25','10:40:44 AM','',1,3,303.00,500.00,197.00,0,7.00),(1022,'2022-02-25','10:54:26 AM','',1,3,303.00,500.00,197.00,0,7.00),(1023,'2022-02-25','11:05:07 AM','',1,3,303.00,5010.00,4707.00,0,7.00),(1024,'2022-02-25','11:12:46 AM','',1,1,58.00,500.00,442.00,0,2.00),(1025,'2022-02-25','12:58:51 PM','',1,5,1118.00,5000.00,3882.00,0,17.00),(1027,'2022-02-25','01:05:09 PM','',1,4,963.00,1000.00,37.00,0,12.00),(1028,'2022-02-25','06:48:27 PM','',1,1,58.00,58.00,0.00,0,2.00),(1029,'2022-02-25','06:54:30 PM','',1,1,58.00,58.00,0.00,0,2.00),(1030,'2022-02-25','06:56:38 PM','',1,1,58.00,58.00,0.00,0,2.00),(1031,'2022-02-25','07:01:48 PM','',1,1,58.00,58.00,0.00,0,2.00),(1032,'2022-02-25','07:06:15 PM','',1,1,58.00,58.00,0.00,0,2.00),(1033,'2022-02-25','07:10:03 PM','',1,1,58.00,58.00,0.00,0,2.00),(1034,'2022-02-25','07:20:17 PM','',1,1,58.00,58.00,0.00,0,2.00),(1035,'2022-02-25','07:22:07 PM','',1,1,58.00,58.00,0.00,0,2.00),(1036,'2022-02-25','07:24:28 PM','',1,1,58.00,58.00,0.00,0,2.00),(1037,'2022-02-25','07:33:36 PM','SEHAN',1,1,58.00,58.00,0.00,0,2.00),(1038,'2022-02-26','07:46:53 AM','',1,1,58.00,60.00,2.00,0,2.00),(1040,'2022-02-26','07:56:24 AM','',1,1,58.00,60.00,2.00,0,2.00),(1041,'2022-02-27','08:05:19 PM','',1,1,58.00,60.00,2.00,0,2.00),(1042,'2022-02-27','08:06:09 PM','',1,2,116.00,120.00,4.00,0,4.00),(1043,'2022-02-28','02:44:07 PM','',1,1,58.00,500.00,442.00,0,2.00),(1044,'2022-02-28','02:45:47 PM','',1,1,58.00,500.00,442.00,0,2.00),(1045,'2022-03-02','09:29:35 AM','',1,2,610.00,1000.00,390.00,0,0.00),(1046,'2022-03-02','09:47:39 AM','',1,2,108.00,200.00,92.00,0,12.00),(1047,'2022-03-02','08:09:03 PM','',1,1,58.00,60.00,2.00,0,2.00),(1048,'2022-03-07','12:52:54 PM','',1,1,58.00,0.00,-58.00,1,2.00),(1049,'2022-03-08','07:39:33 PM','',1,4,361.00,400.00,39.00,0,9.00),(1050,'2022-03-08','07:42:39 PM','',1,13,784.00,0.00,-784.00,1,36.00);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `invoiceNo` int DEFAULT NULL,
  `code1` int DEFAULT NULL,
  `code2` int DEFAULT NULL,
  `description` text,
  `qty` decimal(10,2) DEFAULT NULL,
  `markPrice` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `nextAmount` decimal(10,2) DEFAULT NULL,
  `buyingPrice` decimal(10,2) DEFAULT NULL,
  KEY `table_name_order__fk` (`invoiceNo`),
  CONSTRAINT `table_name_order__fk` FOREIGN KEY (`invoiceNo`) REFERENCES `order` (`invoiceNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1000,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,116.00,NULL),(1000,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1002,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1005,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,116.00,NULL),(1006,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1007,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1008,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1009,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1010,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1011,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1012,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,116.00,NULL),(1012,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1012,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',2.00,150.00,145.00,290.00,NULL),(1013,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1013,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1013,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1014,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1014,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1015,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1015,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1015,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1016,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1016,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1016,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1017,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1017,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1018,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1018,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1018,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1019,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1019,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1019,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1020,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1020,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',2.00,150.00,145.00,290.00,NULL),(1020,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1021,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1021,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1021,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1022,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1022,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1022,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1023,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1023,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1023,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1024,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1025,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1025,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1025,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1025,100,0,'MORTIN 600ML',1.00,665.00,660.00,660.00,NULL),(1025,115,0,'ATLAS CR160',1.00,160.00,155.00,155.00,NULL),(1027,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1027,100,0,'MORTIN 600ML',1.00,665.00,660.00,660.00,NULL),(1027,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,NULL),(1027,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,NULL),(1028,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1029,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1030,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1031,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1032,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1033,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1034,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1035,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1036,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1037,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1038,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1040,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1041,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1042,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,116.00,NULL),(1043,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1044,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,NULL),(1045,112,0,'PARIPPU NO-1',2.00,310.00,310.00,610.00,NULL),(1046,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,108.00,NULL),(1047,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,0.00),(1048,106,0,'RATHMAL BABY SHOP',1.00,60.00,58.00,58.00,0.00),(1049,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,0.00),(1049,102,1,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,150.00,145.00,145.00,0.00),(1049,106,0,'RATHMAL BABY SHOP',2.00,60.00,58.00,116.00,0.00),(1050,102,0,'PASYALE ANTUREL ASAMODAGAM 375ML',1.00,100.00,100.00,100.00,0.00),(1050,106,0,'RATHMAL BABY SHOP',12.00,60.00,57.00,684.00,0.00);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `owner` tinyint(1) DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `NIC` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `user_user_name_uindex` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('','',1,'NULL','123423432123',NULL),('nohim','12345',0,'NOHIM SATHSARA PANDITHA','123456789012','2010-02-22'),('Sehan','12345',0,'SEHAN DEVINDA PANDITHA','123456789012','2001-03-16'),('Sehan Devinda','Sehan@123',1,'','',NULL),('sehanDevinda','12345',1,'SEHAN DEVINDA','123456789013','2001-03-16');
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

-- Dump completed on 2022-03-09 12:54:53
