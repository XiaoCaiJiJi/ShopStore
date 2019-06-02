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
	
	
	//获取所有的一级服务类型
	public List<ArticleType> findAllFArticleType() {
		// TODO Auto-generated method stub
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleTypeMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		List<ArticleType> articleTypes = articleTypeMapper.findAllFArticleType();
		//ConnectionFactory.closeSqlSession();
		return articleTypes;
	}
	

	//根据物品类型获取物品信息
	public List<Article> findArticlesByCode(String typeCode,String keyword,PageModel pageModel) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		int totalNum =articleMapper.findTotalNum(typeCode+"%",keyword);
		pageModel.setTotalNum(totalNum);
		List<Article> articles = articleMapper.findArticlesByCode(typeCode+"%",keyword,pageModel);
		//ConnectionFactory.closeSqlSession();
		return articles;
	}
	
	//获取商品类型
	public List<ArticleType> findSeArticleTypes(String typecode) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		List<ArticleType> articles = articleTypeMapper.findSeArticleTypes(typecode+"%");
		//ConnectionFactory.closeSqlSession();
		return articles;
	}

	//根据用户所选类型获取类型名字
	public String findArticleTypeNameByCode(String typecode) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleTypeMapper articleMapper = sqlSession.getMapper(ArticleTypeMapper.class);
		String typeName = articleTypeMapper.findArticleTypeNameByCode(typecode);
		//ConnectionFactory.closeSqlSession();
		return typeName;
	}

	
	//根据id获取物品信息
	public Article getArticleById(String id) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		Article article  = articleMapper.getArticleById(id);
		//ConnectionFactory.closeSqlSession();
		return article;
	}

	//处理用户登陆
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

	//根据用户Id和商品信息处理购物车信息
	public ShopCar getShopCarByArticleIdAndUserId(String id, int id2) {
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		ShopCar shopCar=shopCarMapper.getShopCarByArticleIdAndUserId(id,id2);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
		return shopCar;
	}

	//更新购物车信息
	public void updateShop(ShopCar shopcar) {
		// TODO Auto-generated method stub
		//SqlSession sqlSession = ConnectionFactory.getSqlSession();
		//ShopCarMapper shopCarMapper = sqlSession.getMapper(ShopCarMapper.class);
		shopCarMapper.updateShop(shopcar);
		//sqlSession.commit();
		//ConnectionFactory.closeSqlSession();
	}

	//根据用户id获取购物车信息
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

	//返回用户对象
	public User getUserByLoginName(String loginName) {
		return userMapper.getUserByLoginName(loginName);
	}

	//保存用户信息
	public void save(User user,HttpServletRequest request) {
		try {
			userMapper.save(user);
			//创建Properties对象，用来封装邮件服务器相关信息
			Properties props = new Properties();
			//设置邮件服务器的地址
			props.setProperty("mail.smtp.host", "smtp.163.com");
			//邮件服务器需要权限，指定用户必须登录邮件服务器才能发送邮件
			props.setProperty("mail.smtp.auth", "true");
			
			//创建Authenticator的实例，实现账户、密码的鉴权。
	        Authenticator auth = new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("***@***.com", "*******");
		//改为自己的邮箱服务器
	            }

			
	        };
		
				//通过Session与服务器建立连接
				Session session = Session.getDefaultInstance(props,auth);
				
				//创建发送邮件对象，该对象主要用于封装邮件相关信息，比如  主题  发件人  邮件内容等
				SMTPMessage message = new SMTPMessage(session);
				
				//设置邮件的主题
				message.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
				//设置邮件的内容
				message.setContent("<a href='http://127.0.0.1:8080/"+request.getContextPath()+"/active.action?activeCode="+user.getActive()+"' target='_blank'>恭喜您注册成功，请点击该链接进行激活，无需回复！</a>", "text/html;charset=utf-8");
				
				//设置发件人
				message.setFrom(new InternetAddress("pascalQAQ@163.com"));
				
				//设置收件人   接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
				message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
				
				//发送邮件
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
		//先保存订单再保存订单明细，订单和订单明细一对多
		orderMapper.saveOrder(order);
		//order对象会存储orderId
		int orderID = order.getId();
		System.out.println(orderID);
		for(int i =0; i <order.getItems().size();i++){
			//设置订单明细的id
			order.getItems().get(i).setOrderId(orderID);
			//保存订单明细的信息
			orderItemMapper.saveOrderItem(order.getItems().get(i));
		}
		
		//提交订单后，清空购物车(根据userId查找ec_shop表)
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

	//更新订单状态
	public void updateOrderInfo(Order order) {
		// TODO Auto-generated method stub
		orderMapper.updateOrderInfo(order);
	}
	
}
