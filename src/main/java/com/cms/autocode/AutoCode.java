package com.cms.autocode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.util.FreeMarkerUtils;
import com.cms.util.TemplateVariableUtils;
import com.jfinal.kit.PathKit;

import freemarker.template.TemplateException;

public class AutoCode {

	/**
	 * 将对象转化为map key:对象属性名称 value 对象属性值
	 * 
	 * @param obj
	 *            对象
	 * @return
	 * @throws CmsException
	 */
	public static Map<String, Object> objToMap(Object obj){
		Map<String, Object> dataBaseMap = new HashMap<String, Object>();
		// 获取属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object o = field.get(obj);
				if (o == null) {
					continue;
				}
				dataBaseMap.put(field.getName(), o);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dataBaseMap;
	}
	
	//模板地址
	public static final String autoCodePath = PathKit.getWebRootPath()+"/src/main/resources/autocode";
	public static final String templatePath = autoCodePath+"/template";
	public static final String viewCreatePath = autoCodePath+"/view";
	public static final String javaCreatePath = autoCodePath+"/java";
	
	public static String [] jsonFiles = new String[]{"User.json"};
	public static String [] viewNames = new String[]{"add.html","edit.html","list.html"};
	
	public static void main(String[] args) throws IOException, TemplateException {
		initFreemarker();
		for(String jsonFile : jsonFiles){
			String jsonContent = FileUtils.readFileToString(new File(autoCodePath+"/"+jsonFile));
			AutoClass autoClass = JSONObject.parseObject(jsonContent, AutoClass.class);
			Map<String,Object> model = objToMap(autoClass);
			//生成action
			File actionFile = new File(templatePath+"/action.html");
			String actionFileContent = FileUtils.readFileToString(actionFile);
			String actionCode = FreeMarkerUtils.process(actionFileContent, model);
			String actionCodePath = javaCreatePath+"/"+autoClass.getPackageName().replace(".", "/")+"/"+autoClass.getClassName()+".java";
			FileUtils.write(new File(actionCodePath), actionCode);
			for(String viewName : viewNames){
				File viewFile = new File(templatePath+"/"+viewName);
				String viewFileContent = FileUtils.readFileToString(viewFile);
				String viewCode = FreeMarkerUtils.process(viewFileContent, model);
				String viewCodePath = viewCreatePath+"/"+viewName;
				FileUtils.write(new File(viewCodePath), viewCode);
			}
		}
	}
	
	public static void initFreemarker(){
		TemplateVariableUtils.setCommonConfig();
	}
}
