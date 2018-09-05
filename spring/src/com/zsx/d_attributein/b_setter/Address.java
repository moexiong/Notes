package com.zsx.d_attributein.b_setter;

public class Address {
	
	private String aname;
	private String atel;
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAtel() {
		return atel;
	}
	public void setAtel(String atel) {
		this.atel = atel;
	}
	
	@Override
	public String toString() {
		return "Address [aname=" + aname + ", atel=" + atel + "]";
	}
	
}
