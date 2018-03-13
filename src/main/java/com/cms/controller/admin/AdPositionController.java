/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.cms.entity.AdPosition;

import com.cms.routes.RouteMapping;


/**
 * Controller - 广告位
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/ad_position")

public class AdPositionController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
	    render(getView("ad_position/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		AdPosition adPosition = getModel(AdPosition.class,"",true); 
		adPosition.setCreateDate(new Date());
		adPosition.setModifyDate(new Date());
		adPosition.save();
		redirect(getListQuery("/admin/ad_position/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("adPosition", new AdPosition().dao().findById(id));
		render(getView("ad_position/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		AdPosition adPosition = getModel(AdPosition.class,"",true); 
		adPosition.setModifyDate(new Date());
		adPosition.update();
		redirect(getListQuery("/admin/ad_position/list"));
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
		setAttr("page", new AdPosition().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("ad_position/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new AdPosition().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}