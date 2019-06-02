package it.aust.utils.filter;

import it.aust.bean.User;
import it.aust.utils.ShopContant;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("*.do")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
			//��session�л�ȡ�û���Ϣ
			User user = (User)((javax.servlet.http.HttpServletRequest)request).getSession().getAttribute(ShopContant.SESSION_USER);
		    if(user==null){
		    	request.setAttribute("message", "����δ��¼�����¼�ٽ�����ز�����");
		    	//��ת����¼ҳ��
		    	request.getRequestDispatcher("/login.action").forward(request, response);
		    }else{
		    	//����   
				chain.doFilter(request, response);
		    }
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
