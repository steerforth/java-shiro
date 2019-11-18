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

 Date: 18/11/2019 23:01:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  `pids` varchar(512) NOT NULL,
  `simple_name` varchar(45) DEFAULT NULL,
  `sort` int(5) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `code` varchar(30) NOT NULL,
  `pcode` varchar(30) NOT NULL,
  `pcodes` varchar(255) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  `sort` int(5) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  `status` int(3) NOT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 'system', '0', '[0],', 'layui-icon layui-icon-username', '#', 1, 100, NULL, 'Menu', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, '用户管理', 'user', 'system', '[0],[system],', NULL, '/user', 2, 1, NULL, 'Menu', 1, 1, 0, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '添加用户', 'user_add', 'user', '[0],[system],[user],', NULL, '/user/add', 3, 1, NULL, 'Button', 1, 1, 0, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '修改用户', 'user_edit', 'user', '[0],[system],[user],', NULL, '/user/edit', 3, 2, NULL, 'Button', 1, 1, 0, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, '删除用户', 'user_delete', 'user', '[0],[system],[user],', NULL, '/user/delete', 3, 3, NULL, 'Button', 1, 1, 0, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (10, '角色管理', 'role', 'system', '[0],[system],', NULL, '/role', 2, 2, NULL, 'Menu', 1, 1, 0, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (20, '菜单管理', 'menu', 'system', '[0],[system],', NULL, '/menu', 2, 3, NULL, 'Menu', 1, 1, 0, 1, NULL, NULL);
COMMIT;

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
  `create_user_id` bigint(20) NOT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '系统管理员', NULL, 0, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5);
INSERT INTO `sys_role_menu` VALUES (10, 1, 10);
INSERT INTO `sys_role_menu` VALUES (20, 1, 20);
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
  `status` int(3) DEFAULT NULL,
  `salt` varchar(255) DEFAULT '',
  `birthday` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `version` int(11) NOT NULL,
  `role_ids` varchar(255) DEFAULT '',
  `create_user_id` bigint(20) NOT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 1, '434', 'admin', 'ac801000db536f59b7b5b097da347319', '2019-10-20 23:04:43', '2019-10-20 23:04:47', 0, '3avlg', NULL, NULL, NULL, 0, '1', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
