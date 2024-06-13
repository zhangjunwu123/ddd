/* 
 * Created by 2019年1月30日
 */
package ddd.zjw.biz.service.impl;

import ddd.zjw.biz.entity.Supplier;
import ddd.zjw.biz.service.SupplierService;
import ddd.zjw.support.dao.BasicDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * The implement of the supplier service.
 * @author zjw
 */
public class SupplierServiceImpl implements SupplierService {
	@Resource(name = "basicDao")
	private BasicDao dao;

	@Override
	public Supplier loadSupplier(long id) {
		Supplier supplier = new Supplier();
		return dao.load(id, supplier);
	}
	@Override
	public List<Supplier> listOfSuppliers() {
		Supplier supplier = new Supplier();
		return dao.loadAll(supplier);
	}
	@Override
	public List<Supplier> loadSuppliers(List<Long> ids) {
		Supplier supplier = new Supplier();
		return dao.loadForList(ids, supplier);
	}
}
