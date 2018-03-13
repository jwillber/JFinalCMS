package com.cms.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cms.entity.Form;
import com.cms.entity.FormField;
import com.cms.entity.FormModel;
import com.cms.routes.RouteMapping;
/**
 * Controller - 表单
 * 
 * 
 * 
 */
@RouteMapping(url = "/form")
public class FormController extends BaseController {

	/**
	 * 保存
	 */
	public void save(){
		Long formModelId = getParaToLong("formModelId");
		Long contentId = getParaToLong("contentId");
		List<FormField> formFields = new FormField().dao().findList(formModelId, null);
		Map<String,Object> model = new HashMap<String,Object>();
		for(FormField formField : formFields){
			String value = getPara(formField.getName());
			model.put(formField.getName(), value);
		}
		Form form = new Form();
		form.setCreateDate(new Date());
		form.setModifyDate(new Date());
		form.setFormModelId(formModelId);
		form.setContentId(contentId);
		form.setFormFieldValue(JSONObject.toJSONString(model));
		form.setIsEnabled(false);
		form.save();
		renderJson(SUCCESS_FEEDBACK);
	}
	
	/**
	 * 内容
	 */
	public void list(){
		Long formModelId = getParaToLong("formModelId");
		Long contentId = getParaToLong("contentId");
		Integer pageNumber = getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber = 1;
		}
		int pageSize = 20 ; 
		setAttr("page", new Form().dao().findPage(formModelId, contentId, pageNumber,pageSize));
		FormModel formModel = new FormModel().dao().findById(formModelId);	
		render("/templates/"+getTheme()+"/front/"+formModel.getListTemplate());
	}
}
