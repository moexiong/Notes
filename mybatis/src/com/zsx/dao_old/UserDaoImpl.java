package com.zsx.dao_old;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zsx.pojo.User;
//old版本的DAO层写法，使用了user_old.xml配置
public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sessionFactory;
	public UserDaoImpl(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User findUserById(Integer id) {
		SqlSession session = sessionFactory.openSession();
		return session.selectOne("user.findUserById", id);
	}

}
