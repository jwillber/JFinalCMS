package com.cms.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.cms.ClassScaner;
import com.cms.Config;
import com.cms.TemplateVariable;
import com.cms.entity.Setting;
import com.jfinal.kit.StrKit;
import com.jfinal.render.FreeMarkerRender;

import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * Utils - 模板变量
 * 
 * 
 * 
 */
public class TemplateVariableUtils {

	/**
	 * 设置公共配置
	 */
	public static void setCommonConfig(){
		/** null处理 */
		FreeMarkerRender.getConfiguration().setClassicCompatible(true);
		/** 标签由<替换[处理 */
		FreeMarkerRender.getConfiguration().setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
	}
	
	/**
	 * 设置基础变量
	 */
	public static void setBaseVariable(){
		try {
			/** config */
			Config config = SystemUtils.getConfig();
			FreeMarkerRender.getConfiguration().setSharedVariable("config",config);
			/** setting */
			List<Setting> settings = new Setting().dao().findAll();
			for(Setting setting : settings){
				FreeMarkerRender.getConfiguration().setSharedVariable(setting.getName(),setting.getValue());
			}
		} catch (TemplateModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 设置标签变量
	 */
	public static void setDirectiveVariable(){
		/** 自定义标签 */
		List<Class<TemplateDirectiveModel>> directiveList = ClassScaner.scanSubClass(TemplateDirectiveModel.class,true,false);
		if (CollectionUtils.isNotEmpty(directiveList)) {
			for (Class<?> clazz : directiveList) {
				TemplateVariable templateVariable = clazz.getAnnotation(TemplateVariable.class);
				if (null != templateVariable && StrKit.notBlank(templateVariable.name())) {
					try {
						FreeMarkerRender.getConfiguration().setSharedVariable(templateVariable.name(),clazz.newInstance());
					} catch (TemplateModelException | InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 设置方法变量
	 */
	public static void setMethodVariable(){
		/** 自定义方法 */
		List<Class<TemplateMethodModelEx>> methodList = ClassScaner.scanSubClass(TemplateMethodModelEx.class,true,false);
		if (CollectionUtils.isNotEmpty(methodList)) {
			for (Class<?> clazz : methodList) {
				TemplateVariable templateVariable = clazz.getAnnotation(TemplateVariable.class);
				if (null != templateVariable && StrKit.notBlank(templateVariable.name())) {
					try {
						FreeMarkerRender.getConfiguration().setSharedVariable(templateVariable.name(),clazz.newInstance());
					} catch (TemplateModelException | InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
