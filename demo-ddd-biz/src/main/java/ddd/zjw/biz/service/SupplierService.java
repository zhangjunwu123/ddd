/* 
 * Created by 2019年1月30日
 */
package ddd.zjw.biz.service;

import ddd.zjw.biz.entity.Supplier;

import java.util.List;

/**
 * The service of suppliers.
 * @author zjw
 */
public interface SupplierService {
	/**
	 * @param id
	 * @return the supplier
	 */
	public Supplier loadSupplier(long id);
	/**
	 * @param ids
	 * @return
	 */
	public List<Supplier> loadSuppliers(List<Long> ids);
	
	/**
	 * @return the list of supplier
	 */
	public List<Supplier> listOfSuppliers();
}
