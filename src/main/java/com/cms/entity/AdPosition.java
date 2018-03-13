package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.cms.entity.base.BaseAdPosition;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 广告位
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class AdPosition extends BaseAdPosition<AdPosition> {
	
    /**
     * 广告
     */
    @JSONField(serialize=false)  
    private List<Ad> ads;
	
	/**
	 * 查找所有广告位
	 * 
	 * @return 所有广告位
	 */
	public List<AdPosition> findAll(){
		return find("select * from kf_ad_position");
	}
	
	/**
	 * 查找广告位分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 广告位分页
	 */
	public Page<AdPosition> findPage(String name,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_ad_position where 1=1 "+filterSql+orderBySql);
	}
	
    /**
     * 获取广告
     * 
     * @return 广告
     */
    public List<Ad> getAds(){
        if(ads == null){
            ads = new Ad().dao().find("select * from kf_ad where adPositionId=?",getId());
        }
        return ads;
    }
}
