package com.cms.entity;

import java.util.List;

import com.cms.entity.base.BaseAdminRole;

/**
 * Entity - 管理员角色
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class AdminRole extends BaseAdminRole<AdminRole> {
	
	
	/**
	 * 根据管理员ID查找管理员角色
	 * 
	 * @param adminId
	 *           管理员ID
	 * @return 管理员角色
	 */
	public List<AdminRole> findByAdminId(Long adminId){
		return find("select * from kf_admin_role where adminId=?",adminId);
	}
}
