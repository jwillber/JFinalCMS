package com.cms.entity;

import java.util.List;

import com.cms.entity.base.BaseSetting;

/**
 * Entity - 设置
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Setting extends BaseSetting<Setting> {
	
	/**
	 * 查找设置
	 * 
	 * @param name
	 *           name
	 * @return 设置
	 */
	public Setting findByName(String name){
		return findFirst("select * from kf_setting where name = ?",name);
	}
	
	/**
	 * 查找所有设置
	 * 
	 * @return 所有设置
	 */
	public List<Setting> findAll(){
		return find("select * from kf_setting");
	}
}
