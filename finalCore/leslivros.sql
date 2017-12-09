-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leslivros
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `cartao`
--

DROP TABLE IF EXISTS `cartao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartao` (
  `id_cartao` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(30) NOT NULL,
  `bandeira` varchar(100) NOT NULL,
  `dtVencimento` varchar(10) NOT NULL,
  `codigo_seg` varchar(20) NOT NULL,
  `pk_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_cartao`),
  KEY `fk_cartao` (`pk_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartao`
--

LOCK TABLES `cartao` WRITE;
/*!40000 ALTER TABLE `cartao` DISABLE KEYS */;
INSERT INTO `cartao` VALUES (1,'1112223334455','MasterCard','09/2022','655',5),(4,'123124124','MasterCard','09/2022','667',7),(5,'124545','MasterCard','09/2022','123',8),(15,'123124124','MasterCard','09/2022','667',9),(14,'1112223334455','American Express','01/26','655',6),(16,'1112223334455','VISA','01/26','593',9),(17,'1112223334455','American Express','01/26','657',9);
/*!40000 ALTER TABLE `cartao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(40) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'InformÃ¡tica'),(2,'AdministraÃ§Ã£o');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_livro`
--

DROP TABLE IF EXISTS `categoria_livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_livro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_livro` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_subcategoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  KEY `id_subcategoria` (`id_subcategoria`),
  KEY `id_livro` (`id_livro`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_livro`
--

LOCK TABLES `categoria_livro` WRITE;
/*!40000 ALTER TABLE `categoria_livro` DISABLE KEYS */;
INSERT INTO `categoria_livro` VALUES (1,12,2,8),(2,13,2,8),(3,14,2,8),(4,16,2,9);
/*!40000 ALTER TABLE `categoria_livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `genero` varchar(15) NOT NULL,
  `nome_cliente` varchar(100) NOT NULL,
  `dtNascimento` date NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (5,'M','Marco','1965-07-23','11122233344','teste','123',1,0),(6,'M','Lucas Julio Alves','1997-07-25','11122233344','lucasjulio','123',1,0),(7,'M','Felipe','2000-08-23','4443332221100','felipe@teste.com','123',1,0),(8,'M','Fabio','1996-08-01','55544433322','fabio@teste.com','123',1,0),(9,'M','Admin','1997-07-23','11122233344','Admin','123',1,1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom`
--

DROP TABLE IF EXISTS `cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupom` (
  `id_cupom` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(6) NOT NULL,
  `desconto` double(4,2) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `dtVencimento` date DEFAULT NULL,
  PRIMARY KEY (`id_cupom`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
INSERT INTO `cupom` VALUES (1,'aaa111',20.00,0,'2017-12-29'),(2,'bbb222',30.00,0,'2017-11-20'),(3,'ccc333',40.00,0,'2017-11-29');
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom_pedido`
--

DROP TABLE IF EXISTS `cupom_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupom_pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cupom` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pedido` (`fk_pedido`),
  KEY `fk_cupom` (`fk_cupom`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom_pedido`
--

LOCK TABLES `cupom_pedido` WRITE;
/*!40000 ALTER TABLE `cupom_pedido` DISABLE KEYS */;
INSERT INTO `cupom_pedido` VALUES (1,1,6),(2,1,17);
/*!40000 ALTER TABLE `cupom_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom_troca_cliente`
--

DROP TABLE IF EXISTS `cupom_troca_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupom_troca_cliente` (
  `id_cupom_troca` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_cupom_troca` varchar(6) DEFAULT NULL,
  `desconto_cupom_troca` double(5,2) DEFAULT NULL,
  `dtCriacao` date DEFAULT NULL,
  `dtExpiracao` date DEFAULT NULL,
  `fk_cliente` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cupom_troca`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom_troca_cliente`
--

LOCK TABLES `cupom_troca_cliente` WRITE;
/*!40000 ALTER TABLE `cupom_troca_cliente` DISABLE KEYS */;
INSERT INTO `cupom_troca_cliente` VALUES (8,'1372af',50.80,'2017-12-09','2018-01-09',9,1),(9,'e7c365',101.60,'2017-12-09','2018-01-09',9,1);
/*!40000 ALTER TABLE `cupom_troca_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_res` varchar(30) NOT NULL,
  `tipo_log` varchar(200) NOT NULL,
  `logradouro` varchar(200) NOT NULL,
  `num_casa` varchar(6) NOT NULL,
  `bairro` varchar(60) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `pk_cliente` int(11) NOT NULL,
  `nome_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `fk_endereco` (`pk_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (6,'TEste','Teste','Teste','123','Morte','0114687','Suzano','SP','Brasil',5,'Teste'),(20,'Apartamento','Avenida','R. Teste','123','Jardim Teste','08676250','Suzano','SP','Brasil',9,'Casa 123'),(8,'Casa','Teste','Teste','801','Teste','12345520','Suzno','SP','Brasil',7,'Casa'),(9,'Apartamento','Avenida','R. Teste','100','Jardim Teste','08676250','Suzano','SP','Brasil',6,'Trabalho'),(16,'Apartamento','Avenida','R. Teste','1234','Jardim Teste','06676250','Mogi','SP','Brasil',6,'Casa'),(12,'Casa','Rua','Teste','801','Bairro','08676250','Ferraz','SP','Brasil',8,'Casa');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupoprecificacao`
--

DROP TABLE IF EXISTS `grupoprecificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupoprecificacao` (
  `id_grupo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_grupo` varchar(40) NOT NULL,
  `taxa` double NOT NULL,
  PRIMARY KEY (`id_grupo`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupoprecificacao`
--

LOCK TABLES `grupoprecificacao` WRITE;
/*!40000 ALTER TABLE `grupoprecificacao` DISABLE KEYS */;
INSERT INTO `grupoprecificacao` VALUES (1,'A',0),(2,'B',15);
/*!40000 ALTER TABLE `grupoprecificacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pedido` (
  `id_item_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `fk_livro` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double(4,2) DEFAULT NULL,
  `PK_PEDIDO` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_item_pedido`),
  KEY `fk_livro` (`fk_livro`),
  KEY `PK_PEDIDO` (`PK_PEDIDO`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pedido`
--

LOCK TABLES `item_pedido` WRITE;
/*!40000 ALTER TABLE `item_pedido` DISABLE KEYS */;
INSERT INTO `item_pedido` VALUES (47,12,2,50.60,37),(46,16,2,50.70,36),(45,13,2,50.80,35),(44,13,2,50.80,34),(43,13,2,50.80,33);
/*!40000 ALTER TABLE `item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livros`
--

DROP TABLE IF EXISTS `livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `editora` varchar(40) NOT NULL,
  `edicao` varchar(4) NOT NULL,
  `isbn` varchar(14) NOT NULL,
  `num_paginas` varchar(5) NOT NULL,
  `sinopse` text NOT NULL,
  `altura` double NOT NULL,
  `peso` double NOT NULL,
  `profundidade` double NOT NULL,
  `pk_grupo` int(11) NOT NULL,
  `qtde_estoque` int(11) DEFAULT NULL,
  `preco_livro` double(4,2) DEFAULT NULL,
  `largura` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (12,'Nudge','Richard Thaler','2009',1,'Nudge','Penguin Books UK','1','9780141040011','100','Sinopse',19,13,2,2,2,50.60,'14.00'),(13,'Building Resilience','Sandberg, Sheryl / Grant, Adam','2017',1,'Building Resilience','Knopf','1','9781524711214','240','Livro',24.1,0.44,2.5,1,2,50.80,'15.00'),(14,'The Founder\'s Mentality','Allen, James / Zook, Chris','2016',1,'The Founder\'s Mentality','Harvard Business Review Press','1','9781633691162','224','Sinopse',23.62,0.41,2.29,2,2,50.50,'16.00'),(16,'Facing Adversity','Sandberg, Sheryl / Grant, Adam','2017',1,'Facing Adversity','Knopf','1','9781524711214','240','Livro',24.1,0.44,2.5,1,2,50.70,'17.00');
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_cartao`
--

DROP TABLE IF EXISTS `pagamento_cartao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento_cartao` (
  `id_pagamento_cartao` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cartao` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  `valor` double(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_pagamento_cartao`),
  KEY `fk_cartao` (`fk_cartao`),
  KEY `fk_pedido` (`fk_pedido`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_cartao`
--

LOCK TABLES `pagamento_cartao` WRITE;
/*!40000 ALTER TABLE `pagamento_cartao` DISABLE KEYS */;
INSERT INTO `pagamento_cartao` VALUES (28,17,33,60.42),(27,15,33,60.42);
/*!40000 ALTER TABLE `pagamento_cartao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtPedido` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `pk_cliente` int(11) NOT NULL,
  `precoTotal` double(5,2) DEFAULT NULL,
  `frete` double(4,2) DEFAULT NULL,
  `fk_endereco` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_endereco` (`fk_endereco`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (37,'2017-12-09','ENTREGUE\n',9,116.72,15.52,20),(36,'2017-12-09','ENTREGUE\n',9,121.84,20.44,20),(35,'2017-12-09','ENTREGUE\n',9,120.84,19.24,20),(33,'2017-12-09','ENTREGUE\n',9,120.84,19.24,20),(34,'2017-12-09','ENTREGUE\n',9,120.84,19.24,20);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_troca`
--

DROP TABLE IF EXISTS `pedido_troca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_troca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_pedido` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `dtTroca` date NOT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pedido` (`fk_pedido`),
  KEY `fk_usuario` (`fk_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_troca`
--

LOCK TABLES `pedido_troca` WRITE;
/*!40000 ALTER TABLE `pedido_troca` DISABLE KEYS */;
INSERT INTO `pedido_troca` VALUES (24,33,9,'2017-12-09','TROCADO\n'),(23,33,9,'2017-12-09','TROCADO\n');
/*!40000 ALTER TABLE `pedido_troca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_troca_itens`
--

DROP TABLE IF EXISTS `pedido_troca_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_troca_itens` (
  `id_pedido_troca_itens` int(11) NOT NULL AUTO_INCREMENT,
  `fk_pedido_troca` int(11) NOT NULL,
  `fk_livro` int(11) NOT NULL,
  `qtde_livro` int(11) NOT NULL,
  `preco_troca` double(4,2) DEFAULT NULL,
  PRIMARY KEY (`id_pedido_troca_itens`),
  KEY `fk_pedido_troca` (`fk_pedido_troca`),
  KEY `fk_livro` (`fk_livro`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_troca_itens`
--

LOCK TABLES `pedido_troca_itens` WRITE;
/*!40000 ALTER TABLE `pedido_troca_itens` DISABLE KEYS */;
INSERT INTO `pedido_troca_itens` VALUES (22,24,13,2,50.80),(21,23,13,1,50.80);
/*!40000 ALTER TABLE `pedido_troca_itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategoria` (
  `id_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome_subcategoria` varchar(40) NOT NULL,
  `pk_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_subcategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,'Linguagens',1),(2,'Programas',1),(3,'ComputaÃ§Ã£o grÃ¡fica',1),(4,'Redes',1),(5,'Sistemas Operacionais',1),(6,'Analise de Sistemas',1),(7,'Banco de dados',1),(8,'AdministraÃ§Ã£o Geral',2),(9,'AdministraÃ§Ã£o de RH e TerceirizaÃ§Ã£o',2),(10,'AdministraÃ§Ã£o e MatemÃ¡tica Financeira',2),(11,'AdministraÃ§Ã£o de Vendas e Marketing',2),(12,'AdministraÃ§Ã£o de ProduÃ§Ã£o',2),(13,'EstatÃ­stica',2),(14,'ComÃ©rcio Exterior',2);
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `id_telefone` int(11) NOT NULL AUTO_INCREMENT,
  `ddd` varchar(2) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `tipo_telefone` varchar(20) NOT NULL,
  `fk_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_telefone`),
  KEY `fk_cliente` (`fk_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (3,'11','6478644','Telefone',5),(4,'11','6478644','Celular',6),(5,'11','1646498','Celular',7),(6,'11','9498446','Celular',6),(7,'11','64643513','Telefone',8),(8,'11','64643513','Telefone',9);
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-09 17:05:03
