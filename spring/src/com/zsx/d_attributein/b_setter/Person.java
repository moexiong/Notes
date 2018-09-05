package com.zsx.d_attributein.b_setter;

public class Person {
	
	private String pname;
	private Integer page;
	private Address address;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person [pname=" + pname + ", page=" + page + ", address="
				+ address + "]";
	}
	
}
