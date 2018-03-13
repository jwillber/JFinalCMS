/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.cms.entity.FormModel;
import com.cms.routes.RouteMapping;


/**
 * Controller - 表单模型
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/form_model")

public class FormModelController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
	    render(getView("form_model/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		FormModel formModel = getModel(FormModel.class,"",true);   
		formModel.setCreateDate(new Date());
		formModel.setModifyDate(new Date());
		formModel.save();
		redirect(getListQuery("/admin/form_model/list"));
	}
	
	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("formModel", new FormModel().dao().findById(id));
		render(getView("form_model/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		FormModel formModel = getModel(FormModel.class,"",true); 
		formModel.setModifyDate(new Date());
		formModel.update();
		redirect(getListQuery("/admin/form_model/list"));
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
		setAttr("page", new FormModel().dao().findPage(name,pageNumber,PAGE_SIZE));
		setAttr("name", name);
		render(getView("form_model/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new FormModel().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}