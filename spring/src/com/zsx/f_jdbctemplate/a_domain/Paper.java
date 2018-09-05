package com.zsx.f_jdbctemplate.a_domain;
/** 数据库配置
 *  CREATE DATABASE spring001;
 *	USE spring001;
 *	CREATE TABLE t_paper(
 *	id INT PRIMARY KEY AUTO_INCREMENT,
 *	NAME VARCHAR(20),
 *	author VARCHAR(20)
 *	);
 *	INSERT INTO t_paper(NAME,author) VALUES('hard','tom');
 *	INSERT INTO t_paper(NAME,author) VALUES('light','jerry');
 *	INSERT INTO t_paper(NAME,author) VALUES('normal','jack');
 */
public class Paper {
	
	private Integer id;
	private String name;
	private String author;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Paper [id=" + id + ", name=" + name + ", author=" + author
				+ "]";
	}
	
}
