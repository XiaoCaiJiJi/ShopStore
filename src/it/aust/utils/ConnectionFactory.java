package it.aust.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class ConnectionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	//由于SqlSession是非线程安全，java中提供了一个类ThreadLocal可以用于保证类线程安全
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	//初始化数据源(dataSourse)
	static{
		
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	//获取连接   SqlSession是非线程安全的可以通过ThreadLocal来保证该类线程安全    张三  李四
	public static SqlSession getSqlSession(){
		
		//先从threadLocal中获取SqlSession
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession==null){
			sqlSession = sqlSessionFactory.openSession();
			//将连接存放在threadLocal
			threadLocal.set(sqlSession);   
		}
		
		return sqlSession;	
	}
	
	
	
	//关闭连接
	public static void closeSqlSession(){
	    
		//从threadLocal中获取SqlSession
		SqlSession sqlSession = threadLocal.get(); 
		if(sqlSession!=null){
			sqlSession.close();
			//将连接从threadLocal中移除
			threadLocal.remove();
		}
		
	
	}

}
