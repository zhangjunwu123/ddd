/* 
 * Created by 2019年4月17日
 */
package ddd.zjw.support.dao.impl.factory;

import java.util.ArrayList;
import java.util.List;

/** 领域对象实体
 * The configuration of the value object.
 * @author zjw
 */
public class VObj {
	private String clazz;
	private String table;
	private List<Property> properties = new ArrayList<>();
	private List<Join> joins = new ArrayList<>();
	private List<Ref> refs = new ArrayList<>();
	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	/**
	 * @return the joins
	 */
	public List<Join> getJoins() {
		return joins;
	}
	/**
	 * @param joins the joins to set
	 */
	public void setJoins(List<Join> joins) {
		this.joins = joins;
	}
	/**
	 * @return the refs
	 */
	public List<Ref> getRefs() {
		return refs;
	}
	/**
	 * @param refs the refs to set
	 */
	public void setRefs(List<Ref> refs) {
		this.refs = refs;
	}
}
