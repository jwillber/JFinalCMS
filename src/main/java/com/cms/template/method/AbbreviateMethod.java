/*
 * 
 * 
 * 
 */
package com.cms.template.method;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cms.TemplateVariable;
import com.cms.util.FreeMarkerUtils;
import com.jfinal.kit.StrKit;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 字符串缩略
 * ${abbreviate("啊发发发发发",4,"...")}
 * 
 * 
 */
@TemplateVariable(name="abbreviate")
public class AbbreviateMethod implements TemplateMethodModelEx {

	/**
	 * 执行
	 * 
	 * @param arguments
	 *            参数
	 * @return 结果
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		String str = FreeMarkerUtils.getArgument(0, String.class, arguments);
		Integer width = FreeMarkerUtils.getArgument(1, Integer.class, arguments);
		String ellipsis = FreeMarkerUtils.getArgument(2, String.class, arguments);
		if (StrKit.isBlank(str) || width == null) {
			return str;
		}
		if(str.length()<=width){
			return str;
		}
		return StringUtils.substring(str, 0, width)+ellipsis;
	}

}