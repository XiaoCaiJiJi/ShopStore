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
	//����id��ȡ��Ʒ��Ϣ
	Article getArticleById(String id);
	
	//�����û����������ȡ�û���Ϣ
	User findUserByNameAndPass(String loginName, String password);
	
	//��ӹ��ﳵ��Ϣ
	void addShop(String id, int id2, String buyNum);
	
	//������Ʒid���û�Id��ȡ���ﳵ��Ϣ
	ShopCar getShopCarByArticleIdAndUserId(String id, int id2);
	
	//���¹��ﳵ��Ϣ
	void updateShop(ShopCar shopcar);
	
	//�����û�id��ȡ���ﳵ��Ϣ
	List<ShopCar> getShopCarByUserId(User user);
	
	//ɾ�����ﳵ��Ʒ��Ϣ
	void deleteShopCar(String articleId, int id);

	//�����û�����
	User getUserByLoginName(String loginName);
	
	//�����û���Ϣ
	void save(User user,HttpServletRequest request);
	
	//�����˻���Ϣ
	void activeUser(String activeCode);
	
	//���涩��
	void saveOrder(Order order);
	
	//��ѯ�û���Ӧ�����ж�����Ϣ
	List<Order> getAllOrderByUserId(int id);
	
	//�����û���Ϣ
	void updateUserInfo(User user);

	//������Ʒ��Ϣ
	void updateArticleInfo(Article article);
	
	//���¶���״̬
	void updateOrderInfo(Order order);
}
