/*
 * 
 * 
 * 
 */
package com.cms.controller.front;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.util.SystemUtils;
import com.jfinal.core.Controller;

/**
 * Controller - 基类
 * 
 * 
 * 
 */
public class BaseController extends Controller{
	
	/** 错误消息 */
	protected static final Feedback ERROR_FEEDBACK = Feedback.error("错误");

	/** 成功消息 */
	protected static final Feedback SUCCESS_FEEDBACK = Feedback.success("成功");

	/**
	 * 获取主题
	 * 
	 * @return 主题
	 */
	public String getTheme(){
		return SystemUtils.getConfig().getTheme();
	}
	
	/**
	 * 获取BigDecimal数据
	 * 
	 * @param name
	 * 			名称
	 * @return BigDecimal数据
	 */
	public BigDecimal getParaToBigDecimal(String name){
		String value = getPara(name);
		if(StringUtils.isNotBlank(value)){
			return new BigDecimal(value);
		}
		return null;
	}

}