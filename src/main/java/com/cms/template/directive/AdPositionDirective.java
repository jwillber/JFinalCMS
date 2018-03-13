/*
 * 
 * 
 * 
 */
package com.cms.template.directive;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.cms.TemplateVariable;
import com.cms.entity.AdPosition;
import com.cms.util.FreeMarkerUtils;
import com.jfinal.render.FreeMarkerRender;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 广告位
 * 
 * 
 * 
 */
@TemplateVariable(name="ad_position")
public class AdPositionDirective extends BaseDirective {

	/** "ID"参数名称 */
	private static final String ID_PARAMETER_NAME = "id";
	
	/** 变量名称 */
	private static final String VARIABLE_NAME = "adPosition";

	/**
	 * 执行
	 * 
	 * @param env
	 *            环境变量
	 * @param params
	 *            参数
	 * @param loopVars
	 *            循环变量
	 * @param body
	 *            模板内容
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long id = FreeMarkerUtils.getParameter(ID_PARAMETER_NAME, Long.class, params);
		AdPosition adPosition = new AdPosition().dao().findById(id);
		setLocalVariable(VARIABLE_NAME, adPosition, env, body);
	}

}