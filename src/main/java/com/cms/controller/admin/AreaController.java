/*
 * 
 * 
 * 
 */
package com.cms.controller.admin;

import java.util.Date;
import java.util.List;

import com.cms.Feedback;
import com.cms.entity.Area;
import com.cms.routes.RouteMapping;


/**
 * Controller - 地区
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/area")

public class AreaController extends BaseController {

	/**
	 * 添加
	 */
	public void add() {
		Long parentId = getParaToLong("parentId"); 
		setAttr("parent", new Area().dao().findById(parentId));
		render(getView("area/add"));
	}

	/**
	 * 保存
	 */
	public void save() {
		Area area =  getModel(Area.class,"",true); 
		Long parentId = getParaToLong("parentId"); 
		area.setParentId(parentId);
		area.setFullName(null);
		area.setCreateDate(new Date());
		area.setModifyDate(new Date());
		area.setValue();
		area.save();
		redirect(getListQuery("/admin/area/list"));
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Long id = getParaToLong("id");
		setAttr("area", new Area().dao().findById(id));
		render(getView("area/edit"));
	}

	/**
	 * 更新
	 */
	public void update() {
		Area area =  getModel(Area.class,"",true); 
		area.setModifyDate(new Date());
		area.update();
		redirect(getListQuery("/admin/area/list"));
	}

	/**
	 * 列表
	 */
	public void list() {
		Long parentId = getParaToLong("parentId"); 
		Area parent = new Area().dao().findById(parentId);
		if (parent != null) {
			setAttr("parent", parent);
			setAttr("areas", parent.getChildren());
		} else {
			setAttr("areas", new Area().dao().findRoots());
		}
		render(getView("area/list"));
	}

	/**
	 * 删除
	 */
	public void delete() {
	    Long id = getParaToLong("id");
        Area area = new Area().dao().findById(id);
        if (area == null) {
            renderJson(ERROR_FEEDBACK);
            return;
        }
        List<Area> children = area.getChildren();
        if (children != null && !children.isEmpty()) {
            renderJson(Feedback.error("存在下级地区，无法删除"));
            return;
        }
        new Area().dao().deleteById(id);
        renderJson(SUCCESS_FEEDBACK);
	}

}