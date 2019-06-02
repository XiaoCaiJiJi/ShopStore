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
			
			//�ж��ܼ�¼���Ƿ����0
			if(this.pageModel.getTotalNum()>0){
				
				//��ȡ�����еĲ�������������װ��submitUrl����
				HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
				
				//��ȡ���е��������������  keyword    typeCode  pageIndex
				Enumeration<String> paramNames = request.getParameterNames();
				StringBuffer params = new StringBuffer();

				while(paramNames.hasMoreElements()){
					String pagemName  = paramNames.nextElement();
					if(pagemName.equals("pageIndex")){
						continue;
					}
					
					//��ȡ������ֵ
					String pagemValue = request.getParameter(pagemName);
					params.append(pagemName).append("=").append(pagemValue).append("&");
				}
				
				//�������������ַ   
				submitUrl = submitUrl +"?"+ params.toString();
				
				//������ҳ��      17  8
				int totalPage = pageModel.getTotalNum() % pageModel.getPageSize()==0?pageModel.getTotalNum() / pageModel.getPageSize():pageModel.getTotalNum() / pageModel.getPageSize()+1;
				
				StringBuffer page = new StringBuffer();
				//�жϵ�ǰҳ���ǲ��ǵ�һҳ
				if(pageModel.getPageIndex()==1){
					//��ǰҳ���ڵ�һҳ
					page.append("<h3>��&nbsp;&nbsp;ҳ</h3><h3>��һҳ</h3>");
					
					//�����ܹ���һҳ����һҳҲ���ܵ��
					if(totalPage==1){
						page.append("<h3>��һҳ</h3><h3>β&nbsp;&nbsp;ҳ</h3>");
					}else{
						page.append("<h3><a href='"+submitUrl+"pageIndex=2'>��һҳ</a></h3><h3><a href='"+submitUrl+"pageIndex="+totalPage+"'>β&nbsp;&nbsp;ҳ</a></h3>");
					}
			
					//��ǰҳ����βҳ����һҳ βҳ���ܵ��
				}else if(totalPage==pageModel.getPageIndex()){
					page.append("<h3><a href='"+submitUrl+"pageIndex=1'>��&nbsp;&nbsp;ҳ</a></h3><h3><a href='"+submitUrl+"pageIndex="+(totalPage-1)+"'>��һҳ</a></h3>");
					page.append("<h3>��һҳ</h3><h3>β&nbsp;&nbsp;ҳ</h3>");
				}else{
					//��ǰҳ�����м�
					page.append("<h3><a href='"+submitUrl+"pageIndex=1'>��&nbsp;&nbsp;ҳ</a></h3><h3><a href='"+submitUrl+"pageIndex="+(this.pageModel.getPageIndex()-1)+"'>��һҳ</a></h3>");
					page.append("<h3><a href='"+submitUrl+"pageIndex="+(this.pageModel.getPageIndex()+1)+"'>��һҳ</a></h3><h3><a href='"+submitUrl+"pageIndex="+totalPage+"'>β&nbsp;&nbsp;ҳ</a></h3>");
				}
				page.append("<h6>��ǰ��ʾ��&nbsp;<font style='color:red;'>" + this.pageModel.getPageIndex() +"</font>").append("/"+totalPage + "&nbsp;ҳ&nbsp;</h6>");

				write.print(page.toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//������ʼ��ǩ�Լ�������ǩ�м������
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
