/*
 * Copyright 2005-2015 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.cms.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

import com.cms.CommonAttribute;
import com.cms.Config;
import com.cms.entity.StoragePlugin;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import freemarker.template.TemplateException;

/**
 * Service - 文件
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public class StorageUtils{

	/**
	 * 添加文件上传任务
	 * 
	 * @param storagePlugin
	 *            存储插件
	 * @param path
	 *            上传路径
	 * @param file
	 *            上传文件
	 * @param contentType
	 *            文件类型
	 */
	private static void addUploadTask(final StoragePlugin storagePlugin, final String path, final File file, final String contentType) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				upload(storagePlugin, path, file, contentType);
			}
		});
		thread.start();
	}

	/**
	 * 上传文件
	 * 
	 * @param storagePlugin
	 *            存储插件
	 * @param path
	 *            上传路径
	 * @param file
	 *            上传文件
	 * @param contentType
	 *            文件类型
	 */
	private static void upload(StoragePlugin storagePlugin, String path, File file, String contentType) {

		try {
			storagePlugin.upload(path, file, contentType);
		} finally {
			FileUtils.deleteQuietly(file);
		}
	}

	public boolean isValid(String fileType, UploadFile uploadFile) {

		Config config = SystemUtils.getConfig();
		if (config.getUploadMaxSize() != null && config.getUploadMaxSize() != 0 && uploadFile.getFile().length() > config.getUploadMaxSize() * 1024L * 1024L) {
			return false;
		}
		String[] uploadExtensions;
		switch (fileType) {
		case CommonAttribute.FILE_TYPE_MEDIA:
			uploadExtensions = config.getUploadMediaExtensions();
			break;
		case CommonAttribute.FILE_TYPE_FILE:
			uploadExtensions = config.getUploadFileExtensions();
			break;
		default:
			uploadExtensions = config.getUploadImageExtensions();
			break;
		}
		if (ArrayUtils.isNotEmpty(uploadExtensions)) {
			return FilenameUtils.isExtension(uploadFile.getOriginalFileName(), uploadExtensions);
		}
		return false;
	}

	public static String upload(String fileType, UploadFile uploadFile, boolean async) {

		Config config = SystemUtils.getConfig();
		String uploadPath;
		switch (fileType) {
		case CommonAttribute.FILE_TYPE_MEDIA:
			uploadPath = config.getMediaUploadPath();
			break;
		case CommonAttribute.FILE_TYPE_FILE:
			uploadPath = config.getFileUploadPath();
			break;
		default:
			uploadPath = config.getImageUploadPath();
			break;
		}
		try {
			String path = FreeMarkerUtils.process(uploadPath, null);
			String destPath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(uploadFile.getOriginalFileName());
			for (StoragePlugin storagePlugin : new StoragePlugin().dao().findList(true)) {
				File tempFile = new File(FileUtils.getTempDirectory(), UUID.randomUUID() + ".tmp");
				FileUtils.copyFile(uploadFile.getFile(), tempFile);
				if(SystemUtils.getConfig().getIsWatermarkEnabled()){
					File watermarkFile = new File(PathKit.getWebRootPath()+SystemUtils.getConfig().getWatermarkImage());
					ImageUtils.addWatermark(tempFile, tempFile, watermarkFile, SystemUtils.getConfig().getWatermarkPosition(), SystemUtils.getConfig().getWatermarkAlpha());
				}
				String contentType = uploadFile.getContentType();
				if (async) {
					addUploadTask(storagePlugin, destPath, tempFile, contentType);
				} else {
					upload(storagePlugin, destPath, tempFile, contentType);
				}
				return storagePlugin.getUrl(destPath);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return null;
	}

	public static String upload(String fileType, UploadFile uploadFile) {

		return upload(fileType, uploadFile, true);
	}
}