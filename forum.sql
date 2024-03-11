/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.32 : Database - forum
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`forum` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `forum`;

/*Table structure for table `posts` */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `posts_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `user_id` int NOT NULL COMMENT '用户id',
  `posts_bar_id` int DEFAULT NULL COMMENT '贴吧id',
  `posts_title` varchar(255) DEFAULT NULL COMMENT '帖子标题',
  `posts_content` longtext COMMENT '帖子内容',
  `posts_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帖子发布时间',
  PRIMARY KEY (`posts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts` */

insert  into `posts`(`posts_id`,`user_id`,`posts_bar_id`,`posts_title`,`posts_content`,`posts_time`) values (16,14,1,'前端吧标题','我是内容','2022-11-13 10:11:02'),(17,17,4,'车车车','车','2022-11-13 10:13:51'),(18,14,9,'编程编程','123','2022-11-13 10:16:11'),(19,14,6,'123','3','2022-11-13 10:47:40'),(20,14,8,'32','123321312','2022-11-13 10:47:55'),(21,14,6,'323214214','12321321321','2022-11-13 10:48:17'),(22,14,10,'qq炫舞','234','2022-11-13 10:48:36'),(23,14,3,'安师大','安师大','2022-11-13 17:00:33'),(24,14,7,'哈哈','哈哈','2022-11-13 17:00:58'),(25,14,10,'炫舞','炫舞','2022-11-13 17:01:19'),(26,14,2,'LOL','LOL','2022-11-13 19:09:04'),(27,14,1,'前端前端','前端内容','2022-11-13 19:14:41'),(28,14,2,'LOL','LOL','2022-11-13 19:15:23'),(29,14,4,'QQ飞车哦','飞车','2022-11-13 19:16:06'),(30,14,1,'43231','123213','2022-11-14 14:16:39'),(31,14,8,'大脑','大脑','2022-11-14 14:21:29'),(34,14,1,'测试','测试','2022-11-24 10:40:17'),(35,14,4,'飞车','123','2022-12-05 09:27:09');

/*Table structure for table `posts_bar` */

DROP TABLE IF EXISTS `posts_bar`;

CREATE TABLE `posts_bar` (
  `posts_bar_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子吧表id',
  `posts_bar_name` varchar(255) NOT NULL COMMENT '帖子吧吧名',
  `posts_bar_logo_path` varchar(255) NOT NULL COMMENT '帖子logo地址',
  PRIMARY KEY (`posts_bar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_bar` */

insert  into `posts_bar`(`posts_bar_id`,`posts_bar_name`,`posts_bar_logo_path`) values (1,'前端吧','1.jpg'),(2,'LOL陪玩吧','2.jpg'),(3,'安徽师范大学吧','3.jpg'),(4,'QQ飞车吧','4.jpg'),(5,'穿越火线吧','5.jpg'),(6,'马鞍山师范高等专科学校吧','6.jpg'),(7,'笑话吧','7.jpg'),(8,'最强大脑吧','8.jpg'),(9,'编程吧','9.jpg'),(10,'QQ炫舞吧','10.jpg');

/*Table structure for table `posts_bar_like` */

DROP TABLE IF EXISTS `posts_bar_like`;

CREATE TABLE `posts_bar_like` (
  `posts_bar_like_id` int NOT NULL AUTO_INCREMENT COMMENT '吧 喜欢表id',
  `posts_bar_id` int NOT NULL COMMENT '吧 id',
  `user_id` int NOT NULL COMMENT '用户id -- 喜欢的用户',
  PRIMARY KEY (`posts_bar_like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_bar_like` */

insert  into `posts_bar_like`(`posts_bar_like_id`,`posts_bar_id`,`user_id`) values (1,1,14),(2,1,16),(3,2,14);

/*Table structure for table `posts_com` */

DROP TABLE IF EXISTS `posts_com`;

CREATE TABLE `posts_com` (
  `posts_com_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子评论表id',
  `posts_id` int NOT NULL COMMENT '帖子id',
  `posts_com_content` varchar(255) NOT NULL COMMENT '帖子评论内容',
  `user_id` int NOT NULL COMMENT '用户id',
  `posts_com_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`posts_com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_com` */

insert  into `posts_com`(`posts_com_id`,`posts_id`,`posts_com_content`,`user_id`,`posts_com_time`) values (1,16,'我是一条评论哦',14,'2022-11-25 09:46:13'),(9,34,'我是第一条评论',14,'2022-11-27 19:50:12'),(16,34,'我是二楼',14,'2022-11-28 09:12:14'),(18,34,'333',15,'2022-11-28 13:48:53'),(19,29,'1',14,'2022-12-05 13:52:21'),(21,35,'2',14,'2022-12-05 15:32:55'),(22,35,'33',15,'2022-12-05 16:15:10');

/*Table structure for table `posts_com_com` */

DROP TABLE IF EXISTS `posts_com_com`;

CREATE TABLE `posts_com_com` (
  `posts_com_com_id` int NOT NULL AUTO_INCREMENT,
  `posts_com_id` int NOT NULL,
  `user_id` int NOT NULL,
  `posts_com_com_content` varchar(255) NOT NULL,
  `posts_com_com_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`posts_com_com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_com_com` */

insert  into `posts_com_com`(`posts_com_com_id`,`posts_com_id`,`user_id`,`posts_com_com_content`,`posts_com_com_time`) values (28,16,14,'我是二楼的第一条回复','2022-11-28 09:12:25'),(30,16,14,'222','2022-11-28 13:02:19'),(31,9,14,'111','2022-11-28 13:46:39'),(32,16,15,'333','2022-11-28 13:49:10'),(33,19,14,'2','2022-12-05 13:52:27'),(34,21,14,'22','2022-12-05 15:33:01'),(35,22,14,'333','2022-12-05 16:15:19');

/*Table structure for table `posts_history` */

DROP TABLE IF EXISTS `posts_history`;

CREATE TABLE `posts_history` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `posts_id` int NOT NULL,
  `posts_title` varchar(64) NOT NULL,
  `posts_bar_name` varchar(64) NOT NULL,
  `history_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_history` */

insert  into `posts_history`(`history_id`,`user_id`,`posts_id`,`posts_title`,`posts_bar_name`,`history_time`) values (226,14,27,'前端前端','前端吧','2022-12-05 15:50:22'),(240,15,34,'测试','前端吧','2022-12-05 16:14:34'),(243,15,29,'QQ飞车哦','QQ飞车吧','2022-12-05 16:14:36'),(245,14,31,'大脑','最强大脑吧','2022-12-05 16:14:38'),(249,15,35,'飞车','QQ飞车吧','2022-12-05 16:15:20'),(264,14,29,'QQ飞车哦','QQ飞车吧','2022-12-05 16:23:48'),(267,14,34,'测试','前端吧','2022-12-05 16:24:32'),(268,14,30,'43231','前端吧','2022-12-07 15:01:41'),(270,14,35,'飞车','QQ飞车吧','2023-04-27 14:32:15');

/*Table structure for table `posts_img` */

DROP TABLE IF EXISTS `posts_img`;

CREATE TABLE `posts_img` (
  `posts_img_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子图片表id',
  `posts_id` int NOT NULL COMMENT '帖子id',
  `posts_img_path` varchar(255) NOT NULL COMMENT '帖子图片地址',
  PRIMARY KEY (`posts_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_img` */

insert  into `posts_img`(`posts_img_id`,`posts_id`,`posts_img_path`) values (17,16,'\\16_src=http___www.zsc5.com_d_file_bigpic_2019-07-10_11_1115201786.jpg&refer=http___www.zsc5.webp'),(18,17,'\\17_u=2630013711,2420456103&fm=253&fmt=auto&app=120&f=JPEG.webp'),(19,17,'\\17_u=1882316300,3119310550&fm=253&fmt=auto&app=120&f=JPEG.webp'),(20,18,'\\18_u=289540961,3933027878&fm=253&fmt=auto&app=120&f=JPEG.webp'),(21,18,'\\18_u=1142140242,3726674946&fm=253&fmt=auto&app=138&f=JPEG.webp'),(22,18,'\\18_u=3787473611,3330068340&fm=253&fmt=auto&app=138&f=JPEG.webp'),(23,19,'\\19_u=148336726,1200440456&fm=253&fmt=auto&app=138&f=JPEG.webp'),(24,20,'\\20_wx.png'),(25,21,'\\21_github.jpg'),(26,21,'\\21_u=2630013711,2420456103&fm=253&fmt=auto&app=120&f=JPEG.webp'),(27,22,'\\22_u=3787473611,3330068340&fm=253&fmt=auto&app=138&f=JPEG.webp'),(28,23,'\\23_u=148336726,1200440456&fm=253&fmt=auto&app=138&f=JPEG.webp'),(29,24,'\\24_u=2630013711,2420456103&fm=253&fmt=auto&app=120&f=JPEG.webp'),(30,25,'\\25_src=http___www.zsc5.com_d_file_bigpic_2019-07-10_11_1115201786.jpg&refer=http___www.zsc5.webp'),(31,25,'\\25_u=4281860672,1981139753&fm=253&fmt=auto&app=138&f=JPEG.webp'),(32,26,'\\26_u=1882316300,3119310550&fm=253&fmt=auto&app=120&f=JPEG.webp'),(33,27,'\\27_u=289540961,3933027878&fm=253&fmt=auto&app=120&f=JPEG.webp'),(34,27,'\\27_u=1142140242,3726674946&fm=253&fmt=auto&app=138&f=JPEG.webp'),(35,27,'\\27_u=3787473611,3330068340&fm=253&fmt=auto&app=138&f=JPEG.webp'),(36,28,'\\28_u=511065577,1628409028&fm=253&fmt=auto&app=120&f=JPEG.webp'),(37,29,'\\29_u=701033976,3048768085&fm=253&fmt=auto&app=138&f=JPEG.webp'),(38,29,'\\29_u=1783544891,1949283678&fm=253&fmt=auto&app=138&f=PNG.webp'),(39,30,'\\30_11140001.png'),(40,31,'\\31_u=3787473611,3330068340&fm=253&fmt=auto&app=138&f=JPEG.webp'),(41,34,'34_wx.png'),(42,34,'34_zfb.jpg'),(43,35,'35_ss.png');

/*Table structure for table `posts_like` */

DROP TABLE IF EXISTS `posts_like`;

CREATE TABLE `posts_like` (
  `posts_like_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子喜欢表id',
  `posts_id` int NOT NULL COMMENT '帖子id',
  `user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`posts_like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_like` */

insert  into `posts_like`(`posts_like_id`,`posts_id`,`user_id`) values (3,34,15),(4,17,14),(16,31,14),(33,30,14),(74,34,14),(75,35,14),(76,29,14),(77,27,14),(78,35,15);

/*Table structure for table `posts_save` */

DROP TABLE IF EXISTS `posts_save`;

CREATE TABLE `posts_save` (
  `posts_save_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子收藏表id',
  `posts_id` int NOT NULL COMMENT '帖子id',
  `save_user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`posts_save_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `posts_save` */

insert  into `posts_save`(`posts_save_id`,`posts_id`,`save_user_id`) values (1,16,14),(2,17,14),(5,27,14);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nickname` varchar(128) NOT NULL COMMENT '用户昵称',
  `user_sex` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '男' COMMENT '用户性别',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户手机号',
  `avatar` varchar(255) NOT NULL,
  `user_autograph` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '默认的个性签名' COMMENT '用户个性签名',
  `user_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`username`,`password`,`nickname`,`user_sex`,`phone`,`avatar`,`user_autograph`,`user_time`) values (14,'前端菜鸟','123456','我是编号14','男','13359074748','github.jpg','默认的个性签名123','2022-10-26 17:09:06'),(15,'用户名','123456','我是编号15','男','18949981187','github.jpg','默认的个性签名','2022-11-21 15:49:22'),(17,'用户名1','123456','我是编号17','男','12312312321','github.jpg','默认的个性签名','2022-11-23 22:45:58');

/*Table structure for table `users_like` */

DROP TABLE IF EXISTS `users_like`;

CREATE TABLE `users_like` (
  `users_like_id` int NOT NULL AUTO_INCREMENT COMMENT '关注表id',
  `user_id` int NOT NULL COMMENT '当前用户id',
  `user_b_id` int NOT NULL COMMENT '被关注用户的id',
  PRIMARY KEY (`users_like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users_like` */

insert  into `users_like`(`users_like_id`,`user_id`,`user_b_id`) values (1,14,15),(2,15,14),(4,17,15),(5,14,17);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
