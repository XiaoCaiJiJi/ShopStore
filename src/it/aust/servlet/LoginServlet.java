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

//�����û���½����
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
		//����������
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//������������
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		//��ȡ���е�һ����Ʒ
		List<ArticleType> firstTypes = service.findAllFArticleType();
		
		request.setAttribute("firstTypes", firstTypes);
		
		String url ="/WEB-INF/jsp/login.jsp";
		String flag = request.getParameter("flag");
		if("userLogin".equals(flag)){
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			User user = service.findUserByNameAndPass(loginName,password);
			if(user==null){
				request.setAttribute("message", "�û��������벻��ȷ!");
			}else if(user.getDisabled().equals("0")){
				request.setAttribute("message", "����δ������¼����["+user.getEmail()+"]���м���");
			}else if(user.getRole()==1){
				//���
				request.getSession().setAttribute(ShopContant.SESSION_USER, user);
				url="index.action";
			}else {
				//����
				request.getSession().setAttribute(ShopContant.SESSION_USER, user);
				url="/admin/jsp/main.jsp";
			}
			request.getRequestDispatcher(url).forward(request, response);
		}else{

			String id = request.getParameter("id");
			//����id��ȡ��Ʒ��Ϣ
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			
		}
	}
}
