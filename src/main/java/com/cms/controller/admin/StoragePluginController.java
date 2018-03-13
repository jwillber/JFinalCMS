/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import com.cms.entity.StoragePlugin;

import com.cms.routes.RouteMapping;


/**
 * Controller - 存储插件
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/storage_plugin")

public class StoragePluginController extends BaseController {

	/**
	 * 列表
	 */
	public void list() {
		setAttr("storagePlugins", new StoragePlugin().dao().findAll());
		render(getView("storage_plugin/list"));
	}

}