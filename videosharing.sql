-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 12, 2023 at 08:51 AM
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
(19, 'Update', 'Update', 20, '2023-11-12 07:28:37');

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
(4, 4, 4, 'Comment 1', '2023-01-16 00:00:00.000000'),
(5, 5, 5, 'Comment 2', '2023-02-25 00:00:00.000000'),
(6, 6, 6, 'Comment 3', '2023-03-15 00:00:00.000000');

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
  `join_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `role`, `enabled`, `join_date`) VALUES
(4, 'user1', 'password1', 'user1@example.com', 'ADMIN', b'1', '2023-01-01 00:00:00.000000'),
(5, 'user2', 'password2', 'user2@example.com', 'USER', b'1', '2023-02-01 00:00:00.000000'),
(6, 'user3', 'password3', 'user3@example.com', 'USER', b'1', '2023-03-01 00:00:00.000000'),
(7, 'username11', '$2a$10$wQHHNI392AYIaJAl/5bYrOU88G.0xpDveTJ4zvd7QhmOhBKfRZyAy', 'username@gmail.com', 'ROLE_null', b'0', NULL),
(8, 'username111', '$2a$10$NXd2TaW1RTncWK6S/f.V4OXQfmVeDTsglTvTuewlLJsn/PA3KII0a', 'username@gmail.com', 'USER', b'0', '2023-10-23 18:20:33.020000'),
(9, 'username1111', '$2a$10$MYcm.2lyOMRUn0TZLoUcd..faatWXjJmwYz9D86UCNwho2B64d/gy', 'username@gmail.com', 'USER', b'1', '2023-10-23 18:23:21.048000'),
(10, 'username11111', '$2a$10$OureWF1P.8BeXXFmu8DuWe.CnLpz/d3qtdKGISzmcnj.Cw4jCLrEe', 'username@gmail.com', 'USER', b'1', '2023-10-23 18:34:08.421000'),
(11, 'update', 'update', 'update@gmail.com', 'USER', b'1', '2023-10-23 18:38:15.991000'),
(12, 'username1121', '$2a$10$Vif.pfydMQXZ.B6ZFSGNMeEus2DkNP9CpPgneAnHRcOtro940CgW.', 'username@gmail.com', 'USER', b'1', '2023-10-23 20:29:00.247000'),
(13, 'username11211', '$2a$10$MqGkJBZ7VbSHnv8DM0QZ/uRMls2CvSC5LuIh6.IdUIyUdt/0.FvBu', 'username@gmail.com', 'USER', b'1', '2023-10-25 04:34:09.984000'),
(14, 'update', '$2a$10$St0bIu2rw1SLEnRvdQhAIu18JjBEm.cMmSqbf0eOoI9fhfQaMZJTG', 'update@gmail.com', 'USER', b'1', '2023-10-27 06:06:16.676000'),
(15, 'userupdated', '$2a$10$pVdnTRejfp43X4tWLOsr9OrHXrY4pavm05ScwHzdeEMW9T/taujUG', 'update@gmail.com', 'USER', b'1', '2023-10-27 06:10:59.312000'),
(16, 'qssss', '$2a$10$O0Kii9lHiibEmHCMsCwv9.ZeApqG.OTak1IZlXuur7InYe7/M5v2S', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:08:33.756000'),
(17, 'anbquan1', '$2a$10$rrF6h3FYxZPGoumAp1SbPOb20s9WSMD7mUnqagPFs7eg05vFz.ZH.', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:09:54.854000'),
(18, 'anbquan771', '$2a$10$YEh9dJcW1EXaViHQU9XioOX3J4InM0zdFhZQE5IUByStu6HRjcJ72', 'username@gmail.com', 'USER', b'1', '2023-10-27 10:15:09.021000'),
(19, 'userupdatd', '$2a$10$.ZdiTYWOyNKXcLBuRZJ/Zu2U3wBZnqg6E7C5Dd6PYVjnI108LrssO', 'update@gmail.com', 'USER', b'1', '2023-10-27 23:09:14.757000'),
(20, 'anbquean7721', '$2a$10$08G5vUhvr8mIU15.M31jdeQyoHBjBNqvYkDOD.27ycnKsv.hlIzV.', 'username@gmail.com', 'USER', b'1', '2023-11-07 10:20:15.775000');

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
(20, 2);

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
(4, 'Video 1', 'Description 1', 'video1.mp4', '2023-01-15 00:00:00', 300, 10, NULL),
(5, 'Video 2', 'Description 2', 'video2.mp4', '2023-02-20 00:00:00', 240, 11, NULL),
(6, 'Video 3', 'Description 3', 'video3.mp4', '2023-03-12 00:00:00', 180, 12, NULL),
(12, 'test 1', 'test 1', 'test 1', '2023-11-11 14:12:23', 284328, 17, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/c73f45af-d29a-4c7a-8513-fe1bc99733c6?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1699715537&Signature=TeeStSAcKUzZikY0%2FmAhZK5gsEnjXPMZnO%2FVC5UnO91bZraEApmpw65%2FiXuYljO7Y6RAF3u4pT3orxi29%2B%2BDJkIvO9QZ9u7EcZqgZ8LI2as4JpDoxR6Maejgfp1CJLOHbxkBjv0%2FyPXB7GSFRt75Vmkafq5V%2BtpRhuamUfR7ybLqF1bHtIVKXszGTnl7HIKma0qfebC4C83zBNjtcKILw6dqWvofHYvOFMMq2tKg84gFWtdbvF%2Bo1C33Bo7DNT%2F2bSg9apT7fIJj4YQlRuW0g%2BhTV9IgcPX3%2B3NNyh4OT0MZMGJxScLhxCVV6xg5qiqNWZdhrl%2BDx59XwhYFrXHImQ%3D%3D'),
(13, 'test 1', 'test 1', 'test 1', '2023-11-11 17:08:22', 284328, 17, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/f7a3bf4e-6624-4015-98ce-ff7c916e2fe3?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1699726096&Signature=NYxBl6g5G9lO002oGuQBsetHwm0R8sv0qrW%2Bc748dy1uw3Q9DfSSUCUliTRpDFOv%2BPr%2B2j%2Fc8kzilhdKNZ0KR1xaGuoy9P2boOcRacleK%2B1u45mM3ttfTsp1KWCmm9jIdKSWCo%2Bzn3jxhEbgokl0WCroG%2F1aYkDmEwgqJfNbGrMoBfNk1BqK6YWRRlbVrfDb8fKi2DmDbE46tzWo5BiTvglP%2FnpuJFzxyrGrox4ZSIbGC1m6ALb9%2F6dtxjTWIFibV7Cxovn3LzPd%2BtyBhCeoHVsuwF%2BhUjgTUBUPssFrCnPIvSp3UKmAuR3H45GDg9fUck07l3wlO2R9J6O9bDdh%2FA%3D%3D'),
(14, 'test 1', 'test 1', 'test 1', '2023-11-11 17:09:29', 284328, 18, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/90c638ab-52e7-4bcc-8968-f69321f1f148?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1699726168&Signature=Dtk6jlkMG0TAIEZNwxN39ofGI5yxiuAgqXJUEuQse1jyfStxWupvvR6HnuhmegSd%2BjzqoAa9fqu4j7WgAIDb6%2Bfs9kBEjF9Z6F21sG70sw5nFrg%2B5J09x9pSzaE6BHVAVwKdjCvSPRGygBdMVk4rC8w3PfGlVMA6gEGizzLt5HwSwtRh7OfzkVTOT6q%2BrGKIPu0ASUIIt9DdgPnooBR2uGfKFqZilmA7dmgih%2BeovVQU%2BdVdm5D%2Bel5iDoZZCCmZ1Odk2XcSrsBL%2Bf45BGcgkZcS2HTHf4SXu3PeCQVqHbYAM2e1Y2dIHZDir3YQ7ntOuvPXuDRMQZE4HP8mFQqTpQ%3D%3D'),
(15, 'test 1', 'test 1', 'test 1', '2023-11-12 07:30:04', 284328, 17, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/506541ab-2055-477b-ba20-3250fbbf15f0?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1699777797&Signature=dU7bGkVuZxfCDHSBYNGgn7TjNsqUf6E5Di4l2CVnmKBEp2r9B6bry0XZQZO8QJavcYkm5lk1xg7GTnDngMLkHl2D7T2EshSDgCG%2BiPn3f0IS2M3XhnMVElmIjJ3Z1aY9G7S6v0cHESLc3SoMXf3MSURopOdSWnIpDEfwPRY9249EyPdo5Z2eiz5hCwyS4uF1mW0Dk6EouvsnXEQUvcKptm%2BwikTIxfeLIsqCBQ9eakNeJf2h3f4tr4cRmwcKJi%2FUP7tD%2BKgItiRxAVn1UvBk65RA0EygwX45w0HZcSv8yHXQLZxyfvJg83yzu%2Bw2v8GhkSjhhYszl%2Bzn8kpcGKlANw%3D%3D'),
(16, 'test 1', 'test 1', 'test 1', '2023-11-12 07:32:47', 284328, 17, 'https://storage.googleapis.com/videosharing-9c5f9.appspot.com/fdff7d3a-1daa-4854-b3bf-1f5ecd799113?GoogleAccessId=firebase-adminsdk-sqzec@videosharing-9c5f9.iam.gserviceaccount.com&Expires=1699777961&Signature=l1b%2BGvb116adFeEq9fsOV45d%2F%2BFlDgZYDdMSU31v7ADcO%2BbR9yoYJywD1%2Bx7GZ2KhrRCN0JdffSy4eBjslYOrAoXN8WASjTg3jxpPhqn0ZDIemclp9EIsEXgxn37aI94dlQQjXXZm5PDMq4CD2u9wonvVsAGsy5e3jVa6SX5tQTxGrfyGUJ0SvhlkQQ2FXV5xIx%2F%2B30X30M600KsleJyj2dkRIsMM0EHm8%2Bdz7W0iMG9KMrQNibkScTPT%2B2WzJXV%2FbIiCiI3yGSOoxu%2Fx5ApdTRUuKLY%2BCEGVJCKYJvghJ7Y4IP2Mjkqf2cSgF1x0dviY%2FNHMe6IZi6BwzrSM4jK4g%3D%3D');

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
(1, 4, 1),
(2, 5, 2),
(3, 6, 3);

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
(1, 4, 4, 5, '2023-01-20 00:00:00'),
(2, 5, 5, 4, '2023-03-01 00:00:00'),
(3, 6, 6, 3, '2023-03-25 00:00:00');

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
(1, 4, 1),
(2, 5, 2),
(3, 6, 3);

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
-- Dumping data for table `video_views`
--

INSERT INTO `video_views` (`view_id`, `video_id`, `viewer_ip`, `view_datetime`) VALUES
(1, 4, '192.168.1.1', '2023-01-17 00:00:00'),
(2, 5, '192.168.1.2', '2023-02-26 00:00:00'),
(3, 6, '192.168.1.3', '2023-03-20 00:00:00'),
(4, 4, '{\r\n  \"viewerIp\"  :\"192.168.1.1.1\"\r\n}', '2023-10-27 20:34:38'),
(5, 4, '{\r\n  \"viewerIp\"  :\"192.168.1.1.1\"\r\n}', '2023-10-27 20:35:32'),
(6, 4, '0:0:0:0:0:0:0:1', '2023-10-27 20:48:36');

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
  MODIFY `channel_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `refresh_token`
--
ALTER TABLE `refresh_token`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `user_channel_subs`
--
ALTER TABLE `user_channel_subs`
  MODIFY `sub_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `videos`
--
ALTER TABLE `videos`
  MODIFY `video_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `video_categories`
--
ALTER TABLE `video_categories`
  MODIFY `category_mapping_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `video_ratings`
--
ALTER TABLE `video_ratings`
  MODIFY `rating_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `video_tags`
--
ALTER TABLE `video_tags`
  MODIFY `tag_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `video_tags_mapping`
--
ALTER TABLE `video_tags_mapping`
  MODIFY `mapping_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
