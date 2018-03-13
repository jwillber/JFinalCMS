package com.cms.entity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.cms.entity.base.BaseStoragePlugin;
import com.cms.util.SystemUtils;
import com.jfinal.kit.PathKit;

/**
 * Entity - 存储插件
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class StoragePlugin extends BaseStoragePlugin<StoragePlugin> {
	
	/**
	 * 查找所有存储插件
	 * 
	 * @return 所有存储插件
	 */
	public List<StoragePlugin> findAll(){
		return find("select * from kf_storage_plugin");
	}
	
	/**
	 * 查找存储插件
	 * 
	 * @param isEnabled
	 *            是否启用
	 * @return 存储插件
	 */
	public List<StoragePlugin> findList(Boolean isEnabled){
		String isEnabledSql="";
		if(isEnabled!=null){
			isEnabledSql = " and isEnabled = "+isEnabled;
		}
		return find("select * from kf_storage_plugin where 1=1 "+isEnabledSql);
	}
	
	/**
	 * 根据key查找存储插件
	 * 
	 * @param key
	 *          key
	 * @return	存储插件
	 */
	public StoragePlugin findByKey(String key){
		return findFirst("select * from kf_storage_plugin where pluginKey=?",key);
	}
	
	/**
	 * 获取属性
	 * 
	 * @return 属性
	 */
	@SuppressWarnings("unchecked")
    public Map<String, String> getAttributes() {
		return JSONObject.parseObject(getAttribute(),Map.class);
	}
	
	/**
	 * 获取属性值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性值
	 */
	public String getAttribute(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		return getAttributes() != null ? getAttributes().get(name) : null;
	}
	
	/**
	 * 获取设置URL
	 * 
	 * @return 设置URL
	 */
	public String getSettingUrl() {
		if("localStoragePlugin".equals(getId())){
			return "local_storage/setting";
		}else if("ftpStoragePlugin".equals(getId())){
			return "ftp_storage/setting";
		}else if("ossStoragePlugin".equals(getId())){
			return "oss_storage/setting";
		}
		return null;
	}

	/**
	 * 文件上传
	 * 
	 * @param path
	 *            上传路径
	 * @param file
	 *            上传文件
	 * @param contentType
	 *            文件类型
	 */
	public void upload(String path, File file, String contentType) {
		if("localStoragePlugin".equals(getId())){
			File destFile = new File(PathKit.getWebRootPath()+path);
			try {
				FileUtils.moveFile(file, destFile);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}else if("ftpStoragePlugin".equals(getId())){
			String host = getAttribute("host");
			Integer port = Integer.valueOf(getAttribute("port"));
			String username = getAttribute("username");
			String password = getAttribute("password");
			FTPClient ftpClient = new FTPClient();
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				ftpClient.connect(host, port);
				ftpClient.login(username, password);
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
					String directory = StringUtils.substringBeforeLast(path, "/");
					String filename = StringUtils.substringAfterLast(path, "/");
					if (!ftpClient.changeWorkingDirectory(directory)) {
						String[] paths = StringUtils.split(directory, "/");
						String p = "/";
						ftpClient.changeWorkingDirectory(p);
						for (String s : paths) {
							p += s + "/";
							if (!ftpClient.changeWorkingDirectory(p)) {
								ftpClient.makeDirectory(s);
								ftpClient.changeWorkingDirectory(p);
							}
						}
					}
					ftpClient.storeFile(filename, inputStream);
					ftpClient.logout();
				}
			} catch (SocketException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(inputStream);
				try {
					if (ftpClient.isConnected()) {
						ftpClient.disconnect();
					}
				} catch (IOException e) {
				}
			}
		}else if("ossStoragePlugin".equals(getId())){
			String endpoint = getAttribute("endpoint");
			String accessId = getAttribute("accessId");
			String accessKey = getAttribute("accessKey");
			String bucketName = getAttribute("bucketName");
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.length());
				ossClient.putObject(bucketName, StringUtils.removeStart(path, "/"), inputStream, objectMetadata);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}

	/**
	 * 获取访问URL
	 * 
	 * @param path
	 *            上传路径
	 * @return 访问URL
	 */
	public String getUrl(String path) {
		if("localStoragePlugin".equals(getId())){
			return SystemUtils.getConfig().getSiteUrl() + path;
		}else if("ftpStoragePlugin".equals(getId())){
			String urlPrefix = getAttribute("urlPrefix");
			return urlPrefix + path;
		}else if("ossStoragePlugin".equals(getId())){
			String urlPrefix = getAttribute("urlPrefix");
			return urlPrefix + path;
		}
		return null;
	}
}
