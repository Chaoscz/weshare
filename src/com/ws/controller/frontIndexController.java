package com.ws.controller;


import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class frontIndexController extends Controller {
	
	public void index() {
		render("index.html");
	}
	
	/**
	 * @Desc 用户登录
	 * @author Glacier 
	 * @date 2017年7月18日
	 */
	public void login() {
		String userName = getPara("userName");
		String cardId = getPara("pwd");
		List<Record> user = Db.find("select * from user where cardid = ?",cardId);
		if (user.size() != 0) {
			//获取用户id
			setAttr("user", user.get(0));
			render("canteen.html");
		}else {
			render("index.html");
		}
	}
	
	/**
	 * @Desc 接受表单
	 * @author Glacier 
	 * @throws Exception 
	 * @date 2017年7月18日
	 */
	public void formsend() throws Exception {
		String userid = getPara("userid");
		int i ;
		for (i = 1; i <= 10; i++) {
			String score = getPara("v"+i+"");
			String info = getPara("txt"+i+"");
			Record f_answer = new Record();
			f_answer.set("score", score);
			f_answer.set("info", info);
			f_answer.set("uid", userid);
			f_answer.set("qid", i);
			Db.save("answer", f_answer);
		}
		
		String like = getPara("like");
		String hate = getPara("hate");
		String addfood = getPara("addfood");
		String foodstyle = getPara("foodstyle");
		String up = getPara("up");
		
		int flat =  Db.update("INSERT into answer(score,info,uid,qid) "
				+ "VALUES('','"+ like +"',"+userid+","+ i++ +"),"
				+ "		 ('','"+ hate +"',"+userid+","+ i++ +"),"
				+ "		 ('','"+ addfood +"',"+userid+","+ i++ +"),"
				+ "		 ('','"+ foodstyle +"',"+userid+","+ i++ +"),"
				+ "		 ('','"+ up +"',"+userid+","+ i++ +")");
		
		System.out.println(flat);
		render("success.html");
	}
	
}
