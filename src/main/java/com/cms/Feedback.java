/*
 * 
 * 
 * 
 */
package com.cms;

/**
 * 消息
 * 
 * 
 * 
 */
public class Feedback {


	/** 类型 */
	private String type;

	/** 内容 */
	private String content;
	
	/**
	 * 构造方法
	 */
	public Feedback() {
	}

	/**
	 * 构造方法
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public Feedback(String type, String content) {
		this.type = type;
		this.content = content;
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param data
	 *            数据
	 * @return 成功消息
	 */
	public static Feedback success(String content) {
		return new Feedback(CommonAttribute.FEEDBACK_TYPE_SUCCESS, content);
	}

	/**
	 * 返回警告消息
	 * 
	 * @param content
	 *            内容
	 * @return 警告消息
	 */
	public static Feedback warn(String content) {
		return new Feedback(CommonAttribute.FEEDBACK_TYPE_WARN, content);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @return 错误消息
	 */
	public static Feedback error(String content) {
		return new Feedback(CommonAttribute.FEEDBACK_TYPE_ERROR, content);
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
	 * 重写toString方法
	 * 
	 * @return 字符串
	 */
	@Override
	public String toString() {
		return content;
	}

}