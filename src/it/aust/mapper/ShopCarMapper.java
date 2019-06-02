package it.aust.mapper;



import it.aust.bean.ShopCar;
import it.aust.bean.User;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface ShopCarMapper {

	@Insert("insert into ec_shopcar(article_id,user_id,buy_num)  values(#{shopId},#{userId},#{buyNum})")
	void addShop(@Param("shopId")String shopId, @Param("userId")int userId, @Param("buyNum")String buyNum);

	
	//根据用户id以及商品id获取购物车中的当前用户购物车的商品信息
	ShopCar getShopCarByArticleIdAndUserId(@Param("shopId")String shopId, @Param("userId")int userId);

	
	
	//更新购物车中商品信息
	@Update("update ec_shopcar set buy_num = #{buyNum} where article_id = #{articleId} and user_id=#{user.id}")
	void updateShop(ShopCar shopCar);

	//根据用户id获取购物车商品信息   
	//在shopMapper中做关联查询
	List<ShopCar> getShopCarByUserId(User user);


	//删除购物车中商品信息
	@Delete("delete from ec_shopcar where article_id = #{articleId} and user_id = #{userId}")
	void deleteShopCar(@Param("articleId")String articleId, @Param("userId")int userId);

	//清空当前用户的购物车
	@Delete("delete from ec_shopcar where  user_id = #{userId}")
	void removeShopCarByUserId(int userId);

}