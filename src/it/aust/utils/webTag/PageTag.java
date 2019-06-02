package it.aust.utils.webTag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @version 1.0
 */
public class PageTag extends TagSupport{
	
	private String submitUrl;//index.action?typeCode=?&keyword=?
	private PageModel pageModel;
	
	
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("==========doStartTag============");
		JspWriter write =  this.pageContext.getOut();
		try {
			
			//判断总记录数是否大于0
			if(this.pageModel.getTotalNum()>0){
				
				//获取请求中的参数，将参数封装到submitUrl后面
				HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
				
				//获取所有的请求参数的名称  keyword    typeCode  pageIndex
				Enumeration<String> paramNames = request.getParameterNames();
				StringBuffer params = new StringBuffer();

				while(paramNames.hasMoreElements()){
					String pagemName  = paramNames.nextElement();
					if(pagemName.equals("pageIndex")){
						continue;
					}
					
					//获取参数的值
					String pagemValue = request.getParameter(pagemName);
					params.append(pagemName).append("=").append(pagemValue).append("&");
				}
				
				//重新设置请求地址   
				submitUrl = submitUrl +"?"+ params.toString();
				
				//计算总页数      17  8
				int totalPage = pageModel.getTotalNum() % pageModel.getPageSize()==0?pageModel.getTotalNum() / pageModel.getPageSize():pageModel.getTotalNum() / pageModel.getPageSize()+1;
				
				StringBuffer page = new StringBuffer();
				//判断当前页码是不是第一页
				if(pageModel.getPageIndex()==1){
					//当前页码在第一页
					page.append("<h3>首&nbsp;&nbsp;页</h3><h3>上一页</h3>");
					
					//假设总共就一页，下一页也不能点击
					if(totalPage==1){
						page.append("<h3>下一页</h3><h3>尾&nbsp;&nbsp;页</h3>");
					}else{
						page.append("<h3><a href='"+submitUrl+"pageIndex=2'>下一页</a></h3><h3><a href='"+submitUrl+"pageIndex="+totalPage+"'>尾&nbsp;&nbsp;页</a></h3>");
					}
			
					//当前页码在尾页，下一页 尾页不能点击
				}else if(totalPage==pageModel.getPageIndex()){
					page.append("<h3><a href='"+submitUrl+"pageIndex=1'>首&nbsp;&nbsp;页</a></h3><h3><a href='"+submitUrl+"pageIndex="+(totalPage-1)+"'>上一页</a></h3>");
					page.append("<h3>下一页</h3><h3>尾&nbsp;&nbsp;页</h3>");
				}else{
					//当前页码在中间
					page.append("<h3><a href='"+submitUrl+"pageIndex=1'>首&nbsp;&nbsp;页</a></h3><h3><a href='"+submitUrl+"pageIndex="+(this.pageModel.getPageIndex()-1)+"'>上一页</a></h3>");
					page.append("<h3><a href='"+submitUrl+"pageIndex="+(this.pageModel.getPageIndex()+1)+"'>下一页</a></h3><h3><a href='"+submitUrl+"pageIndex="+totalPage+"'>尾&nbsp;&nbsp;页</a></h3>");
				}
				page.append("<h6>当前显示第&nbsp;<font style='color:red;'>" + this.pageModel.getPageIndex() +"</font>").append("/"+totalPage + "&nbsp;页&nbsp;</h6>");

				write.print(page.toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳过开始标签以及结束标签中间的内容
		return SKIP_BODY;
	}


	public String getSubmitUrl() {
		return submitUrl;
	}


	public void setSubmitUrl(String submitUrl) {
		System.out.println("submitUrl:"+submitUrl);
		this.submitUrl = submitUrl;
	}


	public PageModel getPageModel() {
		return pageModel;
	}


	public void setPageModel(PageModel pageModel) {
		System.out.println("pageModel:"+pageModel);
		this.pageModel = pageModel;
	}

	
	
	
	 
}
