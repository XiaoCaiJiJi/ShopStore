package it.aust.mapper;



import it.aust.bean.ArticleType;

import java.util.List;

import org.apache.ibatis.annotations.Select;


/**
 * ArticleTypeMapper 数据访问类
 * @author CHUNLONG.LUO
 * @version 1.0
 */
public interface ArticleTypeMapper {

	//获取一级物品类型
	@Select("select * from ec_article_type where length(code)=4")
	List<ArticleType> findAllFArticleType();

	//获取二级物品类型
    @Select("SELECT * FROM ec_article_type WHERE CODE LIKE #{typeCode}")
	List<ArticleType> findSeArticleTypes(String typeCode);

    
    //获取物品类型获取物品名称
    @Select("SELECT name FROM ec_article_type WHERE CODE = #{typeCode}")
	String findArticleTypeNameByCode(String typeCode);





}