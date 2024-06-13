/* 
 * Created by 2018年9月10日
 */
package ddd.zjw.biz.service.impl;

import ddd.zjw.biz.entity.Product;
import ddd.zjw.biz.service.ProductService;
import ddd.zjw.support.dao.BasicDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * The implement of the product service.
 * @author zjw
 */
public class ProductServiceImpl implements ProductService {
	@Resource(name = "repositoryWithCache")
	private BasicDao dao;


	@Override
	public void saveProduct(Product product) {
		dao.insertOrUpdate(product);
	}

	@Override
	public void saveProductList(List<Product> listOfProducts) {
		dao.insertOrUpdateForList(listOfProducts);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = new Product();
		dao.delete(id, product);
	}

	@Override
	public void deleteProductList(List<Long> ids) {
		List<Product> listOfProducts = dao.loadForList(ids, new Product());
		dao.deleteForList(listOfProducts);
	}

	@Override
	public Product getProduct(Long id) {
		Product template = new Product();
		return dao.load(id, template);
	}

	@Override
	public List<Product> getProductList(List<Long> ids) {
		return dao.loadForList(ids, new Product());
	}
}
