package it.aust.bean;

/**
 * Article 数据传输类
 * @author CHUNLONG.LUO
 * @version 1.0
 */
public class ShopCar implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
    private int articleId;
    private User user;
    private int buyNum;
    
    private Article article;
    
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
    
    
}