/*
 * created on Dec 3, 2009
 */
package ddd.zjw.support.xml;

/**
 * The file filter
 * @author zjw
 */
public abstract class Filter {
	
	/**
	 * @param fileName the filename pattern such as "*.xml"
	 * @return whether satisfied the filename pattern.
	 */
	public abstract boolean isSatisfied(String fileName);
}
