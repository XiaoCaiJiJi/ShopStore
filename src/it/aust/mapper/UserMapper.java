package it.aust.mapper;

import it.aust.bean.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * UserMapper 数据访问类
 * @author CHUNLONG.LUO
 * @version 1.0
 */
public interface UserMapper {

	//根据用户名以及密码获取用户信息
	@Select("select * from ec_user where login_name = #{loginName} and password = #{password}")
	User findUserByNameAndPass(@Param("loginName")String loginName, @Param("password")String password);

	//根据登录名获取用户信息
	@Select("select * from ec_user where login_name = #{loginName}")
	User getUserByLoginName(String loginName);

	@Insert("INSERT INTO ec_user(LOGIN_NAME,PASSWORD,NAME,SEX,EMAIL,PHONE,ADDRESS,CREATE_DATE,ACTIVE) VALUES(#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{createDate},#{active})")
	void save(User user);

	@Select("select * from ec_user where active = #{activeCode}")
	User getUserByCode(String activeCode);

	@Update("update ec_user set disabled = 1,active='' where active = #{activeCode}")
	void activeUser(String activeCode);

	@Update("update ec_user set  password = #{password},sex=#{sex},phone=#{phone},address=#{address} where login_name = #{loginName}")
	void updateUserInfo(User user);

}