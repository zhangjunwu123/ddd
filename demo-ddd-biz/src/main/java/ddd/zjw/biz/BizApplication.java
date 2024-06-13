/* 
 * Created by 2018年9月9日
 */
package ddd.zjw.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * The application for trade.
 * @author zjw
 */
@SpringBootApplication
@ComponentScan(basePackages={"ddd.zjw"})
@ImportResource(locations={"classpath*:applicationContext-*.xml"})
@MapperScan("ddd.zjw.support.dao")
public class BizApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BizApplication.class, args);
		System.out.println(".....................................");
		System.out.println("....The Biz Application started....");
		System.out.println(".....................................");
	}
}
