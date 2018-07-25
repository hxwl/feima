package com.hx.hxfeima.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {
	

    @Id
    @Column(name = "ID")
    private BigDecimal id;

	@Column(name = "CREATEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@org.hibernate.annotations.CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdate;

	@Column(name="AUTHOR")
    private String author;

	@Column(name="CONTENT")
    private String content;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getHits() {
		return hits;
	}

	public void setHits(BigDecimal hits) {
		this.hits = hits;
	}

	public BigDecimal getIspublication() {
		return ispublication;
	}

	public void setIspublication(BigDecimal ispublication) {
		this.ispublication = ispublication;
	}

	public BigDecimal getIstop() {
		return istop;
	}

	public void setIstop(BigDecimal istop) {
		this.istop = istop;
	}

	public String getSeodescription() {
		return seodescription;
	}

	public void setSeodescription(String seodescription) {
		this.seodescription = seodescription;
	}

	public String getSeokeywords() {
		return seokeywords;
	}

	public void setSeokeywords(String seokeywords) {
		this.seokeywords = seokeywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getArticlecategory() {
		return articlecategory;
	}

	public void setArticlecategory(BigDecimal articlecategory) {
		this.articlecategory = articlecategory;
	}

	

	@Column(name="hits")
    private BigDecimal hits;

	@Column(name="ISPUBLICATION")
    private BigDecimal ispublication;

	@Column(name="ISTOP")
    private BigDecimal istop;

	@Column(name="SEODESCRIPTION")
    private String seodescription;

	@Column(name="SEOKEYWORDS")
    private String seokeywords;

	@Column(name="TITLE")
    private String title;

	@Column(name="ARTICLECATEGORY")
    private BigDecimal articlecategory;

	
	@Transient
	private  String aname;

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}
  
    
  
}