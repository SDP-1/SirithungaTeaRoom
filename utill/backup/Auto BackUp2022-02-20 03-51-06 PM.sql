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
INSERT INTO `item` VALUES (100,0,'Mortin 600ml','Mortin 600ml','4792037130923','',35.00,0,0.00,665.00,0.00,0.00,660.00,0.00),(101,0,'Lemon Puff 200g','Lemon Puff 200g','8888101090401','Munchee',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(102,0,'Pasyale Anturel Asamodagam 375ml','Pasyale Anturel Asamodagam 375ml','4796014750012','pasyale',36.00,0,0.00,100.00,0.00,0.00,100.00,0.00),(102,1,'Pasyale Anturel Asamodagam 375ml','Pasyale Anturel Asamodagam 375ml','4796014750012','pasyale',36.00,0,0.00,150.00,0.00,0.00,145.00,0.00),(103,0,'Highland Milk Powder 400g','Highland Milk Powder 400g','4792094003086','',0.00,0,0.00,470.00,0.00,0.00,465.00,0.00),(104,0,'Lemon Puff 100g','Lemon Puff 100g','8888101090203','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(105,0,'Orange Nectar 1L','Orange Nectar 1L','4792143133412','kist',0.00,0,0.00,270.00,0.00,0.00,270.00,0.00),(106,0,'Rathmal baby shop','Rathmal baby shop','4792054006263','Panda baby',24.00,0,0.00,60.00,0.00,0.00,58.00,57.00),(107,0,'Orange Creem Biscuits 100g','Orange Creem Biscuits 100g','4792192847544','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(108,0,'Chocolate puff 100g','Chocolate puff 100g','8888101271206','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(109,0,'NestOmalt','NestOmalt','4792024016179','Nestle',500.00,0,0.00,570.00,0.00,0.00,560.00,558.00),(110,0,'Chocolate puff 200g','Chocolate puff 200g','8888101271404','Munchee',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(111,0,'Cheese Cracker 100g','Cheese Cracker 100g','8888101131203','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(112,0,'parippu NO-1','parippu NO-1','','',0.00,1,0.00,310.00,0.00,0.00,310.00,0.00),(112,1,'parippu NO-2','parippu NO-2','','',0.00,1,0.00,280.00,0.00,0.00,280.00,0.00),(113,0,'kakulu hal','kakulu hal','','',0.00,1,0.00,210.00,0.00,0.00,210.00,0.00),(113,1,'kakulu hal (polish)','kakulu hal (polish)','','',0.00,1,0.00,210.00,10.00,0.00,0.00,0.00),(113,2,'kakulu hal 3','kakulu hal 3','','',0.00,1,0.00,210.00,0.00,8.00,210.00,0.00),(114,0,'kothmalli','kothmalli','1111111111111','',0.00,1,0.00,450.00,0.00,0.00,445.00,0.00),(115,0,'Atlas CR160','Atlas CR160','4792210100262','Atlas',24.00,0,0.00,160.00,0.00,0.00,155.00,0.00),(116,0,'Super Creem Cracker 125g','Super Creem Cracker 125g','8888101430153','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(117,0,'Super Creem Cracker 380g','Super Creem Cracker 380g','8888101430085','Munchee',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(118,0,'Hawaian Cookies 200g','Hawaian Cookies 200g','8888101080402','Munchee',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(119,0,'Chocolate Creem Buscuitsb100g','Chocolate Creem Buscuitsb100g','8888101270018','Munchee',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(120,0,'Chocolate Creem Buscuitsb100g','Chocolate Creem Buscuitsb100g','4792192845366','Munchee',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(121,0,'Milk Short Cake 200g','Milk Short Cake 200g','8888101030407','Munchee',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(122,0,'Tikiri Marie 230g','Tikiri Marie 230g','4792192845809','Munchee',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(123,0,'Custard Creem Biscuits 240g','Custard Creem Biscuits 240g','8888101275389','Munchee',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(124,0,'Nice Buscuits 100g','Nice Buscuits 100g','8888101020200','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(125,0,'Super Creem Cracker 85g','Super Creem Cracker 85g','8888101430276','Munchee',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(126,0,'Soyoga Buscuits 80g','Soyoga Buscuits 80g','8888101591281','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(127,0,'Kome Rice Cracker 90g','Kome Rice Cracker 90g','4792192845991','Munchee',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(128,0,'Hawaian Cookies 100g','Hawaian Cookies 100g','8888101080204','Munchee',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(129,0,'Milk Shorties 75g','Milk Shorties 75g','8888101306045','Munchee',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(130,0,'Sun Cracker 95g','Sun Cracker 95g','8888101135454','Munchee',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(131,0,'Milk Short Cake 85g','Milk Short Cake 85g','8888101030278','Munchee',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(132,0,'Crunchee Carols 100g','Crunchee Carols 100g','8888101276201','Munchee',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(133,0,'Custard cream Buscuits 100g','Custard cream Buscuits 100g','8888101275204','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(134,0,'Chocolate Marie 200g','Chocolate Marie 200g','4792192845823','Munchee',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(135,0,'Ginger Buscuits 85g','Ginger Buscuits 85g','8888101570286','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(136,0,'Tikiri Mari Bucuits 80g','Tikiri Mari Bucuits 80g','8888101280208','Munchee',0.00,0,0.00,50.00,0.00,0.00,50.00,0.00),(137,0,'Tifin Original 125g','Tifin Original 125g','8888101932190','Munchee',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(138,0,'Tifin Onion 125g','Tifin Onion 125g','8888101931193','Munchee',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(139,0,'Sun Cracker 95g','Sun Cracker 95g','8888101134457','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(140,0,'Milk Shrties Choco 70g','Milk Shrties Choco 70g','8888101629045','Munchee',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(141,0,'Chocolate Marie 90g','Chocolate Marie 90g','8888101281205','Munchee',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(142,0,'Cheese Cracker 200g','Cheese Cracker 200g','8888101131401','Munchee',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(143,0,'Milk Creem buscuits 110g','Milk Creem buscuits 110g','4792192845274','Munchee',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(144,0,'Marie Buscuits 50g','Marie Buscuits 50g','8888101287238','Munchee',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(145,0,'Choc Shock 90g','Choc Shock 90g','4792192845380','Munchee',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(146,0,'Snak Cracker 170g','Snak Cracker 170g','8888101610708','Munchee',0.00,0,0.00,200.00,0.00,0.00,200.00,0.00),(147,0,'Onion Buscuits 250g','Onion Buscuits 250g','8888101613808','Munchee',0.00,0,0.00,350.00,0.00,0.00,350.00,0.00),(148,0,'Cheese Buttons Buscuits 215g','Cheese Buttons Buscuits 215g','8888101611804','Munchee',0.00,0,0.00,360.00,0.00,0.00,360.00,0.00),(149,0,'Wafers Lemon Cream 90g','Wafers Lemon Cream 90g','4791034072748','Maliban',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(150,0,'Milk shortcake 200g','Milk shortcake 200g','4791034070874','Maliban',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(151,0,'Cream Cracker 190g','Cream Cracker 190g','4791034042116','Maliban',0.00,0,0.00,110.00,0.00,0.00,110.00,0.00),(152,0,'Cream Cracker 125g','Cream Cracker 125g','4791034042611','Maliban',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(153,0,'Cream Cracker 230g','Cream Cracker 230g','4791034071604','Maliban',0.00,0,0.00,120.00,0.00,0.00,120.00,0.00),(154,0,'Cream Cracker 500g','Cream Cracker 500g','4791034042215','Maliban',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(155,0,'Real Chocolate 100g','Real Chocolate 100g','4791034005111','Maliban',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(156,0,'Real Chocolate 200g','Real Chocolate 200g','4791034072366','Maliban',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(157,0,'Real Chocolate 400g','Real Chocolate 400g','4791034070546','Maliban',0.00,0,0.00,270.00,0.00,0.00,270.00,0.00),(158,0,'Lemon Puff 100g','Lemon Puff 100g','4791034017114','Maliban',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(159,0,'Lemon Puff 200g','Lemon Puff 200g','4791034017015','Maliban',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(160,0,'Ginger Biscuit 80g','Ginger Biscuit 80g','4791034070232','Maliban',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(161,0,'Ginger Biscuit 240g','Ginger Biscuit 240g','4791034070263','Maliban',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(162,0,'Ginger Biscuit 370g','Ginger Biscuit 370g','4791034070218','Maliban',0.00,0,0.00,240.00,0.00,0.00,240.00,0.00),(163,0,'Chocolate Puf Biscuit 100g','Chocolate Puf Biscuit 100g','4791034070270','Maliban',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(164,0,'Chocolate Puf Biscuit 200g','Chocolate Puf Biscuit 200g','4791034070287','Maliban',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(165,0,'White Chocolate Puff 200g','White Chocolate Puff 200g','4791034070096','Maliban',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(166,0,'Gold Marie 75g','Gold Marie 75g','4791034027410','Maliban',0.00,0,0.00,45.00,0.00,0.00,45.00,0.00),(167,0,'Gold Marie 230g','Gold Marie 230g','4791034072465','Maliban',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(168,0,'Gold Marie 350g','Gold Marie 350g','4791034071567','Maliban',0.00,0,0.00,200.00,0.00,0.00,200.00,0.00),(169,0,'Orange Cream Buscuit 100g','Orange Cream Buscuit 100g','4791034021111','Maliban',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(170,0,'Orange Cream Buscuit 410g','Orange Cream Buscuit 410g','4791034070614','Maliban',0.00,0,0.00,250.00,0.00,0.00,250.00,0.00),(171,0,'Custard Cream Biscuit 100g','Custard Cream Biscuit 100g','4791034008112','Maliban',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(172,0,'Custard Cream Biscuit 200g','Custard Cream Biscuit 200g','4791034072656','Maliban',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(173,0,'Custard Cream Biscuit 410g','Custard Cream Biscuit 410g','4791034070607','Maliban',0.00,0,0.00,250.00,0.00,0.00,250.00,0.00),(174,0,'Light Marie 50g','Light Marie 50g','4791034027915','Maliban',0.00,0,0.00,35.00,0.00,0.00,35.00,0.00),(175,0,'Milk Shortcake 85g','Milk Shortcake 85g','4791034039215','Maliban',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(176,0,'Real Bran Cracker 210g','Real Bran Cracker 210g','4791034001519','Maliban',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(177,0,'Hawaiian Cookies 100g','Hawaiian Cookies 100g','4791034057110','Maliban',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(178,0,'Hawaiian Cookies 200g','Hawaiian Cookies 200g','4791034057011','Maliban',0.00,0,0.00,130.00,0.00,0.00,130.00,0.00),(179,0,'Nice 100g','Nice 100g','4791034020114','Maliban',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(180,0,'Nice 4353g','Nice 4353g','4791034070249','Maliban',0.00,0,0.00,230.00,0.00,0.00,230.00,0.00),(181,0,'Cracker Thins 190g','Cracker Thins 190g','4791034073035','Maliban',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(182,0,'Spicy Crackers 170g','Spicy Crackers 170g','4791034060110','Maliban',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(183,0,'Veggie Cracker 170g','Veggie Cracker 170g','4791034072298','Maliban',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(184,0,'Milky Sorties 325g','Milky Sorties 325g','4791034032216','Maliban',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(185,0,'Chocolate Marie 300g','Chocolate Marie 300g','4791034070355','Maliban',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(186,0,'Wafers Vanila Cream 225g','Wafers Vanila Cream 225g','4791034071673','Maliban',0.00,0,0.00,170.00,0.00,0.00,170.00,0.00),(187,0,'Wafers Vanila Cream 400g','Wafers Vanila Cream 400g','4791034071826','Maliban',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(188,0,'Wafers Chocolate Cream 225g','Wafers Chocolate Cream 225g','4791034071659','Maliban',0.00,0,0.00,150.00,0.00,0.00,150.00,0.00),(189,0,'Wafers Chocolate Cream 400g','Wafers Chocolate Cream 400g','4791034071833','Maliban',0.00,0,0.00,260.00,0.00,0.00,260.00,0.00),(190,0,'Lemon Puff 400g','Lemon Puff 400g','4791034070447','Maliban',0.00,0,0.00,280.00,0.00,0.00,280.00,0.00),(191,0,'Krisco 170g','Krisco 170g','4791034015318','Maliban',0.00,0,0.00,210.00,0.00,0.00,210.00,0.00),(192,0,'White Bord Marker','White Bord Marker','4796009862935','Mango',0.00,0,0.00,80.00,0.00,0.00,80.00,0.00),(193,0,'Deweni Batha(Sudu kakulu) 350g','Deweni Batha(Sudu kakulu) 350g','4792149097107','Raigam',0.00,0,0.00,165.00,0.00,0.00,165.00,0.00),(194,0,'Deweni Batha(Rathu kakulu) 350g','Deweni Batha(Rathu kakulu) 350g','4792149097206','Raigam',0.00,0,0.00,175.00,0.00,0.00,175.00,0.00),(195,0,'Family Meal Pack 335g','Family Meal Pack 335g','4792024015745','Maggi',0.00,0,0.00,180.00,0.00,0.00,180.00,0.00),(196,0,'papare Kottu 77g','papare Kottu 77g','4792024016070','Maggi',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(197,0,'Daiya Devilled 76g','Daiya Devilled 76g','4792024015769','maggi',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(198,0,'Toppz 80g','Toppz 80g','4792018233148','Prima',0.00,0,0.00,62.00,0.00,0.00,62.00,0.00),(199,0,'Daiya Devilled 76g','Daiya Devilled 76g','4792024015660','maggi',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(200,0,'Kottu Mee 80g','Kottu Mee 80g','4792018233131','Prima',0.00,0,0.00,70.00,0.00,0.00,70.00,0.00),(201,0,'Stella Chicken 74g','Stella Chicken 74g','4792018234312','Prima',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(202,0,'Maggi Noodles 73g','Maggi Noodles 73g','4792024015646','Maggi',0.00,0,0.00,65.00,0.00,0.00,65.00,0.00),(203,0,'Kottu Mee 80g','Kottu Mee 80g','4792018233421','Prima',0.00,0,0.00,60.00,0.00,0.00,60.00,0.00),(204,0,'Harischandra Noodles - kakulu 145g','Harischandra Noodles 145g','4792083010316','Harischandra',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(205,0,'Harischandra Noodles -Kurakkan 400g','Harischandra Noodles 400g','4792083010217','Harischandra ',0.00,0,0.00,140.00,0.00,0.00,140.00,0.00),(206,0,'Harischandra Noodeles - Sudu 400g','Harischandra Noodeles 400g','4792083010118','Harischandra ',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(207,0,'wijaya Nooldels 400g','wijaya Nooldels 400g','4792173100088','Wijaya',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(208,0,'Sera Noodles 325g','Sera Noodles 325g','4792109000666','Sera',0.00,0,0.00,145.00,0.00,0.00,145.00,0.00),(209,0,'Nikado Noodeles 400g','Nikado Noodeles 400g','4792201007013','Nikado',0.00,0,0.00,155.00,0.00,0.00,155.00,0.00),(210,0,'Milan pasta 1Kg','Milan pasta 1Kg','4792225001677','Milan',0.00,0,0.00,380.00,0.00,0.00,380.00,0.00),(211,0,'Nikado Noodeles 300g','Nikado Noodeles 300g','4792201020845','Nikado',0.00,0,0.00,160.00,0.00,0.00,160.00,0.00),(212,0,'MDK Hopper Flour - Sudu 1Kg','MDK Hopper Flour 1Kg','4792101019147','MDK',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(213,0,'MDK Hopper Flour -Kakulu 1Kg','MDK Hopper Flour 1Kg','4792101019154','MDK',0.00,0,0.00,220.00,0.00,0.00,220.00,0.00),(214,0,'sera Noodels 400g','sera Noodels 400g','4792109000659','Sera',0.00,0,0.00,450.00,0.00,0.00,450.00,0.00),(215,0,'Harischandra Noodles 200g','Harischandra Noodles 200g','4792083030451','Harischandra ',0.00,0,0.00,75.00,0.00,0.00,75.00,0.00),(216,0,'Harischandra Rice Noodles 200g','Harischandra Rice Noodles 200g','4792083010514','Harischandra ',0.00,0,0.00,85.00,0.00,0.00,85.00,0.00),(217,0,'Varda pasta 400g','Varda pasta 400g','6291047000045','Varda',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(218,0,'Varda pasta 400g','Varda pasta 400g','6291047000007','Varda',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(219,0,'Varda Pasta 400g','Varda Pasta 400g','6291047073438','Varda',0.00,0,0.00,256.00,0.00,0.00,256.00,0.00),(220,0,'Sriposha 400g','Sriposha 400g','4797001101749','Sriposha',0.00,0,0.00,175.00,0.00,0.00,175.00,0.00),(221,0,'Sriposha 700g','Sriposha 700g','4797001101787','Sriposha ',0.00,0,0.00,295.00,0.00,0.00,295.00,0.00),(222,0,'Milan Pasta 1Kg','Milan Pasta 1Kg','4792225001660','Milan',0.00,0,0.00,380.00,0.00,0.00,380.00,0.00);
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
INSERT INTO `location` VALUES ('larstBackUpLocation','C:\\Users\\sehan\\OneDrive\\Desktop\\New folder (2)');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
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
INSERT INTO `user` VALUES ('','',1,'null','123423432123',NULL),('12345','12345',1,'pasan','123423432123','2022-02-03'),('34354','12345',0,'kamal','123423432123','2022-02-03'),('46567','12345',0,'nimal','123423432123','2022-02-03'),('bgsfbfscbvfs','12345',0,'bfbvfvcxbfxb','241353153431','2022-02-10'),('rootrootroot','12345',1,'rootrootroot','123456789087','2022-02-03'),('Sehan','12345',0,'Sehan Devinda Panditha','123456789012','2001-03-16'),('Sehan Devinda','Sehan@123',1,'','',NULL),('sehanDevinda','12345',1,'Sehan devinda','123456789013','2001-03-16');
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

-- Dump completed on 2022-02-20 15:51:08
