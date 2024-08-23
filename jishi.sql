/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : jishi

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 23/08/2024 13:55:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jishi_dept
-- ----------------------------
DROP TABLE IF EXISTS `jishi_dept`;
CREATE TABLE `jishi_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jishi_dept
-- ----------------------------
INSERT INTO `jishi_dept` VALUES (1, '后端部门');
INSERT INTO `jishi_dept` VALUES (2, '前端部门');
INSERT INTO `jishi_dept` VALUES (3, '测试部门');

-- ----------------------------
-- Table structure for jishi_order
-- ----------------------------
DROP TABLE IF EXISTS `jishi_order`;
CREATE TABLE `jishi_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_no` int(11) NULL DEFAULT NULL,
  `order_type` int(255) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handle_dept_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `fenpai_time` datetime NULL DEFAULT NULL,
  `is_overdue` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jishi_order
-- ----------------------------
INSERT INTO `jishi_order` VALUES (1, 1000, 0, '工单1', '工单1的内容', 1, '2024-07-21 17:27:53', '2024-08-21 17:27:53', 0);
INSERT INTO `jishi_order` VALUES (2, 1001, 0, '工单2', '工单2的内容', 1, '2024-07-21 17:27:53', '2024-08-21 17:27:53', 0);
INSERT INTO `jishi_order` VALUES (3, 1002, 0, '工单3', '工单3的内容', 2, '2024-07-21 17:27:53', '2024-08-21 17:27:53', 0);
INSERT INTO `jishi_order` VALUES (5, 1008, 0, '测试', '测试的内容', NULL, '2024-07-21 17:27:53', '2024-08-23 13:17:33', NULL);

SET FOREIGN_KEY_CHECKS = 1;
