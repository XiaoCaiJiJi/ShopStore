package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.webTag.PageModel;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userManager.action")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
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
		User user = new User();
		String loginName = request.getParameter("loginName");
		user.setLoginName(loginName);
		String passWord =request.getParameter("passWord");
		user.setPassword(passWord);
		String sex = request.getParameter("sex");
		user.setSex(Integer.valueOf(sex));
		 //获取电话号码
	    String phone = request.getParameter("phone");
	    user.setPhone(phone);
	    String address = request.getParameter("address");
	    user.setAddress(address);
		System.out.println(address);
	    service.updateUserInfo(user);
	    request.setAttribute("message", "用户信息修改成功");
		request.getRequestDispatcher("/admin/jsp/usermanager.jsp").forward(request, response);
	}
	
}
