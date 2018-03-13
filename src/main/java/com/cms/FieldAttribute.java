package com.cms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 字段属性
 * 
 * 
 * 
 */
public class FieldAttribute implements Serializable {

	private static final long serialVersionUID = -1614102154240831735L;

	/** 内容 */
	private String content;
	
	/** 格式 */
	private String format;
	
	/** 默认值 */
	private String defaultValue;

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取格式
	 * 
	 * @return 格式
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 设置格式
	 * 
	 * @param format
	 *            格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * 获取默认值
	 * 
	 * @return 默认值
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 设置默认值
	 * 
	 * @param defaultValue
	 *            默认值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * 获取复选框属性
	 * 
	 * @return 复选框属性
	 */
	@JSONField(serialize = false)
	public List<String[]> getCheckboxAttributes(){
		return getAttributes();
	}
	
	/**
	 * 获取下拉选择框属性
	 * 
	 * @return 下拉选择框属性
	 */
	@JSONField(serialize = false)
	public List<String[]> getSelectAttributes(){
		return getAttributes();
	}
	
	/**
	 * 获取单选按钮属性
	 * 
	 * @return 单选按钮属性
	 */
	@JSONField(serialize = false)
	public List<String[]> getRadioAttributes(){
		return getAttributes();
	}
	
	/**
	 * 获取属性
	 * 
	 * @return 属性
	 */
	@JSONField(serialize = false)
	public List<String[]> getAttributes(){
		List<String[]> attributes = new ArrayList<String[]>();
		if(StringUtils.isNotBlank(getContent())){
			String [] attributeArray = StringUtils.split(getContent(),",");
			if(ArrayUtils.isNotEmpty(attributeArray)){
				for(String attribute : attributeArray){
					attributes.add(StringUtils.split(attribute,"\\|"));
				}
			}
		}
		return attributes;
	}
}
