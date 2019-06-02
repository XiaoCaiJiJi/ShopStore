package it.aust.servlet.service.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import com.sun.net.httpserver.Filter;


import it.aust.annotation.AutoMapper;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.utils.ConnectionFactory;

public class ServiceProxy {

	@SuppressWarnings("unchecked")
	public <T> T bind(final T obj) {
		// TODO Auto-generated method stub
		return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				SqlSession sqlSession =null;
				try{	
					sqlSession = ConnectionFactory.getSqlSession();
					//获取被代理对象的类型
					Class<?> clazz = obj.getClass();
					//获取被代理对象中属性
					Field[] fields =clazz.getDeclaredFields();
					for(Field field:fields){
						//判断属性是否有注解@AutoMapper,有则注入
						AutoMapper autoMapper = field.getAnnotation(AutoMapper.class);
						if(autoMapper != null &&autoMapper.required()){
							if(!field.isAccessible()){
								field.setAccessible(true);
							}
							field.set(obj, sqlSession.getMapper(field.getType()));
						}
					}
					Object result = method.invoke(obj, args);
					return result;
				}catch (Exception e) {
					e.printStackTrace();
					sqlSession.rollback();
				}finally{
					sqlSession.commit();
					ConnectionFactory.closeSqlSession();
				}
				return null;
			}
		});
	}

}
