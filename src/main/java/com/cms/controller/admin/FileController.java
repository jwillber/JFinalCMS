/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.cms.routes.RouteMapping;
import com.cms.util.StorageUtils;

import com.jfinal.upload.UploadFile;

/**
 * Controller - 文件
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/file")

public class FileController extends BaseController {

	/**
	 * 上传
	 */
	public void upload() {
		UploadFile uploadFile = getFile();
		String fileType = getPara("fileType");
		Map<String, Object> data = new HashMap<String, Object>();
		if (fileType == null || uploadFile == null || uploadFile.getFile().length()==0) {
			data.put("message", "操作错误");
			data.put("state", "ERROR");
			renderJson(data);
			return;
		}
		String url = StorageUtils.upload(fileType, uploadFile, false);
		if (StringUtils.isEmpty(url)) {
			data.put("message", "上传文件出现错误");
			data.put("state", "ERROR");
			renderJson(data);
			return;
		}
		data.put("message", "成功");
		data.put("state", "SUCCESS");
		data.put("url", url);
		uploadFile.getFile().delete();
		renderJson(data);
	}
}