/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.entity.Form;
import com.cms.entity.FormField;

import com.cms.routes.RouteMapping;


/**
 * Controller - 表单
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/form")

public class FormController extends BaseController {

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		Form form = new Form().dao().findById(id);
		List<FormField> formFields = new FormField().dao().findList(form.getFormModelId(), null);
		setAttr("formFields",formFields);
		setAttr("form", new Form().dao().findById(id));
		render(getView("form/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Long id = getParaToLong("id");
		Form form = new Form().dao().findById(id);
		List<FormField> formFields = new FormField().dao().findList(form.getFormModelId(), null);
		Map<String,Object> model = new HashMap<String,Object>();
		for(FormField formField : formFields){
			String value = getPara(formField.getName());
			model.put(formField.getName(), value);
		}
		form.setModifyDate(new Date());
		form.setFormFieldValue(JSONObject.toJSONString(model));
		form.update();
		redirect(getListQuery("/admin/form/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
		Long formModelId = getParaToLong("formModelId");
		Integer pageNumber = getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber = 1;
		}
		List<FormField> formFields = new FormField().dao().findList(formModelId, true);
		setAttr("formFields",formFields);
		setAttr("page", new Form().dao().findPage(formModelId,null,pageNumber,PAGE_SIZE));
		render(getView("form/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new Form().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}