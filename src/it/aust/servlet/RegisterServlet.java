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
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.client.SenderException;

//�����û���½����
@WebServlet("/register.action")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����������
		ServiceProxy serviceProxy =new ServiceProxy();
		
		//������������
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		//��ȡ���е�һ����Ʒ
		List<ArticleType> firstTypes = service.findAllFArticleType();
		
		request.setAttribute("firstTypes", firstTypes);
		
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//����������
		ServiceProxy serviceProxy =new ServiceProxy();
		//������������
		EcShopService service =serviceProxy.bind(new EcShopServiceImpl());
		
		String tip="";
		User user = new User();
		String loginName = request.getParameter("loginName");
		user.setLoginName(loginName);
		//��ȡ����
		String passWord = request.getParameter("passWord");
		user.setPassword(passWord);
		//��ȡȷ������
		String okPassWord = request.getParameter("okPassWord");
	    if(passWord!=null&&!passWord.equals(okPassWord)){
	    	tip = "�������������ȷ�����벻��������ʵ��";
	    }
	    //��ȡ�û��������֤��
	    String authcode = request.getParameter("authcode");
        //��session�л�ȡ��֤��
	    String randomData = (String)request.getSession().getAttribute(ShopContant.RANDOMDATA);
	    if(authcode!=null&&!authcode.equals(randomData)){
	    	tip = "���������֤�벻��ȷ�����ʵ��";
	    }

	    //��ȡ��ʵ����
	    String name = request.getParameter("name");
	    user.setName(name);
	    //��ȡ�Ա�
	    String sex = request.getParameter("sex");
	    user.setSex(Integer.valueOf(sex));
	    //��ȡ��ַ
	    String address = request.getParameter("address");
	    user.setAddress(address);
	    //��ȡ�绰����
	    String phone = request.getParameter("phone");
	    user.setPhone(phone);
	    //����ʱ��
	    user.setCreateDate(new Date());
	    //����
	    user.setEmail(loginName);
	    String activeCode = UUID.randomUUID().toString();
	    //���ü�����
	    user.setActive(activeCode);
	    
	    //�����û���Ϣ
	    try {
			if(!tip.equals("")){
				List<ArticleType> firstTypes = service.findAllFArticleType();
				request.setAttribute("firstTypes", firstTypes);
				request.setAttribute("message", tip);
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			}else {
				//�����û���Ϣ
				service.save(user,request);
				request.setAttribute("message", "��ϲ����ע��ɹ������¼����["+loginName+"]���м��");
				request.getRequestDispatcher("login.action").forward(request, response);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ע��ʧ��");
		}
	}



	
}
