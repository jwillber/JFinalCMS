package com.cms.entity;

import org.apache.commons.lang.StringUtils;

import com.cms.entity.base.BaseTag;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 标签
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Tag extends BaseTag<Tag> {
	
   /**
     * 查找标签分页
     * 
     * @param pageNumber
     *            页码
     * @param pageSize
     *            每页记录数
     * @return 标签分页
     */
    public Page<Tag> findPage(String name,Integer pageNumber,Integer pageSize){
        String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
        String orderBySql = DBUtils.getOrderBySql("createDate desc");
        return paginate(pageNumber, pageSize, "select *", "from kf_tag where 1=1 "+filterSql+orderBySql);
    }
}
