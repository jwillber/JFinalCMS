package com.cms.controller.admin;

import com.cms.CommonAttribute;
import com.cms.Config;
import com.cms.routes.RouteMapping;
import com.cms.util.SystemUtils;
import com.cms.util.TemplateVariableUtils;

/**
 * Controller - 系统设置
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/config")
public class ConfigController extends BaseController {

    /**
     * 编辑
     */
    public void edit(){
        setAttr("config", SystemUtils.getConfig());
        setAttr("themes", SystemUtils.getThemes());
        setAttr("configSiteModelNames", CommonAttribute.configSiteModelNames);
        setAttr("configTypeNames", CommonAttribute.configTypeNames);
        setAttr("configWatermarkPositionNames", CommonAttribute.configWatermarkPositionNames);
        render(getView("config/edit"));
    }
    
    /**
     * 更新
     */
    public void update(){
        Config config = getBean(Config.class, "",true);
        if(config.getIsWatermarkEnabled()==null){
            config.setIsWatermarkEnabled(false);
        }
        if(config.getIsCronEnabled()==null){
            config.setIsCronEnabled(false);
        }
        SystemUtils.setConfig(config);
        TemplateVariableUtils.setBaseVariable();
        redirect("/admin/config/edit");
    }
}
