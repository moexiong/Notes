package com.zsx.b_di;

public class BookServiceImpl implements BookService {

	//old方式  接口=实现类  耦合
	//private BookDao bd = new BookDaoImpl();
	
	//new方式  接口		    解耦
	private BookDao bd;//此处省略了get方法，因为用不上
	public void setBd(BookDao bd) {
		this.bd = bd;
	}

	@Override
	public void addBook() {
		this.bd.save();
	}

}
