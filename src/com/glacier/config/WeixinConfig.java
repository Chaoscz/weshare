package com.glacier.config;

import java.util.Timer;

import com.glacier.controller.frontIndexController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;
import com.util.TaskService;


/**
 * @Desc jfinal 文件
 * @author Glacier
 * @time 2017年7月17日
 */
public class WeixinConfig extends JFinalConfig{
		
		private Timer timer = new Timer();
		
		/**配置路由**/
		@Override
		public void configRoute(Routes me) {
			me.add("/",frontIndexController.class);
		}
		
		@Override
		public void configConstant(Constants me) {
			PropKit.use("config.properties");	
			me.setEncoding("utf-8");
//			me.setDevMode(PropKit.getBoolean("devMode"));
			}
		
		@Override
		public void configPlugin(Plugins me) {
			//链接配置
			C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(),PropKit.get("driver").trim()); 
			me.add(c3p0Plugin); 
			// 配置ActiveRecord插件 
			ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin); 
			arp.setDialect(new AnsiSqlDialect()); 
			me.add(arp); 
		}

		@Override
		public void configInterceptor(Interceptors me) {}

		@Override
		public void configHandler(Handlers me) {
		}

		@Override
		public void afterJFinalStart() {
			// TODO Auto-generated method stub
			super.afterJFinalStart();
			timer.schedule(new TaskService(), 1000*60*60*6, 1000*60*60*24*4); 
		}

		@Override
		public void beforeJFinalStop() {
			// TODO Auto-generated method stub
			super.beforeJFinalStop();
			timer.cancel();  
		}

		@Override
		public void configEngine(Engine arg0) {
			// TODO Auto-generated method stub
			
		}
		

	}

