package com.cms.controller.admin;

import com.cms.entity.Admin;
import com.cms.routes.RouteMapping;

/**
 * Controller - 管理员退出
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/logout")
public class LogoutController extends BaseController{

	/**
	 * 退出
	 */
	public void index(){
		getSession().removeAttribute(Admin.SESSION_ADMIN);
		redirect("/admin/login");
	}
}
