package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.ShopContant;


import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理用户登陆请求
@WebServlet("/active.action")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建代理类
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//创建服务层对象
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		
		String activeCode = request.getParameter("activeCode");
		
		try {
			service.activeUser(activeCode);
			request.setAttribute("message", "恭喜您激活成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "激活失败");
		}
		
		request.getRequestDispatcher("login.action").forward(request, response);
	}
	
}
