/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.cms.entity.ContentModel;
import com.cms.routes.RouteMapping;


/**
 * Controller - 内容模型
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/content_model")

public class ContentModelController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
	    render(getView("content_model/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		ContentModel contentModel = getModel(ContentModel.class,"",true);   
		contentModel.setCreateDate(new Date());
		contentModel.setModifyDate(new Date());
		contentModel.save();
		redirect(getListQuery("/admin/content_model/list"));
	}
	
	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("contentModel", new ContentModel().dao().findById(id));
		render(getView("content_model/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		ContentModel contentModel = getModel(ContentModel.class,"",true); 
		contentModel.setModifyDate(new Date());
		contentModel.update();
		redirect(getListQuery("/admin/content_model/list"));
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
		setAttr("page", new ContentModel().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("content_model/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new ContentModel().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}