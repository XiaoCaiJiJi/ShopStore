package it.aust.utils.webTag;

/**
 * 
 * @author Nivo
 *	用于封装分页信息 当前页码  每页显示多少条数据 总记录条数
 */
public class PageModel {
	private int pageIndex =1;    //当前页码
	private int pageSize = 8; //每一页显示8条数据
	private int totalNum;	  //页码总数
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
