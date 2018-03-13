/*
 * 
 * 
 * 
 */
package com.cms.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.cms.TemplateFile;
import com.jfinal.kit.PathKit;

import freemarker.template.TemplateException;

/**
 * Utils - 模板
 * 
 * 
 * 
 */
public class TemplateUtils {

	
	/**
	 * 获取模板文件
	 * 
	 * @return 模板文件
	 */
	public static List<TemplateFile> getTemplateFiles(String directory){
		String templatePath = PathKit.getWebRootPath()+"/templates/"+directory;
		List<TemplateFile> templateFiles=new ArrayList<TemplateFile>();
		File file =new File(templatePath);
		if(!file.exists()){
			file.mkdirs();
		}
		File [] files=file.listFiles();
		for(int i=0;i<files.length;i++){
			TemplateFile templateFile=new TemplateFile();
			templateFile.setName(files[i].getName());
			templateFile.setSize(files[i].length()/ 1024 +"KB");
			templateFile.setModifyDate(DateFormatUtils.format(files[i].lastModified(), "yyyy-MM-dd HH:mm:ss"));
			if(files[i].isDirectory()){
				templateFile.setIsDirectory(true);
			}else{
				templateFile.setIsDirectory(false);
				templateFile.setType(FilenameUtils.getExtension(files[i].getName()));
			}
			templateFiles.add(templateFile);
		}
		return templateFiles;
	}
	
	/**
	 * 读取模板文件内容
	 * 
	 * @param templatePath
	 *            模板路径
	 * @return 模板文件内容
	 */
	public static String read(String templatePath) {
		try {
			String path = PathKit.getWebRootPath()+"/templates/"+templatePath;
			File templateFile = new File(path);
			return FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} 
	}

	/**
	 * 写入模板文件内容
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param content
	 *            模板文件内容
	 */
	public static void write(String templatePath, String content) {
		try {
			String path = PathKit.getWebRootPath()+"/templates/"+templatePath;
			File file = new File(path);
			FileUtils.writeStringToFile(file, content, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} 
	}
	
	/**
	 * 获取静态文件路径
	 * 
	 * @param staticPath
	 *            静态路径
	 * @param model
	 *            数据
	 * @return 静态文件路径
	 */
	public static String getStaticPath(String staticPath,Map<String, Object> model) {
		try {
			return FreeMarkerUtils.process(staticPath, model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 获取模板文件路径
	 * 
	 * @param templatePath
	 * 			  模板路径
	 * @param model
	 *            数据
	 * @return 模板文件路径
	 */
	public static String getTemplatePath(String templatePath,Map<String, Object> model) {
		try {
			return FreeMarkerUtils.process(templatePath, model);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 获取首页静态文件路径
	 * 
	 * @return 首页静态文件路径
	 */
	public static String getIndexStaticPath(){
		return getStaticPath("/index.html",null);
	}

	/**
	 * 获取首页模板文件路径
	 * 
	 * @return index模板文件路径
	 */
	public static String getIndexTemplatePath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("theme", SystemUtils.getConfig().getTheme());
		return getTemplatePath("/templates/${theme}/front/index.html", model);
	}
	
	/**
	 * 获取sitemap静态文件路径
	 * 
	 * @param model
	 *            数据
	 * @return sitemap静态文件路径
	 */
	public static String getSitemapStaticPath(){
		return getStaticPath("/sitemap.xml",null);
	}

	/**
	 * 获取sitemap模板文件路径
	 * 
	 * @return sitemap模板文件路径
	 */
	public static String getSitemapTemplatePath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("theme", SystemUtils.getConfig().getTheme());
		return getTemplatePath("/templates/${theme}/front/sitemap.html", model);
	}
}