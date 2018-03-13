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
 * Controller - 阿里云存储
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/storage_plugin/oss_storage")

public class OssStorageController extends BaseController {


	/**
	 * 设置
	 */
	public void setting() {
		String id = getPara("id");
		StoragePlugin storagePlugin = new StoragePlugin().dao().findById(id);
		setAttr("storagePlugin", storagePlugin);
		render(getView("storage_plugin/oss_storage/setting"));
	}

	/**
	 * 更新
	 */
	public void update() {
		String endpoint = getPara("endpoint");
		String accessId = getPara("accessId"); 
		String accessKey = getPara("accessKey"); 
		String bucketName = getPara("bucketName"); 
		String urlPrefix = getPara("urlPrefix"); 
		Integer sort = getParaToInt("sort");
		StoragePlugin storagePlugin = getModel(StoragePlugin.class,"",true); 
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("endpoint", endpoint);
		attributes.put("accessId", accessId);
		attributes.put("accessKey", accessKey);
		attributes.put("bucketName", bucketName);
		attributes.put("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
		storagePlugin.setAttribute(JSONObject.toJSONString(attributes));
		storagePlugin.setSort(sort);
		storagePlugin.update();
		redirect(getListQuery("/admin/storage_plugin/list"));
	}

}