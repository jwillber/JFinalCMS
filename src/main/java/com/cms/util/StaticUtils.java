package com.cms.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.entity.Category;
import com.cms.entity.Content;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.render.FreeMarkerRender;

import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * Utils - 静态化
 * 
 * 
 * 
 */
public class StaticUtils {

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @param model
	 *            数据
	 * @return 生成数量
	 */
	public static int generate(String templatePath, String staticPath, Map<String, Object> model) {

		Writer writer = null;
		try {
			Template template = FreeMarkerRender.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(PathKit.getWebRootPath()+staticPath);
			File staticDir = staticFile.getParentFile();
			if (staticDir != null) {
				staticDir.mkdirs();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticFile), "UTF-8"));
			template.process(model, writer);
			writer.flush();
			return 1;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * 生成静态
	 * 
	 * @param content
	 *            内容
	 * @return 生成数量
	 */
	public static int generate(Content content) {
		if (content == null || StringUtils.isBlank(content.getTemplatePath())) {
			return 0;
		}
		delete(content);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("content", content);
		return generate(content.getTemplatePath(), content.getStaticPath(), model);
	}
	
	/**
	 * 生成静态
	 * 
	 * @param content
	 *            内容
	 * @return 生成数量
	 */
	public static int generate(Category category) {
		if (category == null || CommonAttribute.CATEGORY_TYPE_LINK.equals(category.getType())){
			return 0;
		}
		if(CommonAttribute.CATEGORY_TYPE_PAGE.equals(category.getType())){
			delete(category);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("currentCategory", category);
			return generate(category.getTemplatePath(), category.getStaticPath(), model);
		}else{
			int pageSize = 20 ; 
			int generateCount = 0;
			if(category.getListPageSize()!=null){
				pageSize = category.getListPageSize();
			}
			Page<Content> page = new Content().dao().findPage(category.getId(), true,null, 1,pageSize);
			int totalPage = page.getTotalPage();
			if(totalPage == 0){
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("currentCategory", category);
				model.put("page", new Content().dao().findPage(category.getId(), true,null, 1,pageSize));
				generateCount += generate(category.getTemplatePath(), category.getStaticListPath(1), model);
			}else{
				for(int i=1;i<=totalPage;i++){
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("currentCategory", category);
					model.put("page", new Content().dao().findPage(category.getId(), true,null, i,pageSize));
					generateCount += generate(category.getTemplatePath(), category.getStaticListPath(i), model);
				}
			}
			return generateCount;
		}
	}

	/**
	 * 生成内容静态
	 * 
	 * @param categoryId
	 *            分类ID
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 生成数量
	 */
	public static int generateCategory() {
		int generateCount = 0;
		List<Category> categoryTree = new Category().dao().findTree();
		for(Category category : categoryTree){
			generateCount += generate(category);
		}
		return generateCount;
	}

	/**
	 * 生成内容静态
	 * 
	 * @param categoryId
	 *            分类ID
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 生成数量
	 */
	public static int generateContent(Long categoryId, Date beginDate, Date endDate) {
		int generateCount = 0;
		for (int i = 0;; i += 100) {
			List<Content> contents = new Content().dao().findList(categoryId, beginDate, endDate, i, 100);
			if (CollectionUtils.isNotEmpty(contents)) {
				for (Content content : contents) {
					generateCount += generate(content);
				}
			}
			if (contents.size() < 100) {
				break;
			}
		}
		return generateCount;
	}

	/**
	 * 生成首页静态
	 * 
	 * @return 生成数量
	 */
	public static int generateIndex() {
		return generate(TemplateUtils.getIndexTemplatePath(), TemplateUtils.getIndexStaticPath(), null);
	}

	/**
	 * 生成Sitemap
	 * 
	 * @return 生成数量
	 */
	public static int generateSitemap() {
		int generateCount = 0;
		List<SitemapUrl> sitemapUrls = new ArrayList<SitemapUrl>();
		for (int i = 0;; i += 100) {
			List<Content> contents = new Content().dao().findList(i, 100, null);
			if (CollectionUtils.isNotEmpty(contents)) {
				for (Content content : contents) {
					SitemapUrl contentSitemapUrl = new SitemapUrl();
					contentSitemapUrl.setLoc(content.getUrl());
					contentSitemapUrl.setLastmod(content.getModifyDate());
					contentSitemapUrl.setChangefreq(SitemapUrl.Changefreq.daily);
					contentSitemapUrl.setPriority(0.6F);
					sitemapUrls.add(contentSitemapUrl);
				}
			}
			if (contents.size() < 100) {
				break;
			}
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sitemapUrls", sitemapUrls);
		String sitemapPath = TemplateUtils.getSitemapStaticPath();
		generateCount += generate(TemplateUtils.getSitemapTemplatePath(), sitemapPath, model);
		return generateCount;
	}

	/**
	 * 生成所有静态
	 * 
	 * @return 生成数量
	 */
	public static int generateAll() {
		int generateCount = 0;
		generateCount += generateCategory();
		generateCount += generateContent(null, null, null);
		generateCount += generateIndex();
		generateCount += generateSitemap();
		return generateCount;
	}

	/**
	 * 删除静态
	 * 
	 * @param staticPath
	 *            静态文件路径
	 * @return 删除数量
	 */
	public static int delete(String staticPath) {
		if (StringUtils.isEmpty(staticPath)) {
			return 0;
		}
		File staticFile = new File(PathKit.getWebRootPath()+staticPath);
		return FileUtils.deleteQuietly(staticFile) ? 1 : 0;
	}


	/**
	 * 删除静态
	 * 
	 * @param content
	 *            内容
	 * @return 删除数量
	 */
	public static int delete(Content content) {
		if (content == null || StringUtils.isEmpty(content.getStaticPath())) {
			return 0;
		}
		return delete(content.getPath());
	}
	
	/**
	 * 删除静态
	 * 
	 * @param category
	 *            栏目
	 * @return 删除数量
	 */
	public static int delete(Category category) {
		if (category == null || StringUtils.isEmpty(category.getStaticPath())) {
			return 0;
		}
		return delete(category.getStaticPath());
	}
	
	/**
	 * 删除静态
	 * 
	 * @param category
	 *            栏目
	 * @param pageNumber
	 *            栏目
	 * @return 删除数量
	 */
	public static int delete(Category category,Integer pageNumber) {
		if (category == null || pageNumber == null || StringUtils.isEmpty(category.getStaticListPath(pageNumber))) {
			return 0;
		}
		return delete(category.getStaticListPath(pageNumber));
	}

	/**
	 * 删除首页静态
	 * 
	 * @return 删除数量
	 */
	public static int deleteIndex() {
		return delete(TemplateUtils.getIndexStaticPath());
	}

	/**
	 * SitemapUrl
	 * 
	 * 
	 * 
	 */
	public static class SitemapUrl {

		/**
		 * 更新频率
		 */
		public enum Changefreq {

			/** 经常 */
			always,

			/** 每小时 */
			hourly,

			/** 每天 */
			daily,

			/** 每周 */
			weekly,

			/** 每月 */
			monthly,

			/** 每年 */
			yearly,

			/** 从不 */
			never
		}

		/** 链接地址 */
		private String loc;

		/** 最后修改日期 */
		private Date lastmod;

		/** 更新频率 */
		private Changefreq changefreq;

		/** 权重 */
		private float priority;

		/**
		 * 获取链接地址
		 * 
		 * @return 链接地址
		 */
		public String getLoc() {
			return loc;
		}

		/**
		 * 设置链接地址
		 * 
		 * @param loc
		 *            链接地址
		 */
		public void setLoc(String loc) {
			this.loc = loc;
		}

		/**
		 * 获取最后修改日期
		 * 
		 * @return 最后修改日期
		 */
		public Date getLastmod() {
			return lastmod;
		}

		/**
		 * 设置最后修改日期
		 * 
		 * @param lastmod
		 *            最后修改日期
		 */
		public void setLastmod(Date lastmod) {
			this.lastmod = lastmod;
		}

		/**
		 * 获取更新频率
		 * 
		 * @return 更新频率
		 */
		public Changefreq getChangefreq() {
			return changefreq;
		}

		/**
		 * 设置更新频率
		 * 
		 * @param changefreq
		 *            更新频率
		 */
		public void setChangefreq(Changefreq changefreq) {
			this.changefreq = changefreq;
		}

		/**
		 * 获取权重
		 * 
		 * @return 权重
		 */
		public float getPriority() {
			return priority;
		}

		/**
		 * 设置权重
		 * 
		 * @param priority
		 *            权重
		 */
		public void setPriority(float priority) {
			this.priority = priority;
		}

	}
	
}
