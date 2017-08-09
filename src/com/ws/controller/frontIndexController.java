package com.ws.controller;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.ws.dao.artDAO;
import com.ws.dao.userDAO;

public class frontIndexController extends Controller {
	
	public void index() {
		List<Record> artlist = artDAO.getArticleList();
		setAttr("user", getSessionAttr("acount"));
		setAttr("artlist", artlist);
		render("index.html");
	}
	
	/**
	 * @Desc 分页
	 * @author Glacier 
	 * @date 2017年8月8日
	 * pageSize 每页显示的数据条数
	 * curPage 当前显示的页数
	 */
	public void getpagelist() {
		// int pageSize = getParaToInt("pageSize"); 
		int pageSize = 20; //每页20条
		int curPage = getParaToInt(0);
		List<Record> querylist = artDAO.pageQuery(pageSize, curPage,"");
		setAttr("user", getSessionAttr("acount"));
		setAttr("artlist", querylist);
		render("index.html");
	}
	
	
	
	/**
	 * @Desc 用户登录
	 * @author Glacier 
	 * @throws NoSuchAlgorithmException 
	 * @date 2017年7月18日
	 */
	public void login() throws NoSuchAlgorithmException {
		String userName = getPara("userName");
		String pwd = getPara("password");
		Record user =  new userDAO().login(userName, pwd);
		if (user != null ) {
			setAttr("user", user);
			setSessionAttr("acount", user);
			render("index.html");
		}else {
			setAttr("msg", "用户名或密码错误!");
			render("index.html");
		}
	
	}
	
	/**
	 * @Desc 用户注册
	 * @author Glacier 
	 * @date 2017年8月9日
	 * @throws NoSuchAlgorithmException
	 */
	public void registered() throws NoSuchAlgorithmException {
		
		String userName =getPara("userName");
		String pwd = getPara("password");
		Record user = new userDAO().registered(userName,pwd);
		
		setAttr("user", user);
		setSessionAttr("acount", user);
		render("index.html");
		
	}
	
	
	
	
}
