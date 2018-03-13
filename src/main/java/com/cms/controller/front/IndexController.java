package com.cms.controller.front;

import com.cms.CommonAttribute;
import com.cms.routes.RouteMapping;
import com.cms.util.SystemUtils;

/**
 * Controller - 首页
 * 
 * 
 * 
 */
@RouteMapping(url = "/")
public class IndexController extends BaseController {
	
	/**
	 * 首页
	 */
	public void index() {
		if(CommonAttribute.CONFIG_SITE_MODEL_DYNAMIC.equals(SystemUtils.getConfig().getSiteModel())){
			render("/templates/"+getTheme()+"/front/index.html");
		}else if(CommonAttribute.CONFIG_SITE_MODEL_STATIC.equals(SystemUtils.getConfig().getSiteModel())){
			render("index.html");
		}
	}
}





