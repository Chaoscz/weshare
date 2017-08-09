package com.ws.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class artDAO {
	
	/**
	 * @Desc 获取文章列表
	 * @author Glacier 
	 * @date 2017年8月8日
	 * @return artlist
	 */
	public static List<Record> getArticleList() {
		//根据 istop字段 倒序 没有被删除的文章
		List<Record> artlist = Db.find("SELECT a.id,a.aid,b.nick,b.headimg,a.ctime,a.comsum,a.title,a.artlabel,a.watchsum,a.istop "
				+ "FROM h_article a LEFT JOIN h_account b on  b.id = a.aid   "
				+ "where astate <> 0 ORDER BY istop DESC LIMIT 20");
		return artlist;
	}
	
	/**
	 * @Desc 分页查询
	 * @author Glacier 
	 * @date 2017年8月8日
	 * @param pageSize 每页显示的数据条数
	 * @param curPage 当前显示的页数
	 * @param sql 查询条件
	 * @return
	 */
	public static List<Record> pageQuery(int pageSize,int curPage,String sql) {
		// select * from user order by id desc limit （curPage-1）*pageSize,pageSize
		List<Record> querylist = Db.find("SELECT a.id,a.aid,b.nick,b.headimg,a.ctime,a.comsum,a.title,a.artlabel,a.watchsum,a.istop "
				+ "FROM h_article a LEFT JOIN h_account b on  b.id = a.aid  "
				+ " where astate <> 0 "+ sql
				+ "ORDER BY istop DESC LIMIT ?,?",(curPage-1)*pageSize,pageSize);
//		System.out.println("测试输出："+ querylist);
		return querylist;
	}
	
	
	
	
	

}
