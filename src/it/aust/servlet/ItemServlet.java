package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/item.action")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
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
		
		String id = request.getParameter("id");
		//����id��ȡ��Ʒ��Ϣ
		if(id.equals("")||id==null){
			response.sendRedirect(request.getContextPath()+"/index.action");
		}else{
			Article article = service.getArticleById(id);
			
			request.setAttribute("article", article);
			
			request.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(request, response);
		}
		
	}
}
