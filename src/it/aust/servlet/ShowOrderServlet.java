package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.Order;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.ShopContant;


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�ύ������չʾ�û��Ķ�����Ϣ
@WebServlet("/showOrderList.do")
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����������
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//������������
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		
		//��ȡ���е�һ����Ʒ����
		List<ArticleType> firstTypes = service.findAllFArticleType();
		request.setAttribute("firstTypes", firstTypes);
		
		User user = (User)request.getSession().getAttribute(ShopContant.SESSION_USER);
		
		//���ݵ�ǰ�û�id��ȡ������Ϣ
		List<Order> orders = service.getAllOrderByUserId(user.getId());
		
		request.setAttribute("orderList", orders);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/orderList.jsp").forward(request, response);
	}
	
}
