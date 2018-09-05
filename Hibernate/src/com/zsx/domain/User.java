package com.zsx.domain;

/**
 * 实体类中的数据类型尽量使用基本数据类型的-》封装类型
 * @author Administrator
 *
 */
public class User {
	
	private Integer id;//尽量使用包装类Integer
	private String name;
	private String password;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}
}
