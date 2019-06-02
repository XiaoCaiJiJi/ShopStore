package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.Order;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.webTag.PageModel;


import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orderManager.action")
public class OrderManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//创建代理类
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//创建服务层对象
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		String orderID = request.getParameter("orderID");
		String userID = request.getParameter("userID");
		int oID = Integer.valueOf(orderID);
		List<Order> orders = service.getAllOrderByUserId(Integer.valueOf(userID));
		for (Order order:orders) {
			System.out.println(order.getId());
			if(order.getId()==oID){
				order.setSendDate(new Date());
				service.updateOrderInfo(order);
				request.setAttribute("message", "订单信息修改成功");
			}else{
				request.setAttribute("message", "没有对应订单信息！");
			}
		}
		request.getRequestDispatcher("/admin/jsp/ordermanager.jsp").forward(request, response);
	}
	
}
