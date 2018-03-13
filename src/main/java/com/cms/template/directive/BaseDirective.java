/*
 * 
 * 
 * 
 */
package com.cms.template.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cms.util.FreeMarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 模板指令 - 基类
 * 
 * 
 * 
 */
public abstract class BaseDirective implements TemplateDirectiveModel {

	/** "数量"参数名称 */
	private static final String COUNT_PARAMETER_NAME = "count";

	/** "排序"参数名称 */
	private static final String ORDER_BY_PARAMETER_NAME = "orderBy";
	

	/**
	 * 获取数量
	 * 
	 * @param params
	 *            参数
	 * @return 数量
	 */
	protected Integer getCount(Map<String, TemplateModel> params) throws TemplateModelException {
		return FreeMarkerUtils.getParameter(COUNT_PARAMETER_NAME,Integer.class, params);
	}
	
	/**
	 * 获取排序
	 * 
	 * @param params
	 *            参数
	 * @return 数量
	 */
	protected String getOrderBy(Map<String, TemplateModel> params) throws TemplateModelException {
		return FreeMarkerUtils.getParameter(ORDER_BY_PARAMETER_NAME,String.class, params);
	}
	

	/**
	 * 设置局部变量
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            变量值
	 * @param env
	 *            环境变量
	 * @param body
	 *            模板内容
	 */
	protected void setLocalVariable(String name, Object value, Environment env, TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateModel preVariable = FreeMarkerUtils.getVariable(name, env);
		try {
			FreeMarkerUtils.setVariable(name, value, env);
			body.render(env.getOut());
		} finally {
			FreeMarkerUtils.setVariable(name, preVariable, env);
		}
	}

	/**
	 * 设置局部变量
	 * 
	 * @param variables
	 *            变量
	 * @param env
	 *            环境变量
	 * @param body
	 *            模板内容
	 */
	protected void setLocalVariables(Map<String, Object> variables, Environment env, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> preVariables = new HashMap<String, Object>();
		for (String name : variables.keySet()) {
			TemplateModel preVariable = FreeMarkerUtils.getVariable(name, env);
			preVariables.put(name, preVariable);
		}
		try {
			FreeMarkerUtils.setVariables(variables, env);
			body.render(env.getOut());
		} finally {
			FreeMarkerUtils.setVariables(preVariables, env);
		}
	}
}