/*
 * 
 * 
 * 
 */
package com.cms.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cms.TemplateVariable;
import com.cms.entity.Form;
import com.cms.util.FreeMarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 内容列表
 * 
 * 
 * 
 */
@TemplateVariable(name="form_list")
public class FormListDirective extends BaseDirective {

	/** "栏目ID"参数名称 */
	private static final String FORM_MODEL_ID_PARAMETER_NAME = "formModelId";
	
	/** "栏目ID"参数名称 */
	private static final String CONTENT_ID_PARAMETER_NAME = "contentId";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "forms";

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
		Long formModelId = FreeMarkerUtils.getParameter(FORM_MODEL_ID_PARAMETER_NAME, Long.class, params);
		Long contentId = FreeMarkerUtils.getParameter(CONTENT_ID_PARAMETER_NAME, Long.class, params);
		Integer count = getCount(params);
		String orderBy = getOrderBy(params);
		List<Form> forms = new Form().dao().findList(formModelId,contentId, count, orderBy);
		setLocalVariable(VARIABLE_NAME, forms, env, body);
	}

}