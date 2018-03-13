package com.cms.entity;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cms.entity.base.BaseForm;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 表单
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Form extends BaseForm<Form> {
	
	/**
	 * 查找表单分页
	 * 
	 * @param formModelId
	 *            表单模型ID
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 表单分页
	 */
	public Page<Form> findPage(Long formModelId,Long contentId,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
		if(formModelId!=null){
		    filterSql+=" and formModelId = " + formModelId;
		}
		if(contentId!=null){
		    filterSql+=" and contentId = " + contentId;
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_form where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 查找内容字段
	 * 
	 * @param contentModelId
	 * 				内容模型ID
	 * @return 内容字段
	 */
	public List<Form> findList(Long formModelId,Long contentId,Integer count,String orderBy){
	    String filterSql = "";
		if(formModelId!=null){
		    filterSql+=" and formModelId = " + formModelId;
		}
		if(contentId!=null){
		    filterSql+=" and contentId = " + contentId;
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return find("select * from kf_form where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 获取属性值
	 * @param key
	 *         key
	 * @return
	 */
	public String getFieldValue(String key){
		return JSONObject.parseObject(getFormFieldValue()).getString(key);
	}
}
