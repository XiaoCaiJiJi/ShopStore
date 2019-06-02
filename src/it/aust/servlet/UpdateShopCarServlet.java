package it.aust.servlet;

import it.aust.bean.ArticleType;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
import it.aust.mapper.ShopCarMapper;
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

//更新购物车中商品信息
@WebServlet("/updateShopCar.do")
public class UpdateShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShopCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建代理类
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//创建服务层对象
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		String articleId = request.getParameter("articleId");
		User user =(User) request.getSession().getAttribute(ShopContant.SESSION_USER);
		String buyNum =request.getParameter("buyNum");
		ShopCar shopCar = new ShopCar();
		
		//处理恶意参数为空的跳转
		if(articleId ==null ||articleId.equals("")||buyNum ==null ||buyNum.equals("")){
			response.sendRedirect(request.getContextPath()+"/index.action");
		}else{
			shopCar.setArticleId(Integer.valueOf(articleId));
			shopCar.setUser(user);
			shopCar.setBuyNum(Integer.valueOf(buyNum));
			service.updateShop(shopCar);
		
			//完成更新后展示购物车信息
			response.sendRedirect(request.getContextPath()+"/showShopCar.do");
		}
	}
}
