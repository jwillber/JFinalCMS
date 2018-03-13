package com.cms.config;

import java.util.List;

import com.cms.ClassScaner;
import com.cms.CommonAttribute;
import com.cms.entity._MappingKit;
import com.cms.handler.ResourcesHandler;
import com.cms.routes.RouteMapping;
import com.cms.util.TemplateVariableUtils;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

/**
 * 配置
 * 
 *
 */
public class CmsConfig extends JFinalConfig{
    
    /**
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     * 
     * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
     * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
     * -XX:PermSize=64M -XX:MaxPermSize=256M
     */
    public static void main(String[] args) {
        /**
         * 特别注意：Eclipse 之下建议的启动方式
         */
        JFinal.start("src/main/webapp", 8888, "/", 5);
        
        /**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        // JFinal.start("src/main/webapp", 80, "/");
    }
	
	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
	    PropKit.use(CommonAttribute.CONFIG_PROPERTIES);
		/** 开发者模式 */
		me.setDevMode(PropKit.getBoolean("system.devMode", false));
		/** 配置页面 */
		me.setViewType(ViewType.FREE_MARKER);
		me.setViewExtension(CommonAttribute.VIEW_EXTENSION);
		me.setError404View(CommonAttribute.FRONT_RESOURCE_NOT_FOUND_VIEW);
		me.setError500View(CommonAttribute.FRONT_ERROR_VIEW);
		/** 编码配置 */
		me.setEncoding(CommonAttribute.UTF_8);
		/** 上传文件目录 */
		me.setBaseUploadPath(CommonAttribute.BASE_UPLOAD_PATH);
		/** 设置参数分隔符 */
		me.setUrlParaSeparator(CommonAttribute.URL_PARA_SEPARATOR);
		/** 设置JSON */
		me.setJsonFactory(new FastJsonFactory());
		me.setJsonDatePattern(CommonAttribute.JSON_DATE_PATTERN);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		List<Class<Controller>> controllerClassList = ClassScaner.scanSubClass(Controller.class,true,false);
		if (controllerClassList != null) {
			for (Class<?> clazz : controllerClassList) {
				RouteMapping urlMapping = clazz.getAnnotation(RouteMapping.class);
				if (null != urlMapping && StrKit.notBlank(urlMapping.url())) {
					me.add(urlMapping.url(), (Class<? extends Controller>) clazz);
				}
			}
		}
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		/** 数据库配置 */
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password"),PropKit.get("jdbc.driver"));
        me.add(druidPlugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		/** 打印sql */
		activeRecordPlugin.setShowSql(true);
		me.add(activeRecordPlugin);
		/** 表对应的实体配置 */
		_MappingKit.mapping(activeRecordPlugin);
		/** 定时任务 */
		me.add(new Cron4jPlugin(CommonAttribute.JOB_PROPERTIES));
		/** 缓存 */
		me.add(new EhCachePlugin());  
	}

	
	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		/** session */
		me.add(new SessionInViewInterceptor());
		/** 事物 */
		me.add(new TxByMethods("save","update"));
	}

	/**
	 * 配置Handler
	 */
	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new ContextPathHandler("base"));      
		me.add(new ResourcesHandler());
	}

	/**
	 * 程序启动后
	 */
	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		/** freemarker */
		TemplateVariableUtils.setCommonConfig();
		TemplateVariableUtils.setBaseVariable();
		TemplateVariableUtils.setDirectiveVariable();
		TemplateVariableUtils.setMethodVariable();
		super.afterJFinalStart();
	}

	/**
	 * 程序停止前
	 */
	@Override
	public void beforeJFinalStop() {
		// TODO Auto-generated method stub
		super.beforeJFinalStop();
	}

	/**
	 * 标签配置
	 */
	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
	}
}
