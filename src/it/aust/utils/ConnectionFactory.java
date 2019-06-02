package it.aust.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class ConnectionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	//����SqlSession�Ƿ��̰߳�ȫ��java���ṩ��һ����ThreadLocal�������ڱ�֤���̰߳�ȫ
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

	//��ʼ������Դ(dataSourse)
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

	
	//��ȡ����   SqlSession�Ƿ��̰߳�ȫ�Ŀ���ͨ��ThreadLocal����֤�����̰߳�ȫ    ����  ����
	public static SqlSession getSqlSession(){
		
		//�ȴ�threadLocal�л�ȡSqlSession
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession==null){
			sqlSession = sqlSessionFactory.openSession();
			//�����Ӵ����threadLocal
			threadLocal.set(sqlSession);   
		}
		
		return sqlSession;	
	}
	
	
	
	//�ر�����
	public static void closeSqlSession(){
	    
		//��threadLocal�л�ȡSqlSession
		SqlSession sqlSession = threadLocal.get(); 
		if(sqlSession!=null){
			sqlSession.close();
			//�����Ӵ�threadLocal���Ƴ�
			threadLocal.remove();
		}
		
	
	}

}
