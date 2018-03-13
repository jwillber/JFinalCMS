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
import com.cms.entity.Content;
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
@TemplateVariable(name="content_list")
public class ContentListDirective extends BaseDirective {

	/** "栏目ID"参数名称 */
	private static final String CATEGORY_ID_PARAMETER_NAME = "categoryId";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "contents";

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
		Long categoryId = FreeMarkerUtils.getParameter(CATEGORY_ID_PARAMETER_NAME, Long.class, params);
		Integer count = getCount(params);
		String orderBy = getOrderBy(params);
		List<Content> contents = new Content().dao().findList(categoryId, count, orderBy);
		setLocalVariable(VARIABLE_NAME, contents, env, body);
	}

}