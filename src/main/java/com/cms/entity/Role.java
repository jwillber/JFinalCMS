package com.cms.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.cms.entity.base.BaseRole;
import com.cms.util.DBUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * Entity - 角色
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Role extends BaseRole<Role> {
	
    /**
     * 管理员
     */
    @JSONField(serialize=false)  
    private List<Admin> admins;
    
	/**
	 * 查找所有角色
	 * 
	 * @return 所有角色
	 */
	public List<Role> findAll(){
		return find("select * from kf_role");
	}
	
    /**
     * 获取权限
     * 
     * @return 权限
     */
    public List<String> getPermissions() {
        return JSONArray.parseArray(getPermission(), String.class);
    }
	
    /**
     * 获取管理员
     * 
     * @return 管理员
     */
    public List<Admin> getAdmins() {
        if(admins == null){
            admins = new Admin().dao().find("select * from kf_admin where id in(select adminId from kf_admin_role where roleId=?)",getId());
        }
        return admins;
    }
	
	/**
	 * 查找角色分页
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @return 角色分页
	 */
	public Page<Role> findPage(String name,Integer pageNumber,Integer pageSize){
	    String filterSql = "";
        if(StringUtils.isNotBlank(name)){
            filterSql+= " and name like '%"+name+"%'";
        }
	    String orderBySql = DBUtils.getOrderBySql("createDate desc");
		return paginate(pageNumber, pageSize, "select *", "from kf_role where 1=1 "+filterSql+orderBySql);
	}
}
