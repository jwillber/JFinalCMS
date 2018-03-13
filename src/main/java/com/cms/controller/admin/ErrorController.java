package com.cms.controller.admin;

import com.cms.CommonAttribute;
import com.cms.routes.RouteMapping;

/**
 * Controller - 错误
 * 
 * 
 * 
 */
@RouteMapping(url = "/admin/error")
public class ErrorController extends BaseController {

    /**
     * 权限错误
     */
    public void unauthorized() {
        render(CommonAttribute.ADMIN_UNAUTHORIZED_VIEW);
    }
    
   /**
     * 异常
     */
    public void exception() {
        render(CommonAttribute.ADMIN_ERROR_VIEW);
    }
}
