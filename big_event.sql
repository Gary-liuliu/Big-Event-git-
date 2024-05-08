-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: big_event
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(30) NOT NULL COMMENT '文章标题',
  `content` varchar(10000) NOT NULL COMMENT '文章内容',
  `cover_img` varchar(128) NOT NULL COMMENT '文章封面',
  `state` varchar(3) DEFAULT '草稿' COMMENT '文章状态: 只能是[已发布] 或者 [草稿]',
  `category_id` int unsigned DEFAULT NULL COMMENT '文章分类ID',
  `create_user` int unsigned NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_article_category` (`category_id`),
  KEY `fk_article_user` (`create_user`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_article_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'长沙旅游攻略','<p>天安门...爱去哪去哪...</p>','https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png','已发布',6,2,'2023-12-04 15:16:11','2023-12-22 09:31:28'),(4,'长沙旅游事件','<p>长沙旅游知识宣传得好，其实并没有那么好，是的</p>','https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png','已发布',6,2,'2023-12-17 15:20:57','2023-12-22 09:06:44'),(6,'最新消息','<p>最新消息，能成功吗</p>','https://egokaka.oss-cn-beijing.aliyuncs.com/82cd664f-3d32-4792-8da8-df659373e16a.jpg','已发布',6,2,'2023-12-17 16:11:16','2023-12-17 16:11:16'),(7,'最新发现，兵马俑造假','<p> 我是标题党</p>','https://egokaka.oss-cn-beijing.aliyuncs.com/ff1c819d-3dc1-45b1-9fdb-c8e072c14282.jpg','已发布',7,2,'2023-12-17 16:19:29','2024-01-14 12:24:37'),(8,'第一个项目真的不容易','<p> 也许吧，但是对自己来说肯定是一个挑战</p>','https://egokaka.oss-cn-beijing.aliyuncs.com/5c524795-fbd0-4eda-9047-7eae71cacda0.jpg','保存',6,2,'2023-12-18 20:50:15','2023-12-18 20:50:15'),(9,'Ego','<p> 第一篇</p>','https://egokaka.oss-cn-beijing.aliyuncs.com/89ffba3d-b3ee-4eb8-8350-e9ffdf81df6d.jpg','已发布',10,5,'2023-12-22 09:10:11','2023-12-22 09:10:11');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(32) NOT NULL COMMENT '分类名称',
  `category_alias` varchar(32) NOT NULL COMMENT '分类别名',
  `create_user` int unsigned NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_category_user` (`create_user`),
  CONSTRAINT `fk_category_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'军事','js',2,'2023-12-01 15:58:55','2023-12-17 15:44:15'),(4,'美食','meihsi',2,'2023-12-01 16:45:27','2023-12-17 15:38:06'),(6,'新闻','news',2,'2023-12-13 14:22:25','2023-12-13 14:22:25'),(7,'历史','history',2,'2023-12-13 14:23:27','2023-12-13 14:23:27'),(10,'我的','my',5,'2023-12-22 09:09:54','2023-12-22 09:09:54'),(11,'历史','history',6,'2024-04-17 16:15:38','2024-04-17 16:15:52');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(10) DEFAULT '' COMMENT '昵称',
  `email` varchar(128) DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(128) DEFAULT '' COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'zouzou','629cc5705334f2a861f2c49ce4d278ca','刘刘','1516495256@qq.com','https://egokaka.oss-cn-beijing.aliyuncs.com/038280a1-5dea-4220-91fe-b1d00086c36b.jpg','2023-11-23 15:55:59','2024-01-14 12:25:09'),(4,'liuliu','e09c80c42fda55f9d992e59ca6b3307d','','','','2023-12-09 16:35:18','2023-12-09 16:35:18'),(5,'Egozouzou','629cc5705334f2a861f2c49ce4d278ca','','','','2023-12-22 09:05:15','2023-12-22 09:05:15'),(6,'egokaka','b2c56c4d6d70b6b12e5e314bdb6068b8','卡卡','1515477256@qq.com','https://egokaka.oss-cn-beijing.aliyuncs.com/84813407-3924-4de5-932e-6aa33cfcd9ed.jpg','2024-04-17 16:15:05','2024-04-17 16:18:18');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice','alice_pass'),(2,'zouzou','1234'),(3,'charlie','charlie_pass'),(4,'david','david_pass');
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

-- Dump completed on 2024-05-08 15:27:32
