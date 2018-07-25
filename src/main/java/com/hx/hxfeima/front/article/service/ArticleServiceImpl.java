package com.hx.hxfeima.front.article.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import com.hx.hxfeima.base.dao.BaseDao;
import com.hx.hxfeima.bo.Article;
import com.hx.hxfeima.core.model.DataGrid;
import com.hx.hxfeima.core.model.JsonMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 文章列表service
 * 
 * @author liushuai
 * 
 */
@Service("articleListService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private BaseDao baseDao;
	


	public DataGrid query(Article article, Integer page, Integer rows) {
		// TODO Auto-generated method stub

		StringBuffer queryHql = new StringBuffer("from Article r where 1=1");
		StringBuffer countHql = new StringBuffer(
				"select count (*) from Article r where 1=1");

		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(article.getTitle())) {
//			queryHql.append(" and r.title=:title ");
//			countHql.append(" and  r.title=:title ");
//			queryMap.put("title", article.getTitle());
			queryHql.append(" and r.title like '%"+ article.getTitle() + "%'" );
			countHql.append(" and  r.title like '%"+ article.getTitle() + "%'" );
		}
		queryHql.append(" order by r.createdate desc");
		return new DataGrid(baseDao.find(queryHql.toString(), queryMap, page,
				rows), baseDao.count(countHql.toString(), queryMap));

	}

	@Override
	public DataGrid select(Article article) {
		// TODO Auto-generated method stub

		StringBuffer queryHql = new StringBuffer("from Article r where 1=1");
		StringBuffer countHql = new StringBuffer(
				"select count (*) from Article r where 1=1");

		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(article.getTitle())) {
			queryHql.append(" and r.title=:title ");
			countHql.append(" and r.title=:title ");
			queryMap.put("title", article.getTitle());
		}
		queryHql.append(" order by r.createdate desc");
		return new DataGrid(baseDao.find(queryHql.toString(), queryMap),
				baseDao.count(countHql.toString(), queryMap));

	}

	@Override
	public Article getArticleById(BigDecimal id) {
		// TODO Auto-generated method stub
		return (Article) baseDao.get(Article.class, id);
	}

	@Override
	public JsonMessage add(Article article) {
		// TODO Auto-generated method stub
		
		try {
			article.setHits(new BigDecimal(0));
			Serializable ser = baseDao.save(article);
			if (ser != null) {

				return new JsonMessage(true, "操作成功", 1, ser.toString());

			} else {

				return new JsonMessage(false, "操作失败", 0);

			}

		} catch (Exception e) {

			return new JsonMessage(false, "操作失败", 0);
		}

	}

	@Override
	public JsonMessage delete(BigDecimal id) {
		// TODO Auto-generated method stub
		Serializable ser = 1;
		if (baseDao.delete("delete Article where id=?", new Object[] { id }) <= 0) {
			ser = null;
		}
		if (ser != null) {

			return new JsonMessage(true, "操作成功", 1, ser.toString());

		} else {

			return new JsonMessage(false, "操作失败", 0);

		}
	}

	@Override
	public JsonMessage update(Article article) {
		// TODO Auto-generated method stub

		try {
			baseDao.update(article);

			return new JsonMessage(true, "操作成功", 1);

		} catch (Exception e) {
			e.printStackTrace();

			return new JsonMessage(false, "操作失败", 0);
		}

	}

	@Override
	public boolean saveTest(Article article) {
		

		// TODO Auto-generated method stub
		
	         Article  ar= this.getArticleById(new BigDecimal(1462));
		 
			
			 ar.setAuthor("梦4");
			
			
			
			this.update(ar);
			
			

		
//			Article  ar2=getArticleById(new BigDecimal(ja.getData()));
//			
//			
//			 ar.setAuthor("梦2");
//			
//			 ar.setIstop(new BigDecimal(0));
//			  
//			  
//			 update(ar2);
		
		
		
		
		
		return true;
	}

}
