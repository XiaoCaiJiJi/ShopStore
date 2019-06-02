package it.aust.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.session.SqlSession;

import com.sun.mail.smtp.SMTPMessage;


import it.aust.annotation.AutoMapper;
import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.Order;
import it.aust.bean.OrderItem;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
import it.aust.mapper.ArticleMapper;
import it.aust.mapper.ArticleTypeMapper;
import it.aust.mapper.OrderItemMapper;
import it.aust.mapper.OrderMapper;
import it.aust.mapper.ShopCarMapper;
import it.aust.mapper.UserMapper;
import it.aust.service.EcShopService;
import it.aust.utils.ConnectionFactory;
import it.aust.utils.webTag.PageModel;

public class EcShopServiceImpl implements EcShopService{
	@AutoMapper
	private ArticleTypeMapper articleTypeMapper;
	@AutoMapper
	private ArticleMapper articleMapper;
	@AutoMapper
	private ShopCarMapper shopCarMapper;
	@AutoMapper
	private	UserMapper userMapper;
	@AutoMapper
	private	OrderMapper orderMapper;
	@AutoMapper
	private	OrderItemMapper orderItemMapper;
	
	
	//��ȡ���е�һ����������
	public List<ArticleType> findAllFArticleType() {
		// TODO Auto-generated method stub
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleTypeMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		List<ArticleType> articleTypes = articleTypeMapper.findAllFArticleType();
		//ConnectionFactory.closeSqlSession();
		return articleTypes;
	}
	

	//������Ʒ���ͻ�ȡ��Ʒ��Ϣ
	public List<Article> findArticlesByCode(String typeCode,String keyword,PageModel pageModel) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		int totalNum =articleMapper.findTotalNum(typeCode+"%",keyword);
		pageModel.setTotalNum(totalNum);
		List<Article> articles = articleMapper.findArticlesByCode(typeCode+"%",keyword,pageModel);
		//ConnectionFactory.closeSqlSession();
		return articles;
	}
	
	//��ȡ��Ʒ����
	public List<ArticleType> findSeArticleTypes(String typecode) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		List<ArticleType> articles = articleTypeMapper.findSeArticleTypes(typecode+"%");
		//ConnectionFactory.closeSqlSession();
		return articles;
	}

	//�����û���ѡ���ͻ�ȡ��������
	public String findArticleTypeNameByCode(String typecode) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		String typeName = articleTypeMapper.findArticleTypeNameByCode(typecode);
		//ConnectionFactory.closeSqlSession();
		return typeName;
	}

	
	//����id��ȡ��Ʒ��Ϣ
	public Article getArticleById(String id) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		Article article  = articleMapper.getArticleById(id);
		//ConnectionFactory.closeSqlSession();
		return article;
	}

	//�����û���½
	public User findUserByNameAndPass(String loginName, String password) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user  = userMapper.findUserByNameAndPass(loginName,password);
		//ConnectionFactory.closeSqlSession();
		return user;
	}

	
	public void addShop(String id, int id2, String buyNum) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		shopCarMapper.addShop(id,id2,buyNum);
		//sqlSession.commit();
		//	ConnectionFactory.closeSqlSession();
	}

	//�����û�Id����Ʒ��Ϣ�����ﳵ��Ϣ
	public ShopCar getShopCarByArticleIdAndUserId(String id, int id2) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		ShopCar shopCar=shopCarMapper.getShopCarByArticleIdAndUserId(id,id2);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
		return shopCar;
	}

	//���¹��ﳵ��Ϣ
	public void updateShop(ShopCar shopcar) {
		// TODO Auto-generated method stub
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		shopCarMapper.updateShop(shopcar);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
	}

	//�����û�id��ȡ���ﳵ��Ϣ
	public List<ShopCar> getShopCarByUserId(User user) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		List<ShopCar> shopCars=shopCarMapper.getShopCarByUserId(user);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
		return shopCars;
	}


	public void deleteShopCar(String articleId, int id) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		shopCarMapper.deleteShopCar(articleId, id);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
	}

	//�����û�����
	public User getUserByLoginName(String loginName) {
		return userMapper.getUserByLoginName(loginName);
	}

	//�����û���Ϣ
	public void save(User user,HttpServletRequest request) {
		try {
			userMapper.save(user);
			//����Properties����������װ�ʼ������������Ϣ
			Properties props = new Properties();
			//�����ʼ��������ĵ�ַ
			props.setProperty("mail.smtp.host", "smtp.163.com");
			//�ʼ���������ҪȨ�ޣ�ָ���û������¼�ʼ����������ܷ����ʼ�
			props.setProperty("mail.smtp.auth", "true");
			
			//����Authenticator��ʵ����ʵ���˻�������ļ�Ȩ��
	        Authenticator auth = new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("***@***.com", "*******");
		//��Ϊ�Լ������������
	            }

			
	        };
		
				//ͨ��Session���������������
				Session session = Session.getDefaultInstance(props,auth);
				
				//���������ʼ����󣬸ö�����Ҫ���ڷ�װ�ʼ������Ϣ������  ����  ������  �ʼ����ݵ�
				SMTPMessage message = new SMTPMessage(session);
				
				//�����ʼ�������
				message.setSubject("�û�ע�ἤ���ʼ�������ظ�������ָ������");
				//�����ʼ�������
				message.setContent("<a href='http://127.0.0.1:8080/"+request.getContextPath()+"/active.action?activeCode="+user.getActive()+"' target='_blank'>��ϲ��ע��ɹ������������ӽ��м������ظ���</a>", "text/html;charset=utf-8");
				
				//���÷�����
				message.setFrom(new InternetAddress("pascalQAQ@163.com"));
				
				//�����ռ���   �����������ɣ�TO(�ռ���)��CC(����)��BCC(����)
				message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
				
				//�����ʼ�
				Transport.send(message);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
	}


	public void activeUser(String activeCode) {
		// TODO Auto-generated method stub
		User  user = userMapper.getUserByCode(activeCode);
		userMapper.activeUser(activeCode);
	}


	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		//�ȱ��涩���ٱ��涩����ϸ�������Ͷ�����ϸһ�Զ�
		orderMapper.saveOrder(order);
		//order�����洢orderId
		int orderID = order.getId();
		System.out.println(orderID);
		for(int i =0; i <order.getItems().size();i++){
			//���ö�����ϸ��id
			order.getItems().get(i).setOrderId(orderID);
			//���涩����ϸ����Ϣ
			orderItemMapper.saveOrderItem(order.getItems().get(i));
		}
		
		//�ύ��������չ��ﳵ(����userId����ec_shop��)
		shopCarMapper.removeShopCarByUserId(order.getUserId());
	}


	public List<Order> getAllOrderByUserId(int id) {
		// TODO Auto-generated method stub
		return orderMapper.getAllOrderByUserId(id);
	}


	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		 userMapper.updateUserInfo(user);
	}


	public void updateArticleInfo(Article article) {
		// TODO Auto-generated method stub
		articleMapper.updateArticleInfo(article);
	}

	//���¶���״̬
	public void updateOrderInfo(Order order) {
		// TODO Auto-generated method stub
		orderMapper.updateOrderInfo(order);
	}
	
}
