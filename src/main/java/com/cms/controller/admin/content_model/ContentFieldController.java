/*
 * 
 * 
 * 
 */
package com.cms.controller.admin.content_model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.CommonAttribute;
import com.cms.FieldAttribute;
import com.cms.controller.admin.BaseController;
import com.cms.entity.ContentField;
import com.cms.entity.ContentModel;

import com.cms.routes.RouteMapping;


/**
 * Controller - 内容字段
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/content_model/content_field")

public class ContentFieldController extends BaseController {


	/**
	 * 添加
	 */
	public void add() {
		Long contentModelId = getParaToLong("contentModelId");
		setAttr("contentModel", new ContentModel().dao().findById(contentModelId));
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("modelFieldPatternNames", CommonAttribute.modelFieldPatternNames);
		render(getView("content_model/content_field/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		Long contentModelId = getParaToLong("contentModelId");
		FieldAttribute fieldAttribute = getBean(FieldAttribute.class,"fieldAttribute",true);
		ContentField contentField = getModel(ContentField.class,"",true); 
		if(contentField.getIsEnabled()==null){
            contentField.setIsEnabled(false);
        }
        if(contentField.getIsRequired()==null){
            contentField.setIsRequired(false);
        }
		contentField.setCreateDate(new Date());
		contentField.setModifyDate(new Date());
		contentField.setAttributeValue(JSONObject.toJSONString(fieldAttribute));
		contentField.save();
		redirect(getListQuery("/admin/content_model/content_field/list?contentModelId="+contentModelId));
	}

	
	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("contentField", new ContentField().dao().findById(id));
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("modelFieldPatternNames", CommonAttribute.modelFieldPatternNames);
		render(getView("content_model/content_field/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		FieldAttribute fieldAttribute = getBean(FieldAttribute.class,"fieldAttribute",true);
		ContentField contentField = getModel(ContentField.class,"",true); 
		if(contentField.getIsEnabled()==null){
		    contentField.setIsEnabled(false);
		}
		if(contentField.getIsRequired()==null){
		    contentField.setIsRequired(false);
		}
		contentField.setAttributeValue(JSONObject.toJSONString(fieldAttribute));
		contentField.setModifyDate(new Date());
		contentField.update();
		redirect(getListQuery("/admin/content_model/content_field/list?contentModelId="+contentField.getContentModelId()));
	}

	/**
	 * 列表
	 */
	public void list() {
		Long contentModelId = getParaToLong("contentModelId");
		List<ContentField> contentFields = new ContentField().dao().findList(contentModelId);
		setAttr("contentFields", contentFields);
		setAttr("fieldTypeNames", CommonAttribute.fieldTypeNames);
		setAttr("contentModelId", contentModelId);
		render(getView("content_model/content_field/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new ContentField().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}