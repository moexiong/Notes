package com.zsx.dao_mapper;

import java.util.List;

import com.zsx.pojo.QueryVo;
import com.zsx.pojo.User;
/**
 * 	MyBatis中的新版DAO层的编写，不需要编写实现类，但必须遵循以下4个原则
 *  	1.命名空间一般为对象的DAO接口类的全类名
 *		2.对应的配置文件中SQL语句的id值与接口中的方法名一致
 *		3.对应的配置文件中SQL语句的传入参数的类型与接口中的方法的传入参数的类型一致
 *		4.对应的配置文件中SQL语句的返回类型与接口中的方法返回类型一致
 */
public interface UserMapper {
	//User == user.xml->resultType="com.zsx.pojo.User"
	//findUserById == id="findUserById"
	//Integer == parameterType="Integer"
	public User findUserById(Integer id);
	
	//封装User到QueryVo，通过user中的username来模糊查询user
	public List<User> findUsersByQueryVo(QueryVo vo);
	
	//查询user表中数据的数目
	public Integer countUsers();
	
	//sql片段用于提取出公共部分的sql语句
	//通过性别或名称来查询用户
	public List<User> findUsersBySexOrUsername(User user);
	
	//根据数个id来查询数个用户(foreach遍历)
	public List<User> findUsersByIds(Integer[] ids);
	
	//一对多关系通过用户id来查询用户及其订单信息
	public User findUserOrdersById(Integer id);
}
