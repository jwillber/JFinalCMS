/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONArray;
import com.cms.CommonAttribute;
import com.cms.Feedback;
import com.cms.entity.Role;
import com.cms.routes.RouteMapping;

/**
 * Controller - 角色
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/role")

public class RoleController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
	    render(getView("role/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		Role role = getModel(Role.class, "", true);
		String[] permissions = getParaValues("permissions");
		if(ArrayUtils.isNotEmpty(permissions)){
			role.setPermission(JSONArray.toJSONString(permissions));
		}else{
			role.setPermission("[]");
		}
		role.setIsSystem(false);
		role.setCreateDate(new Date());
		role.setModifyDate(new Date());
		role.save();
		redirect(getListQuery("/admin/role/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("role", new Role().dao().findById(id));
		render(getView("role/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Role role = getModel(Role.class, "", true);
		String[] permissions = getParaValues("permissions");
        if(ArrayUtils.isNotEmpty(permissions)){
            role.setPermission(JSONArray.toJSONString(permissions));
        }else{
            role.setPermission("[]");
        }
		Role pRole = new Role().dao().findById(role.getId());
		if (pRole == null || pRole.getIsSystem()) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		role.setModifyDate(new Date());
		role.update();
		redirect(getListQuery("/admin/role/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
	    String name = getPara("name");
		Integer pageNumber = getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber = 1;
		}
		setAttr("page", new Role().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("role/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for (Long id : ids) {
				Role role = new Role().dao().findById(id);
				if (role != null && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins().isEmpty()))) {
					renderJson(Feedback.error("删除失败，角色["+role.getName()+"]下存在管理员"));
				}
			}
			for(Long id:ids){
				new Role().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}