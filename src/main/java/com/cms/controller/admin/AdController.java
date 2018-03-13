/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.cms.CommonAttribute;
import com.cms.entity.Ad;
import com.cms.entity.AdPosition;

import com.cms.routes.RouteMapping;


/**
 * Controller - 广告
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/ad")

public class AdController extends BaseController {


	/**
	 * 添加
	 */
	public void add() {
		setAttr("adTypeNames", CommonAttribute.adTypeNames);
		setAttr("adPositions", new AdPosition().dao().findAll());
		render(getView("ad/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		Ad ad = getModel(Ad.class,"",true); 
		Long adPositionId = getParaToLong("adPositionId");
		ad.setAdPositionId(adPositionId);
		if (CommonAttribute.AD_TYPE_TEXT.equals(ad.getType())) {
			ad.setImage(null);
		} else {
			ad.setContent(null);
		}
		if(ad.getIsEnabled()==null){
		    ad.setIsEnabled(false);
		}
		ad.setCreateDate(new Date());
		ad.setModifyDate(new Date());
		ad.save();
		redirect(getListQuery("/admin/ad/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("adTypeNames", CommonAttribute.adTypeNames);
		setAttr("ad", new Ad().dao().findById(id));
		setAttr("adPositions", new AdPosition().dao().findAll());
		render(getView("ad/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Ad ad = getModel(Ad.class,"",true); 
		Long adPositionId = getParaToLong("adPositionId");
		ad.setAdPositionId(adPositionId);
		if (CommonAttribute.AD_TYPE_TEXT.equals(ad.getType())) {
			ad.setImage(null);
		} else {
			ad.setContent(null);
		}
		if(ad.getIsEnabled()==null){
            ad.setIsEnabled(false);
        }
		ad.setModifyDate(new Date());
		ad.update();
		redirect(getListQuery("/admin/ad/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
	    String title = getPara("title");
		Integer pageNumber = getParaToInt("pageNumber");
		if(pageNumber==null){
			pageNumber = 1;
		}
		setAttr("page", new Ad().dao().findPage(title,pageNumber,PAGE_SIZE));
		setAttr("adTypeNames", CommonAttribute.adTypeNames);
		setAttr("title", title);
		render(getView("ad/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
		Long ids[] = getParaValuesToLong("ids");
		if(ArrayUtils.isNotEmpty(ids)){
			for(Long id:ids){
				new Ad().dao().deleteById(id);
			}
		}
		renderJson(SUCCESS_FEEDBACK);
	}

}