package com.zsx.b_di;

public class BookDaoImpl implements BookDao {

	@Override
	public void save() {
		System.out.println("b_di --> save执行了");
	}

}
