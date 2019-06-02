package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.webTag.PageModel;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		request.setCharacterEncoding("utf-8");
		
		String typeCode =request.getParameter("typeCode");
		String keyword = request.getParameter("keyword");
		request.setAttribute("typeCode", typeCode);
		request.setAttribute("keyword", keyword);
		if(firstTypes.size()>0&&(typeCode ==null||typeCode.equals(""))){
			typeCode = firstTypes.get(0).getCode();
		}
		

		//获取二级商品信息
		String parentCode = typeCode.substring(0,4);
		List<ArticleType> seTypes =service.findSeArticleTypes(parentCode);
		request.setAttribute("seTypes", seTypes);
		
		//获取页码
		String pageIndex =request.getParameter("pageIndex");
		//创建分页实体，进行分页查询
		PageModel pageModel =new PageModel();
		if(pageIndex!=null&&!pageIndex.equals("")){
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}
				//根据物品类型以及用户输入的关键字获取物品信息
		List<Article> articles =service.findArticlesByCode(typeCode,keyword==null?"%%":"%"+keyword+"%",pageModel);
		
		//将pageModel存放到request中
		request.setAttribute("pageModel", pageModel);
		
		request.setAttribute("articles", articles);
		
		
		//根据用户所查询物品类型获取物品类型名字
		String typeName =service.findArticleTypeNameByCode(typeCode);
		request.setAttribute("typeName", typeName);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}

}
