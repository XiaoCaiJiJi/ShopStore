package it.aust.servlet;

import it.aust.bean.ArticleType;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.ShopContant;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 展示当前用户购物车中商品信息
 */
@WebServlet("/showShopCar.do")
public class ShowShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowShopCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建代理类
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//创建服务层对象
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());

		//获取所有的一级物品类型
		List<ArticleType> firstTypes = service.findAllFArticleType();
		request.setAttribute("firstTypes", firstTypes);
		
		User user = (User)request.getSession().getAttribute(ShopContant.SESSION_USER);
		
	    //根据用户id获取购物车商品信息
		List<ShopCar> shopCars = service.getShopCarByUserId(user);
		request.getSession().setAttribute("shopCars", shopCars);
		
		int totalNum = 0;
		double totalPrice =0.0;
		for(ShopCar shopCar:shopCars){
			totalNum += shopCar.getBuyNum();
			totalPrice +=shopCar.getArticle().getDiscountPrice() * shopCar.getBuyNum();
		}
	    
		request.setAttribute("totalNum", totalNum);
		request.getSession().setAttribute("totalPrice", totalPrice);
		
		//判断是跳转至购物车页面还是审核订单页面
		
		String flag =request.getParameter("flag");
		if("checkOrder".equals(flag)){
			request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/jsp/shopCar.jsp").forward(request, response);
		}
	}

}
