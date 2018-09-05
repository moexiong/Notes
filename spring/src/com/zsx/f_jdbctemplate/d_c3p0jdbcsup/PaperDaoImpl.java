package com.zsx.f_jdbctemplate.d_c3p0jdbcsup;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.zsx.f_jdbctemplate.a_domain.Paper;
//通过继承JdbcDaoSupport来实现自动得到jdbcTemplate,需要给JdbcDaoSupport传入一个》数据源《让其自动配置,也可以配置jdbcTemplate后传入
//this.getJdbcTemplate()或super.getJdbcTemplate()
public class PaperDaoImpl extends JdbcDaoSupport{
	
	public List<Paper> findAllPaper(){
		String sql = "select * from t_paper";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Paper.class));
	}
	
}
