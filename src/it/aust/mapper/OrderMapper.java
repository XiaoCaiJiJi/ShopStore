package it.aust.mapper;

import it.aust.bean.Order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


/**
 * OrderMapper 数据访问类
 * @author CHUNLONG.LUO
 * @version 1.0
 */
public interface OrderMapper {

	//保存订单
	void saveOrder(Order order);

	//根据用户id获取用户订单信息
	List<Order> getAllOrderByUserId(int userId);
	//更新订单信息
	@Update("update ec_order set status = 1,order_code = #{orderCode},create_date = #{createDate},amount =#{amount},user_id =#{userId},send_date =#{sendDate} where id = #{id}")
	void updateOrderInfo(Order order);



}