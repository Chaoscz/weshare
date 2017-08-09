package com.ws.dao;

import java.security.*;
import java.math.*;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class userDAO {

	/**
	 * @Desc 用户登录
	 * @author Glacier 
	 * @date 2017年8月9日
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public Record login(String userName, String pwd) throws NoSuchAlgorithmException {
		
		//MD5 加密
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(pwd.getBytes(),0,pwd.length());
	    String password =  new BigInteger(1,m.digest()).toString(16);
		Record userInfo = Db.findFirst("SELECT id,gid,nick,headimg,score,grade,pwd from h_account where state <> 0 and nick =? and pwd = ?",userName,password);
		
		// System.out.println(userName);
		// System.out.println(password);
		//System.out.println(userInfo);
		
		return userInfo;
	}

	
	/**
	 * @Desc  注册
	 * @author Glacier 
	 * @date 2017年8月9日
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public Record registered(String userName, String pwd) throws NoSuchAlgorithmException {
		
			//MD5 加密
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(pwd.getBytes(),0,pwd.length());
			String password =  new BigInteger(1,m.digest()).toString(16);
			
			Record h_account = new Record();
			h_account.set("nick", userName);
			h_account.set("headimg", "http://i4.bvimg.com/596193/e4da0827c385b6a5s.jpg");
			h_account.set("pwd", password);
			Db.save("h_account", h_account);
			
			Long id = h_account.getLong("id");
			Record user = Db.findById("h_account", id.intValue());
			return user;
	}
	
	
	
}
