package ddd.zjw.biz.controller;

import ddd.zjw.biz.entity.Order;
import ddd.zjw.support.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrmService ormService;

    @PostMapping("create")
    public void createOrder(Map<String, Object> json, HttpServletRequest request){
        ormService.execute("order", "createOrder", json, request);
    }


    @GetMapping("list")
    public List<Order> listOfOrders(Map<String, Object> json, HttpServletRequest request) {
        return (List<Order>)ormService.execute("order", "listOfOrders", json, request);
    }
}
