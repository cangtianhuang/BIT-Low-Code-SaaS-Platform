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

 Date: 20/11/2023 18:17:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for outer_depend
-- ----------------------------
DROP TABLE IF EXISTS `outer_depend`;
CREATE TABLE `outer_depend`  (
  `id` int NOT NULL,
  `module` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '模块',
  `groupId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `artifactId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `version` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '这三个可空，空代表无',
  `scope` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `optional` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outer_depend
-- ----------------------------
INSERT INTO `outer_depend` VALUES (1, 'multimod', 'org.springframework.boot', 'spring-boot-starter', '', '', '');
INSERT INTO `outer_depend` VALUES (2, 'multimod', 'org.springframework.boot', 'spring-boot-devtools', '', 'runtime', 'true');
INSERT INTO `outer_depend` VALUES (3, 'multimod', 'org.springframework.boot', 'spring-boot-starter-test', '', 'test', '');
INSERT INTO `outer_depend` VALUES (4, 'top', 'org.springframework.boot', 'spring-boot-autoconfigure', NULL, NULL, NULL);
INSERT INTO `outer_depend` VALUES (5, 'father', 'org.springframework.boot', 'spring-boot-starter-web', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
