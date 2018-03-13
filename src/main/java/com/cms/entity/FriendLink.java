package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cms.entity.base.BaseFriendLink;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 友情链接
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class FriendLink extends BaseFriendLink<FriendLink> {

	/**
	 * 查找友情链接分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 友情链接分页
	 */
	public Page<FriendLink> findPage(String name,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_friend_link where 1=1 "+filterSql+orderBySql);
	}
	
	/**
	 * 查找友情链接
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orderBy
	 *            排序
	 * @return 友情链接
	 */
	public List<FriendLink> findList(Integer count,  String orderBy) {
		String orderBySql = "";
		if(StringUtils.isBlank(orderBy)){
		    orderBySql = DBUtils.getOrderBySql("createDate desc");
		}else{
		    orderBySql = DBUtils.getOrderBySql(orderBy);
		}
		String countSql=DBUtils.getCountSql(null, count);
		return find("select * from kf_friend_link where 1=1 "+orderBySql+countSql);
	}
	
	/**
	 * 查找友情链接
	 * 
	 * @param type
	 *            类型
	 * @return 友情链接
	 */
	public List<FriendLink> findList(String type) {
	    String filterSql = "";
		if(type!=null){
		    filterSql+= " and type = '"+type+"'";
		}
		String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return find("select * from kf_friend_link where 1=1 "+filterSql+orderBySql);
	}
}
