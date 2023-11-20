/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : saas

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 20/11/2023 18:16:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for depend
-- ----------------------------
DROP TABLE IF EXISTS `depend`;
CREATE TABLE `depend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `main` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `sub` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of depend
-- ----------------------------
INSERT INTO `depend` VALUES (1, 'top', 'father');
INSERT INTO `depend` VALUES (2, 'father', 'modd');
INSERT INTO `depend` VALUES (3, 'father', 'moda');
INSERT INTO `depend` VALUES (4, 'father', 'modb');
INSERT INTO `depend` VALUES (5, 'father', 'modc');
INSERT INTO `depend` VALUES (6, 'modc', 'modb');
INSERT INTO `depend` VALUES (7, 'modb', 'moda');
INSERT INTO `depend` VALUES (8, 'modc', 'mode');

SET FOREIGN_KEY_CHECKS = 1;
