/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.entity.Admin;

import com.cms.routes.RouteMapping;


/**
 * Controller - 个人资料
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/profile")

public class ProfileController extends BaseController {


	/**
	 * 验证当前密码
	 */
	public void checkCurrentPassword() {
	    String currentPassword = getPara("currentPassword");
		if (StringUtils.isEmpty(currentPassword)) {
			renderJson(false);
			return;
		}
		Admin admin = getCurrentAdmin();
		renderJson(StringUtils.equals(DigestUtils.md5Hex(currentPassword), admin.getPassword()));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		setAttr("admin", getCurrentAdmin());
		render(getView("profile/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		String currentPassword = getPara("currentPassword");
		String password = getPara("password");
		Admin pAdmin = getCurrentAdmin();
		if (StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(password)) {
			if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), pAdmin.getPassword())) {
				render(CommonAttribute.ADMIN_ERROR_VIEW);
				return;
			}
			pAdmin.setPassword(DigestUtils.md5Hex(password));
		}
		pAdmin.setModifyDate(new Date());
		pAdmin.update();
		redirect("/admin/profile/edit");
	}

}