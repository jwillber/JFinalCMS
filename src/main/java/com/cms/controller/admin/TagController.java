package com.cms.controller.admin;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.cms.entity.Tag;
import com.cms.routes.RouteMapping;
/**
 * Controller - 标签
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/tag")
public class TagController extends BaseController {

    /**
     * 添加
     */
    public void add() {
        render(getView("tag/add"));
    }

    /**
     * 保存
     */
    public void save() {
        Tag tag = getModel(Tag.class,"",true); 
        tag.setCreateDate(new Date());
        tag.setModifyDate(new Date());
        tag.save();
        redirect(getListQuery("/admin/tag/list"));
    }

    /**
     * 编辑
     */
    public void edit() {
        Long id = getParaToLong("id");
        setAttr("tag", new Tag().dao().findById(id));
        render(getView("tag/edit"));
    }

    /**
     * 更新
     */
    public void update() {
        Tag tag = getModel(Tag.class,"",true); 
        tag.setModifyDate(new Date());
        tag.update();
        redirect(getListQuery("/admin/tag/list"));
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
        setAttr("page", new Tag().dao().findPage(name,pageNumber,PAGE_SIZE));
        setAttr("name", name);
        render(getView("tag/list"));
    }

    /**
     * 删除
     */
    public void delete() {
        Long ids[] = getParaValuesToLong("ids");
        if(ArrayUtils.isNotEmpty(ids)){
            for(Long id:ids){
                new Tag().dao().deleteById(id);
            }
        }
        renderJson(SUCCESS_FEEDBACK);
    }
}
