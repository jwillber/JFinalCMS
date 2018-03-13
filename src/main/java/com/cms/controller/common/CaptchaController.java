package com.cms.controller.common;

import com.cms.routes.RouteMapping;
import com.jfinal.core.Controller;
@RouteMapping(url = "/common/captcha")
public class CaptchaController extends Controller{

    public void image(){
        renderCaptcha();
    }
}
