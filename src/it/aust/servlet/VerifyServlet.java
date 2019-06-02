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

//�����û���½����
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
		
		//���ɻ���
		BufferedImage image =new BufferedImage(70, 25,BufferedImage.TYPE_INT_RGB);
		//����2d����
		Graphics2D d2 =image.createGraphics();
		//���û��ʵ���ɫ
		d2.setColor(Color.white);
		//�����������ɫ
		d2.fillRect(0, 0, 70,25);
		d2.setStroke(new BasicStroke(2.0f));
		//���Ƹ�����
		Random random = new Random();
		for(int i=0;i<200;i++){
			int r =random.nextInt(255);
			int g =random.nextInt(255);
			int b =random.nextInt(255);
			
			int x =random.nextInt(70);
			int y =random.nextInt(25);
			
			Color c = new Color(r,g,b);
			d2.setColor(c);
			//���Ƹ�����
			d2.drawLine(x,y,x,y);
		}
		
		d2.setColor(Color.red);
		d2.setFont(new Font("����", Font.BOLD, 24));
		//����������ڻ�����
		d2.drawString(randomDate, 10, 25);
		//�������ݵ���Ӧ������
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		//��ͼƬ��Ӧ���ͻ���
		ImageIO.write(image, "png", response.getOutputStream());
		out.flush();
		out.close();
		
	}

	//������֤��������
	private String createRandomDate() {
		StringBuffer bf = new StringBuffer();
		Random random = new Random();
		for(int i =0 ;i <4 ;i++){
			bf.append(random.nextInt(10));
		}
		return bf.toString();
	}
}
