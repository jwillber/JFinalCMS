/*
 * 
 * 
 * 
 */
package com.cms.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.cms.CommonAttribute;
import com.cms.Config;
import com.cms.EnumConverter;
import com.jfinal.kit.PathKit;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Utils - 系统
 * 
 * 
 * 
 */
public final class SystemUtils {

	/** CacheManager */
	private static final CacheManager CACHE_MANAGER = CacheUtils.getCacheManager();

	/** BeanUtilsBean */
	private static final BeanUtilsBean BEAN_UTILS;

	static {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
			@Override
			public String convert(Object value) {
				if (value != null) {
					Class<?> type = value.getClass();
					if (type.isEnum() && super.lookup(type) == null) {
						super.register(new EnumConverter(type), type);
					} else if (type.isArray() && type.getComponentType().isEnum()) {
						if (super.lookup(type) == null) {
							ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
							arrayConverter.setOnlyFirstToString(false);
							super.register(arrayConverter, type);
						}
						Converter converter = super.lookup(type);
						return ((String) converter.convert(String.class, value));
					}
				}
				return super.convert(value);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String value, Class clazz) {
				if (clazz.isEnum() && super.lookup(clazz) == null) {
					super.register(new EnumConverter(clazz), clazz);
				}
				return super.convert(value, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String[] values, Class clazz) {
				if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
					super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
				}
				return super.convert(values, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(Object value, Class targetType) {
				if (super.lookup(targetType) == null) {
					if (targetType.isEnum()) {
						super.register(new EnumConverter(targetType), targetType);
					} else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
						ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
						arrayConverter.setOnlyFirstToString(false);
						super.register(arrayConverter, targetType);
					}
				}
				return super.convert(value, targetType);
			}
		};

		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(CommonAttribute.DATE_PATTERNS);
		convertUtilsBean.register(dateConverter, Date.class);
		BEAN_UTILS = new BeanUtilsBean(convertUtilsBean);
	}

	/**
	 * 不可实例化
	 */
	private SystemUtils() {
	}

	/**
	 * 获取系统设置
	 * 
	 * @return 系统设置
	 */
	@SuppressWarnings("unchecked")
	public static Config getConfig() {
		Ehcache cache = CACHE_MANAGER.getEhcache(Config.CACHE_NAME);
		String cacheKey = "config";
		Element cacheElement = cache.get(cacheKey);
		if (cacheElement == null) {
			Config config = new Config();
			try {
				File configXmlFile = new File(PathKit.getRootClassPath()+CommonAttribute.CONFIG_XML_PATH);
				Document document = new SAXReader().read(configXmlFile);
				List<org.dom4j.Element> elements = document.selectNodes("/kuaifan/config");
				for (org.dom4j.Element element : elements) {
					try {
						String name = element.attributeValue("name");
						String value = element.attributeValue("value");
						BEAN_UTILS.setProperty(config, name, value);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e.getMessage(), e);
					}
				}
			} catch (DocumentException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			cache.put(new Element(cacheKey, config));
			cacheElement = cache.get(cacheKey);
		}
		return (Config) cacheElement.getObjectValue();
	}

	/**
	 * 设置系统设置
	 * 
	 * @param config
	 *            系统设置
	 */
	@SuppressWarnings("unchecked")
	public static void setConfig(Config config) {
		try {
			File configXmlFile = new File(PathKit.getRootClassPath()+CommonAttribute.CONFIG_XML_PATH);
			Document document = new SAXReader().read(configXmlFile);
			List<org.dom4j.Element> elements = document.selectNodes("/kuaifan/config");
			for (org.dom4j.Element element : elements) {
				try {
					String name = element.attributeValue("name");
					String value = BEAN_UTILS.getProperty(config, name);
					Attribute attribute = element.attribute("value");
					attribute.setValue(value);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e.getMessage(), e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e.getMessage(), e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}

			XMLWriter xmlWriter = null;
			try {
				OutputFormat outputFormat = OutputFormat.createPrettyPrint();
				outputFormat.setEncoding("UTF-8");
				outputFormat.setIndent(true);
				outputFormat.setIndent("	");
				outputFormat.setNewlines(true);
				xmlWriter = new XMLWriter(new FileOutputStream(configXmlFile), outputFormat);
				xmlWriter.write(document);
				xmlWriter.flush();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				try {
					if (xmlWriter != null) {
						xmlWriter.close();
					}
				} catch (IOException e) {
				}
			}
			Ehcache cache = CACHE_MANAGER.getEhcache(Config.CACHE_NAME);
			String cacheKey = "config";
			cache.put(new Element(cacheKey, config));
		} catch (DocumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}


	/**
	 * 获取所有主题
	 * 
	 * @return 所有主题
	 */
	public static List<String> getThemes(){
		File[] files = new File(PathKit.getWebRootPath()+File.separator+"templates").listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.exists() && file.isDirectory();
			}
		});
		List<String> themes = new ArrayList<String>();
		for (File file : files) {
			themes.add(file.getName());
		}
		return themes;
	}
}