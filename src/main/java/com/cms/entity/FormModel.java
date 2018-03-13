package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cms.entity.base.BaseFormModel;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 表单模型
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class FormModel extends BaseFormModel<FormModel> {
	
	/**
	 * 查找表单模型分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 表单模型分页
	 */
	public Page<FormModel> findPage(String name,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_form_model where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 查找所有表单模型
	 * 
	 * @return 所有表单模型
	 */
	public List<FormModel> findAll(){
		return find("select * from kf_form_model");
	}
}
