/*
 * 
 * 
 * 
 */
package com.cms;

import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * 公共参数
 * 
 * 
 * 
 */
public final class CommonAttribute {
	
	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** UTF-8编码 */
	public static final String UTF_8 = "UTF-8";
	
	/** POST */
	public static final String POST="post";
	
	/** GET */
	public static final String GET="get";
	
	/** 后台页面 */
	public static final String ADMIN_PATH="/WEB-INF/admin/";
	
	/** 后台错误页面 */
	public static final String ADMIN_ERROR_VIEW = ADMIN_PATH+"error/500.html";
	
	/** 后台权限错误页面 */
	public static final String ADMIN_UNAUTHORIZED_VIEW = ADMIN_PATH+"error/403.html";
	
	/** 前台错误页面 */
	public static final String FRONT_ERROR_VIEW = "/500.html";
	
	/** 前台权限错误页面 */
	public static final String FRONT_RESOURCE_NOT_FOUND_VIEW = "/404.html";
	
	/** config.xml文件路径 */
	public static final String CONFIG_XML_PATH = "/config.xml";
	
	/** config.properties */
	public static final String CONFIG_PROPERTIES = "config.properties";
	
	/** job.properties */
	public static final String JOB_PROPERTIES = "job.properties";
	
	/** 页面后缀 */
	public static final String VIEW_EXTENSION = ".html";
	
	/** 参数分隔符 */
	public static final String URL_PARA_SEPARATOR = "-";
	
	/** 上传文件目录 */
	public static final String BASE_UPLOAD_PATH = "upload";
	
	/** JSON时间格式 */
	public static final String JSON_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 不可实例化
	 */
	private CommonAttribute() {
	}
	
	/**
	 *消息类型
	 */
	
	/** 成功 */
	public static final String FEEDBACK_TYPE_SUCCESS="success";
	
	/** 警告 */
	public static final String FEEDBACK_TYPE_WARN="warn";
	
	/** 错误 */
	public static final String FEEDBACK_TYPE_ERROR="error";
	
	/** 消息类型名称 */
	public static final Map feedbackTypeNames = new ListOrderedMap(){{
		put(FEEDBACK_TYPE_SUCCESS,"成功");
		put(FEEDBACK_TYPE_WARN, "警告");
		put(FEEDBACK_TYPE_ERROR, "错误");
	}};
	
	/**
	 *文件类型
	 */
	
	/** 图片 */
	public static final String FILE_TYPE_IMAGE="image";
	
	/** 媒体 */
	public static final String FILE_TYPE_MEDIA="media";
	
	/** 文件 */
	public static final String FILE_TYPE_FILE="file";
	
	/** 文件类型名称 */
	public static final Map fileTypeNames = new ListOrderedMap(){{
		put(FILE_TYPE_IMAGE, "图片");
		put(FILE_TYPE_MEDIA, "媒体");
		put(FILE_TYPE_FILE, "文件");
	}};
	
	/**
	 * 广告类型
	 */
	
	/** 文本 */
	public static final String AD_TYPE_TEXT="text";
	
	/** 图片 */
	public static final String AD_TYPE_IMAGE="image";
	
	/** 广告类型名称 */
	public static final Map adTypeNames = new ListOrderedMap(){{
		put(AD_TYPE_TEXT, "文本");
		put(AD_TYPE_IMAGE, "图片");
	}};
	
	/**
	 * 友情链接类型
	 */
	
	/** 文本 */
	public static final String FRIENDLINK_TYPE_TEXT="text";
	
	/** 图片 */
	public static final String FRIENDLINK_TYPE_IMAGE="image";
	
	/** 友情链接类型名称 */
	public static final Map friendLinkTypeNames = new ListOrderedMap(){{
		put(FRIENDLINK_TYPE_TEXT, "文本");
		put(FRIENDLINK_TYPE_IMAGE, "图片");
	}};
	
	/**
	 * 水印位置
	 */
	
	/** 无 */
	public static final String CONFIG_WATERMARK_POSITION_NO="no";
	
	/** 左上 */
	public static final String CONFIG_WATERMARK_POSITION_TOP_LEFT="topLeft";
	
	/** 右上 */
	public static final String CONFIG_WATERMARK_POSITION_TOP_RIGHT="topRight";
	
	/** 居中 */
	public static final String CONFIG_WATERMARK_POSITION_CENTER="center";
	
	/** 左下 */
	public static final String CONFIG_WATERMARK_POSITION_BOTTOM_LEFT="bottomLeft";
	
	/** 右下 */
	public static final String CONFIG_WATERMARK_POSITION_BOTTOM_RIGHT="bottomRight";
	
	/** 水印位置名称 */
	public static final Map configWatermarkPositionNames = new ListOrderedMap(){{
		put(CONFIG_WATERMARK_POSITION_NO, "无");
		put(CONFIG_WATERMARK_POSITION_TOP_LEFT, "左上");
		put(CONFIG_WATERMARK_POSITION_TOP_RIGHT, "右上");
		put(CONFIG_WATERMARK_POSITION_CENTER, "居中");
		put(CONFIG_WATERMARK_POSITION_BOTTOM_LEFT, "左下");
		put(CONFIG_WATERMARK_POSITION_BOTTOM_RIGHT, "右下");
	}};
	
	/**
	 * 网站模式
	 */
	
	/** 动态 */
	public static final String CONFIG_SITE_MODEL_DYNAMIC="dynamic";
	
	/** 静态 */
	public static final String CONFIG_SITE_MODEL_STATIC="static";
	
	/** 网站模式名称 */
	public static final Map configSiteModelNames = new ListOrderedMap(){{
		put(CONFIG_SITE_MODEL_DYNAMIC, "动态");
		put(CONFIG_SITE_MODEL_STATIC, "静态");
	}};
	
	/**
	 * 设置类型
	 */
	
	/** input(单行文本) */
	public static final String CONFIG_TYPE_INPUT="input";
	
	/** textarea(多行文本) */
	public static final String CONFIG_TYPE_TEXTAREA="textarea";
	
	/** editor(编辑器) */
	public static final String CONFIG_TYPE_EDITOR="editor";
	
	/** file(单文件/图片上传) */
	public static final String CONFIG_TYPE_FILE="file";
	
	/** 设置类型名称 */
	public static final Map configTypeNames = new ListOrderedMap(){{
		put(CONFIG_TYPE_INPUT, "input(单行文本)");
		put(CONFIG_TYPE_TEXTAREA, "textarea(多行文本)");
		put(CONFIG_TYPE_EDITOR, "editor(编辑器)");
		put(CONFIG_TYPE_FILE, "file(单文件/图片上传)");
	}};
	
	
	/**
	 * 静态化生成类型
	 */
	
	/** 首页 */
	public static final String STATIC_GENERATE_TYPE_INDEX="index";
	
	/** 栏目 */
	public static final String STATIC_GENERATE_TYPE_CATEGORY="category";
	
	/** 内容 */
	public static final String STATIC_GENERATE_TYPE_CONTENT="content";
	
	/** 静态化生成类型名称 */
	public static final Map generateTypeNames = new ListOrderedMap(){{
		put(STATIC_GENERATE_TYPE_INDEX, "首页");
		put(STATIC_GENERATE_TYPE_CATEGORY, "栏目页");
		put(STATIC_GENERATE_TYPE_CONTENT, "内容页");
	}};
	
	/**
	 * 字段类型
	 */
	
	/** input(单行文本) */
	public static final String FIELD_TYPE_INPUT="input";
	
	/** textarea(多行文本) */
	public static final String FIELD_TYPE_TEXTAREA="textarea";
	
	/** editor(编辑器) */
	public static final String FIELD_TYPE_EDITOR="editor";
	
	/** radio(单选按钮) */
	public static final String FIELD_TYPE_RADIO="radio";
	
	/** checkbox(复选框) */
	public static final String FIELD_TYPE_CHECKBOX="checkbox";
	
	/** file(单文件/图片上传) */
	public static final String FIELD_TYPE_FILE="file";
	
	/** select(下拉选择框) */
	public static final String FIELD_TYPE_SELECT="select";
	
	/** date(日期时间) */
	public static final String FIELD_TYPE_DATE="date";
	
	/** 字段类型名称 */
	public static final Map fieldTypeNames = new ListOrderedMap(){{
		put(FIELD_TYPE_INPUT, "input(单行文本)");
		put(FIELD_TYPE_TEXTAREA, "textarea(多行文本)");
		put(FIELD_TYPE_EDITOR, "editor(编辑器)");
		put(FIELD_TYPE_RADIO, "radio(单选按钮)");
		put(FIELD_TYPE_CHECKBOX, "checkbox(复选框)");
		put(FIELD_TYPE_FILE, "file(单文件/图片上传)");
		put(FIELD_TYPE_SELECT, "select(下拉选择框)");
		put(FIELD_TYPE_DATE, "date(日期时间)");
	}};
	
	/**
	 *栏目类型
	 */
	
	/** 列表 */
	public static final String CATEGORY_TYPE_LIST="list";
	
	/** 页面 */
	public static final String CATEGORY_TYPE_PAGE="page";
	
	/** 链接 */
	public static final String CATEGORY_TYPE_LINK="link";
	
	/** 栏目类型名称 */
	public static final Map categoryTypeNames = new ListOrderedMap(){{
		put(CATEGORY_TYPE_LIST,"列表");
		put(CATEGORY_TYPE_PAGE, "页面");
		put(CATEGORY_TYPE_LINK, "链接");
	}};
	
	/**
	 *模型字段正则
	 */
	
	/** 数字 */
	public static final String MODEL_FIELD_PATTERN_NUMBER="/^[0-9.-]+$/";
	
	/** 整数 */
	public static final String MODEL_FIELD_PATTERN_INTEGER="/^[0-9-]+$/";
	
	/** 字母 */
	public static final String MODEL_FIELD_PATTERN_LETTER="/^[a-z]+$/i";
	
	/** 数字+字母 */
	public static final String MODEL_FIELD_PATTERN_NUMBER_AND_LETTER="/^[0-9a-z]+$/i";
	
	/** E-mail */
	public static final String MODEL_FIELD_PATTERN_EMAIL="/^[\\w\\-\\.]+@[\\w\\-\\.]+(\\.\\w+)+$/";
	
	/** QQ */
	public static final String MODEL_FIELD_PATTERN_QQ="/^[0-9]{5,20}$/";
	
	/** 链接 */
	public static final String MODEL_FIELD_PATTERN_LINK="/^http:\\/\\//";
	
	/** 手机号码 */
	public static final String MODEL_FIELD_PATTERN_CELL_PHONE_NUMBER="/^(1)[0-9]{10}$/";
	
	/** 电话号码 */
	public static final String MODEL_FIELD_PATTERN_PHONE_NUMBER="/^[0-9-]{6,13}$/";
	
	/** 邮政编码 */
	public static final String MODEL_FIELD_PATTERN_POSTCODE="/^[0-9]{6}$/";
	
	
	/** 模型字段正则名称 */
	public static final Map modelFieldPatternNames = new ListOrderedMap(){{
		put(MODEL_FIELD_PATTERN_NUMBER,"数字");
		put(MODEL_FIELD_PATTERN_INTEGER, "整数");
		put(MODEL_FIELD_PATTERN_LETTER, "字母");
		put(MODEL_FIELD_PATTERN_NUMBER_AND_LETTER, "数字+字母");
		put(MODEL_FIELD_PATTERN_EMAIL, "E-mail");
		put(MODEL_FIELD_PATTERN_QQ, "QQ");
		put(MODEL_FIELD_PATTERN_LINK, "链接");
		put(MODEL_FIELD_PATTERN_CELL_PHONE_NUMBER, "手机号码");
		put(MODEL_FIELD_PATTERN_PHONE_NUMBER, "电话号码");
		put(MODEL_FIELD_PATTERN_POSTCODE, "邮政编码");
	}};
}