-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: turismo_spring
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `id_actividad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `imagen` varchar(150) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id_actividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (1,'Aventura de remo de kayak (Bariloche)','/img/actividades/Aventuraderemodekayak(Bariloche)-imagen.jpg',16306),(2,'Esquiar en el cerro catedral (Bariloche)','/img/actividades/Esquiarenelcerrocatedral(Bariloche)-imagen.jpg',12620),(3,'Aquasol (Mar del Plata)','/img/actividades/Aquasol(MardelPlata)-imagen.jpg',2200),(4,'Aquarium (Mar del Plata)','/img/actividades/Aquarium(MardelPlata)-imagen.jpg',1250),(5,'Excursión en barco, tren y camión (Cataratas del Iguazú)','/img/actividades/Excursiónenbarco,trenycamión(CataratasdelIguazú)-imagen.jpg',15300),(6,'Tour (Cataratas del Iguazú)','/img/actividades/Tour(CataratasdelIguazú)-imagen.jpg',8429),(7,'Senderismo (Glaciar Perito Moreno)','/img/actividades/Senderismo(GlaciarPeritoMoreno)-imagen.jpg',28553),(8,'Ruta de los 7 Lagos (San Martín de los Andes)','/img/actividades/Rutadelos7Lagos(SanMartíndelosAndes)-imagen.jpg',8157),(9,'Navegación (San Martín de los Andes)','/img/actividades/Navegación(SanMartíndelosAndes)-imagen.jpg',10865),(10,'Teatro Colón (Buenos Aires)','/img/actividades/TeatroColón(BuenosAires)-imagen.jpg',4200);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditoria` (
  `id_auditoria` int NOT NULL AUTO_INCREMENT,
  `accion` varchar(255) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_auditoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
INSERT INTO `auditoria` VALUES (1,'Compra: Mar del Plata + 1, Aquarium (Mar del Plata), Total: 44250.0','Pepe','2022-03-18 13:10:37',2),(2,'Compra: San Martín de los Andes + 1, Navegación (San Martín de los Andes), Total: 55921.75','Pepe','2022-03-21 13:05:57',2);
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto` (
  `id_contacto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `comentario` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id_contacto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` VALUES (1,'Pepe','pepearg@mail.com','Queridos señores de Turismo Argentina, me complace contactar con ustedes para poder comentarles acerca de la calidad de su servicio, la cual, no difiere mucho respecto a la calidad de los servicios públicos del país. Atentamente, Pepe.'),(2,'Oscar','oscarca@mail.com','asasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasas');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupon`
--

DROP TABLE IF EXISTS `cupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupon` (
  `id_cupon` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descuento` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id_cupon`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupon`
--

LOCK TABLES `cupon` WRITE;
/*!40000 ALTER TABLE `cupon` DISABLE KEYS */;
INSERT INTO `cupon` VALUES (1,'LANZAMIENTO2022',5,'2022-03-16 14:16:25');
/*!40000 ALTER TABLE `cupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar` (
  `id_lugar` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(600) DEFAULT NULL,
  `portada` varchar(100) DEFAULT NULL,
  `foto1` varchar(100) DEFAULT NULL,
  `foto2` varchar(100) DEFAULT NULL,
  `foto3` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id_lugar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
INSERT INTO `lugar` VALUES (1,'San Martín de los Andes','San Martín de los Andes es una ciudad en el noroeste de la Patagonia, Argentina. Es conocida como la vía de acceso al boscoso Parque Nacional Lanín, que alberga el volcán Lanín y una fauna variada que incluye guanacos y pumas. La ciudad se ubica en las orillas del lago Lácar, uno de los muchos lagos glaciares del parque, y tiene un muelle y una playa con arena. El Museo Primeros Pobladores muestra exhibiciones de tribus indígenas y colonizadores europeos.','/img/lugares/SanMartíndelosAndes-portada.jpg','/img/lugares/SanMartíndelosAndes-foto1.jpg','/img/lugares/SanMartíndelosAndes-foto2.jpg','/img/lugares/SanMartíndelosAndes-foto3.jpg',25000),(2,'Cataratas del Iguazú','Las cataratas del Iguazú, llamadas popularmente en Argentina como «Las Cataratas» o «Las Cataratas del Iguazú», son un conjunto de cataratas que se localizan sobre el río Iguazú, en el límite entre la provincia argentina de Misiones y el estado brasileño de Paraná.','/img/lugares/CataratasdelIguazú-portada.jpg','/img/lugares/CataratasdelIguazú-foto1.jpg','/img/lugares/CataratasdelIguazú-foto2.jpg','/img/lugares/CataratasdelIguazú-foto3.jpg',23000),(3,'Mar del Plata','Mar del Plata es una ciudad balnearia argentina en la costa del Atlántico. Su larga franja de playas incluye la amplia Punta Mogotes y Playa Grande, con sus rompientes para el surf. Detrás de Playa Grande, las calles rodeadas de árboles del barrio Los Troncos tienen elegantes casas de comienzos del siglo XX, que ahora son museos. Entre ellas se incluye el Museo de Historia Roberto T. Barili que documenta el pasado de la ciudad con fotografías y objetos.','/img/lugares/MardelPlata-portada.jpg','/img/lugares/MardelPlata-foto1.jpg','/img/lugares/MardelPlata-foto2.jpg','/img/lugares/MardelPlata-foto3.jpg',20000),(4,'Bariloche','San Carlos de Bariloche (comúnmente llamada Bariloche) es una ciudad en la región de la Patagonia argentina. Limita con Nahuel Huapi, un gran lago glacial rodeado de montañas de los Andes. Bariloche es conocida por su arquitectura al estilo alpino de Suiza y su chocolate, que se vende en tiendas de la calle Mitre, la avenida principal. También es una base popular para el excursionismo y el esquí en las montañas cercanas, y para explorar los alrededores del Distrito de los Lagos.','/img/lugares/Bariloche-portada.jpg','/img/lugares/Bariloche-foto1.jpg','/img/lugares/Bariloche-foto2.jpg','/img/lugares/Bariloche-foto3.jpg',19000),(5,'Glaciar Perito Moreno','El glaciar Perito Moreno es una gruesa masa de hielo ubicada en el departamento Lago Argentino de la provincia de Santa Cruz, en el sudoeste de la Argentina, en la región de la Patagonia. Se integra dentro del parque nacional Los Glaciares. Este glaciar se origina en el campo de hielo Patagónico Sur.','/img/lugares/GlaciarPeritoMoreno-portada.jpg','/img/lugares/GlaciarPeritoMoreno-foto1.jpg','/img/lugares/GlaciarPeritoMoreno-foto2.jpg','/img/lugares/GlaciarPeritoMoreno-foto3.jpg',20000),(6,'Buenos Aires','Buenos Aires es la gran capital cosmopolita de Argentina. Su centro es la Plaza de Mayo, rodeada de imponentes edificios del siglo XIX, incluida la Casa Rosada, el icónico palacio presidencial que tiene varios balcones. Entre otras atracciones importantes, se incluyen el Teatro Colón, un lujoso teatro de ópera de 1908 con cerca de 2,500 asientos, y el moderno museo MALBA, que exhibe arte latinoamericano.','/img/lugares/BuenosAires-portada.jpg','/img/lugares/BuenosAires-foto1.jpg','/img/lugares/BuenosAires-foto2.jpg','/img/lugares/BuenosAires-foto3.jpg',21000);
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uso`
--

DROP TABLE IF EXISTS `uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uso` (
  `id_uso` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `id_cupon` int DEFAULT NULL,
  `usado` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_uso`),
  KEY `fk_cod_usuario_idx` (`id_usuario`),
  KEY `fk_cod_cupon_idx` (`id_cupon`),
  CONSTRAINT `fk_cod_cupon` FOREIGN KEY (`id_cupon`) REFERENCES `cupon` (`id_cupon`),
  CONSTRAINT `fk_cod_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uso`
--

LOCK TABLES `uso` WRITE;
/*!40000 ALTER TABLE `uso` DISABLE KEYS */;
INSERT INTO `uso` VALUES (1,2,1,1),(2,3,1,0);
/*!40000 ALTER TABLE `uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_rol_idx` (`id_rol`),
  CONSTRAINT `fk_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Administrador','admin@mail.com','$2a$10$JCaR3.qz1kXWU7vSaOQrT.CzDVUz.cX6PoBcVOdlV3CFq/tHUP6Wy',1),(2,'Pepe','pepearg@mail.com','$2a$10$Zhz2CwyEj7aLnRqKbyeVJemLcShptzM0/NREVZsBciC.HyP.HMU82',2),(3,'Jose','jose@mail.com','$2a$10$KsMxcdE9M8bQXCKiSK6rtOgZ/FWLi7VoSi9ZEXVW.XQ3F0OU8rUN6',2),(10,'Oscar','oscarca@mail.com','$2a$10$EkU5oLGgWdhkpCqeG2Hiwe1jqd.1fgOtEdSGbgY/kSkzP3Efy3asi',2);
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

-- Dump completed on 2022-03-21 10:08:19
