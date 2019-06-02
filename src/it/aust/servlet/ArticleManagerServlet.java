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

@WebServlet("/articleManager.action")
public class ArticleManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleManagerServlet() {
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
		Article article = new Article();
		
		String articleId = request.getParameter("articleId");
		article.setId(Integer.valueOf(articleId));
		
		String articleTitle =request.getParameter("articleTitle");
		article.setTitle(articleTitle);
		
		String price = request.getParameter("price");
		article.setPrice(Double.valueOf(price));
		
	    String supplier = request.getParameter("supplier");
	    article.setSupplier(supplier);
	    
	    String locality = request.getParameter("locality");
	    article.setLocality(locality);
	    
	    String storage = request.getParameter("storage");
	    article.setStorage(Integer.valueOf(storage));
	    
	    String discount = request.getParameter("discount");
	    article.setDiscount(Double.valueOf(discount));
	    
	    String description = request.getParameter("description");
	    article.setDescription(description);
	    
	    service.updateArticleInfo(article);
	    request.setAttribute("message", "商品信息修改成功");
		request.getRequestDispatcher("/admin/jsp/articlemanager.jsp").forward(request, response);
	}
	
}
