/*
 * created by 2019年7月23日 下午4:18:12
 */
package ddd.zjw.biz.service;

import ddd.zjw.biz.entity.Order;

import java.util.List;

/**
 * @author zjw
 */
public interface OrderService {
	/**
	 * @param order
	 */
	public void createOrder(Order order);
	/**
	 * @param order
	 */
	public void modifyOrder(Order order);
	/**
	 * @param order
	 */
	public void deleteOrder(Order order);
	/**
	 * @param id
	 * @return
	 */
	public Order checkOrder(Long id);
	/**
	 * @return
	 */
	public List<Order> listOfOrders();
}
