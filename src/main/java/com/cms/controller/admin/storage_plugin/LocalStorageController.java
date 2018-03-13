/*
 * 
 * 
 * 
 */
package com.cms.controller.admin.storage_plugin;

import com.cms.controller.admin.BaseController;
import com.cms.entity.StoragePlugin;

import com.cms.routes.RouteMapping;


/**
 * Controller - 本地文件存储
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/storage_plugin/local_storage")

public class LocalStorageController extends BaseController {

	/**
	 * 设置
	 */
	public void setting() {
		String id = getPara("id");
		StoragePlugin storagePlugin = new StoragePlugin().dao().findById(id);
		setAttr("storagePlugin", storagePlugin);
		render(getView("storage_plugin/local_storage/setting"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Integer sort = getParaToInt("sort");
		StoragePlugin storagePlugin = getModel(StoragePlugin.class,"",true); 
		if(storagePlugin.getIsEnabled()==null){
            storagePlugin.setIsEnabled(false);
        }
		storagePlugin.setSort(sort);
		storagePlugin.update();
		redirect(getListQuery("/admin/storage_plugin/list"));
	}

}