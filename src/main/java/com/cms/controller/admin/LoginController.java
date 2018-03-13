/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.Feedback;
import com.cms.entity.Admin;
import com.cms.routes.RouteMapping;

/**
 * Controller - 管理员登录
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/login")
public class LoginController extends BaseController {

    /**
     * 登录
     */
    public void index() {
        String username = getPara("username");
        String password = getPara("password");
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            Admin admin = new Admin().dao().findByUsername(username);
            if(admin==null){
                setAttr("feedback", Feedback.error("用户名不存在!"));
            }else if(!admin.getPassword().equals(DigestUtils.md5Hex(password))){
                setAttr("feedback", Feedback.error("用户名密码错误!"));
            }else if(!admin.getIsEnabled()){
                setAttr("feedback", Feedback.error("账户被禁用!"));
            }else{
                getSession().setAttribute(Admin.SESSION_ADMIN, admin);
                redirect("/admin/common/main");
                return;
            }
        }
        render(getView("login/index"));
    }
	

}