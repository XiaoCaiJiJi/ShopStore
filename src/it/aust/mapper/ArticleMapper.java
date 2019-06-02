package it.aust.mapper;

import java.util.List;

import javax.mail.search.FromStringTerm;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import it.aust.bean.Article;
import it.aust.utils.webTag.PageModel;

/**
 * ArticleMapper 数据访问类
 * @author CHUNLONG.LUO
 * @version 1.0
 */
public interface ArticleMapper {
	
	//获取物品类别信息
	@Select ("select * from ec_article where type_code like #{typeCode} and title like #{keyword} limit #{pageModel.startNum},#{pageModel.pageSize}")
	List<Article> findArticlesByCode(@Param("typeCode")String typeCode,@Param("keyword")String keyword,@Param("pageModel")PageModel pageModel);

	
	//根据商品id获取商品信息
	@Select ("select * from ec_article where id = #{id}")
	Article getArticleById(String id);
	
	//计算查询总数
	@Select ("select count(*) from ec_article where type_code like #{typeCode} and title like #{keyword} ")
	int findTotalNum(@Param("typeCode")String typeCode,@Param("keyword")String keyword);

	@Update("update ec_article set title =#{title},price=#{price},discount=#{discount},locality=#{locality},storage=#{storage},description=#{description} where id =#{id}")
	void updateArticleInfo(Article article);
	
	

	


}