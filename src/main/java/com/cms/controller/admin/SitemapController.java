/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;


import com.cms.routes.RouteMapping;
import com.cms.util.StaticUtils;
import com.cms.util.TemplateUtils;


/**
 * Controller - Sitemap
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/sitemap")

public class SitemapController extends BaseController {

	/**
	 * 生成Sitemap
	 */
	public void generate() {
		setAttr("sitemapPath", TemplateUtils.getSitemapStaticPath());
		render(getView("sitemap/generate"));
	}

	/**
	 * 生成Sitemap
	 */
	public void generateSubmit() {
		StaticUtils.generateSitemap();
		redirect("/admin/sitemap/generate");
	}

}