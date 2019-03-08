/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3308
Source Database       : mycms

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2019-03-08 15:31:31
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
INSERT INTO `kf_admin` VALUES ('1', '2019-03-08 14:57:08', '2019-03-08 14:57:10', '2019-03-08 14:57:12', '127.0.0.1', '管理员', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '');

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
INSERT INTO `kf_admin_role` VALUES ('1', '1');

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
INSERT INTO `kf_category` VALUES ('1', '2017-03-21 17:17:51', '2017-03-21 17:17:51', null, '0', '案例', null, '', null, null, null, ',', null, '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case');
INSERT INTO `kf_category` VALUES ('2', '2017-03-21 17:17:59', '2017-03-21 17:17:59', null, '0', '模板', null, '', null, null, null, ',', null, '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template');
INSERT INTO `kf_category` VALUES ('3', '2017-03-21 17:41:46', '2017-03-21 17:41:46', null, '1', '游戏其他', null, null, null, null, null, ',1,', '1', '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case3');
INSERT INTO `kf_category` VALUES ('4', '2017-03-21 17:41:59', '2017-03-21 17:41:59', null, '1', '综合门户', null, null, null, null, null, ',1,', '1', '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case4');
INSERT INTO `kf_category` VALUES ('5', '2017-03-21 17:42:09', '2017-03-21 17:42:09', null, '1', '企业官网', null, null, null, null, null, ',1,', '1', '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case5');
INSERT INTO `kf_category` VALUES ('6', '2017-03-21 17:42:22', '2017-03-21 17:42:22', null, '1', '医疗美容', null, null, null, null, null, ',1,', '1', '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case6');
INSERT INTO `kf_category` VALUES ('7', '2017-03-21 17:42:32', '2017-03-21 17:42:32', null, '1', '电子商务', null, null, null, null, null, ',1,', '1', '1', 'list', 'caseList.html', '9', 'caseContent.html', null, null, null, '', '/case7');
INSERT INTO `kf_category` VALUES ('8', '2017-03-22 16:04:09', '2017-03-22 16:04:09', null, '1', '礼品/玩具/小商品', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template8');
INSERT INTO `kf_category` VALUES ('9', '2017-03-22 16:04:22', '2017-03-22 16:04:22', null, '1', '餐饮/酒店/旅游服务', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template9');
INSERT INTO `kf_category` VALUES ('10', '2017-03-22 16:04:38', '2017-03-22 16:04:38', null, '1', '律师/物流/生活服务', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template10');
INSERT INTO `kf_category` VALUES ('11', '2017-03-22 16:04:51', '2017-03-22 16:04:51', null, '1', '数码/家具/家居百货', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template11');
INSERT INTO `kf_category` VALUES ('12', '2017-03-22 16:05:05', '2017-03-22 16:05:05', null, '1', '金融/工商服务', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template12');
INSERT INTO `kf_category` VALUES ('13', '2017-03-22 16:05:17', '2017-03-22 16:05:17', null, '1', '化工/原材料/农畜牧', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template13');
INSERT INTO `kf_category` VALUES ('14', '2017-03-22 16:05:33', '2017-03-22 16:05:33', null, '1', '教育/培训/机构组织', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template14');
INSERT INTO `kf_category` VALUES ('15', '2017-03-22 16:05:47', '2017-03-22 16:05:47', null, '1', 'IT/科技/官网/门户', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template15');
INSERT INTO `kf_category` VALUES ('16', '2017-03-22 16:07:25', '2017-03-22 16:07:25', null, '1', '五金/设备/工业制品', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template16');
INSERT INTO `kf_category` VALUES ('17', '2017-03-22 16:07:56', '2017-03-22 16:07:56', null, '1', '文化/广告/设计服务', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template17');
INSERT INTO `kf_category` VALUES ('18', '2017-03-22 16:08:09', '2017-03-22 16:08:09', null, '1', '服装/饰品/个人护理', null, null, null, null, null, ',2,', '2', '2', 'list', 'templateList.html', '9', '', null, null, null, '', '/template18');
INSERT INTO `kf_category` VALUES ('32', '2017-05-12 15:47:22', '2017-05-12 15:47:25', null, '0', '新闻', null, '', null, null, null, ',', null, '3', 'list', 'newsList.html', '5', 'newsContent.html', null, null, null, '', '/news');
INSERT INTO `kf_category` VALUES ('33', '2017-05-12 15:47:29', '2017-05-12 15:47:32', null, '0', '关于', null, '', null, null, null, ',', null, null, 'page', null, null, '', 'about.html', null, null, '', '/about');
INSERT INTO `kf_category` VALUES ('34', '2017-05-15 18:00:48', '2017-12-21 21:56:29', null, '1', '建站教程', null, null, null, null, null, ',32,', '32', '3', 'list', 'newsList.html', '5', 'newsContent.html', null, null, null, '', '/news34');
INSERT INTO `kf_category` VALUES ('41', '2018-02-07 11:09:35', '2018-02-07 11:09:35', null, '1', 'SEO教程', null, '', null, null, null, ',32,', '32', '3', 'list', 'newsList.html', '5', 'newsContent.html', null, null, null, '', '/news41');
INSERT INTO `kf_category` VALUES ('42', '2018-02-07 11:44:52', '2018-02-07 11:44:52', null, '1', '品牌推广', null, '', null, null, null, ',32,', '32', '3', 'list', 'newsList.html', '6', 'newsContent.html', null, null, null, '', '/news42');
INSERT INTO `kf_category` VALUES ('43', '2018-02-07 13:49:51', '2018-02-07 13:49:51', null, '1', '微信营销', null, '', null, null, null, ',32,', '32', '3', 'list', 'newsList.html', '6', 'newsContent.html', null, null, null, '', '/news43');

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
) ENGINE=InnoDB AUTO_INCREMENT=2117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_content
-- ----------------------------
INSERT INTO `kf_content` VALUES ('1', '2017-03-21 17:48:48', '2017-03-22 13:56:22', '1', '', null, null, '企业网站建设需要做哪些准备呢', '/upload/news.jpg', '<html>\n <head></head>\n <body>\n  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现在很多的公司都会为自己建立网站，这是非常普通的一种宣传方式了，如果你的公司没有，就会给人们一种规模很小，又或者是非常的不可靠的一种印象，所以说每一个企业，想要在竞争中脱颖而出，就要建立自己的网站，网站建设需要准备什么材料呢?</p>\n  <p>第一、前期的知识</p>\n  <p>　　想要建立网站，就要了解网站的相关内容，包括网站会产生的各种积极和消极的作用，网站的产生不仅仅会有积极的作用，如果经营的不好，也会产生消极的作用，所以说建立网站之前，公司的领导一定要清楚的知道网站的重要性，在后期的维护上多花费一些精力，这些建立网站的前期知识一定要有所了解，否则就算是网站已经建立好了，也会有非常不理想的效果。</p>\n  <p>第二、公司的简介</p>\n  <p>　　每一个网站都必须要有公司的简介，因为一个公司的发展史和简介往往包括了这个公司的很多的内容，领导的眼光和发展规划，公司的文化，而这些也代表了这个公司以后会不会有非常的好的发展，所以说很多的人都非常的看重公司的简介，这部分内容一定要整理好。</p>\n  <p>第三、公司的标志</p>\n  <p>　　这是人们认识并且熟悉一个公司的最基本的信息，可以说一个好的标志可以为你带来很多的商机。所以建立网站的时候要把公司的标志给提供上去，放在比较明显的位置上，让人们一进入这个网站就可以非常清晰的看见。</p>\n  <p>第四、公司的产品或者是服务的介绍和图片</p>\n  <p>　　这是最主要的内容之一，因为人们会关注你的公司的网站，基本上也就是对于你的公司的产品比较感兴趣。在这种情况之下还是需要多多的介绍一些你的产品的，最好是图文并茂，让人们可以有一个更加的清楚的概念。</p>\n  <p>　　网站建设的材料自己一定要准备的非常的全面，有的时候就算是建设网站的人没有和你要，你自己也要知道你的网站上面都需要放什么资料。</p>\n  <p><br></p>\n </body>\n</html>', '34', null, null);
INSERT INTO `kf_content` VALUES ('2', '2017-03-22 14:14:49', '2017-03-22 14:19:21', '2', '', null, null, '企业在网站建设过程中有什么关键因素', '/upload/news.jpg', '<html>\n <head></head>\n <body>\n  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;随着近年来互联网的高速发展以及网络基础设施的完善，越来越多的企业和个人投身到网络营销之中。其中还有非常多的中小企业，他们资源有限，可借鉴的也不多，在建站的过程中遇到很多问题。今天网总管就网站建设过程中的几个重要因素做出分析。</p>\n  <p>1、企业网站建设的主要目的</p>\n  <p>　　知道seo的朋友们都知道企业网站建设其实并不难，根据不同行业的网站，企业网站定位也就不同，需要把产品和服务，把相关的栏目、重点搜索的关键词有条理的整理在一起，通过设计页面来展示给用户，突出网站的特色，才能够达到一个企业初步的印象。根据自己企业的产品、销售的对象，明确自己的网站是信息服务型、销售型、销售服务型还是综合型的网站，目的就是能卖产品，企业的信息、产品的名称、联系方式等要突出显示出来。</p>\n  <p>2、企业的网站推广</p>\n  <p>　　企业网站不仅单纯的只是网站，应具有网络营销的目的，其中推广起到了相当重要的作用。企业的网站宣传的空间有，各大搜索引擎，大型的企业网站集合的网站，搜索引擎和商务类的网站，给企业建立了一个良好的平台，可以及时的有效的宣传其企业的产品。很多企业在建设中，提供了企业在线客服的功能，可以有效的加深与客户的沟通关系，更能了解客户所需求的，提高企业的效率，这样的功能，能更好的给企业网站推广创建了一个基础。</p>\n  <p>3、企业域名的选择</p>\n  <p>　　域名是企业网络推广的一个前提，域名给用户带来的是一种形象的展现。在选择域名的时候，我们要考虑域名与企业的名称，企业的特色，企业产的品相统一。一个好的域名，一个简洁的域名，使客户更容易牢记，为企业也树立了一个很好的形象。</p>\n  <p>4、空间和服务商的选择</p>\n  <p>　　一个稳定的空间服务器对网站起着非常大的作用，服务器的稳定是影响网站的其中的一个因素。企业在网站建设的时候，不了解自己网站所需求的，导致在访问网站的时候，速度很慢，IIS的链接数量不够用，有的空间程序不支持其网站。因此要选择好的服务器。</p>\n  <p><br></p>\n </body>\n</html>', '34', null, null);
INSERT INTO `kf_content` VALUES ('3', '2017-03-22 14:25:49', '2017-03-22 14:27:18', '0', '', null, null, '企业网站建设技术方面需要注意的几个问题', '/upload/news.jpg', '<html>\n <head></head>\n <body>\n  <p>网络技术决定了网页设计最终的下载显示以及使用，那网站建设技术方面需要注意的几个问题呢?</p>\n  <p>（1）带宽因素</p>\n  <p>　　网络带宽是指通讯路上一定时间内的信息流量，一般用来表示网络的信息传输速度。带宽所决定的连接速度将影响到网站设计。</p>\n  <p>　　有关下载时间一个“8秒钟规则”，即绝大多数浏览者不会等待8秒钟来完整下载一个网页，所以在设计网页时应使预计的下载时间少于8秒钟。如果页面的下载速度太慢，则访问者不会考虑页面中有什么吸引人的地方，而是会很快地按下浏览上的“停止按钮”，或是转到其他的网站中。</p>\n  <p>　　影响网站建设显示速度的最主要因素就是图像的数量和大小，加快页面下载时间最有效的方法，就是减少页面中的图像大小和数量。</p>\n  <p>（2）浏览器与分辨率</p>\n  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鉴于目前国内用户使用的大多是windows98/me/nt/2000/xp自身捆绑的IE浏览器，所以在制作网页时通常不必考虑浏览器的兼容问题。</p>\n  <p>显示器的屏幕分辨率是网页设计者应该特别关注的因素，因为同一个网页在不同的分辨率下的显示效果可能大不相同。通常可以将国内用户显示器分辨率的设计标准定为：800*600像素。</p>\n  <p>（3）即时交互与插件</p>\n  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接下来就需要考虑网站设计是否需要进行交互。例如，如果希望提供即时交互，那么就要在网页中加入JavaScript脚本，或是使用一些服务器技术，或是使用一些可实现交互式功能的对象。</p>\n  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;另外，如果在网页中使用了flash动画或其它多媒体对象，就要下载相关插件。现在大多数较新版本的浏览器都已经安装了Flash插件，但要使用Authorware或Shockwave对象，则需要浏览者下载其插件。</p>\n  <p>以上的3个方面是网站建设从业者特别需要注意的几个问题。网站用户体验的好坏很大程度上决定于网站设计细节的注重。</p>\n  <p><br></p>\n </body>\n</html>', '34', null, null);
INSERT INTO `kf_content` VALUES ('2114', '2019-03-08 15:26:18', '2019-03-08 15:26:20', '0', '', null, null, '案例案例案例3', '/upload/product.jpg', '<p><a href=\"http://www.jrecms.com\" _src=\"http://www.jrecms.com\"><strong>http://www.jrecms.com</strong></a></p><p><strong>案例案例案例</strong></p>', '4', '{\"summary\":\"\",\"url\":\"\"}', '[]');
INSERT INTO `kf_content` VALUES ('2115', '2019-03-08 15:27:06', '2019-03-08 15:27:08', '0', '', null, null, '案例案例案例2', '/upload/product.jpg', '<p><a href=\"http://www.jrecms.com\" _src=\"http://www.jrecms.com\"><strong>http://www.jrecms.com</strong></a></p><p><strong>案例案例案例</strong></p>', '4', '{\"summary\":\"\",\"url\":\"\"}', '[]');
INSERT INTO `kf_content` VALUES ('2116', '2019-03-08 15:27:31', '2019-03-08 15:27:33', '0', '', null, null, '案例案例案例1', '/upload/product.jpg', '<p><a href=\"http://www.jrecms.com\" _src=\"http://www.jrecms.com\"><strong>http://www.jrecms.com</strong></a></p><p><strong>案例案例案例</strong></p>', '4', '{\"summary\":\"\",\"url\":\"\"}', '[]');

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
INSERT INTO `kf_content_field` VALUES ('1', '2017-05-15 17:53:54', '2018-01-11 14:58:47', 'url', 'input', 'url', 'url', null, '{}', '2', '', '');
INSERT INTO `kf_content_field` VALUES ('2', '2017-05-15 17:54:18', '2018-01-11 15:16:03', 'url', 'input', 'url', 'url', null, '{}', '1', '', '');
INSERT INTO `kf_content_field` VALUES ('3', '2017-05-15 17:54:41', '2017-05-15 17:54:41', 'summary', 'input', '备注', '备注', null, '{}', '1', '', '');

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
INSERT INTO `kf_content_model` VALUES ('1', '2017-05-12 15:02:53', '2017-05-12 15:02:55', '案例', 'case_list.html', 'case_detail.html');
INSERT INTO `kf_content_model` VALUES ('2', '2017-05-12 15:03:14', '2017-05-12 15:03:16', '模板', 'templateList.html', 'templateContent.html');
INSERT INTO `kf_content_model` VALUES ('3', '2017-05-12 15:03:40', '2017-05-12 15:03:42', '新闻', 'newsList.html', 'newsContent.html');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kf_role
-- ----------------------------
INSERT INTO `kf_role` VALUES ('1', '2019-03-08 14:59:58', '2019-03-08 14:59:58', '[\"/admin/category\",\"/admin/content_model\",\"/admin/form_model\",\"/admin/content\",\"/admin/form\",\"/admin/ad\",\"/admin/ad_position\",\"/admin/friend_link\",\"/admin/role\",\"/admin/admin\",\"/admin/storage_plugin\",\"/admin/area\",\"/admin/setting\",\"/admin/template\",\"/admin/cache\",\"/admin/static\",\"/admin/sitemap\",\"/admin/profile\"]', '超级管理员', '', '超级管理员');

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
INSERT INTO `kf_setting` VALUES ('1', 'address', '长沙市雨花区树木岭', '地址', null, '2018-03-06 16:05:47', 'input');
INSERT INTO `kf_setting` VALUES ('2', 'certtext', '湘ICP备16006112号-1', '备案', null, null, 'input');
INSERT INTO `kf_setting` VALUES ('3', 'email', '644080923@qq.com', '邮箱', null, null, 'input');
INSERT INTO `kf_setting` VALUES ('4', 'zipCode', '410000', '邮编', null, null, 'input');
INSERT INTO `kf_setting` VALUES ('5', 'qq', '644080923', 'QQ', null, null, 'input');
INSERT INTO `kf_setting` VALUES ('6', 'phone', '', '电话', null, null, 'input');
INSERT INTO `kf_setting` VALUES ('7', 'statisticsCode', '<script src=\"http://s11.cnzz.com/stat.php?id=1261488447&web_id=1261488447\" language=\"JavaScript\"></script>', '统计代码', null, null, 'textarea');

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
INSERT INTO `kf_storage_plugin` VALUES ('ftpStoragePlugin', 'FTP存储', '3', '{}', '');
INSERT INTO `kf_storage_plugin` VALUES ('localStoragePlugin', '本地文件存储', '1', '{}', '');
INSERT INTO `kf_storage_plugin` VALUES ('ossStoragePlugin', '阿里云存储', '2', '{}', '');

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
