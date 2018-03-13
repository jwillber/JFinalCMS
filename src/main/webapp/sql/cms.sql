/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3308
Source Database       : www-test

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-03-13 09:32:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kf_ad`
-- ----------------------------
DROP TABLE IF EXISTS `kf_ad`;
CREATE TABLE `kf_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `content` longtext,
  `image` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `adPositionId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_ad
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_admin`
-- ----------------------------
DROP TABLE IF EXISTS `kf_admin`;
CREATE TABLE `kf_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `loginDate` datetime DEFAULT NULL,
  `loginIp` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_admin
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `kf_admin_role`;
CREATE TABLE `kf_admin_role` (
  `adminId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`adminId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_ad_position`
-- ----------------------------
DROP TABLE IF EXISTS `kf_ad_position`;
CREATE TABLE `kf_ad_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `template` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_area`
-- ----------------------------
DROP TABLE IF EXISTS `kf_area`;
CREATE TABLE `kf_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `fullName` longtext,
  `grade` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `treePath` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3229 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_area
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_category`
-- ----------------------------
DROP TABLE IF EXISTS `kf_category`;
CREATE TABLE `kf_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `isMenu` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `treePath` varchar(255) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `contentModelId` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `listTemplate` varchar(255) DEFAULT NULL,
  `listPageSize` int(11) DEFAULT NULL,
  `contentTemplate` varchar(255) DEFAULT NULL,
  `pageTemplate` varchar(255) DEFAULT NULL,
  `pageContent` text,
  `linkUrl` varchar(255) DEFAULT NULL,
  `isEnabled` bit(1) NOT NULL,
  `directory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_category
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_content`
-- ----------------------------
DROP TABLE IF EXISTS `kf_content`;
CREATE TABLE `kf_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `hits` bigint(20) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `introduction` longtext,
  `categoryId` bigint(20) NOT NULL,
  `contentFieldValue` text,
  `tagValue` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_content
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_content_field`
-- ----------------------------
DROP TABLE IF EXISTS `kf_content_field`;
CREATE TABLE `kf_content_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `prompt` varchar(255) DEFAULT NULL,
  `pattern` varchar(255) DEFAULT NULL,
  `attributeValue` text,
  `contentModelId` bigint(20) DEFAULT NULL,
  `isEnabled` bit(1) DEFAULT NULL,
  `isRequired` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_content_field
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_content_model`
-- ----------------------------
DROP TABLE IF EXISTS `kf_content_model`;
CREATE TABLE `kf_content_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `listTemplate` varchar(255) DEFAULT NULL,
  `contentTemplate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_content_model
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_form`
-- ----------------------------
DROP TABLE IF EXISTS `kf_form`;
CREATE TABLE `kf_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `isRelationModel` bit(1) DEFAULT NULL,
  `formModelId` bigint(20) NOT NULL,
  `contentId` bigint(20) DEFAULT NULL,
  `formFieldValue` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_form
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_form_field`
-- ----------------------------
DROP TABLE IF EXISTS `kf_form_field`;
CREATE TABLE `kf_form_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `prompt` varchar(255) DEFAULT NULL,
  `pattern` varchar(255) DEFAULT NULL,
  `attributeValue` text,
  `formModelId` bigint(20) DEFAULT NULL,
  `isEnabled` bit(1) DEFAULT NULL,
  `isRequired` bit(1) DEFAULT NULL,
  `isAdminShow` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_form_field
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_form_model`
-- ----------------------------
DROP TABLE IF EXISTS `kf_form_model`;
CREATE TABLE `kf_form_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `listTemplate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_form_model
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_friend_link`
-- ----------------------------
DROP TABLE IF EXISTS `kf_friend_link`;
CREATE TABLE `kf_friend_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_role`
-- ----------------------------
DROP TABLE IF EXISTS `kf_role`;
CREATE TABLE `kf_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `permission` longtext NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isSystem` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_role
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_setting`
-- ----------------------------
DROP TABLE IF EXISTS `kf_setting`;
CREATE TABLE `kf_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_setting
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_storage_plugin`
-- ----------------------------
DROP TABLE IF EXISTS `kf_storage_plugin`;
CREATE TABLE `kf_storage_plugin` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sort` int(11) NOT NULL,
  `attribute` longtext NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_storage_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for `kf_tag`
-- ----------------------------
DROP TABLE IF EXISTS `kf_tag`;
CREATE TABLE `kf_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_tag
-- ----------------------------
