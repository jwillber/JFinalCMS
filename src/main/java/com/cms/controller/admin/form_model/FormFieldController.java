/*
 * 
 * 
 * 
 */
package com.cms.controller.admin.form_model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.CommonAttribute;
import com.cms.Feedback;
import com.cms.FieldAttribute;
import com.cms.controller.admin.BaseController;
import com.cms.entity.FormField;
import com.cms.entity.FormModel;

import com.cms.routes.RouteMapping;


/**
 * Controller - 表单字段
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/form_model/form_field")

public class FormFieldController extends BaseController {


	/**
	 * 添加
	 */
	public void add() {
		Long formModelId = getParaToLong("formModelId");
		setAttr("formModel", new FormModel().dao().findById(formModelId));
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("modelFieldPatternNames", CommonAttribute.modelFieldPatternNames);
		render(getView("form_model/form_field/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		FieldAttribute fieldAttribute = getBean(FieldAttribute.class,"fieldAttribute",true);
		FormField formField = getModel(FormField.class,"",true); 
		if(formField.getIsEnabled()==null){
		    formField.setIsEnabled(false);
        }
        if(formField.getIsRequired()==null){
            formField.setIsRequired(false);
        }
        if(formField.getIsAdminShow()==null){
            formField.setIsAdminShow(false);
        }
		formField.setCreateDate(new Date());
		formField.setModifyDate(new Date());
		formField.setAttributeValue(JSONObject.toJSONString(fieldAttribute));
		formField.save();
		redirect(getListQuery("/admin/form_model/form_field/list"));
	}

	
	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("formField", new FormField().dao().findById(id));
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("modelFieldPatternNames", CommonAttribute.modelFieldPatternNames);
		render(getView("form_model/form_field/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		FieldAttribute fieldAttribute = getBean(FieldAttribute.class,"fieldAttribute",true);
		FormField formField = getModel(FormField.class,"",true); 
		if(formField.getIsEnabled()==null){
            formField.setIsEnabled(false);
        }
        if(formField.getIsRequired()==null){
            formField.setIsRequired(false);
        }
        if(formField.getIsAdminShow()==null){
            formField.setIsAdminShow(false);
        }
		formField.setAttributeValue(JSONObject.toJSONString(fieldAttribute));
		formField.setModifyDate(new Date());
		formField.update();
		redirect(getListQuery("/admin/form_model/form_field/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
		Long formModelId = getParaToLong("formModelId");
		List<FormField> formFields = new FormField().dao().findList(formModelId,null);
		setAttr("formFields", formFields);
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("formModelId", formModelId);
		render(getView("form_model/form_field/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new FormField().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}