package com.hx.hxfeima.core.bean;

import javax.servlet.http.HttpSession;

public class PageBean  {
	
	/**
	 * 当前页码
	 */
	private Integer page;
	
	/**
	 * 起始条数
	 */
	private Integer start;
	
	/**
	 * 每页条数
	 */
	private Integer limit;
	
	/**
	 * 上一页 标志
	 */	
	private HttpSession keyLocal;

	public void setKeyLocal(HttpSession keyLocal) {
		this.keyLocal = keyLocal;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(Integer page, Integer start, Integer limit) {
		super();
		this.page = page;
		this.start = start;
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Object getKey(String key) {
		return (Object)keyLocal.getAttribute(key);
	}

	public void setKey(String key,Object value) {
		this.keyLocal.setAttribute(key, value);
	}
	
	/**
	 * 判断是否翻到最后一页
	 * @return
	 */
	public boolean isLastPage(){
		boolean flag=false;
		Integer total=(Integer)keyLocal.getAttribute("total");
		int temp=total/limit+1;
		if(temp==page){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 最后一页数量
	 * @return
	 */
	public int getLastNum(){
		Integer total=(Integer)keyLocal.getAttribute("total");
		int num=total-(page-1)*limit+1;
		return num;
	}
	
}
