package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.cms.FieldAttribute;
import com.cms.entity.base.BaseFormField;

/**
 * Entity - 表单字段
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class FormField extends BaseFormField<FormField> {
	
    /**
     * 表单模型
     */
    private FormModel formModel;
    
	/**
	 * 获取表单模型
	 * 
	 * @return 表单模型
	 */
	public FormModel getFormModel(){
	    if(formModel == null){
	        formModel = new FormModel().dao().findById(getFormModelId());
	    }
		return formModel;
	}
	
	/**
	 * 查找表单字段
	 * 
	 * @param formModelId
	 * 				表单模型ID
	  * @param isAdminShow
	 * 				是否后台展示
	 * @return 表单字段
	 */
	public List<FormField> findList(Long formModelId,Boolean isAdminShow){
	    String filterSql = "";
		if(formModelId != null){
		    filterSql+= " and formModelId = "+formModelId;
		}
		if(isAdminShow != null){
		    filterSql+= " and isAdminShow = "+BooleanUtils.toInteger(isAdminShow);
		}
		return find("select * from kf_form_field where 1=1 "+filterSql);
	}
	
	/**
	 * 获取字段属性
	 * 
	 * @return 字段属性
	 */
	public FieldAttribute getFieldAttribute(){
		FieldAttribute fieldAttribute = JSONObject.parseObject(getAttributeValue(), FieldAttribute.class);
		return fieldAttribute;
	}
}
