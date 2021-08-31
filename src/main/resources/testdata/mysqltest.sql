/*
 Navicat Premium Data Transfer

 Source Server         : localhostMysql
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3333
 Source Schema         : mysqltest

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 31/08/2021 23:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for date_and_time
-- ----------------------------
DROP TABLE IF EXISTS `date_and_time`;
CREATE TABLE `date_and_time`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id comment',
  `date` date NULL DEFAULT NULL COMMENT 'date 0,0',
  `datetime` datetime(5) NULL DEFAULT '2021-11-12 13:14:15.12345' COMMENT 'datetime 5,0',
  `datetime2` datetime NULL DEFAULT NULL COMMENT 'datetime 0,0',
  `time` time(5) NULL DEFAULT NULL COMMENT 'time 5,0',
  `time2` time NULL DEFAULT NULL COMMENT 'time 0,0',
  `timestamp` timestamp(5) NULL DEFAULT NULL COMMENT 'timestamp 5,0',
  `timestamp2` timestamp NULL DEFAULT NULL COMMENT 'timestamp 0,0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of date_and_time
-- ----------------------------
INSERT INTO `date_and_time` VALUES (1, '2021-08-10', '2021-08-05 16:25:58.77778', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (2, '2021-08-10', '2021-08-05 16:25:58.77778', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (3, '2021-08-10', '2021-08-05 16:25:58.77778', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (4, '2021-08-10', '2021-08-05 16:25:58.77778', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (5, '2021-08-10', '2021-08-05 16:25:58.77778', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (6, '2021-08-10', '2021-08-16 16:53:14.00000', '2021-08-10 19:10:25', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (7, '2021-08-10', '2021-08-16 16:53:20.00000', '2021-08-16 16:53:20', '19:10:28.00000', '19:10:31', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (8, '2021-08-10', '2021-08-16 16:53:25.00000', '2021-08-16 16:53:25', '16:53:25.00000', '16:53:25', '2021-08-10 19:10:33.00000', '2021-08-10 19:10:35');
INSERT INTO `date_and_time` VALUES (9, '2021-08-16', '2021-08-16 16:53:41.00000', '2021-08-16 16:53:41', '16:53:41.00000', '16:53:41', '2021-08-16 16:53:41.00000', '2021-08-16 16:53:41');
INSERT INTO `date_and_time` VALUES (10, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (11, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (12, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (13, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (14, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (15, '2021-08-16', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42', '16:53:42.00000', '16:53:42', '2021-08-16 16:53:42.00000', '2021-08-16 16:53:42');
INSERT INTO `date_and_time` VALUES (16, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (17, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (18, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (19, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (20, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (21, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (22, '2021-08-16', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43', '16:53:43.00000', '16:53:43', '2021-08-16 16:53:43.00000', '2021-08-16 16:53:43');
INSERT INTO `date_and_time` VALUES (23, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (24, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (25, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (26, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (27, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (28, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (29, '2021-08-16', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44', '16:53:44.00000', '16:53:44', '2021-08-16 16:53:44.00000', '2021-08-16 16:53:44');
INSERT INTO `date_and_time` VALUES (30, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (31, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (32, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (33, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (34, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (35, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (36, '2021-08-16', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45', '16:53:45.00000', '16:53:45', '2021-08-16 16:53:45.00000', '2021-08-16 16:53:45');
INSERT INTO `date_and_time` VALUES (37, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (38, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (39, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (40, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (41, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (42, '2021-08-16', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46', '16:53:46.00000', '16:53:46', '2021-08-16 16:53:46.00000', '2021-08-16 16:53:46');
INSERT INTO `date_and_time` VALUES (43, '2021-08-16', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47', '16:53:47.00000', '16:53:47', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47');
INSERT INTO `date_and_time` VALUES (44, '2021-08-16', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47', '16:53:47.00000', '16:53:47', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47');
INSERT INTO `date_and_time` VALUES (45, '2021-08-16', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47', '16:53:47.00000', '16:53:47', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47');
INSERT INTO `date_and_time` VALUES (46, '2021-08-16', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47', '16:53:47.00000', '16:53:47', '2021-08-16 16:53:47.00000', '2021-08-16 16:53:47');

-- ----------------------------
-- Table structure for nums
-- ----------------------------
DROP TABLE IF EXISTS `nums`;
CREATE TABLE `nums`  (
  `id` int(6) NOT NULL DEFAULT 123 COMMENT '主键',
  `int15` int(15) NULL DEFAULT NULL COMMENT 'int-15',
  `tinyint` tinyint(20) NULL DEFAULT NULL COMMENT 'tinyint 20',
  `smallint10` smallint(10) NULL DEFAULT NULL COMMENT 'small-10',
  `mediumint` mediumint(9) NULL DEFAULT NULL,
  `bigint` bigint(255) NULL DEFAULT NULL,
  `decimal100` decimal(10, 0) NULL DEFAULT NULL COMMENT 'decimal10-0',
  `decimal163` decimal(16, 3) NULL DEFAULT NULL COMMENT 'decimal16-3',
  `numeric200` decimal(20, 0) NULL DEFAULT NULL COMMENT 'numeric20-0',
  `numeric204` decimal(20, 4) NULL DEFAULT NULL COMMENT 'numeric20-4',
  `float` float(10, 0) NULL DEFAULT NULL COMMENT 'float  10,0',
  `float2` float(10, 3) NULL DEFAULT NULL COMMENT 'float  10,3',
  `real` double(10, 0) NULL DEFAULT NULL COMMENT 'real 10,0',
  `real2` double(10, 3) NULL DEFAULT NULL COMMENT 'real 10,3',
  `double` double(10, 0) NULL DEFAULT NULL COMMENT 'double 10,0',
  `double2` double(10, 3) NULL DEFAULT NULL COMMENT 'double 10,3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'tablecomment' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of nums
-- ----------------------------
INSERT INTO `nums` VALUES (40461, 2295, 17, 78, 6431, 8990, 39, 494.555, 46, 152.6834, 599, 99.662, 299, 130.177, 56, 85.524);
INSERT INTO `nums` VALUES (303067, 544, 8, 26, 1067, 3796, 172, 690.142, 526, 520.2342, 52, 515.201, 43, 765.145, 251, 174.228);
INSERT INTO `nums` VALUES (336451, 4522, 1, 54, 2505, 448, 65, 305.636, 4, 32.7049, 52, 78.054, 96, 20.819, 830, 227.420);
INSERT INTO `nums` VALUES (378381, 2873, 20, 41, 8599, 2936, 10, 200.162, 227, 803.5141, 220, 25.532, 135, 290.377, 57, 416.894);
INSERT INTO `nums` VALUES (391875, 7308, 94, 20, 7696, 9634, 199, 1.733, 206, 368.8958, 112, 184.666, 378, 427.911, 200, 702.530);
INSERT INTO `nums` VALUES (415033, 9982, 30, 23, 2342, 889, 20, 76.222, 23, 398.5064, 135, 454.436, 224, 143.877, 2, 150.012);
INSERT INTO `nums` VALUES (472562, 1746, 99, 11, 7509, 3280, 88, 112.114, 239, 33.7017, 212, 846.921, 160, 461.176, 37, 545.993);
INSERT INTO `nums` VALUES (555132, 6288, 46, 59, 5026, 519, 734, 108.282, 207, 161.6693, 156, 469.917, 19, 200.757, 186, 241.902);
INSERT INTO `nums` VALUES (622584, 2305, 75, 60, 9547, 325, 607, 532.858, 333, 88.5218, 54, 45.357, 123, 332.288, 75, 301.680);
INSERT INTO `nums` VALUES (666577, 2427, 82, 39, 1909, 4683, 29, 5.950, 48, 255.7048, 102, 28.280, 705, 536.918, 435, 83.451);
INSERT INTO `nums` VALUES (753581, 7070, 26, 21, 1140, 5184, 159, 1.155, 692, 766.5219, 227, 604.382, 331, 35.532, 548, 648.434);
INSERT INTO `nums` VALUES (794372, 1199, 75, 49, 6982, 1708, 400, 53.380, 22, 40.2423, 29, 205.863, 180, 259.835, 634, 78.340);
INSERT INTO `nums` VALUES (854350, 7273, 22, 62, 7439, 3919, 524, 467.948, 567, 128.9584, 521, 283.138, 126, 401.834, 318, 63.701);
INSERT INTO `nums` VALUES (917782, 6038, 77, 59, 1080, 8067, 538, 627.357, 65, 82.3336, 69, 453.814, 45, 26.573, 40, 329.371);
INSERT INTO `nums` VALUES (941157, 8497, 76, 34, 1008, 4867, 82, 249.448, 721, 71.8222, 166, 514.983, 783, 65.247, 510, 109.481);
INSERT INTO `nums` VALUES (974172, 6219, 51, 84, 222, 1242, 138, 11.248, 78, 273.8960, 141, 608.281, 95, 178.711, 360, 63.041);
INSERT INTO `nums` VALUES (1016116, 2978, 8, 15, 7794, 7800, 77, 596.239, 570, 40.0187, 719, 693.871, 42, 83.476, 184, 505.392);
INSERT INTO `nums` VALUES (1033670, 4212, 19, 74, 513, 5973, 580, 795.124, 665, 364.1476, 446, 510.010, 119, 546.263, 232, 354.113);
INSERT INTO `nums` VALUES (1137395, 3695, 89, 93, 4680, 6399, 9, 476.156, 212, 318.0912, 108, 17.531, 17, 225.276, 415, 538.042);
INSERT INTO `nums` VALUES (1149187, 4930, 47, 48, 2864, 4487, 191, 347.423, 112, 31.9554, 292, 435.209, 31, 25.386, 350, 575.604);
INSERT INTO `nums` VALUES (1153927, 2203, 61, 63, 9625, 6405, 107, 203.454, 209, 382.9855, 3, 0.188, 140, 212.010, 261, 344.722);
INSERT INTO `nums` VALUES (1182127, 6673, 18, 35, 5261, 6339, 61, 496.217, 103, 721.7353, 21, 556.896, 12, 90.978, 79, 431.467);
INSERT INTO `nums` VALUES (1203099, 6220, 40, 61, 9374, 4263, 843, 11.549, 63, 246.7472, 225, 35.175, 36, 493.266, 789, 263.504);
INSERT INTO `nums` VALUES (1252038, 874, 10, 75, 3945, 9184, 32, 35.222, 210, 99.1413, 8, 17.150, 337, 101.165, 535, 141.807);
INSERT INTO `nums` VALUES (1323330, 3699, 36, 61, 2069, 7037, 318, 51.629, 349, 84.0224, 41, 824.430, 290, 102.096, 196, 11.249);
INSERT INTO `nums` VALUES (1493042, 5835, 62, 9, 5693, 8375, 214, 513.242, 95, 473.2033, 910, 92.559, 256, 64.525, 43, 383.838);
INSERT INTO `nums` VALUES (1524233, 9327, 53, 41, 1613, 4410, 183, 246.175, 175, 313.1508, 756, 178.094, 49, 188.693, 217, 0.333);
INSERT INTO `nums` VALUES (1709128, 1943, 1, 70, 4664, 4915, 163, 107.169, 837, 350.7445, 15, 170.090, 358, 591.375, 675, 123.775);
INSERT INTO `nums` VALUES (1867457, 1430, 68, 38, 5683, 3446, 266, 71.069, 705, 54.8381, 168, 119.184, 97, 223.227, 394, 541.632);
INSERT INTO `nums` VALUES (1929913, 1416, 74, 0, 8223, 1289, 133, 59.069, 556, 159.4508, 87, 93.191, 0, 229.786, 724, 166.220);
INSERT INTO `nums` VALUES (2010318, 9625, 66, 35, 8480, 4106, 72, 7.448, 143, 198.9800, 321, 273.084, 350, 389.746, 660, 311.701);
INSERT INTO `nums` VALUES (2039774, 7054, 7, 54, 4123, 5333, 45, 129.852, 723, 124.8042, 94, 190.598, 146, 143.370, 43, 158.117);
INSERT INTO `nums` VALUES (2112658, 8523, 4, 35, 5090, 6037, 710, 40.061, 61, 535.6622, 629, 220.094, 418, 106.937, 115, 74.891);
INSERT INTO `nums` VALUES (2297342, 5114, 85, 16, 9139, 1158, 218, 853.334, 565, 48.2656, 260, 201.564, 11, 6.500, 210, 141.287);
INSERT INTO `nums` VALUES (2354518, 9932, 20, 11, 1261, 9726, 17, 249.655, 195, 11.7077, 483, 575.196, 276, 73.974, 382, 103.775);
INSERT INTO `nums` VALUES (2450148, 6423, 53, 85, 6192, 6046, 210, 9.165, 438, 371.6819, 163, 18.568, 359, 51.619, 47, 0.059);
INSERT INTO `nums` VALUES (2514862, 7538, 17, 57, 8740, 1781, 14, 321.643, 66, 390.6957, 343, 126.664, 577, 33.859, 679, 333.699);
INSERT INTO `nums` VALUES (2538006, 5398, 73, 64, 6018, 9149, 294, 86.224, 62, 258.0282, 466, 354.919, 578, 492.511, 146, 21.070);
INSERT INTO `nums` VALUES (2553432, 3935, 98, 91, 8567, 9420, 582, 254.530, 286, 313.3962, 52, 94.577, 518, 122.342, 246, 572.601);
INSERT INTO `nums` VALUES (2588978, 4989, 57, 70, 2578, 1889, 788, 121.193, 906, 503.0684, 285, 515.848, 0, 200.428, 287, 347.105);
INSERT INTO `nums` VALUES (2701859, 2365, 42, 82, 4739, 1361, 120, 248.980, 91, 264.8919, 355, 272.851, 91, 641.355, 78, 119.642);
INSERT INTO `nums` VALUES (2804846, 3496, 21, 92, 2275, 2717, 35, 61.857, 412, 410.7429, 76, 297.189, 330, 112.021, 216, 7.569);
INSERT INTO `nums` VALUES (2821678, 4615, 93, 73, 147, 8151, 7, 700.744, 269, 323.0371, 22, 26.190, 393, 409.538, 58, 138.552);
INSERT INTO `nums` VALUES (2897518, 8748, 42, 94, 744, 172, 72, 228.030, 37, 384.4321, 602, 388.540, 52, 717.956, 229, 293.294);
INSERT INTO `nums` VALUES (2951909, 3969, 35, 11, 4716, 8341, 157, 47.431, 118, 221.7424, 377, 40.785, 177, 327.283, 126, 185.096);
INSERT INTO `nums` VALUES (2955630, 5660, 34, 85, 4010, 178, 501, 134.288, 16, 777.6848, 209, 387.535, 31, 109.435, 265, 725.893);
INSERT INTO `nums` VALUES (2996491, 6380, 50, 85, 520, 6553, 49, 239.280, 41, 113.9784, 627, 598.435, 27, 40.350, 775, 56.751);
INSERT INTO `nums` VALUES (3008763, 7335, 19, 84, 3799, 117, 436, 132.477, 502, 153.4625, 12, 224.296, 240, 283.629, 109, 75.390);
INSERT INTO `nums` VALUES (3079094, 8670, 5, 63, 6022, 8789, 166, 203.950, 134, 11.8978, 110, 7.507, 18, 33.765, 352, 258.787);
INSERT INTO `nums` VALUES (3100959, 9144, 27, 35, 3456, 1135, 641, 264.968, 276, 2.0774, 7, 72.206, 41, 300.586, 0, 115.657);
INSERT INTO `nums` VALUES (3172651, 2078, 79, 4, 8074, 5432, 851, 6.281, 355, 107.9806, 349, 309.654, 0, 664.017, 32, 363.982);
INSERT INTO `nums` VALUES (3204454, 7801, 61, 99, 8414, 3401, 698, 190.981, 32, 15.3875, 12, 234.810, 27, 71.413, 38, 535.073);
INSERT INTO `nums` VALUES (3219312, 4457, 12, 50, 584, 6974, 170, 199.395, 162, 114.5177, 307, 503.511, 275, 292.752, 477, 462.080);
INSERT INTO `nums` VALUES (3238303, 3608, 30, 15, 2042, 3313, 88, 185.101, 292, 134.5798, 5, 320.964, 4, 105.341, 523, 167.055);
INSERT INTO `nums` VALUES (3250628, 3136, 43, 63, 2475, 2927, 698, 70.536, 276, 54.9007, 225, 59.139, 140, 130.653, 24, 271.016);
INSERT INTO `nums` VALUES (3265241, 9344, 91, 6, 5250, 9978, 88, 29.852, 731, 3.2349, 106, 133.723, 173, 10.614, 76, 96.444);
INSERT INTO `nums` VALUES (3266460, 5653, 15, 45, 6397, 3972, 87, 129.066, 668, 189.4106, 18, 224.286, 242, 332.918, 33, 240.081);
INSERT INTO `nums` VALUES (3269483, 260, 71, 99, 2613, 7715, 19, 376.284, 21, 21.6906, 292, 345.138, 382, 569.130, 442, 145.063);
INSERT INTO `nums` VALUES (3294878, 2032, 41, 58, 1951, 5559, 25, 133.595, 151, 16.6355, 435, 189.425, 24, 736.694, 6, 193.517);
INSERT INTO `nums` VALUES (3544949, 4838, 35, 14, 59, 480, 36, 315.906, 447, 1.3493, 262, 170.072, 49, 127.977, 337, 88.906);
INSERT INTO `nums` VALUES (3554226, 8387, 42, 75, 3648, 9919, 135, 581.514, 493, 300.4866, 415, 188.944, 329, 122.722, 662, 288.302);
INSERT INTO `nums` VALUES (3554475, 7551, 92, 81, 9472, 1187, 14, 585.175, 320, 114.9998, 145, 49.765, 465, 377.793, 110, 418.553);
INSERT INTO `nums` VALUES (3674721, 7453, 1, 77, 7841, 7115, 681, 333.855, 85, 19.9636, 97, 347.760, 723, 761.613, 779, 2.123);
INSERT INTO `nums` VALUES (3692434, 2366, 77, 62, 1462, 8297, 115, 15.839, 19, 170.2165, 760, 568.816, 53, 47.634, 477, 345.756);
INSERT INTO `nums` VALUES (3695703, 1671, 59, 53, 3561, 9967, 359, 35.665, 28, 45.1030, 56, 50.031, 531, 104.984, 1, 526.029);
INSERT INTO `nums` VALUES (3705222, 6821, 19, 86, 1759, 2362, 323, 164.738, 139, 513.7124, 231, 40.575, 12, 266.247, 169, 712.173);
INSERT INTO `nums` VALUES (3726514, 9711, 19, 62, 9063, 8777, 151, 668.247, 195, 54.6666, 355, 270.937, 843, 296.316, 23, 344.204);
INSERT INTO `nums` VALUES (3737786, 9205, 62, 54, 6743, 9515, 33, 149.789, 75, 979.7928, 294, 601.382, 836, 542.304, 102, 338.262);
INSERT INTO `nums` VALUES (3843116, 4384, 32, 46, 4892, 6730, 429, 70.935, 399, 101.3305, 10, 348.369, 3, 642.551, 338, 433.809);

-- ----------------------------
-- Table structure for pagetest
-- ----------------------------
DROP TABLE IF EXISTS `pagetest`;
CREATE TABLE `pagetest`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11239 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of pagetest
-- ----------------------------
INSERT INTO `pagetest` VALUES (11148, '0.37938470149355447');
INSERT INTO `pagetest` VALUES (11149, '0.5200752890855775');
INSERT INTO `pagetest` VALUES (11150, '0.46222237168086916');
INSERT INTO `pagetest` VALUES (11151, '0.7508860218812581');
INSERT INTO `pagetest` VALUES (11152, '0.3677630250973283');
INSERT INTO `pagetest` VALUES (11153, '0.5861570905765119');
INSERT INTO `pagetest` VALUES (11154, '0.8274964083242197');
INSERT INTO `pagetest` VALUES (11155, '0.37901080062520764');
INSERT INTO `pagetest` VALUES (11156, '0.41256480888702424');
INSERT INTO `pagetest` VALUES (11157, '0.9257916732931432');
INSERT INTO `pagetest` VALUES (11158, '0.3912639705382883');
INSERT INTO `pagetest` VALUES (11159, '0.178944863545657');
INSERT INTO `pagetest` VALUES (11160, '0.7209324368470651');
INSERT INTO `pagetest` VALUES (11161, '0.06782762433199922');
INSERT INTO `pagetest` VALUES (11162, '0.17634084185244595');
INSERT INTO `pagetest` VALUES (11163, '0.678221366999877');
INSERT INTO `pagetest` VALUES (11164, '0.8620843401756924');
INSERT INTO `pagetest` VALUES (11165, '0.27575763061247544');
INSERT INTO `pagetest` VALUES (11166, '0.7925351632689454');
INSERT INTO `pagetest` VALUES (11167, '0.13540295524094528');
INSERT INTO `pagetest` VALUES (11168, '0.29940931713153546');
INSERT INTO `pagetest` VALUES (11169, '0.09083775066848634');
INSERT INTO `pagetest` VALUES (11170, '0.5559608326814703');
INSERT INTO `pagetest` VALUES (11171, '0.5072909421355379');
INSERT INTO `pagetest` VALUES (11172, '0.8685722433669234');
INSERT INTO `pagetest` VALUES (11173, '0.8209884211616483');
INSERT INTO `pagetest` VALUES (11174, '0.4992254064411161');
INSERT INTO `pagetest` VALUES (11175, '0.03316179945428092');
INSERT INTO `pagetest` VALUES (11176, '0.6681328086817011');
INSERT INTO `pagetest` VALUES (11177, '0.24117867577930788');
INSERT INTO `pagetest` VALUES (11178, '0.20149498358508106');
INSERT INTO `pagetest` VALUES (11179, '0.2839389213211266');
INSERT INTO `pagetest` VALUES (11180, '0.8152096865840346');
INSERT INTO `pagetest` VALUES (11181, '0.22423169969043852');
INSERT INTO `pagetest` VALUES (11182, '0.6755294694337337');
INSERT INTO `pagetest` VALUES (11183, '0.7049522788309979');
INSERT INTO `pagetest` VALUES (11184, '0.4981730165874334');
INSERT INTO `pagetest` VALUES (11185, '0.3760082771778184');
INSERT INTO `pagetest` VALUES (11186, '0.3855223668604366');
INSERT INTO `pagetest` VALUES (11187, '0.7995870335023729');
INSERT INTO `pagetest` VALUES (11188, '0.8413680976641998');
INSERT INTO `pagetest` VALUES (11189, '0.8080794185475254');
INSERT INTO `pagetest` VALUES (11190, '0.5162928304786727');
INSERT INTO `pagetest` VALUES (11191, '0.15722592748443218');
INSERT INTO `pagetest` VALUES (11192, '0.23725117671978768');
INSERT INTO `pagetest` VALUES (11193, '0.714578131879287');
INSERT INTO `pagetest` VALUES (11194, '0.8611371599707167');
INSERT INTO `pagetest` VALUES (11195, '0.1619514349493677');
INSERT INTO `pagetest` VALUES (11196, '0.22634572556833338');
INSERT INTO `pagetest` VALUES (11197, '0.6458743537272088');
INSERT INTO `pagetest` VALUES (11198, '0.5503346226646887');
INSERT INTO `pagetest` VALUES (11199, '0.8140500828754623');
INSERT INTO `pagetest` VALUES (11200, '0.4192465771168904');
INSERT INTO `pagetest` VALUES (11201, '0.6540826676917101');
INSERT INTO `pagetest` VALUES (11202, '0.01267363784152422');
INSERT INTO `pagetest` VALUES (11203, '0.1011202168661358');
INSERT INTO `pagetest` VALUES (11204, '0.46758020153975133');
INSERT INTO `pagetest` VALUES (11205, '0.03454038783399424');
INSERT INTO `pagetest` VALUES (11206, '0.7699613652843622');
INSERT INTO `pagetest` VALUES (11207, '0.7461856936534733');
INSERT INTO `pagetest` VALUES (11208, '0.4210444031479251');
INSERT INTO `pagetest` VALUES (11209, '0.8666649655128503');
INSERT INTO `pagetest` VALUES (11210, '0.07019164885412124');
INSERT INTO `pagetest` VALUES (11211, '0.7509633784657003');
INSERT INTO `pagetest` VALUES (11212, '0.5442419764997829');
INSERT INTO `pagetest` VALUES (11213, '0.4683197778354583');
INSERT INTO `pagetest` VALUES (11214, '0.708872990411588');
INSERT INTO `pagetest` VALUES (11215, '0.13940564928520996');
INSERT INTO `pagetest` VALUES (11216, '0.5704093059249309');
INSERT INTO `pagetest` VALUES (11217, '0.43382961250266955');
INSERT INTO `pagetest` VALUES (11218, '0.4579201754722001');
INSERT INTO `pagetest` VALUES (11219, '0.9881120705866367');
INSERT INTO `pagetest` VALUES (11220, '0.5667998572502284');
INSERT INTO `pagetest` VALUES (11221, '0.8696631052248768');
INSERT INTO `pagetest` VALUES (11222, '0.6479159851073436');
INSERT INTO `pagetest` VALUES (11223, '0.6305906405957329');
INSERT INTO `pagetest` VALUES (11224, '0.20920527839027836');
INSERT INTO `pagetest` VALUES (11225, '0.15425450089783826');
INSERT INTO `pagetest` VALUES (11226, '0.14365670005200123');
INSERT INTO `pagetest` VALUES (11227, '0.25552002830013637');
INSERT INTO `pagetest` VALUES (11228, '0.8466300720783231');
INSERT INTO `pagetest` VALUES (11229, '0.4665903062248512');
INSERT INTO `pagetest` VALUES (11230, '0.793061345622932');
INSERT INTO `pagetest` VALUES (11231, '0.5655358401737509');
INSERT INTO `pagetest` VALUES (11232, '0.44849519473360405');
INSERT INTO `pagetest` VALUES (11233, '0.5458684838804123');
INSERT INTO `pagetest` VALUES (11234, '0.3838568659348943');
INSERT INTO `pagetest` VALUES (11235, '0.2816789087668796');
INSERT INTO `pagetest` VALUES (11236, '0.25682397676336016');
INSERT INTO `pagetest` VALUES (11237, '0.439083188249807');
INSERT INTO `pagetest` VALUES (11238, '0.4249440416925997');

-- ----------------------------
-- Table structure for string
-- ----------------------------
DROP TABLE IF EXISTS `string`;
CREATE TABLE `string`  (
  `char` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'char 255',
  `varhcar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123123' COMMENT 'varchar 255',
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'text 255',
  `tinytext` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'tiny text',
  `mediumntext` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'medium text',
  `longtext` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'long text',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_test`(`char`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99469 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of string
-- ----------------------------
INSERT INTO `string` VALUES ('Marissa Harper', '6142 Green Knolls Suite 883\nSimonborough, CO 91691', 'Trade building answer hand girl why significant born. At move yet. Ok race painting safe run office new high.', '#2ccc9e', 'Toxicologist', '000.161.9166x7247', 4031);
INSERT INTO `string` VALUES ('Mary Williams', '81714 Manuel Mission\nMendezmouth, OR 47606', 'Until conference again instead read. Happen line save natural sell policy.\nOpen race marriage discussion traditional special. It technology adult company have. Less laugh smile both accept single.', '#f5b7ff', 'Public affairs consultant', '(658)867-1216x816', 4654);
INSERT INTO `string` VALUES ('Jose Davis', '544 Bonilla Dale Suite 043\nEast Williammouth, VT 26123', 'Seat no condition once cause. Beat care return include professor eat up.\nCourt Democrat career central.', '#5df47b', 'Records manager', '5588989877', 4772);
INSERT INTO `string` VALUES ('Alyssa Ruiz', 'PSC 9151, Box 8353\nAPO AP 37062', 'Image discussion class hope day personal. Clearly experience response activity food police race.\nInterview plan something example.', '#589dd8', 'Scientist, research (physical sciences)', '(904)621-0999x79776', 4878);
INSERT INTO `string` VALUES ('Amber Torres', '79852 Abigail Corner Apt. 030\nSouth Joshuabury, MO 22824', 'Defense if star official class so police. Operation positive science population Democrat check bar. Memory organization morning sound.', '#ed4e9b', 'Insurance account manager', '616.383.6292x7485', 5013);
INSERT INTO `string` VALUES ('Lisa Leon', '14033 Barber Shore\nJosefurt, OH 66014', 'Arrive increase decision day image three you. At poor dinner card space current. Center defense time drug everyone capital safe before. Grow expert draw.', '#efa07f', 'Arts administrator', '9520746180', 5508);
INSERT INTO `string` VALUES ('Darius Frye', '4209 Chang Lakes\nDunnview, SC 60234', 'Room personal to assume. How off marriage operation listen accept place.\nUse one technology affect late project. Suddenly quickly foot ten. Strong religious human site. Fear total mean only.', '#ef926e', 'Quarry manager', '001-541-386-6467x31782', 7561);
INSERT INTO `string` VALUES ('Dr. Keith Boyer', '7306 Sheri Cliff Apt. 741\r\n		Leefort, ND 63105', 'Television play work. Conference difficult do level list step. Apply similar create focus believe election member. Seem forward perform interview grow consumer point.', '#b1f49c', 'Ecologist', '(542)248-0076', 8486);
INSERT INTO `string` VALUES ('Natasha Levy', 'PSC 4225, Box 8896\nAPO AA 84023', 'Idea environment sit old very. Low police Democrat either similar.\nAvoid itself financial second forget measure. Store choice employee model. Hotel just box mouth energy fear condition.', '#61e8bb', 'Therapist, music', '001-205-381-5052x959', 10035);
INSERT INTO `string` VALUES ('Mitchell Rhodes', '341 Joseph Ville\nTommymouth, DE 43743', 'Couple best star nature. Summer six allow weight.\nStructure television become know hot economic start. Difference red story room. Response high personal include town.', '#d5db27', 'Exhibition designer', '001-185-081-8158x949', 10375);
INSERT INTO `string` VALUES ('Stephanie Sutton', '060 Moran Ferry Suite 103\nNew Lisa, AL 74736', 'Only hair beyond anything need everything worry. Agreement near successful hour run them society.', '#abcaf2', 'Tree surgeon', '759-143-0471x449', 13065);
INSERT INTO `string` VALUES ('Annette Bartlett', '92578 Jones Hollow Suite 642\r\n		North Shaneside, NV 73411', 'Method two keep reduce concern cause.\r\n		Group yourself main pick sometimes. Sport detail stock energy. Bring white board their same.', '#ba57db', 'Public relations account executive', '572.829.5422', 13222);
INSERT INTO `string` VALUES ('Thomas Jenkins', '226 Michele Road Apt. 889\nNew Whitney, OH 41683', 'Box strategy think book. Couple require sell scientist what.\nBaby light part how left including wish concern. Two shoulder read decision per industry. Wish central past read senior meeting.', '#e5f287', 'Educational psychologist', '076-095-1362x295', 13290);
INSERT INTO `string` VALUES ('Timothy Tate', '80925 Novak Drives Apt. 426\nNew Kennethfurt, WA 94856', 'Dog against head training. Clear save their director. Month gas study entire record.\nGarden stage some certainly us at. Land American finally large. Necessary rise rise different.', '#9df29e', 'Product/process development scientist', '(397)242-6283x78749', 13898);
INSERT INTO `string` VALUES ('Renee Wiggins', '478 Austin Port Apt. 634\nSouth Jasonton, NY 68155', 'Turn statement wish what.\nFact site exist single design people car ask. Study past ever back bar not he. It continue world.', '#f19bf7', 'Software engineer', '(460)435-1438', 13950);
INSERT INTO `string` VALUES ('Joshua Velasquez', '27724 Robertson Fall Apt. 835\nMilesmouth, VT 56557', 'Person while recent him far yet. Serious their short news would free official from.\nTruth central tell investment beat. Issue practice since firm skill specific.', '#9fc1ea', 'Farm manager', '(779)408-0678', 14638);
INSERT INTO `string` VALUES ('Alexander Graham', '132 Zachary Path Apt. 421\nSouth Anthonyton, RI 10398', 'Raise process debate half military may. Sea sing four main sometimes. Rich knowledge high history new.\nMedia knowledge act effort. Chair exactly tree air.', '#88e82e', 'Web designer', '393-266-5291x74095', 14798);
INSERT INTO `string` VALUES ('John Chen', '25901 Thomas Prairie\nNew Elizabeth, WV 11019', 'Country trial never interesting world camera. Challenge rise good according direction begin.\nInterest large anything same box. Cell evening class measure event car.', '#18f7d5', 'Planning and development surveyor', '527.230.6236', 17292);
INSERT INTO `string` VALUES ('Caleb Stewart', '8817 Eric Key\nEast Darrellfort, LA 29449', 'By politics something us PM attack. Reality star great easy week like.\nSeek religious might with use. Everyone art police another near act result. Town cause page word.', '#fcb076', 'Pensions consultant', '250.514.3923', 18486);
INSERT INTO `string` VALUES ('Tina Strong', '0286 Rachel Camp Suite 169\nNew Codyview, CT 35675', 'Effect believe near marriage. Bar buy note company rise.\nBehind third hot final listen stage heart. Happen candidate paper movement research day management.', '#c0b1ed', 'Chief Executive Officer', '894.254.3912x2381', 21222);
INSERT INTO `string` VALUES ('Veronica Oliver', '99093 Taylor Throughway\nNew Douglas, AK 91360', 'Five two many ask method. Few month game into.\nEye else than safe. Performance north meeting number discover woman. Sing miss development free.', '#ccf9ff', 'Retail merchandiser', '9434637748', 21407);
INSERT INTO `string` VALUES ('Justin Thompson', '0124 Brian Extension\nMillerfurt, OK 86759', 'Theory leg challenge focus story mission always. Bad less husband save that world much.\nFire movie situation front top. Key heavy note born color specific message.', '#b95dd8', 'Senior tax professional/tax inspector', '817-327-5958x2392', 21952);
INSERT INTO `string` VALUES ('Mary Barker', 'USS Higgins\nFPO AE 25668', 'Do find nature all low ok about. Top may practice picture. Long produce any teacher himself growth thus window.', '#ffd6c9', 'Ecologist', '011.380.2233x330', 24820);
INSERT INTO `string` VALUES ('Gregory Hughes', '15401 Eduardo Haven\nEast Makayla, WY 11475', 'Glass today hotel production return safe majority. Wind agency big main. Federal choice city likely down like.', '#c13730', 'Technical brewer', '8175406640', 24865);
INSERT INTO `string` VALUES ('William Patterson', 'PSC 1664, Box 9841\nAPO AA 01722', 'The trouble behavior. Art serve compare pretty nice establish program. Energy another leave appear.\nTable be image sort. Along if civil drive.', '#73ef5d', 'Chartered public finance accountant', '001-210-955-2972x26603', 24964);
INSERT INTO `string` VALUES ('Justin Burton', '6091 Kimberly Street Apt. 354\nJulieshire, RI 86082', 'Fine factor save so serve new. Marriage figure data since decade. Step see middle turn explain nation same.', '#01bec1', 'Art gallery manager', '(207)452-5507x9160', 25037);
INSERT INTO `string` VALUES ('Amanda Bass', '6656 Marshall Garden\nLake Victorburgh, KS 99332', 'Forget continue owner or. Letter choose hit wall treat continue.\nSome better future dog soldier civil either. Likely keep land song begin down. Today window yes quite prove star draw.', '#e8cc43', 'Corporate investment banker', '542-728-1638x82617', 25349);
INSERT INTO `string` VALUES ('Terry Rodriguez DDS', '69724 Diana Burg\r\n		West Hollyport, MA 56729', 'Yes sure themselves say sing nearly start. Fill environment require top address series charge. Ago democratic really mention truth machine also what.', '#e228ca', 'Water quality scientist', '530.299.6272x72795', 25549);
INSERT INTO `string` VALUES ('Susan Cobb', '35939 Melissa Run Apt. 522\nEast Robert, NV 96190', 'Fact by wall forget we one best fire. Chance likely himself relationship morning candidate.\nStatement and mother put. Them better community raise bank yourself fact.', '#c8f99d', 'Passenger transport manager', '(340)129-9747', 25577);
INSERT INTO `string` VALUES ('Judith Alvarez', '80947 Roberta Dale Suite 012\nLesterburgh, NJ 51621', 'Tax loss list enter. Among something might its man.\nSite majority red. Affect on her join candidate ten. Student yard none maintain read sing piece half.', '#8bcc4f', 'Tax inspector', '(453)763-6850x92167', 25922);
INSERT INTO `string` VALUES ('David Olsen', '383 Carlos Drive\nMercadoborough, NM 60720', 'Travel despite close foreign market picture tree test. Forget indicate recent fear seem inside deep.\nAcross local require full son none heart.\nTelevision bar economic open true gun.', '#f4778a', 'Production engineer', '001-495-062-2351', 25976);
INSERT INTO `string` VALUES ('David Hoover', '842 Rogers Ranch Suite 010\nTinahaven, MO 99676', 'Radio store she project. Computer product station successful agent research. Between those company man.\nElection at control until laugh trip. Attention team season would lose.', '#ead410', 'Medical illustrator', '001-399-426-7625x91851', 30051);
INSERT INTO `string` VALUES ('Rebecca Lopez', '2011 Young Springs Apt. 188\nLake Danielle, IL 41646', 'Within establish soldier get sign eat perhaps. Now tax sometimes dark sound.', '#ef5686', 'Call centre manager', '(498)926-6409', 30259);
INSERT INTO `string` VALUES ('Brian Kelley', '677 Robert Fork Suite 488\nWest John, AK 84837', 'Claim where seek laugh car hold.\nHere give nation this. Necessary large relate whether everyone heavy break.', '#a1b7e0', 'Advice worker', '(256)030-2073', 33618);
INSERT INTO `string` VALUES ('Heather Potts', '28635 Lauren Vista Suite 215\nLopezborough, NY 92889', 'Range now mouth sit others people type although.\nFind report voice popular their. Cultural treat some garden Congress walk she. Mind single small will think catch our night.', '#f99fa1', 'Chartered accountant', '+1-510-960-0666x335', 34166);
INSERT INTO `string` VALUES ('Danielle Rodriguez', '05544 Murphy Harbors\nNew Cassandraburgh, IL 34781', 'Science for south class process both. Baby father industry product. Politics nearly record responsibility.\nAnimal baby shake cost. Standard her occur eight character. Accept tough maybe.', '#5ef279', 'Dance movement psychotherapist', '158.644.6973', 34261);
INSERT INTO `string` VALUES ('Holly Ramsey', '25216 Kaitlyn Estate Apt. 329\nWest Joshua, OH 06441', 'Person ahead attack finally question property continue. Election through into movie letter. Factor write why foot agree remain still.', '#f2722e', 'Software engineer', '570.277.5971x5545', 34309);
INSERT INTO `string` VALUES ('Jessica Campbell', '5365 White Stream Suite 052\r\n		Jasonport, NJ 63365', 'History alone leave increase management certain question attention. Follow cover century example ball. Challenge serious security deep kid.', '#bba9fc', 'Psychologist, occupational', '7171365215', 35097);
INSERT INTO `string` VALUES ('Dustin Hayes', '0821 Eugene Point\nHendrixbury, WY 88934', 'Tell book late shake cut security plant. Spend station investment rest. Understand consider place write.\nColor real growth rate.\nKnow last different area play lot.', '#c610ef', 'Product designer', '757.713.5757x7662', 35321);
INSERT INTO `string` VALUES ('Christopher Vaughan', 'Unit 7852 Box 3846\r\n		DPO AA 69290', 'Pattern card arrive middle drug. Site line learn increase politics popular while season.', '#aea7f9', 'Optometrist', '328.417.9869x924', 37973);
INSERT INTO `string` VALUES ('Eric Barnes', '88656 Janet Mews Apt. 880\nNorth Dawnton, NH 48569', 'Commercial purpose cold receive generation hundred include general. Entire from light southern hot while almost.\nBag parent with politics either. Fill market something line add.', '#f9714f', 'Patent examiner', '742-750-7310x435', 38518);
INSERT INTO `string` VALUES ('Jennifer Ross', '0929 Jones Points Suite 516\nWest Willieton, OR 31812', 'Senior specific remain debate cup lose nor. Between before million last. Fund movement yourself sell technology seek hour. Real short state baby into media story data.', '#d8715f', 'Retail buyer', '+1-002-265-5364', 38934);
INSERT INTO `string` VALUES ('Keith Mercer', '9721 Ariana Trace\nLake Shannonfort, ND 69921', 'Return arm time lot. Second manager team however product. Remember wear he moment form beautiful moment put.\nCall culture on our opportunity. Future care nothing report himself cost read.', '#93ed90', 'Holiday representative', '727.519.2739', 40373);
INSERT INTO `string` VALUES ('Sara Chambers', '42799 Patterson Islands Suite 481\nNew Megan, IL 82595', 'Idea stage maybe scientist best. Member seat life as study. Themselves attorney allow agreement third well bring. Risk material most.', '#7ded9d', 'Hydrologist', '017-630-8007', 40667);
INSERT INTO `string` VALUES ('Theresa Stevens', '58810 Peterson Street\nJohnsonmouth, MN 58262', 'Week cover standard run. Manage water school occur work maybe. Argue soon government after course.', '#87ba23', 'Archaeologist', '855.077.5879x37696', 42275);
INSERT INTO `string` VALUES ('Jennifer Gonzalez', '7428 Carolyn Pine\nEast Toddtown, NY 78403', 'Source rate leader. City us movement lead where meeting nearly. Song standard floor walk.\nStreet foot walk realize including bad. Before call indeed whose rest resource.', '#691e8c', 'Chemical engineer', '001-177-154-9438x68187', 42667);
INSERT INTO `string` VALUES ('Steven Parker', '652 Villanueva Springs Apt. 926\nNew Michaelmouth, CO 39557', 'Street strong pretty we body pay. If none team federal.\nBorn note system Congress director stage sure. Occur according election other.', '#f78a8f', 'Astronomer', '(043)693-0198x079', 45611);
INSERT INTO `string` VALUES ('Garrett Roberts', '854 Charles Shoals Apt. 929\r\n		North James, CA 90899', 'Reduce media minute help item. Simple on audience describe past simple.\r\n		Close defense sister organization. Team some key next a. Culture hit road national same.', '#c9f287', 'Radiation protection practitioner', '(160)779-2607', 45958);
INSERT INTO `string` VALUES ('Phillip Ferguson', '501 Randall Center\nMelaniefurt, DC 07119', 'Reach sometimes majority successful under across pass. Hand series challenge long general.\nAll including fund prepare. Attack full carry finish become effect.', '#86f4c5', 'Farm manager', '832-237-3593x0721', 46604);
INSERT INTO `string` VALUES ('Larry Mcclain', '060 Cervantes Cove\nNorth Christopher, MT 14358', 'Agree either learn forget. Set moment writer new remain speak.\nAlone raise end town. Both hope why might seem pass. Fund various the too radio return section.\nSound interest nearly international.', '#e2915a', 'IT technical support officer', '6764140999', 48348);
INSERT INTO `string` VALUES ('Patrick Andrews', '260 Leslie Glens\nJacobton, SD 25823', 'New rock old local. Skin development pressure break. Could condition standard sister so make church.', '#9df2ae', 'Dealer', '+1-404-629-8982x5746', 49796);
INSERT INTO `string` VALUES ('John Johnson', '89409 Alyssa Springs\nWest Adrian, LA 24275', 'Computer training affect. Capital reduce soon.\nLetter accept fish production member catch. Base exist media point green note say. Despite support indeed understand author population.', '#1cc99e', 'Land/geomatics surveyor', '001-178-641-4751x6479', 53873);
INSERT INTO `string` VALUES ('Devin Bradley', '588 Mary Harbors Suite 215\nSpenceton, IN 29447', 'Game simple account set glass sort. Down agency other same anyone design short. Return expert girl amount citizen nature huge. Whose certain picture carry land machine fear.', '#e04355', 'Psychologist, counselling', '822.653.0802', 56462);
INSERT INTO `string` VALUES ('Amber Robertson', 'PSC 1441, Box 4596\nAPO AE 62616', 'Focus door personal leave. That avoid position meet television clearly stage. Wish popular strong protect board view.', '#07107a', 'Hydrogeologist', '086.274.0243', 58655);
INSERT INTO `string` VALUES ('Daniel Hanson', '656 Black Locks Suite 317\nWardland, CT 45829', 'Find book factor door as. Those audience window list name.\nTogether least easy main despite. Financial look top green as art interview act. Television young treat reason.', '#ff8ccf', 'Chiropodist', '970-532-4303', 59475);
INSERT INTO `string` VALUES ('Beverly Harris', '36742 Julie Drive Apt. 753\nCoxburgh, AZ 00604', 'Past result seem chance determine. Occur cut thus product issue. Design professor position stand also push.\nSerious argue ever stop cut deep sometimes. Door front short moment operation.', '#9df2e5', 'Lobbyist', '821-961-9051x26720', 59744);
INSERT INTO `string` VALUES ('Michael Mathews', '03228 Moreno Drive\nPowellville, NY 62613', 'Box add allow. Pay away increase successful interest question. International within picture number picture accept.\nPast simple task. Group everybody stand whether operation employee.', '#2851c1', 'Seismic interpreter', '001-851-488-4547x13267', 59858);
INSERT INTO `string` VALUES ('Mary Diaz', '48571 Miguel Lake Suite 643\nWallmouth, CT 34267', 'Wonder individual third Mr possible.\nNation relate too election such look.\nFinally fall trial include. Significant drive travel near ever. Moment yard race.\nMr close probably board.', '#9becf2', 'Pension scheme manager', '6261523241', 60337);
INSERT INTO `string` VALUES ('Dawn Bennett', '12384 Paige Junctions\nDavidsonshire, MA 81423', 'Reason part time and rule. Receive try industry large treatment idea later. Fight hear almost sit kid prove find.', '#a28fd6', 'Physicist, medical', '+1-191-543-0804x48430', 61298);
INSERT INTO `string` VALUES ('Mark Robinson', '7659 William Squares Suite 761\nElliottfurt, DE 23072', 'Soldier education them bring reveal hear if around. Include record music day. Despite through customer girl group.', '#e3ed2f', 'Broadcast journalist', '+1-639-635-5880x580', 61328);
INSERT INTO `string` VALUES ('Maria Baker', '133 Jacob Shore Suite 071\nJessicaside, UT 26678', 'Score experience health camera campaign blue. Available local financial ground prevent admit send interesting. Quite significant offer.', '#dcf98b', 'Mudlogger', '(549)217-4051', 62736);
INSERT INTO `string` VALUES ('Katherine Boone', '80144 James Centers\nNorth Brittney, CO 64934', 'There act his. Throughout light reach believe. Difference fight admit which hear attack.\nRace article blood stay. Religious almost center share our quite well agree.', '#e65eed', 'Passenger transport manager', '(443)536-7132x03350', 64230);
INSERT INTO `string` VALUES ('Nathan Lane', '506 Dana Burgs\nEast Danaborough, VT 44728', 'Back include various wish ground great improve. End right tend fine check. Still place sit try own audience.\nTo third view final garden. Analysis individual push total catch.', '#76dfe2', 'Town planner', '+1-033-758-7594x853', 64635);
INSERT INTO `string` VALUES ('William Fletcher', 'Unit 1112 Box 1158\nDPO AE 66446', 'Consumer choose theory along.\nSpeak painting care however support window. Affect catch although direction participant body face. Southern writer less example.', '#068202', 'Designer, interior/spatial', '(877)865-3934x196', 64957);
INSERT INTO `string` VALUES ('David Roy', '33733 Eric Court\r\n		South Angelamouth, RI 68777', 'After answer responsibility father resource social shoulder certainly. Design their get Congress election stage.\r\n		Trouble foreign act style out cold carry prevent. Model system help eye foot.', '#d95ee0', 'Journalist, broadcasting', '598-753-5747x909', 65473);
INSERT INTO `string` VALUES ('Joseph Lane', '0054 Weaver Circles\nTeresashire, NJ 82999', 'Race writer difference able stop. Answer college writer. Finish reduce field adult religious nor imagine new.\nEconomic class similar dog window read. Reach throughout his test.', '#a9f9b7', 'Trading standards officer', '001-160-473-8136x293', 65541);
INSERT INTO `string` VALUES ('Lauren Andrews', '66471 Thomas Crest\nDanielstad, IN 60210', 'Per nothing kind action end cover surface.\nSociety it enough these industry pass ability. Thing check accept wall risk sea.', '#72ffc6', 'Artist', '937-261-3994', 66382);
INSERT INTO `string` VALUES ('Earl Garcia', '7813 Stacey Divide Suite 260\nMelissahaven, AR 16655', 'Animal detail land challenge who husband. Very most animal million. Move political dinner. Usually pull better anyone its mind above.\nPhone call according worry. Really often money very try outside.', '#b4e88d', 'Research scientist (life sciences)', '392-267-2735x42239', 69466);
INSERT INTO `string` VALUES ('Kathleen Brown', '11188 Ali Spur Suite 701\nLake Mollyfort, KY 10235', 'Matter floor early kitchen. Success certainly artist.\nStation system daughter north serve some worry. Degree information there successful resource total fill wall.', '#bf245d', 'Teacher, secondary school', '(891)890-5199x489', 69893);
INSERT INTO `string` VALUES ('Larry Cannon', '13242 Lopez Centers\nPort Lisaville, NJ 08043', 'Film support control during whether concern. Dream model hit ground. Same huge modern keep money trade.', '#f7ee47', 'Herbalist', '001-274-376-1479x0726', 70548);
INSERT INTO `string` VALUES ('David Morris', '91499 Lynn Road Suite 065\nSouth Marie, SD 34530', 'But sort officer design security none generation. Church course he sort well.\nSomeone thing dinner free left. Space hotel memory major sure pull environmental.', '#e4e87a', 'Land', '(174)432-9228x18845', 70765);
INSERT INTO `string` VALUES ('Robert Scott MD', '8045 Robinson Streets Apt. 884\nLake Stevenburgh, NJ 50545', 'Quickly whether skin street religious side. Town within make high carry blue everybody. Country action view tonight. As teacher as attorney story account.', '#ffe1c9', 'Estate agent', '+1-379-317-8484x015', 72194);
INSERT INTO `string` VALUES ('Susan Clarke', '23200 Amy Courts\nNew Katie, NV 91141', 'Big detail sell score view. Surface how factor general idea development read lot. Close result stand tough.', '#30ddbb', 'Air broker', '012.967.6894', 72304);
INSERT INTO `string` VALUES ('Melissa Bautista', '4256 Michael Freeway Apt. 189\nSamanthabury, ND 41728', 'Account city president herself development. Yet Republican door claim majority increase type hard.\nFamily contain record fill.', '#39ce4d', 'Psychologist, forensic', '154.391.5838x46361', 72728);
INSERT INTO `string` VALUES ('Jacob Johnson', '49111 Lindsey Harbors Apt. 877\nNew Veronica, MS 81246', 'Yet budget TV paper nor though. Newspaper mind alone woman charge.\nTough event the. Read physical participant kitchen situation certain. Board majority who sister its trip question minute.', '#b78210', 'Conference centre manager', '+1-961-508-9693x5607', 73220);
INSERT INTO `string` VALUES ('Allison Fields', '5544 Farrell Flat Suite 544\nJasminemouth, MI 47243', 'Serve scene professional parent focus. Behavior reach top maybe. Lead travel evidence or do down.\nSpeak community call report various arrive ten. Artist major necessary theory.', '#ef995b', 'Warehouse manager', '6430290791', 74700);
INSERT INTO `string` VALUES ('Jenna Sullivan', '55742 Christopher Ville\nPort Antonio, NY 73725', 'Specific attention property wonder yet section. Enjoy just compare through. Show news simply focus response.', '#8d4eb2', 'Pharmacist, community', '+1-963-061-1554x2622', 74831);
INSERT INTO `string` VALUES ('Spencer Holden', '9084 Nelson Forge\nGonzalezton, CA 51096', 'Full final or beyond middle life. Say media consumer degree.\nMember machine open professional officer heavy. Force week short. Create thousand table. Chance future the beyond whole.', '#ffcce3', 'Secretary, company', '253.843.6975', 75094);
INSERT INTO `string` VALUES ('Scott Christensen', '9388 Debra Stravenue Suite 952\nPort Misty, OH 70082', 'Science improve friend over live. Them every mention actually. Raise lead memory well list over. He big source building parent part of.\nAttack science young. Subject information American not.', '#e8967f', 'Operations geologist', '001-452-377-8591x088', 75875);
INSERT INTO `string` VALUES ('Kelli Russo', '13398 Dawn Brook Suite 554\nNorth Shelley, OR 96123', 'Site I detail region deal. Heavy you he section heavy game.\nThan north around. The its shake far situation scene. Style follow dog.\nInto country such need already. Say message box.', '#86ed7d', 'Health visitor', '(444)430-0625', 75978);
INSERT INTO `string` VALUES ('Patrick Rogers', '359 Madison Trail\nEast Robertfurt, KY 97271', 'Generation check edge hot. Phone perhaps professional always can speak college. Decade use add experience bed discussion.', '#3edb39', 'Engineer, water', '060-847-0035x6963', 76193);
INSERT INTO `string` VALUES ('Jill Wilson', '98358 Williams Mount\nEthanton, ND 01365', 'Ten Congress less population around. We cup much through answer move. Office interview community religious interest.', '#47f7a2', 'Data scientist', '0785202589', 77065);
INSERT INTO `string` VALUES ('Juan Rodriguez', '6077 Samuel Cape\nNorth Morganfort, TX 12088', 'Fall general article little Republican hospital. At blue black respond someone. Later sort try hand base protect.', '#3e4ad1', 'Biomedical scientist', '(006)313-7060x478', 77077);
INSERT INTO `string` VALUES ('Shawn Hill', '8150 Valerie Oval Suite 821\r\n		Garciaborough, DC 75990', 'Finally argue quite early listen significant. Reason station partner finally. Price put ahead have big nature. Medical hold among finish suggest culture where.', '#a9fce7', 'Site engineer', '+1-542-947-2014x6304', 77507);
INSERT INTO `string` VALUES ('Alexander Landry', '74559 Elliott Fork Suite 707\nPort Katie, KS 02279', 'Challenge sure research ahead. Talk east tough he stand form book. Environment use government want tell.', '#a1fcaa', 'Television/film/video producer', '+1-532-482-2453', 78637);
INSERT INTO `string` VALUES ('Roberto Santos', '036 Steven Manor Suite 683\nNorth Joshua, NH 16830', 'Question arrive difference stock. Base admit remain adult.\nStop property since time. Billion audience magazine knowledge.', '#adbc29', 'Police officer', '379.391.3039x4421', 80187);
INSERT INTO `string` VALUES ('Craig Flores', 'USNV Mitchell\nFPO AA 75039', 'Simple because together call town skill student little. Area million show son fund raise large.\nThis positive player reason. Sign part owner.', '#8de888', 'Dietitian', '(543)727-4870x06036', 81447);
INSERT INTO `string` VALUES ('Cory Walker', '496 Andrea Mall Apt. 856\nGonzalezshire, AR 69453', 'Season view expert something. Per serious home.\nEven space home top sea no. Audience actually place within suffer situation court. Something girl story while.', '#5d09bc', 'Tax adviser', '708-532-4813x950', 81550);
INSERT INTO `string` VALUES ('Jesse Lopez', '7127 Benjamin Key Suite 059\nClarkmouth, WV 55321', 'Laugh score that lose painting difference. Theory write hold game animal design ask. Sea hotel form Congress record.', '#ade21b', 'IT technical support officer', '(093)918-6953x7983', 82486);
INSERT INTO `string` VALUES ('Melissa Duncan', 'Unit 1949 Box 6603\nDPO AA 10155', 'Operation hotel management meet the hope.\nWin nothing back bad. Better chance condition interest officer success.', '#b6f28c', 'Holiday representative', '810.157.8222x336', 82520);
INSERT INTO `string` VALUES ('Heidi Harmon', '462 Christopher Parkways Apt. 285\r\n		New Lauraborough, GA 50783', 'Actually wear western include rather institution data rest. Onto ready coach remain. Happy stay action character.', '#bdfc83', 'Chiropractor', '301-920-9294x62628', 83092);
INSERT INTO `string` VALUES ('Kim Friedman', '8213 Christy Causeway\r\n		Millerfort, RI 34395', 'Sing tonight edge enter actually kitchen good. Community drug wear do radio discuss suggest conference.\r\n		Itself less woman help whether those. Wide TV get ability final.', '#c7d3fc', 'English as a foreign language teacher', '787-973-6226', 84298);
INSERT INTO `string` VALUES ('Ashley Huynh', '18522 Megan Estate\nWest Josephland, MO 28823', 'Only again say alone. Place choice student week military clearly factor. Paper line fact staff everyone report.', '#55f93b', 'Paediatric nurse', '001-890-381-6814x0675', 86436);
INSERT INTO `string` VALUES ('Timothy Carter', '5849 Tracy Pass\nNavarroberg, GA 69142', 'Wife now former how bed light travel surface.\nHome between however right where. Let score wait safe responsibility who doctor all.', '#8e040f', 'Freight forwarder', '(773)619-6771', 86576);
INSERT INTO `string` VALUES ('Heather Montoya', '995 Heather Forges\nPort Jesus, AL 67792', 'Cause father whole end. Station himself moment just international their up network.\nSocial knowledge source me. Paper capital strong base concern not. Notice blood sure window.', '#44d67e', 'Lobbyist', '+1-894-009-9952x71054', 86793);
INSERT INTO `string` VALUES ('Mia Morgan', '267 Rodgers Ridges Apt. 910\nSouth John, PA 17753', 'Describe task toward network quality table while water. Middle everything impact so reach exist time. That citizen so forget hotel.', '#ea938c', 'Herpetologist', '528.609.9617x737', 86866);
INSERT INTO `string` VALUES ('Mark Armstrong', '813 Marshall Plains Suite 032\nBeckyburgh, AL 46474', 'Interest book feel how. Support truth student several section animal who plant. Nature need travel under newspaper reveal bar.\nOil will bad both lot.', '#1ea819', 'Site engineer', '(267)667-3847', 87854);
INSERT INTO `string` VALUES ('David Gray', '88177 Francis Station Suite 774\nMichaelfort, HI 28550', 'Hot candidate possible both these. Behavior later yard now way.', '#38ffe7', 'Interpreter', '+1-352-720-5879x96090', 88119);
INSERT INTO `string` VALUES ('Joe Page', '06343 Daniel Fall\nCatherineside, SC 87401', 'Conference necessary school politics. Congress then cell mention hundred modern western.', '#ed90ce', 'Museum/gallery conservator', '593-802-5631x3093', 88242);
INSERT INTO `string` VALUES ('Karina Larsen', '81039 Taylor Circle\nNorth Connorchester, SC 52309', 'Final be cell while.\nLocal building girl close. Skill sense throughout move.\nAny pretty occur us computer watch avoid partner. Life end movement itself interview. North three people history.', '#fc94a2', 'Museum education officer', '001-239-824-6969x86762', 90132);
INSERT INTO `string` VALUES ('Angela Boyer', '903 Walter Flats Apt. 005\nChadside, NJ 19829', 'Here from my into however project put. Imagine green raise family. Per clear country green.\nCurrent certainly very talk culture program similar. View go day plan behavior everything.', '#6d3435', 'Public relations account executive', '684-270-4047', 91686);
INSERT INTO `string` VALUES ('Mr. Kevin Smith PhD', '69868 Richardson Plain Apt. 216\nGrantport, CO 62771', 'Opportunity too air less population. Others white everyone over lawyer whatever less.\nHave young difficult song artist.', '#97d7e5', 'Furniture designer', '931.053.6513x4061', 92024);
INSERT INTO `string` VALUES ('Jeremy Hughes Jr.', '94397 Hicks Lock\nPort Stephanietown, HI 55199', 'Benefit skin second protect. Yeah little nor even writer control.\nCentral fall leader establish shake model professor. Pressure choice red create.', '#6b36af', 'Glass blower/designer', '(887)265-2816', 93168);
INSERT INTO `string` VALUES ('Christine Reeves', '832 Hansen Track Apt. 775\nYoungport, GA 22062', 'Term billion candidate. Art sister make research hundred new able.\nDirection enter those past old performance himself. Box son church design everybody our skin. May board perform ten tax.', '#b8c5f2', 'Medical physicist', '455.740.7119x737', 94428);
INSERT INTO `string` VALUES ('Elizabeth Sanders', '849 Robertson Summit Suite 715\nKingville, AL 08643', 'Above want particularly.\nFeel into alone when already. Development majority there bad good. Provide technology partner guy western talk century.', '#40edc7', 'Patent attorney', '5408199891', 95346);
INSERT INTO `string` VALUES ('Arthur Kelley', '4842 Gina Estate\nRussellberg, WA 88998', 'Ever such center. Raise speak up become card machine go.\nDog cause page professional across process. Positive thought suddenly financial against husband. Soldier threat money decade card.', '#384dc4', 'Astronomer', '001-040-988-6050x062', 95631);
INSERT INTO `string` VALUES ('Christine Villa', '81696 Eric Mill\nMurphybury, HI 31054', 'Fly employee church whether nearly lay. From range low option perform professor.\nSound whole who enter throughout knowledge crime. Prepare lot nation to onto management.', '#ef9b9f', 'Technical brewer', '7932327117', 95704);
INSERT INTO `string` VALUES ('Jessica Frank', '602 Howard Cape Apt. 145\nLewiston, WA 72411', 'Box really test name condition happy sport. Consumer laugh order against focus need best present.\nExpect community mind according do. Fight relate camera in deep. Coach Mr edge others visit trip.', '#4345a0', 'Statistician', '001-250-032-9121x6610', 96984);
INSERT INTO `string` VALUES ('Robin Hamilton', '156 Erica Tunnel\nJordanview, NJ 07521', 'Church able peace federal much. Water then mouth stand difference year. Voice exist for recognize cell.', '#d1baba', 'Osteopath', '(049)412-4822x9166', 97339);
INSERT INTO `string` VALUES ('Bobby Brown', '1072 Lisa Trail\nPattersonside, IA 86592', 'Lot away those sound allow. Mean remain cause side benefit. Near course life way size.\nActivity such enter staff provide shake. Determine type join yourself. Real race safe bill.', '#6c3ca3', 'Senior tax professional/tax inspector', '(435)900-0941', 99468);

SET FOREIGN_KEY_CHECKS = 1;
