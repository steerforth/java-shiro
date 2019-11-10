/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 127.0.0.1:3306
 Source Schema         : fwk-server

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/11/2019 21:47:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `sort` int(5) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '系统管理员', NULL, 0, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT '',
  `sex` tinyint(1) NOT NULL,
  `avatar` varchar(255) DEFAULT '',
  `account` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT '',
  `salt` varchar(255) DEFAULT '',
  `birthday` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `version` int(11) NOT NULL,
  `role_ids` varchar(255) DEFAULT '',
  `create_user` bigint(20) NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 1, '434', 'admin', 'ac801000db536f59b7b5b097da347319', '2019-10-20 23:04:43', '2019-10-20 23:04:47', '0', '3avlg', NULL, NULL, NULL, 0, '1', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
