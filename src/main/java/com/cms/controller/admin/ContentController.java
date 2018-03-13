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

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
import com.cms.CommonAttribute;
import com.cms.entity.Category;
import com.cms.entity.Content;
import com.cms.entity.ContentField;
import com.cms.routes.RouteMapping;
import com.cms.util.StaticUtils;
import com.cms.util.SystemUtils;


/**
 * Controller - 内容
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/content")

public class ContentController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
		Long categoryId = getParaToLong("categoryId");
		Category category = new Category().dao().findById(categoryId);
		List<ContentField> contentFields = new ContentField().dao().findList(category.getContentModelId());
		setAttr("category", category);
		setAttr("contentFields", contentFields);
		render(getView("content/add"));
	}
	
	/**
	 * 保存
	 */
	public void save() {
		Content content = getModel(Content.class,"",true);  
		Long contentModelId = content.getCategory().getContentModelId();
		List<ContentField> contentFields = new ContentField().dao().findList(contentModelId);
		Map<String,String> model = new HashMap<String,String>();
		for(ContentField contentField : contentFields){
			model.put(contentField.getName(), getPara(contentField.getName()));
		}
		if(content.getIsEnabled()==null){
		    content.setIsEnabled(false);
        }
		String tags[] = getParaValues("tags");
        if(ArrayUtils.isEmpty(tags)){
            tags = ArrayUtils.EMPTY_STRING_ARRAY;
        }
        content.setTagValue(JSONObject.toJSONString(tags));
		content.setHits(0L);
		content.setCreateDate(new Date());
		content.setModifyDate(new Date());
		content.setContentFieldValue(JSONObject.toJSONString(model));
		content.save();
		if(CommonAttribute.CONFIG_SITE_MODEL_STATIC.equals(SystemUtils.getConfig().getSiteModel())){
		    new Thread(new Runnable() {
                
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    StaticUtils.generate(new Content().findById(content.getId()));
                    StaticUtils.generate(new Content().findById(content.getId()).getCategory());
                }
            }).start();
        }
		redirect(getListQuery("/admin/content/list?categoryId="+content.getCategoryId()));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		Content content = new Content().dao().findById(id);
		List<ContentField> contentFields = new ContentField().dao().findList(content.getCategory().getContentModelId());
		setAttr("content", content);
		setAttr("contentFields", contentFields);
		render(getView("content/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Content content = getModel(Content.class,"",true);  
		Long contentModelId = content.getCategory().getContentModelId();
		List<ContentField> contentFields = new ContentField().dao().findList(contentModelId);
		Map<String,String> model = new HashMap<String,String>();
		for(ContentField contentField : contentFields){
			model.put(contentField.getName(), getPara(contentField.getName()));
		}
		if(content.getIsEnabled()==null){
            content.setIsEnabled(false);
        }
		String tags[] = getParaValues("tags");
		if(ArrayUtils.isEmpty(tags)){
		    tags = ArrayUtils.EMPTY_STRING_ARRAY;
		}
		content.setTagValue(JSONObject.toJSONString(tags));
		content.setContentFieldValue(JSONObject.toJSONString(model));
		content.setModifyDate(new Date());
		content.update();
		if(CommonAttribute.CONFIG_SITE_MODEL_STATIC.equals(SystemUtils.getConfig().getSiteModel())){
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    StaticUtils.generate(new Content().findById(content.getId()));
                    StaticUtils.generate(new Content().findById(content.getId()).getCategory());
                }
            }).start();
        }
		redirect(getListQuery("/admin/content/list?categoryId="+content.getCategoryId()));
	}

	/**
	 * 列表
	 */
	public void list() {
	    String title = getPara("title");
		Long categoryId = getParaToLong("categoryId");
		Integer pageNumber = getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber = 1;
		}
		setAttr("categoryId",categoryId);
		setAttr("page", new Content().dao().findPage(categoryId,null,title,pageNumber,PAGE_SIZE));
		setAttr("title", title);
		render(getView("content/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new Content().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}