package com.cms.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;
/**
 * Handler - 资源访问控制
 * 
 * 
 * 
 */
public class ResourcesHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {

		if (isDisableAccess(target)) {
			HandlerKit.renderError404(request, response, isHandled);
		}
		next.handle(target, request, response, isHandled);
		
	}

	private static boolean isDisableAccess(String target) {
		// 防止直接访问模板文件
		if (target.endsWith(".html") && target.startsWith("/templates")) {
			return true;
		}
		return false;
	}

}
