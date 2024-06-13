/* 
 * Created by 2018年9月10日
 */
package ddd.zjw.biz.service;

import ddd.zjw.biz.entity.Product;

import java.util.List;

/**
 * The service for products.
 * @author zjw
 */
public interface ProductService {
	/**
	 * save a product.
	 * @param product
	 */
	public void saveProduct(Product product);
	/**
	 * save a list of products.
	 * @param listOfProducts
	 */
	public void saveProductList(List<Product> listOfProducts);
	/**
	 * delete a product by id.
	 * @param id
	 */
	public void deleteProduct(Long id);
	
	/**
	 * delete a list of products
	 * @param ids
	 */
	public void deleteProductList(List<Long> ids);
	/**
	 * get a product by id.
	 * @param id
	 * @return a certain product.
	 */
	public Product getProduct(Long id);
	
	/**
	 * get a list of products by their id.
	 * @param ids
	 * @return a list of products
	 */
	public List<Product> getProductList(List<Long> ids);
}
