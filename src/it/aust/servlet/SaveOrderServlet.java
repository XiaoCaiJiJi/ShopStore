package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.Order;
import it.aust.bean.OrderItem;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
import it.aust.mapper.ShopCarMapper;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.ShopContant;


import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * ���涩��
 */
@WebServlet("/saveOrder.do")
public class SaveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//����������
		ServiceProxy serviceProxy = new ServiceProxy();
		//������������
		EcShopService service = serviceProxy.bind(new EcShopServiceImpl());
		
		User user = (User)request.getSession().getAttribute(ShopContant.SESSION_USER);
		
		//������ϸ
	    //��ȡ��ǰ�û����ﳵ����Ʒ��Ϣ
	    List<ShopCar> shopCars = (List<ShopCar>)request.getSession().getAttribute("shopCars");
	    if(shopCars.size()>0){
	    	Double totalPrice = (Double)request.getSession().getAttribute("totalPrice");
			
			//������������
		    Order order = new Order();
		    //�����µ�ʱ��
		    order.setCreateDate(new Date());
		    
		    order.setUserId(user.getId());
		    
		    order.setAmount(totalPrice);
		    
		    //����ʱ���ʽ������
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    
		    //���ɶ������  ������Ψһ��  PO-20161214114012-1
		    StringBuffer orderNo = new StringBuffer();
		    orderNo.append("PO-").append(sdf.format(new Date())).append("-").append(user.getId());
		    order.setOrderCode(orderNo.toString());

		    List<OrderItem> items = new ArrayList<OrderItem>();
		    
		    for(ShopCar shopCar : shopCars){
		    	OrderItem item = new OrderItem();
		    	item.setArticleId(shopCar.getArticle().getId());
		    	item.setOrderNum(shopCar.getBuyNum());
		    	items.add(item);
			}
		    //��������ϸ����ڶ�����
		    order.setItems(items);
		    //���涩��
		    service.saveOrder(order);
		    //��ת��չʾ������Ϣ��Servlet  
		    response.sendRedirect(request.getContextPath()+"/showOrderList.do");
	    }else{
	    	response.sendRedirect(request.getContextPath()+"/index.action");
	    }
	}

}

