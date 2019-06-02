package it.aust.servlet;

import it.aust.bean.Article;
import it.aust.bean.ArticleType;
import it.aust.bean.User;
import it.aust.service.EcShopService;
import it.aust.service.impl.EcShopServiceImpl;
import it.aust.servlet.service.proxy.ServiceProxy;
import it.aust.utils.ShopContant;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理用户登陆请求
@WebServlet("/verify.action")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String randomDate = createRandomDate();
		request.getSession().setAttribute(ShopContant.RANDOMDATA, randomDate);
		
		//生成画布
		BufferedImage image =new BufferedImage(70, 25,BufferedImage.TYPE_INT_RGB);
		//生成2d画笔
		Graphics2D d2 =image.createGraphics();
		//设置画笔的颜色
		d2.setColor(Color.white);
		//给画布添加颜色
		d2.fillRect(0, 0, 70,25);
		d2.setStroke(new BasicStroke(2.0f));
		//绘制干扰线
		Random random = new Random();
		for(int i=0;i<200;i++){
			int r =random.nextInt(255);
			int g =random.nextInt(255);
			int b =random.nextInt(255);
			
			int x =random.nextInt(70);
			int y =random.nextInt(25);
			
			Color c = new Color(r,g,b);
			d2.setColor(c);
			//绘制干扰线
			d2.drawLine(x,y,x,y);
		}
		
		d2.setColor(Color.red);
		d2.setFont(new Font("宋体", Font.BOLD, 24));
		//将随机数放在画布上
		d2.drawString(randomDate, 10, 25);
		//设置数据的响应的类型
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		//将图片相应至客户端
		ImageIO.write(image, "png", response.getOutputStream());
		out.flush();
		out.close();
		
	}

	//生成验证码的随机数
	private String createRandomDate() {
		StringBuffer bf = new StringBuffer();
		Random random = new Random();
		for(int i =0 ;i <4 ;i++){
			bf.append(random.nextInt(10));
		}
		return bf.toString();
	}
}
