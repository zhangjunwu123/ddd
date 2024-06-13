/*
 * created by 2019年7月23日 下午4:23:12
 */
package ddd.zjw.biz.service.impl;

import ddd.zjw.biz.entity.Order;
import ddd.zjw.biz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import ddd.zjw.support.dao.BasicDao;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjw
 */
public class OrderServiceImpl implements OrderService {
	//代表使用ddd的dao
	@Resource(name = "repository")
	private BasicDao dao;


	@Override
	public void createOrder(Order order) {
		dao.insert(order);
	}

	@Override
	public void modifyOrder(Order order) {
		dao.insertOrUpdate(order);
	}

	@Override
	public void deleteOrder(Order order) {
		dao.delete(order);
	}

	@Override
	public Order checkOrder(Long id) {
		return dao.load(id, new Order());
	}

	@Override
	public List<Order> listOfOrders() {
		return dao.loadAll(new Order());
	}

}
