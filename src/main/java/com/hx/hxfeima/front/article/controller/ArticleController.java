package com.hx.hxfeima.front.article.controller;


import com.hx.hxfeima.bo.Article;
import com.hx.hxfeima.core.model.DataGrid;
import com.hx.hxfeima.core.model.JsonMessage;
import com.hx.hxfeima.front.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;


/**
 * 文章管理
 */
@Controller
@RequestMapping("/article")


public class ArticleController {

	@Autowired
	private ArticleService hrArticleService;

	@RequestMapping(value = "/{page:\\d+}/{rows:\\d+}/articleList",method = RequestMethod.GET)
	@ResponseBody

	public DataGrid listPageJson(Article article, @PathVariable("page")Integer page, @PathVariable("rows")Integer rows) {
		DataGrid dataGrid = hrArticleService.query(article, page, rows);
		return dataGrid;
	}
	
	
	@RequestMapping(value = "/articleList",method = RequestMethod.GET)
	@ResponseBody

	public DataGrid listAllJson(Article article) {
		DataGrid dataGrid = hrArticleService.select(article);
		return dataGrid;
	}

	
	@RequestMapping(value = "/articleList/{id:\\d+}", method = RequestMethod.GET)
	@ResponseBody

	public Article select(@PathVariable("id") Long id) {
		
		Article  ar=hrArticleService.getArticleById(new BigDecimal(id));
		
		if(ar==null){
			
			 return new Article();
			
		}
		
		return ar;

	}

	@RequestMapping(value = "/articleList/add", method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public JsonMessage add(@RequestBody Article article,
						   RedirectAttributes redirectAttr) {
	
	 
		  
		    return hrArticleService.add(article);

	 

	}


	@RequestMapping(value = "/articleList", method = RequestMethod.PUT,consumes = "application/json")
	@ResponseBody
	public JsonMessage update(@RequestBody Article article ,
							  RedirectAttributes redirectAttr) {

		 	  
		 return hrArticleService.update(article);

		 
	}
	
	@RequestMapping(value = "/articleList/{id:\\d+}", method = RequestMethod.DELETE)
	@ResponseBody

	public JsonMessage delete(@PathVariable("id") Long id) {
		
	
		return hrArticleService.delete(new BigDecimal(id));

	}
	
	
}
