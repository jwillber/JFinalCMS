/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.Feedback;
import com.cms.entity.Admin;
import com.jfinal.core.Controller;

/**
 * Controller - 基类
 * 
 * 
 * 
 */
public class BaseController extends Controller{

	/** 每页记录数 */
	protected static final int PAGE_SIZE = 20;
	
	/** 列表查询Cookie名称 */
	private static final String LIST_QUERY_COOKIE_NAME = "listQuery";
	
	/** 错误消息 */
	protected static final Feedback ERROR_FEEDBACK = Feedback.error("错误");

	/** 成功消息 */
	protected static final Feedback SUCCESS_FEEDBACK = Feedback.success("成功");

	/**
	 * 获取当前管理员
	 * 
	 * @return 当前管理员
	 */
	protected Admin getCurrentAdmin() {
		Admin currentAdmin = (Admin) getSession().getAttribute(Admin.SESSION_ADMIN);
		return currentAdmin;
	}
	
    /**
     * 获取页面
     * 
     * @return 页面
     */
    public String getView(String view){
        return CommonAttribute.ADMIN_PATH+view+CommonAttribute.VIEW_EXTENSION;
    }
	
	/**
	 * 获取列表参数
	 * 
	 * @return 列表参数
	 */
	public String getListQuery(String url){
		String listQuery = getCookie(LIST_QUERY_COOKIE_NAME);
		if(StringUtils.isNotBlank(url) && StringUtils.isNotEmpty(listQuery)){
			if (StringUtils.startsWith(listQuery, "?")) {
				listQuery = listQuery.substring(1);
			}
			if (StringUtils.contains(url, "?")) {
				url = url + "&" + listQuery;
			} else {
				url = url + "?" + listQuery;
			}
			removeCookie(LIST_QUERY_COOKIE_NAME);
		}
		return url;
	}
}