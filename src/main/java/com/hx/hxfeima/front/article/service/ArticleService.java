package com.hx.hxfeima.front.article.service;





import com.hx.hxfeima.bo.Article;
import com.hx.hxfeima.core.model.DataGrid;
import com.hx.hxfeima.core.model.JsonMessage;

import java.math.BigDecimal;





public interface ArticleService {
	
	
	  public DataGrid query(Article article, Integer page, Integer rows);  //分页查询
	  

	  public DataGrid select(Article article);  //查询全部
	
	  public Article getArticleById(BigDecimal id);   //单个查询
	  
	  public JsonMessage add(Article article) ;       //增加
	 
	  public JsonMessage delete(BigDecimal id) ;         //删除
	 
	  public JsonMessage update(Article article);  //更新
	  
	  
	  public  boolean  saveTest(Article article); 
	  
	
	
	
	
	

	

}
