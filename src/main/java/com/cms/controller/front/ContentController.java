/*
 * 
 * 
 * 
 */
package com.cms.controller.front;

import com.cms.entity.Content;
import com.cms.routes.RouteMapping;

/**
 * Controller - 内容
 * 
 * 
 * 
 */
@RouteMapping(url = "/content")
public class ContentController extends BaseController {

	/**
	 * 内容
	 */
	public void index() {
		Long contentId = getParaToLong(0);
		Content content = new Content().dao().findById(contentId);
		setAttr("content", content);
		render("/templates/"+getTheme()+"/front/"+content.getCategory().getContentTemplate());
	}

	/**
	 * 点击数
	 */
	public void hits() {
		Long id = getParaToLong(0);
		if (id == null) {
			renderJson(0L);
			return;
		}
		Content content = new Content().dao().findById(id);
		Long hits = content.getHits();
		hits = hits+1;
		content.setHits(hits);
		content.update();
		renderJson(hits);
	}

}