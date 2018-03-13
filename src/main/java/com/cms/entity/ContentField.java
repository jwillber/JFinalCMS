package com.cms.entity;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cms.FieldAttribute;
import com.cms.entity.base.BaseContentField;

/**
 * Entity - 内容字段
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class ContentField extends BaseContentField<ContentField> {
	
    /**
     * 内容模型
     */
    private ContentModel contentModel;
    
	/**
	 * 获取内容模型
	 * 
	 * @return 内容模型
	 */
	public ContentModel getContentModel(){
	    if(contentModel == null){
	        contentModel = new ContentModel().dao().findById(getContentModelId());
	    }
		return contentModel;
	}
	
	/**
	 * 查找内容字段
	 * 
	 * @param contentModelId
	 * 				内容模型ID
	 * @return 内容字段
	 */
	public List<ContentField> findList(Long contentModelId){
	    String filterSql = "";
		if(contentModelId != null){
		    filterSql+= " and contentModelId = "+contentModelId;
		}
		return find("select * from kf_content_field where 1=1 "+filterSql);
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
