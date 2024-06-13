/* 
 * Created by 2019年1月29日
 */
package ddd.zjw.biz.service;

import ddd.zjw.support.entity.ResultSet;
import ddd.zjw.support.service.impl.QueryServiceImpl;
import ddd.zjw.biz.entity.Product;
import ddd.zjw.biz.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implement of the query service for products.
 * @author zjw
 */
public class ProductQueryServiceImpl extends QueryServiceImpl {
	@Autowired
	private SupplierService supplierService;

	@Override
	protected ResultSet afterQuery(Map<String, Object> params,
			ResultSet resultSet) {
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)resultSet.getData();
		
		List<Long> listOfIds = new ArrayList<>();
		for(Product product : list) {
			Long supplierId = product.getSupplierId();
			listOfIds.add(supplierId);
			//Supplier supplier = supplierService.loadSupplier(supplierId);
			//product.setSupplier(supplier);
		}
		List<Supplier> listOfSuppliers = supplierService.loadSuppliers(listOfIds);
		
		Map<Object, Supplier> mapOfSupplier = new HashMap<>();
		for(Supplier supplier : listOfSuppliers) {
			mapOfSupplier.put(supplier.getId(), supplier);
		}
		
		for(Product product : list) {
			Long supplierId = product.getSupplierId();
			Supplier supplier = mapOfSupplier.get(supplierId);
			product.setSupplier(supplier);
		}
		
		resultSet.setData(list);
		return resultSet;
	}
	
}
