package it.aust.utils.webTag;

/**
 * 
 * @author Nivo
 *	���ڷ�װ��ҳ��Ϣ ��ǰҳ��  ÿҳ��ʾ���������� �ܼ�¼����
 */
public class PageModel {
	private int pageIndex =1;    //��ǰҳ��
	private int pageSize = 8; //ÿһҳ��ʾ8������
	private int totalNum;	  //ҳ������
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getStartNum(){
		return (this.getPageIndex()-1)*this.getPageSize();
		
	}
}
