package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cms.entity.base.BaseContentModel;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 内容模型
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class ContentModel extends BaseContentModel<ContentModel> {
	
	/**
	 * 查找内容模型分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 模型分页
	 */
	public Page<ContentModel> findPage(String name,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_content_model where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 查找所有内容模型
	 * 
	 * @return 所有内容模型
	 */
	public List<ContentModel> findAll(){
		return find("select * from kf_content_model");
	}
}
