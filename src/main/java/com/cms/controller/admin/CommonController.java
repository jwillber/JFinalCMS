/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import com.cms.entity.Category;
import com.cms.entity.FormModel;
import com.cms.routes.RouteMapping;
import com.jfinal.core.JFinal;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/common")

public class CommonController extends BaseController {



	/**
	 * 主页
	 */
	public void main() {
	    render(getView("common/main"));
	}
	
	/**
	 * 栏目菜单
	 */
	public void categoryMenu(){
		setAttr("categoryTree", new Category().dao().findTree());
		render(getView("common/categoryMenu"));
	}
	
	/**
	 * 表单菜单
	 */
	public void formMenu(){
		setAttr("formModels", new FormModel().dao().findAll());
		render(getView("common/formMenu"));
	}

	/**
	 * 首页
	 */
	public void index() {
		setAttr("javaVersion", System.getProperty("java.version"));
		setAttr("javaHome", System.getProperty("java.home"));
		setAttr("osName", System.getProperty("os.name"));
		setAttr("osArch", System.getProperty("os.arch"));
		setAttr("serverInfo", JFinal.me().getServletContext().getServerInfo());
		setAttr("servletVersion", JFinal.me().getServletContext().getMajorVersion() + "." + JFinal.me().getServletContext().getMinorVersion());
		render(getView("common/index"));
	}
}