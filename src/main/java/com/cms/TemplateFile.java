package com.cms;

/**
 * 模板文件
 * 
 * 
 * 
 */
public class TemplateFile {

	/** 名称 */
	private String name;
	
	/** 大小 */
	private String size;
	
	/** 修改日期 */
	private String modifyDate;
	
	/** 类型 */
	private String type;
	
	/** 是否是目录 */
	private Boolean isDirectory;

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取大小
	 * 
	 * @return 大小
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 设置大小
	 * 
	 * @param size
	 *            大小
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 获取修改日期
	 * 
	 * @return 修改日期
	 */
	public String getModifyDate() {
		return modifyDate;
	}

	/**
	 * 设置修改日期
	 * 
	 * @param modifyDate
	 *            修改日期
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取是否是目录
	 * 
	 * @return 是否是目录
	 */
	public Boolean getIsDirectory() {
		return isDirectory;
	}

	/**
	 * 设置是否是目录
	 * 
	 * @param isDirectory
	 *            是否是目录
	 */
	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
}
