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
import com.cms.entity.Category;
import com.cms.util.FreeMarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 下级栏目列表
 * 
 * 
 * 
 */
@TemplateVariable(name="category_children_list")
public class CategoryChildrenListDirective extends BaseDirective {
	
	/** "文章分类ID"参数名称 */
	private static final String CATEGORY_ID_PARAMETER_NAME = "categoryId";
	
	/** "是否是菜单"参数名称 */
	private static final String IS_MENU_PARAMETER_NAME = "isMenu";

	/** "是否递归"参数名称 */
	private static final String RECURSIVE_PARAMETER_NAME = "recursive";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "categories";

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
		Boolean isMenu = FreeMarkerUtils.getParameter(IS_MENU_PARAMETER_NAME, Boolean.class, params);
		Boolean recursive = FreeMarkerUtils.getParameter(RECURSIVE_PARAMETER_NAME, Boolean.class, params);
		Integer count = getCount(params);
		List<Category> categories = new Category().dao().findChildren(categoryId,isMenu,true, recursive != null ? recursive : true, count);
		setLocalVariable(VARIABLE_NAME, categories, env, body);
	}

}