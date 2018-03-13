/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;
import java.util.List;

import com.cms.CommonAttribute;
import com.cms.entity.Setting;
import com.cms.routes.RouteMapping;
import com.cms.util.TemplateVariableUtils;


/**
 * Controller - 自定义设置
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/setting")

public class SettingController extends BaseController {
	
	/**
	 * 添加
	 */
	public void add() {
		setAttr("configTypeNames", CommonAttribute.configTypeNames);
		render(getView("setting/add"));
	}
	
	/**
	 * 保存
	 */
	public void save(){
		Setting setting = getModel(Setting.class,"",true); 
		String type = setting.getType();
		String value = getPara(type+"Value");
		setting.setValue(value);
		setting.setCreateDate(new Date());
		setting.setModifyDate(new Date());
		setting.save();
		redirect("/admin/setting/list");
	}
	
	/**
	 * 编辑
	 */
	public void list(){
		List<Setting> settings = new Setting().dao().findAll();
		setAttr("settings", settings);
		render(getView("setting/list"));
	}
	
   /**
     * 编辑
     */
    public void edit() {
        Long id = getParaToLong("id");
        setAttr("configTypeNames", CommonAttribute.configTypeNames);
        setAttr("setting", new Setting().dao().findById(id));
        render(getView("setting/edit"));
    }
	
	/**
	 * 更新
	 */
	public void update(){
	    Setting setting = getModel(Setting.class,"",true); 
	    String type = setting.getType();
        String value = getPara(type+"Value");
        setting.setValue(value);
	    setting.setModifyDate(new Date());
		setting.update();
		TemplateVariableUtils.setBaseVariable();
		redirect("/admin/setting/list");
	}
	
   /**
     * 删除
     */
    public void delete() {
        Long id = getParaToLong("id");
        new Setting().dao().deleteById(id);
        renderJson(SUCCESS_FEEDBACK);
    }
}
