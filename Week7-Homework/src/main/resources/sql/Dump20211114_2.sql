CREATE DATABASE  IF NOT EXISTS `ecom2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecom2`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: ecom
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL,
  `CategoryName` char(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `CustomerName` char(45) NOT NULL,
  `ContactName` char(45) NOT NULL,
  `Address` char(45) NOT NULL,
  `City` char(20) NOT NULL,
  `PostalCode` char(10) NOT NULL,
  `Country` char(20) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Wyman','Wyman','x','x','4119','AU','2021-01-01 00:00:00','2021-01-01 00:00:00');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `EmployeeID` int(11) NOT NULL,
  `LastName` char(45) NOT NULL,
  `FirstName` char(45) NOT NULL,
  `Birthday` date NOT NULL,
  `PhotoPath` varchar(200) NOT NULL,
  `Notes` varchar(500) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'SHI','LI','1981-01-01','xx','xx','2021-01-01 00:00:00','2021-01-01 00:00:00');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderdetails` (
  `OrderDetailID` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderDetailID`),
  KEY `OrderID_idx` (`OrderID`),
  KEY `ProductID_idx` (`ProductID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `ProductID` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders1` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders2` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders3` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders4` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders5` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders6` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders7` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders8` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders9` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders10` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders11`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders11` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders12`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders12` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders13`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders13` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders14` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders15`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders15` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `orders16`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders16` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `OrderDate` datetime NOT NULL,
  `ShipperID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `EmployeeID_idx` (`EmployeeID`),
  KEY `ShipperID_idx` (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `ProductName` char(45) NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `Unit` int(11) NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `SupplierID_idx` (`SupplierID`),
  KEY `CategoryID_idx` (`CategoryID`),
  CONSTRAINT `CategoryID` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`),
  CONSTRAINT `SupplierID` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippers`
--

DROP TABLE IF EXISTS `shippers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shippers` (
  `ShipperID` int(11) NOT NULL,
  `ShipperName` char(45) NOT NULL,
  `Phone` char(20) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`ShipperID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippers`
--

LOCK TABLES `shippers` WRITE;
/*!40000 ALTER TABLE `shippers` DISABLE KEYS */;
INSERT INTO `shippers` VALUES (1,'Wyman Ship','049992229','2021-01-01 00:00:00','2021-01-01 00:00:00');
/*!40000 ALTER TABLE `shippers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `SupplierName` char(45) NOT NULL,
  `ContactName` char(45) NOT NULL,
  `Address` char(100) NOT NULL,
  `City` char(20) NOT NULL,
  `PostalCode` char(10) NOT NULL,
  `Country` char(20) NOT NULL,
  `Phone` char(20) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ChangedDate` datetime NOT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-14 17:39:03
