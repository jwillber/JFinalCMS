/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.entity.FriendLink;

import com.cms.routes.RouteMapping;


/**
 * Controller - 友情链接
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/friend_link")

public class FriendLinkController extends BaseController {


	/**
	 * 添加
	 */
	public void add() {
		setAttr("friendLinkTypeNames", CommonAttribute.friendLinkTypeNames);
		render(getView("friend_link/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		FriendLink friendLink = getModel(FriendLink.class,"",true);   
		if (CommonAttribute.FRIENDLINK_TYPE_TEXT.equals(friendLink.getType())) {
			friendLink.setLogo(null);
		} else if (StringUtils.isEmpty(friendLink.getLogo())) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		friendLink.setCreateDate(new Date());
		friendLink.setModifyDate(new Date());
		friendLink.save();
		redirect(getListQuery("/admin/friend_link/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("friendLinkTypeNames", CommonAttribute.friendLinkTypeNames);
		setAttr("friendLink", new FriendLink().dao().findById(id));
		render(getView("friend_link/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		FriendLink friendLink = getModel(FriendLink.class,"",true);   
		if (CommonAttribute.FRIENDLINK_TYPE_TEXT.equals(friendLink.getType())) {
			friendLink.setLogo(null);
		} else if (StringUtils.isEmpty(friendLink.getLogo())) {
			render(CommonAttribute.ADMIN_ERROR_VIEW);
			return;
		}
		friendLink.setModifyDate(new Date());
		friendLink.update();
		redirect(getListQuery("/admin/friend_link/list"));
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
		setAttr("page", new FriendLink().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("friend_link/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new FriendLink().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}