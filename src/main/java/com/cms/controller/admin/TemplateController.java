/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;

import com.cms.routes.RouteMapping;
import com.cms.util.SystemUtils;
import com.cms.util.TemplateUtils;

import com.jfinal.render.FreeMarkerRender;

/**
 * Controller - 模板
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/template")

public class TemplateController extends BaseController {

	/**
	 * 查看
	 */
	public void view() {
		String fileName = getPara("fileName");
		String directory = getPara("directory");
		if (StringUtils.isBlank(fileName)) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		redirect("/templates/"+SystemUtils.getConfig().getTheme()+"/"+directory.replaceAll(",", "/")+"/"+fileName);
	}
	
	/**
	 * 编辑
	 */
	public void edit() {
		String fileName = getPara("fileName");
		String directory = getPara("directory");
		if (StringUtils.isBlank(fileName)) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		setAttr("directory", directory);
		setAttr("fileName", fileName);
		setAttr("content", TemplateUtils.read(SystemUtils.getConfig().getTheme()+"/"+directory.replaceAll(",", "/")+"/"+fileName));
		render(getView("template/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		String fileName = getPara("fileName");
		String directory = getPara("directory");
		String content = getPara("content");
		if (StringUtils.isBlank(fileName) || content == null) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		TemplateUtils.write(SystemUtils.getConfig().getTheme()+"/"+directory.replaceAll(",", "/")+"/"+fileName, content);
		FreeMarkerRender.getConfiguration().clearTemplateCache();
		redirect(getListQuery("/admin/template/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
		String directory = getPara("directory");
		if(StringUtils.isNotBlank(directory)){
			setAttr("templateFiles", TemplateUtils.getTemplateFiles(SystemUtils.getConfig().getTheme()+"/"+directory.replaceAll(",", "/")));
			setAttr("directory", directory);
		}else{
			setAttr("templateFiles", TemplateUtils.getTemplateFiles(SystemUtils.getConfig().getTheme()));
		}
		render(getView("template/list"));
	}

}