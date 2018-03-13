/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;


import com.cms.routes.RouteMapping;
import com.cms.util.CacheUtils;


/**
 * Controller - 缓存
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/cache")

public class CacheController extends BaseController {

	/**
	 * 缓存查看
	 */
    public void view() {
		Long totalMemory = null;
		Long maxMemory = null;
		Long freeMemory = null;
		try {
			totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
			maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
			freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		} catch (Exception e) {
		}
		setAttr("totalMemory", totalMemory);
		setAttr("maxMemory", maxMemory);
		setAttr("freeMemory", freeMemory);
		setAttr("cacheSize", CacheUtils.getCacheSize());
		setAttr("diskStorePath", CacheUtils.getDiskStorePath());
		render(getView("cache/view"));
	}

	/**
	 * 清除缓存
	 */
    public void delete() {
		CacheUtils.clear();
		redirect("/admin/cache/view");
	}

}