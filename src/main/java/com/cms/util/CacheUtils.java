package com.cms.util;

import com.jfinal.kit.PropKit;
import com.jfinal.render.FreeMarkerRender;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
/**
 * Utils - 缓存
 * 
 * 
 * 
 */
public class CacheUtils {

	/** 缓存管理器 */
	private static CacheManager cacheManager;
	
	/**
	 * 获取缓存管理器
	 * 
	 * @return 缓存管理器
	 */
	public static CacheManager getCacheManager() {
		if(cacheManager == null){
			cacheManager = CacheManager.create();
		}
		return cacheManager;
	}
	
	/**
	 * 获取缓存存储路径
	 * 
	 * @return 缓存存储路径
	 */
	public static String getDiskStorePath() {
		return getCacheManager().getConfiguration().getDiskStoreConfiguration().getPath();
	}

	/**
	 * 获取缓存数
	 * 
	 * @return 缓存数
	 */
	public static int getCacheSize() {
		int cacheSize = 0;
		String[] cacheNames = getCacheManager().getCacheNames();
		if (cacheNames != null) {
			for (String cacheName : cacheNames) {
				Ehcache cache = getCacheManager().getEhcache(cacheName);
				if (cache != null) {
					cacheSize += cache.getSize();
				}
			}
		}
		return cacheSize;
	}

	/**
	 * 清除缓存
	 */
	public static void clear() {
		getCacheManager().clearAll();
		FreeMarkerRender.getConfiguration().clearTemplateCache();
		PropKit.clear();
		TemplateVariableUtils.setBaseVariable();
	}
}
