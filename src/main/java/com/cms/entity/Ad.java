package com.cms.entity;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.cms.entity.base.BaseAd;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 广告
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Ad extends BaseAd<Ad> {
	
    /**
     * 广告位
     */
    @JSONField(serialize=false)  
    private AdPosition adPosition;
	
    /**
     * 获取广告位
     * 
     * @return 广告位 
     */
    public AdPosition getAdPosition(){
        if(adPosition == null){
            adPosition =  new AdPosition().dao().findById(getAdPositionId());
        }
        return adPosition;
    }
	
	/**
	 * 查找广告分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 广告分页
	 */
	public Page<Ad> findPage(String title,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
	    if(StringUtils.isNotBlank(title)){
            filterSql+= " and title like '%"+title+"%'";
        }
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_ad where 1=1 "+filterSql+orderBySql);
	}
}
