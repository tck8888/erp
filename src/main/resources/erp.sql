/*
Navicat MySQL Data Transfer

Source Server         : tck
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-10 20:27:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL,
  `balance` double(11,0) DEFAULT '0',
  `accountName` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `tb_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('2', '1', null, '测试账户', '测试账户备注');

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL DEFAULT '',
  `product_price` decimal(10,2) NOT NULL,
  `product_image` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_product_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------

-- ----------------------------
-- Table structure for tb_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order`;
CREATE TABLE `tb_purchase_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL,
  `warehouseId` int(11) unsigned NOT NULL,
  `warehouseName` varchar(255) NOT NULL DEFAULT '',
  `accountId` int(11) unsigned NOT NULL,
  `accountName` varchar(255) NOT NULL DEFAULT '',
  `totalCount` int(11) NOT NULL,
  `totalPrice` double(10,2) unsigned NOT NULL,
  `productId` varchar(255) NOT NULL DEFAULT '',
  `productCount` varchar(255) NOT NULL DEFAULT '',
  `date` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchase_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `avatar` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '13776356351', 'qwe123', 'tck6666@163.com', '');
INSERT INTO `tb_user` VALUES ('2', '13776356352', 'qwe123', 'tck6666@163.com', '');
INSERT INTO `tb_user` VALUES ('3', '13776356353', 'qwe123', '', '');
INSERT INTO `tb_user` VALUES ('4', '13776356353', 'qwe123', '', '');

-- ----------------------------
-- Table structure for tb_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `productName` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT '',
  `user_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_warehouse_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------

-- ----------------------------
-- Table structure for tb_warehouse_product_count
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_product_count`;
CREATE TABLE `tb_warehouse_product_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(11) unsigned NOT NULL,
  `warehouseId` int(11) unsigned NOT NULL,
  `count` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `warehouseId` (`warehouseId`),
  CONSTRAINT `tb_warehouse_product_count_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_warehouse_product_count_ibfk_2` FOREIGN KEY (`warehouseId`) REFERENCES `tb_warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse_product_count
-- ----------------------------
INSERT INTO `tb_warehouse_product_count` VALUES ('1', '1', '1', '33');
