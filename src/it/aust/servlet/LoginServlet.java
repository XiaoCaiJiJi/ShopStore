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
@WebServlet("/login.action")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建代理类
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//创建服务层对象
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		//获取所有的一级物品
		List<ArticleType> firstTypes = service.findAllFArticleType();
		
		request.setAttribute("firstTypes", firstTypes);
		
		String url ="/WEB-INF/jsp/login.jsp";
		String flag = request.getParameter("flag");
		if("userLogin".equals(flag)){
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			User user = service.findUserByNameAndPass(loginName,password);
			if(user==null){
				request.setAttribute("message", "用户名和密码不正确!");
			}else if(user.getDisabled().equals("0")){
				request.setAttribute("message", "您尚未激活，请登录邮箱["+user.getEmail()+"]进行激活");
			}else if(user.getRole()==1){
				//买家
				request.getSession().setAttribute(ShopContant.SESSION_USER, user);
				url="index.action";
			}else {
				//卖家
				request.getSession().setAttribute(ShopContant.SESSION_USER, user);
				url="/admin/jsp/main.jsp";
			}
			request.getRequestDispatcher(url).forward(request, response);
		}else{

			String id = request.getParameter("id");
			//根据id获取物品信息
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			
		}
	}
}
