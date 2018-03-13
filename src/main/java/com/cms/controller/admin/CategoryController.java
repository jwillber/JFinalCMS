/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cms.CommonAttribute;
import com.cms.Feedback;
import com.cms.entity.Category;
import com.cms.entity.Content;
import com.cms.entity.ContentModel;
import com.cms.routes.RouteMapping;


/**
 * Controller - 分类
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/category")

public class CategoryController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
		setAttr("categoryTypeNames", CommonAttribute.categoryTypeNames);
		setAttr("categoryTree", new Category().dao().findTree());
		setAttr("contentModels",new ContentModel().dao().findAll());
		render(getView("category/add"));
	}
	
   /**
     * 模型模板
     */
    public void modelTemplate(){
        Long contentModelId = getParaToLong("contentModelId");
        ContentModel contentModel = new ContentModel().dao().findById(contentModelId);
        Map<String,Object> data = new HashMap<>();
        data.put("listTemplate", contentModel.getListTemplate());
        data.put("contentTemplate", contentModel.getContentTemplate());
        renderJson(data);
    }

	/**
	 * 保存
	 */
	public void save() {
		Category category = getModel(Category.class,"",true); 
		if(category.getIsEnabled()==null){
		    category.setIsEnabled(false);
        }
		if(category.getIsMenu()==null){
            category.setIsMenu(false);
        }
		category.setValue();
		category.setCreateDate(new Date());
		category.setModifyDate(new Date());
		category.save();
		if(StringUtils.isBlank(category.getDirectory())){
			Category parent = category.getParent();
			if(parent!=null && StringUtils.isNotBlank(parent.getDirectory())){
				category.setDirectory(parent.getDirectory()+category.getId());
				category.update();
			}
		}
		redirect(getListQuery("/admin/category/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		Category category = new Category().dao().findById(id);
		setAttr("categoryTypeNames", CommonAttribute.categoryTypeNames);
		setAttr("categoryTree", new Category().dao().findTree());
		setAttr("category", category);
		setAttr("contentModels",new ContentModel().dao().findAll());
		render(getView("category/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Category category = getModel(Category.class,"",true); 
		if(category.getIsEnabled()==null){
            category.setIsEnabled(false);
        }
        if(category.getIsMenu()==null){
            category.setIsMenu(false);
        }
		category.setValue();
		category.setModifyDate(new Date());
		category.update();
		redirect(getListQuery("/admin/category/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
		setAttr("categoryTypeNames", CommonAttribute.categoryTypeNames);
		setAttr("categoryTree", new Category().dao().findTree());
		render(getView("category/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long id = getParaToLong("id");
		Category category = new Category().dao().findById(id);
		if (category == null) {
			renderJson(ERROR_FEEDBACK);
			return;
		}
		List<Category> children = category.getChildren();
		if (children != null && !children.isEmpty()) {
			renderJson(Feedback.error("存在下级分类，无法删除"));
			return;
		}
		List<Content> articles = category.getContents();
		if (articles != null && !articles.isEmpty()) {
			renderJson(Feedback.error("存在下级内容，无法删除"));
			return;
		}
		new Category().dao().deleteById(id);
		renderJson(SUCCESS_FEEDBACK);
	}

}