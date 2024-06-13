package ddd.zjw.support.service;

import ddd.zjw.support.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
/**
 * 统一Service控制
 * */
@Service
public class OrmService {
    @Autowired
    private ServiceHelper serviceHelper;

    public Object execute(String beanName, String methodName, Map<String, Object> json, HttpServletRequest request){
        Object service = serviceHelper.getBean(beanName);
        Method method = serviceHelper.getMethod(service, methodName);
        //填充入参
        serviceHelper.completeParamsFromReuqest(json, request);
        //得到领域对象并赋值
        Object vo = serviceHelper.getValueObj(method, json);
        //组装service方法的所有入参列表
        Object[] args = serviceHelper.getArguments(method, json, vo);
        //反射调用sevice
        return serviceHelper.invoke(service, method, args);
    }
}
