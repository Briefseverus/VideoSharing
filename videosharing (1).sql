-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 13, 2023 at 07:06 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `videosharing`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `name`) VALUES
(1, 'Category 1'),
(2, 'Category 2'),
(3, 'Category 3'),
(4, 'Category4');

-- --------------------------------------------------------

--
-- Table structure for table `channels`
--

CREATE TABLE `channels` (
  `channel_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text,
  `creator_id` int NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `channels`
--

INSERT INTO `channels` (`channel_id`, `name`, `description`, `creator_id`, `create_date`) VALUES
(10, 'Channel 1', 'Description 1', 4, '2023-01-01 00:00:00'),
(11, 'Channel 2', 'Description 2', 5, '2023-02-01 00:00:00'),
(12, 'Channel 3', 'Description 3', 6, '2023-03-01 00:00:00'),
(14, 'Update', 'Update', 12, '2023-10-24 07:50:36'),
(16, 'Update', 'Update', 11, '2023-10-27 05:04:29'),
(17, 'Update', 'Update', 20, '2023-11-07 10:28:26'),
(18, 'Channel x22', 'Channel x', 20, '2023-11-11 15:27:42'),
(19, 'Update', 'Update', 20, '2023-11-12 07:28:37'),
(20, 'Channel x', 'Channel x', 21, '2023-11-12 11:10:16'),
(23, 'Channel x', 'Channel x', 21, '2023-11-13 18:35:13'),
(24, 'Channel x', 'Channel x', 21, '2023-11-13 18:37:13'),
(25, 'Channel x', 'Channel x', 21, '2023-11-13 18:37:16'),
(26, 'Channel x', 'Channel x', 21, '2023-11-13 18:37:17'),
(27, 'Channel x', 'Channel x', 21, '2023-11-13 18:37:18'),
(28, 'Channel x', 'Channel x', 21, '2023-11-13 18:37:19'),
(29, 'Channel x', 'Channel x', 23, '2023-11-13 18:42:16'),
(30, 'Channel x', 'Channel x', 23, '2023-11-13 18:42:33');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int NOT NULL,
  `video_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text NOT NULL,
  `post_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comment_id`, `video_id`, `user_id`, `content`, `post_date`) VALUES
(8, 18, 21, 'update', '2023-11-13 10:13:40.059000');

-- --------------------------------------------------------

--
-- Table structure for table `refresh_token`
--

CREATE TABLE `refresh_token` (
  `id` int NOT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `join_date` datetime(6) DEFAULT NULL,
  `is_vip` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `role`, `enabled`, `join_date`, `is_vip`) VALUES
(4, 'user1', 'password1', 'user1@example.com', 'ADMIN', b'1', '2023-01-01 00:00:00.000000', NULL),
(5, 'user2', 'password2', 'user2@example.com', 'USER', b'1', '2023-02-01 00:00:00.000000', NULL),
(6, 'user3', 'password3', 'user3@example.com', 'USER', b'1', '2023-03-01 00:00:00.000000', NULL),
(7, 'username11', '$2a$10$wQHHNI392AYIaJAl/5bYrOU88G.0xpDveTJ4zvd7QhmOhBKfRZyAy', 'username@gmail.com', 'ROLE_null', b'0', NULL, NULL),
(8, 'username111', '$2a$10$NXd2TaW1RTncWK6S/f.V4OXQfmVeDTsglTvTuewlLJsn/PA3KII0a', 'username@gmail.com', 'USER', b'0', '2023-10-23 18:20:33.020000', NULL),
(9, 'username1111', '$2a$10$MYcm.2lyOMRUn0TZLoUcd..faatWXjJmwYz9D86UCNwho2B64d/gy', 'username@gmail.com', 'USER', b'1', '2023-10-23 18:23:21.048000', NULL),
(10, 'username11111', '$2a$10$OureWF1P.8BeXXFmu8DuWe.CnLpz/d3qtdKGISzmcnj.Cw4jCLrEe', 'username@gmail.com', 'USER', b'1', '2023-10-23 18:34:08.421000', NULL),
(11, 'update', 'update', 'update@gmail.com', 'USER', b'1', '2023-10-23 18:38:15.991000', NULL),
(12, 'username1121', '$2a$10$Vif.pfydMQXZ.B6ZFSGNMeEus2DkNP9CpPgneAnHRcOtro940CgW.', 'username@gmail.com', 'USER', b'1', '2023-10-23 20:29:00.247000', NULL),
(13, 'username11211', '$2a$10$MqGkJBZ7VbSHnv8DM0QZ/uRMls2CvSC5LuIh6.IdUIyUdt/0.FvBu', 'username@gmail.com', 'USER', b'1', '2023-10-25 04:34:09.984000', NULL),
(14, 'update', '$2a$10$St0bIu2rw1SLEnRvdQhAIu18JjBEm.cMmSqbf0eOoI9fhfQaMZJTG', 'update@gmail.com', 'USER', b'1', '2023-10-27 06:06:16.676000', NULL),
(15, 'userupdated', '$2a$10$pVdnTRejfp43X4tWLOsr9OrHXrY4pavm05ScwHzdeEMW9T/taujUG', 'update@gmail.com', 'USER', b'1', '2023-10-27 06:10:59.312000', NULL),
(16, 'qssss', '$2a$10$O0Kii9lHiibEmHCMsCwv9.ZeApqG.OTak1IZlXuur7InYe7/M5v2S', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:08:33.756000', NULL),
(17, 'anbquan1', '$2a$10$rrF6h3FYxZPGoumAp1SbPOb20s9WSMD7mUnqagPFs7eg05vFz.ZH.', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:09:54.854000', NULL),
(18, 'anbquan771', '$2a$10$YEh9dJcW1EXaViHQU9XioOX3J4InM0zdFhZQE5IUByStu6HRjcJ72', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:15:09.021000', NULL),
(19, 'userupdatd', '$2a$10$.ZdiTYWOyNKXcLBuRZJ/Zu2U3wBZnqg6E7C5Dd6PYVjnI108LrssO', 'update@gmail.com', 'USER', b'1', '2023-10-27 23:09:14.757000', NULL),
(20, 'anbquean7721', '$2a$10$08G5vUhvr8mIU15.M31jdeQyoHBjBNqvYkDOD.27ycnKsv.hlIzV.', 'username@gmail.com', 'USER', b'1', '2023-11-07 10:20:15.775000', NULL),
(21, '1', '$2a$10$kZGEQQilvbu2SEckYe.XTOZ/BEM8f9ySUjztja01O0T1xQwbuLRGS', 'username@gmail.com', 'USER', b'1', '2023-11-12 11:09:34.605000', b'1'),
(22, '2', '$2a$10$RETm7dXI.HuCIWyeEbDwguI8iAsbqlBNEA47wO/DJ0AL6IFra6eH.', 'quan52188@donga.edu.vn', 'USER', b'1', '2023-11-13 11:59:35.889000', b'1'),
(23, '3', '$2a$10$4E/RlqFW.KCfecaX48P9sO/xUdiWARXXf38JdK5Tcpb02QmIMjIQW', 'quan52188@donga.edu.vn', 'USER', b'1', '2023-11-13 12:05:28.941000', b'1'),
(24, '4', '$2a$10$xzbeWiO4busI5kOi8094fOQzd9y6aUlMqOFH.WcLkLDT.pBFH13zy', 'nhinsangngang@Gmail.com', 'USER', b'1', '2023-11-13 12:41:55.355000', b'0'),
(25, '5', '$2a$10$jtl7GmbWCRsB5UcB.g9Qq.HKiF4QyoDKNUq4GlZVpaIsgNUItLIea', 'nhinlaisangngang@Gmail.com', 'USER', b'1', '2023-11-13 12:43:42.096000', b'0'),
(26, '6', '$2a$10$CbSlNu4wGwL6uIazNsLUdupC6gs4CscK7BGpomoLi2DkBzGlp0z0O', 'nhinlaisangngang@Gmail.com', 'USER', b'1', '2023-11-13 14:03:01.320000', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `user_channel_subs`
--

CREATE TABLE `user_channel_subs` (
  `sub_id` int NOT NULL,
  `user_id` int NOT NULL,
  `channel_id` int NOT NULL,
  `subscribe_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_channel_subs`
--

INSERT INTO `user_channel_subs` (`sub_id`, `user_id`, `channel_id`, `subscribe_date`) VALUES
(7, 4, 10, '2023-01-25 00:00:00.000000'),
(8, 5, 11, '2023-02-15 00:00:00.000000'),
(9, 6, 12, '2023-03-05 00:00:00.000000'),
(10, 20, 12, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(4, 1),
(5, 2),
(6, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(24, 2),
(25, 2),
(26, 2);

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE `videos` (
  `video_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `filename` varchar(255) NOT NULL,
  `upload_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `duration` bigint DEFAULT NULL,
  `channel_id` int NOT NULL,
  `video_url` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `videos`
--

INSERT INTO `videos` (`video_id`, `title`, `description`, `filename`, `upload_date`, `duration`, `channel_id`, `video_url`) VALUES
(18, 'update', 'update', 'test 1', '2023-11-12 11:16:17', 284328, 20, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/51c336f4-508e-4f14-b14b-a43cff7b18fa?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1731323771&Signature=I0v5DrMsf2SB1IzONsiDOEKa1lbjvXLU862gFNhIwOUnvMijB7MBUzyUlD8GCAeBK4Hrq%2FCqIRKC14o%2BK7rAGYRa%2B6cXPodQsqfPruHQQrms6pPrX9eabsb%2F5XaKXt7e%2B%2BJKBXjRxI%2FpWbVbIJ09bRhv9vl3NReHJl%2FNwRskU2aFMU7WqlXJYZLySXe%2BWZA6iES0IfACFFaRI1INEClqybG1vxUekqyRjLYgjgkI20q36BqVWrH%2BfPSl8J%2Ffv83hruEo%2F15WJAV0tlWeaI0dWoW%2F1kTlYqOEM90NQ6ehoA7rAq6w0yoWRAhyi8DsPSqqkdjK6xfVFl%2Bti6zx1NMsIw%3D%3D'),
(20, 'test 1', 'test 1', 'test 1', '2023-11-13 18:44:58', 284328, 30, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/03024ebb-01af-4352-b79c-7a40cca2b057?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1731437092&Signature=CCpU3H0XbGfPM%2FCgOXTd6a12O%2FCpeGAjqgsJIuKUAOvm1yUSPzwrxl3s36Z2EmzguWz3u1864hq8sX4vc7d4ad7SHq0W9QuCpy9DhGqoN9U2eyuZNQ4Ms1gZfsvUv2zsKO8jWYW2yJf5YtSarAIRDtQ3VM%2BIpIrPxiqx%2F2JcE0SQCIVixhYIQOiOAg6wy2M9O%2FdCRIG8o3V3Zx2jr9LlfWeARH0sZ3RD6pfPdcednIX3%2FbvQ%2FmwkuUpokSwy34KQx9Br73KS6LK64uQ0QOHunzUcI8XtXZMs%2Fe0EE70xTQfeGGA369cTl5%2Flmdf0v0Qgb92nOHZyAsa314KZB%2BFy6A%3D%3D'),
(21, 'test 1', 'test 1', 'test 1', '2023-11-13 18:45:15', 284328, 30, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/36bfacf8-3cde-4fc2-8c7c-fe949c6fdee5?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1731437113&Signature=dpW3iVvfItpCF%2FqN%2FCdEbNmLQz1t%2F4R%2B2IXrYrbBUjmvdDGfuwMHFrlfw7xPIq9w%2BxLqb6oxqhlMT4g13F%2B%2FJ6EdN70jhrRIGgXoU%2FM5tYn2%2BkYAaKRNDlRKqpAsTa0Qn%2BKGmws3pny%2FKa%2FKB73RFNbiOmRkH3DsxkDHAhv28PlMrPCKkQmgKVTuuwzq%2Bw9CKuM99gMAe8uFlcLpcyg1zNdYFxUr1U6m%2BQj0RUYcJV%2Fy%2FOcWgy5X0s5gKntYu4B1dCvJEkkWAyaPbR%2BYHxKXFl3NVhouvaDbQ4sK43QWEdWbFPLuFLa5Et5t%2FJSnm1DFU5FWDwuNCjdWLoI3kfxltg%3D%3D'),
(22, 'test 1', 'test 1', 'test 1', '2023-11-13 18:45:50', 284328, 30, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/7887b130-3bc4-42c5-83bd-1f94df7c03fd?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1731437148&Signature=pbTd0JhstwKxp98AwD4j3QGrlStDfkP%2FxiQCDFJ3JI3s2XEbiepBkfsRiX0DGSpQtZKirlGTzKpApfNH%2F3HKdjVRzoJmudAkx9HjSt6PVO6qmz0WiPez5MgLOBPD4Le%2BxcmhSvn1qvnsedpUkZ6CVtySAq6eFAR8nwEfIKZ8t02ElZr%2FJ1eLIF%2BI25ydbMeqKqxk5Cst%2F4P%2BjQ3fCSa%2B0wQu8BTKJMa3E%2BizaxNIl1wbn466iIoR5LWoHi5DYgRUb3nsMC4VJdPUgGPpFQKpG2ZoQSjb4%2FQljKHnhRSqWNkqtCVl%2BM5giCdaKrIdT9s00iQUY9qXZohVFGClZSJYgQ%3D%3D'),
(23, 'test 1', 'test 1', 'test 1', '2023-11-13 18:46:08', 284328, 30, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/cdba6c58-e1d9-4884-bacd-106bdb537894?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1731437167&Signature=Vp7FlROoSMBo1rp1jHYkHPy1XjtFKPOCIpsb30WG01z82B%2FZLopS8mDooCzwo1dBkSF3S8V5zIE7RZ1RXSbzr0rQ0miDSAsD5ZGxhKuIXygc6uHoObLRDYkMIMZkN3%2FIlpoGR3AlOVPNBPWWBnodudFL1yvBxyd5Fgz1t7EApWh1pxpcutNg%2BE8iPT49QKvOl%2F21eFQkKu1xnyAdaP2OgoEnPawCWNPFipo%2B2F5r6kqx9JNRND9nW%2BtGKnxvGindWScfE3r9YajR9CEYiIitXUkA0eTzRvlaD%2FcU%2B3PntwnbSGjkNC%2BI54cr35LrkmSdx7GJMcf5OMFO1wg1YBW3jQ%3D%3D');

-- --------------------------------------------------------

--
-- Table structure for table `video_categories`
--

CREATE TABLE `video_categories` (
  `category_mapping_id` int NOT NULL,
  `video_id` int NOT NULL,
  `category_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `video_categories`
--

INSERT INTO `video_categories` (`category_mapping_id`, `video_id`, `category_id`) VALUES
(4, 18, 1);

-- --------------------------------------------------------

--
-- Table structure for table `video_ratings`
--

CREATE TABLE `video_ratings` (
  `rating_id` int NOT NULL,
  `video_id` int NOT NULL,
  `user_id` int NOT NULL,
  `rating` int DEFAULT NULL,
  `rated_date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `video_ratings`
--

INSERT INTO `video_ratings` (`rating_id`, `video_id`, `user_id`, `rating`, `rated_date`) VALUES
(7, 18, 21, 4, '2023-11-13 11:24:55');

-- --------------------------------------------------------

--
-- Table structure for table `video_tags`
--

CREATE TABLE `video_tags` (
  `tag_id` int NOT NULL,
  `tag_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `video_tags`
--

INSERT INTO `video_tags` (`tag_id`, `tag_name`) VALUES
(1, 'Tag 1'),
(2, 'Tag 2'),
(3, 'Tag 3');

-- --------------------------------------------------------

--
-- Table structure for table `video_tags_mapping`
--

CREATE TABLE `video_tags_mapping` (
  `mapping_id` int NOT NULL,
  `video_id` int NOT NULL,
  `tag_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `video_tags_mapping`
--

INSERT INTO `video_tags_mapping` (`mapping_id`, `video_id`, `tag_id`) VALUES
(4, 18, 1);

-- --------------------------------------------------------

--
-- Table structure for table `video_views`
--

CREATE TABLE `video_views` (
  `view_id` int NOT NULL,
  `video_id` int NOT NULL,
  `viewer_ip` varchar(50) NOT NULL,
  `view_datetime` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `channels`
--
ALTER TABLE `channels`
  ADD PRIMARY KEY (`channel_id`),
  ADD KEY `channels_ibfk_1` (`creator_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `comments_ibfk_1` (`video_id`),
  ADD KEY `comments_ibfk_2` (`user_id`);

--
-- Indexes for table `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f95ixxe7pa48ryn1awmh2evt7` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_channel_subs`
--
ALTER TABLE `user_channel_subs`
  ADD PRIMARY KEY (`sub_id`),
  ADD KEY `user_channel_subs_ibfk_1` (`user_id`),
  ADD KEY `user_channel_subs_ibfk_2` (`channel_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`video_id`),
  ADD KEY `videos_ibfk_2` (`channel_id`);

--
-- Indexes for table `video_categories`
--
ALTER TABLE `video_categories`
  ADD PRIMARY KEY (`category_mapping_id`),
  ADD KEY `video_categories_ibfk_1` (`video_id`),
  ADD KEY `video_categories_ibfk_2` (`category_id`);

--
-- Indexes for table `video_ratings`
--
ALTER TABLE `video_ratings`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `video_ratings_ibfk_1` (`video_id`),
  ADD KEY `video_ratings_ibfk_2` (`user_id`);

--
-- Indexes for table `video_tags`
--
ALTER TABLE `video_tags`
  ADD PRIMARY KEY (`tag_id`);

--
-- Indexes for table `video_tags_mapping`
--
ALTER TABLE `video_tags_mapping`
  ADD PRIMARY KEY (`mapping_id`),
  ADD KEY `video_tags_mapping_ibfk_1` (`video_id`),
  ADD KEY `video_tags_mapping_ibfk_2` (`tag_id`);

--
-- Indexes for table `video_views`
--
ALTER TABLE `video_views`
  ADD PRIMARY KEY (`view_id`),
  ADD KEY `video_views_ibfk_1` (`video_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `channels`
--
ALTER TABLE `channels`
  MODIFY `channel_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user_channel_subs`
--
ALTER TABLE `user_channel_subs`
  MODIFY `sub_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `videos`
--
ALTER TABLE `videos`
  MODIFY `video_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `video_categories`
--
ALTER TABLE `video_categories`
  MODIFY `category_mapping_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `video_ratings`
--
ALTER TABLE `video_ratings`
  MODIFY `rating_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `video_tags`
--
ALTER TABLE `video_tags`
  MODIFY `tag_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `video_tags_mapping`
--
ALTER TABLE `video_tags_mapping`
  MODIFY `mapping_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `video_views`
--
ALTER TABLE `video_views`
  MODIFY `view_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `channels`
--
ALTER TABLE `channels`
  ADD CONSTRAINT `channels_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `refresh_token`
--
ALTER TABLE `refresh_token`
  ADD CONSTRAINT `FKjtx87i0jvq2svedphegvdwcuy` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_channel_subs`
--
ALTER TABLE `user_channel_subs`
  ADD CONSTRAINT `user_channel_subs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_channel_subs_ibfk_2` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`channel_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `videos`
--
ALTER TABLE `videos`
  ADD CONSTRAINT `videos_ibfk_2` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`channel_id`);

--
-- Constraints for table `video_categories`
--
ALTER TABLE `video_categories`
  ADD CONSTRAINT `video_categories_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `video_ratings`
--
ALTER TABLE `video_ratings`
  ADD CONSTRAINT `video_ratings_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_ratings_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `video_tags_mapping`
--
ALTER TABLE `video_tags_mapping`
  ADD CONSTRAINT `video_tags_mapping_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_tags_mapping_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `video_tags` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `video_views`
--
ALTER TABLE `video_views`
  ADD CONSTRAINT `video_views_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `refresh_token_cleanup` ON SCHEDULE EVERY 10 MINUTE STARTS '2023-10-30 19:53:29' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM refresh_token
WHERE expiry_date < NOW()$$

CREATE DEFINER=`root`@`localhost` EVENT `DeleteOldDataEvent` ON SCHEDULE EVERY 1 DAY STARTS '2023-10-30 19:57:32' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    DELETE FROM refresh_token WHERE expiry_date < NOW();
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
