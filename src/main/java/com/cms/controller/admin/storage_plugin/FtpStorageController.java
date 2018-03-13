/*
 * 
 * 
 * 
 */
package com.cms.controller.admin.storage_plugin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.controller.admin.BaseController;
import com.cms.entity.StoragePlugin;

import com.cms.routes.RouteMapping;


/**
 * Controller - FTP存储
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/storage_plugin/ftp_storage")

public class FtpStorageController extends BaseController {

	/**
	 * 设置
	 */
	public void setting() {
		String id = getPara("id");
		StoragePlugin storagePlugin = new StoragePlugin().dao().findById(id);
		setAttr("storagePlugin", storagePlugin);
		render(getView("storage_plugin/ftp_storage/setting"));
	}

	/**
	 * 更新
	 */
	public void update() {
		String host = getPara("host"); 
		Integer port = getParaToInt("port");  
		String username = getPara("username");  
		String password = getPara("password");  
		String urlPrefix = getPara("urlPrefix");  
		Integer sort = getParaToInt("sort");
		StoragePlugin storagePlugin = getModel(StoragePlugin.class,"",true); 
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("host", host);
		attributes.put("port", String.valueOf(port));
		attributes.put("username", username);
		attributes.put("password", password);
		attributes.put("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
		storagePlugin.setAttribute(JSONObject.toJSONString(attributes));
		if(storagePlugin.getIsEnabled()==null){
		    storagePlugin.setIsEnabled(false);
		}
		storagePlugin.setSort(sort);
		storagePlugin.update();
		redirect(getListQuery("/admin/storage_plugin/list"));
	}

}