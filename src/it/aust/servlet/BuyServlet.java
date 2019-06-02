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
 * 展示商品首页信息
 */
@WebServlet("/buy.do")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//创建代理类
		ServiceProxy serviceProxy = new ServiceProxy();
		//创建服务层对象
		EcShopService service = serviceProxy.bind(new EcShopServiceImpl());
		
	    //获取购买的商品数量
		String buyNum = request.getParameter("buyNum");
		//获取购买的商品id
		String shopId = request.getParameter("id");
		User user = (User)request.getSession().getAttribute(ShopContant.SESSION_USER);
		
		
		//判断商品是否在当前用户的购物中，如果已存在，进行数量的增加，否则添加至购物车
		ShopCar shopCar = service.getShopCarByArticleIdAndUserId(shopId,user.getId());
		if(shopCar==null){
			//说明该商品不在用户的购物车中
			//往数据中插入数据
			service.addShop(shopId,user.getId(),buyNum);
		}else{
			//已经存在,更新该商品在购物车中的数量
			shopCar.setArticleId(Integer.valueOf(shopId));
			shopCar.setUser(user);
			shopCar.setBuyNum(shopCar.getBuyNum()+Integer.valueOf(buyNum));
			service.updateShop(shopCar);
			
		}
		
	
		
		//展示购物车中商品信息
		response.sendRedirect(request.getContextPath()+"/showShopCar.do");
		
		
	}

}
