package com.zsx.d_attributein.a_construct;

public class User {
	
	private Integer id;
	private String name;
	private Integer age;
	
	//无参构造方法，没有这个不注入参数会报错
	public User() {
		super();
	}
	//有参构造方法
	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//有参构造方法
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
