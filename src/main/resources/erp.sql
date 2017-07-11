/*
Navicat MySQL Data Transfer

Source Server         : tck
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-11 19:00:05
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
INSERT INTO `tb_product` VALUES ('1', '12222', '123.00', 'sadsfdsfd', 'fdfdvvv', '1');
INSERT INTO `tb_product` VALUES ('2', 'rgfdg', '123.00', 'dsfdsfdsf', 'fdsfsdfsf', '2');

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
