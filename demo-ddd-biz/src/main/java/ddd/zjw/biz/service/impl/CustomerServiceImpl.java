/* 
 * Created by 2019年1月13日
 */
package ddd.zjw.biz.service.impl;

import ddd.zjw.biz.entity.Address;
import ddd.zjw.biz.entity.Customer;
import ddd.zjw.biz.service.CustomerService;
import ddd.zjw.support.dao.BasicDao;

import javax.annotation.Resource;

/**
 * The implement of the customer service
 * @author zjw
 */
public class CustomerServiceImpl implements CustomerService {
	@Resource(name = "repositoryWithCache")
	private BasicDao dao;

	@Override
	public void save(Customer customer) {
		dao.insertOrUpdate(customer);
	}
	@Override
	public void delete(long id) {
		Customer customer = dao.load(id, new Customer());
		dao.delete(customer);
	}
	@Override
	public Customer load(long id) {
		Customer customer = new Customer();
		customer.setId(id);
		return dao.load(id, customer);
	}
	@Override
	public Address loadAddress(long id) {
		Address address = new Address();
		address.setId(id);
		return dao.load(id, address);
	}
}
