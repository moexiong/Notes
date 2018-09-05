package com.zsx.f_jdbctemplate.c_dbcp;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.zsx.f_jdbctemplate.a_domain.Paper;

public class PaperDaoImpl {
	//通过在配置文件中配置JdbcTemplate,在注入到PaperDaoImpl中
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Paper> findAllPaper(){
		String sql = "select * from t_paper";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Paper.class));
	}
	
}
