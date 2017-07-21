/*
Navicat MySQL Data Transfer

Source Server         : tck
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-21 19:22:47
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '984', '888.00', '1122', '21jhj', '1');
INSERT INTO `tb_product` VALUES ('2', 'rgfdg', '123.00', 'dsfdsfdsf', 'fdsfsdfsf', '2');

-- ----------------------------
-- Table structure for tb_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order`;
CREATE TABLE `tb_purchase_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `product_id` int(11) unsigned DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `warehouse_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  KEY `warehouse_id` (`warehouse_id`),
  CONSTRAINT `tb_purchase_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_purchase_order_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_purchase_order_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `tb_warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchase_order
-- ----------------------------
INSERT INTO `tb_purchase_order` VALUES ('2', '1', '1', '156', '测试', '1');
INSERT INTO `tb_purchase_order` VALUES ('3', '1', '1', '156', '测试', '1');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------
INSERT INTO `tb_warehouse` VALUES ('1', '8888f88s', '123456');

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
