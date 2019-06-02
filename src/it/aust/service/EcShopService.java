package it.aust.service;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.Order;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
import it.aust.utils.webTag.PageModel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ietf.jgss.Oid;

public interface EcShopService {
	List<ArticleType> findAllFArticleType();

	List<Article> findArticlesByCode(String typeCode,String keyword, PageModel pageModel);

	List<ArticleType> findSeArticleTypes(String typecode);

	String findArticleTypeNameByCode(String typecode);
	//根据id获取物品信息
	Article getArticleById(String id);
	
	//根据用户名和密码获取用户信息
	User findUserByNameAndPass(String loginName, String password);
	
	//添加购物车信息
	void addShop(String id, int id2, String buyNum);
	
	//根据商品id和用户Id获取购物车信息
	ShopCar getShopCarByArticleIdAndUserId(String id, int id2);
	
	//更新购物车信息
	void updateShop(ShopCar shopcar);
	
	//根据用户id获取购物车信息
	List<ShopCar> getShopCarByUserId(User user);
	
	//删除购物车商品信息
	void deleteShopCar(String articleId, int id);

	//返回用户对象
	User getUserByLoginName(String loginName);
	
	//保存用户信息
	void save(User user,HttpServletRequest request);
	
	//激活账户信息
	void activeUser(String activeCode);
	
	//保存订单
	void saveOrder(Order order);
	
	//查询用户对应的所有订单信息
	List<Order> getAllOrderByUserId(int id);
	
	//更新用户信息
	void updateUserInfo(User user);

	//更新商品信息
	void updateArticleInfo(Article article);
	
	//更新订单状态
	void updateOrderInfo(Order order);
}
