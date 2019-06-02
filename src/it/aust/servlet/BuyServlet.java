package it.aust.servlet;

import it.aust.bean.ArticleType;
import it.aust.bean.ShopCar;
import it.aust.bean.User;
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

/**
 * չʾ��Ʒ��ҳ��Ϣ
 */
@WebServlet("/buy.do")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//����������
		ServiceProxy serviceProxy = new ServiceProxy();
		//������������
		EcShopService service = serviceProxy.bind(new EcShopServiceImpl());
		
	    //��ȡ�������Ʒ����
		String buyNum = request.getParameter("buyNum");
		//��ȡ�������Ʒid
		String shopId = request.getParameter("id");
		User user = (User)request.getSession().getAttribute(ShopContant.SESSION_USER);
		
		
		//�ж���Ʒ�Ƿ��ڵ�ǰ�û��Ĺ����У�����Ѵ��ڣ��������������ӣ�������������ﳵ
		ShopCar shopCar = service.getShopCarByArticleIdAndUserId(shopId,user.getId());
		if(shopCar==null){
			//˵������Ʒ�����û��Ĺ��ﳵ��
			//�������в�������
			service.addShop(shopId,user.getId(),buyNum);
		}else{
			//�Ѿ�����,���¸���Ʒ�ڹ��ﳵ�е�����
			shopCar.setArticleId(Integer.valueOf(shopId));
			shopCar.setUser(user);
			shopCar.setBuyNum(shopCar.getBuyNum()+Integer.valueOf(buyNum));
			service.updateShop(shopCar);
			
		}
		
	
		
		//չʾ���ﳵ����Ʒ��Ϣ
		response.sendRedirect(request.getContextPath()+"/showShopCar.do");
		
		
	}

}
