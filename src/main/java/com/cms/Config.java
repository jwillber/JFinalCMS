package com.cms;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Config implements Serializable {

	private static final long serialVersionUID = 1012463140749529805L;

	/** 缓存名称 */
	public static final String CACHE_NAME = "config";

	/** 分隔符 */
	private static final String SEPARATOR = ",";
	
	/** 网站名称 */
	private String siteName;
	
	/** 网站网址 */
	private String siteUrl;
	
	/** logo */
	private String logo;
	
	/** 标题 */
	private String title;
	
	/** 关键词 */
	private String keywords;
	
	/** 描述 */
	private String description;
	
	/** 是否水印开启 */
	private Boolean isWatermarkEnabled;
	
	/** 水印透明度 */
	private Integer watermarkAlpha;
	
	/** 水印图片 */
	private String watermarkImage;
	
	/** 水印位置 */
	private String watermarkPosition;
	
	/** 栏目分页URL */
	private String categoryPageUrl;
	
	/** 栏目URL */
	private String categoryUrl;
	
	/** 内容URL */
	private String contentUrl;
	
	/** 网站模式 */
	private String siteModel;
	
	/** Cookie路径 */
	private String cookiePath;

	/** Cookie作用域 */
	private String cookieDomain;
	
	/** 上传文件最大限制 */
	private Integer uploadMaxSize;

	/** 允许上传图片扩展名 */
	private String uploadImageExtension;

	/** 允许上传媒体扩展名 */
	private String uploadMediaExtension;

	/** 允许上传文件扩展名 */
	private String uploadFileExtension;

	/** 图片上传路径 */
	private String imageUploadPath;

	/** 媒体上传路径 */
	private String mediaUploadPath;

	/** 文件上传路径 */
	private String fileUploadPath;
	
	/** 主题 */
	private String theme;
	
	/** 是否定时任务开启 */
	private Boolean isCronEnabled;
	
	
	/**
	 * 获取网站名称
	 * 
	 * @return 网站名称
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * 设置网站名称
	 * 
	 * @param siteName
	 *            网站名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * 获取网站网址
	 * 
	 * @return 网站网址
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * 设置网站网址
	 * 
	 * @param siteUrl
	 *            网站网址
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *            logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取关键词
	 * 
	 * @return 关键词
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键词
	 * 
	 * @param keywords
	 *            关键词
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取是否水印开启
	 * 
	 * @return 是否水印开启
	 */
	public Boolean getIsWatermarkEnabled() {
		return isWatermarkEnabled;
	}

	/**
	 * 设置是否水印开启
	 * 
	 * @param isWatermarkEnabled
	 *            是否水印开启
	 */
	public void setIsWatermarkEnabled(Boolean isWatermarkEnabled) {
		this.isWatermarkEnabled = isWatermarkEnabled;
	}

	/**
	 * 获取水印透明度
	 * 
	 * @return 水印透明度
	 */
	public Integer getWatermarkAlpha() {
		return watermarkAlpha;
	}
	
	/**
	 * 设置水印透明度
	 * 
	 * @param watermarkAlpha
	 *            水印透明度
	 */
	public void setWatermarkAlpha(Integer watermarkAlpha) {
		this.watermarkAlpha = watermarkAlpha;
	}

	/**
	 * 获取水印图片
	 * 
	 * @return 水印图片
	 */
	public String getWatermarkImage() {
		return watermarkImage;
	}

	/**
	 * 设置水印图片
	 * 
	 * @param watermarkImage
	 *            水印图片
	 */
	public void setWatermarkImage(String watermarkImage) {
		this.watermarkImage = watermarkImage;
	}

	/**
	 * 获取水印位置
	 * 
	 * @return 水印位置
	 */
	public String getWatermarkPosition() {
		return watermarkPosition;
	}

	/**
	 * 设置水印位置
	 * 
	 * @param watermarkPosition
	 *            水印位置
	 */
	public void setWatermarkPosition(String watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	/**
	 * 获取栏目分页URL
	 * 
	 * @return 栏目分页URL
	 */
	public String getCategoryPageUrl() {
		return categoryPageUrl;
	}

	/**
	 * 设置栏目分页URL
	 * 
	 * @param categoryPageUrl
	 *            栏目分页URL
	 */
	public void setCategoryPageUrl(String categoryPageUrl) {
		this.categoryPageUrl = categoryPageUrl;
	}

	/**
	 * 获取栏目URL
	 * 
	 * @return 栏目URL
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}

	/**
	 * 设置栏目URL
	 * 
	 * @param categoryUrl
	 *            栏目URL
	 */
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	/**
	 * 获取内容URL
	 * 
	 * @return 内容URL
	 */
	public String getContentUrl() {
		return contentUrl;
	}

	/**
	 * 设置内容URL
	 * 
	 * @param contentUrl
	 *            内容URL
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	/**
	 * 获取网站模式
	 * 
	 * @return 网站模式
	 */
	public String getSiteModel() {
		return siteModel;
	}

	/**
	 * 设置网站模式
	 * 
	 * @param siteModel
	 *            网站模式
	 */
	public void setSiteModel(String siteModel) {
		this.siteModel = siteModel;
	}

	/**
	 * 获取Cookie路径
	 * 
	 * @return Cookie路径
	 */
	
	
	public String getCookiePath() {
		return cookiePath;
	}

	/**
	 * 设置Cookie路径
	 * 
	 * @param cookiePath
	 *            Cookie路径
	 */
	public void setCookiePath(String cookiePath) {
		if (cookiePath != null && !cookiePath.endsWith("/")) {
			cookiePath += "/";
		}
		this.cookiePath = cookiePath;
	}

	/**
	 * 获取Cookie作用域
	 * 
	 * @return Cookie作用域
	 */
	
	public String getCookieDomain() {
		return cookieDomain;
	}

	/**
	 * 设置Cookie作用域
	 * 
	 * @param cookieDomain
	 *            Cookie作用域
	 */
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	/**
	 * 获取上传文件最大限制
	 * 
	 * @return 上传文件最大限制
	 */
	
	
	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}

	/**
	 * 设置上传文件最大限制
	 * 
	 * @param uploadMaxSize
	 *            上传文件最大限制
	 */
	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	
	public String getUploadImageExtension() {
		return uploadImageExtension;
	}

	/**
	 * 设置允许上传图片扩展名
	 * 
	 * @param uploadImageExtension
	 *            允许上传图片扩展名
	 */
	public void setUploadImageExtension(String uploadImageExtension) {
		if (uploadImageExtension != null) {
			uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadImageExtension = uploadImageExtension;
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	
	public String getUploadMediaExtension() {
		return uploadMediaExtension;
	}

	/**
	 * 设置允许上传媒体扩展名
	 * 
	 * @param uploadMediaExtension
	 *            允许上传媒体扩展名
	 */
	public void setUploadMediaExtension(String uploadMediaExtension) {
		if (uploadMediaExtension != null) {
			uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadMediaExtension = uploadMediaExtension;
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	
	public String getUploadFileExtension() {
		return uploadFileExtension;
	}

	/**
	 * 设置允许上传文件扩展名
	 * 
	 * @param uploadFileExtension
	 *            允许上传文件扩展名
	 */
	public void setUploadFileExtension(String uploadFileExtension) {
		if (uploadFileExtension != null) {
			uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadFileExtension = uploadFileExtension;
	}

	/**
	 * 获取图片上传路径
	 * 
	 * @return 图片上传路径
	 */
	
	
	public String getImageUploadPath() {
		return imageUploadPath;
	}

	/**
	 * 设置图片上传路径
	 * 
	 * @param imageUploadPath
	 *            图片上传路径
	 */
	public void setImageUploadPath(String imageUploadPath) {
		if (imageUploadPath != null) {
			if (!imageUploadPath.startsWith("/")) {
				imageUploadPath = "/" + imageUploadPath;
			}
			if (!imageUploadPath.endsWith("/")) {
				imageUploadPath += "/";
			}
		}
		this.imageUploadPath = imageUploadPath;
	}

	/**
	 * 获取媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */
	
	
	public String getMediaUploadPath() {
		return mediaUploadPath;
	}

	/**
	 * 设置媒体上传路径
	 * 
	 * @param mediaUploadPath
	 *            媒体上传路径
	 */
	public void setMediaUploadPath(String mediaUploadPath) {
		if (mediaUploadPath != null) {
			if (!mediaUploadPath.startsWith("/")) {
				mediaUploadPath = "/" + mediaUploadPath;
			}
			if (!mediaUploadPath.endsWith("/")) {
				mediaUploadPath += "/";
			}
		}
		this.mediaUploadPath = mediaUploadPath;
	}

	/**
	 * 获取文件上传路径
	 * 
	 * @return 文件上传路径
	 */
	
	
	public String getFileUploadPath() {
		return fileUploadPath;
	}

	/**
	 * 设置文件上传路径
	 * 
	 * @param fileUploadPath
	 *            文件上传路径
	 */
	public void setFileUploadPath(String fileUploadPath) {
		if (fileUploadPath != null) {
			if (!fileUploadPath.startsWith("/")) {
				fileUploadPath = "/" + fileUploadPath;
			}
			if (!fileUploadPath.endsWith("/")) {
				fileUploadPath += "/";
			}
		}
		this.fileUploadPath = fileUploadPath;
	}
	
	/**
	 * 获取主题
	 * 
	 * @return 主题
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * 设置主题
	 * 
	 * @param theme
	 *            主题
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * 获取是否定时任务开启
	 * 
	 * @return 是否定时任务开启
	 */
	public Boolean getIsCronEnabled() {
		return isCronEnabled;
	}

	/**
	 * 设置是否定时任务开启
	 * 
	 * @param isCron
	 *            是否定时任务开启
	 */
	public void setIsCronEnabled(Boolean isCronEnabled) {
		this.isCronEnabled = isCronEnabled;
	}
	
	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String[] getUploadMediaExtensions() {
		return StringUtils.split(uploadMediaExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}
}
