package com.zsx.f_jdbctemplate.b_api;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

public class FBtest {
	
	@Test
	public void fun(){
		/*C3P0连接池配置
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring001");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		*/
		
		//DBCP连接池配置
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring001");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		//JdbcTemplate配置
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		String sql = "INSERT INTO t_paper(name, author) VALUES(?, ?);";
		jdbcTemplate.update(sql,"digital","me");
	}
	
}
