package ddd.zjw.support.helper;

import ddd.zjw.support.entity.Entity;
import ddd.zjw.support.utils.BeanUtils;
import ddd.zjw.support.utils.EntityUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 统一服务帮助类
 * */
@Component
public class ServiceHelper {
    @Autowired
    private ApplicationContext context = null;

    public Object getBean(String beanName){
        if(StringUtils.isBlank(beanName)){
            throw new RuntimeException("bean name 不能为空");
        }
        try {
            return context.getBean(beanName);
        } catch (BeansException e) {
            throw new RuntimeException("未找到对应的bean");
        }
    }

    public Method getMethod(Object service, String methodName) {
        if(StringUtils.isBlank(methodName)){
            throw new RuntimeException("methodName不能为空");
        }
        Method[] declaredMethods = service.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if(declaredMethod.getName().equals(methodName)){
                return declaredMethod;
            }
        }
        throw new RuntimeException("在"+service.getClass().getSimpleName()+"中没有找到对应的方法");
    }
    /**
     * 从入参request中补全json
     * */
    public void completeParamsFromReuqest(Map<String, Object> json, HttpServletRequest request) {
        if(json == null){
            json = new HashMap<String, Object>();
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            json.put(key, value);
        }
    }

    /**
     * json ==> vo值对象并赋值
     * */
    public Object getValueObj(Method method, Map<String, Object> json) {
        if(MapUtils.isEmpty(json)) return null;
        Class<?>[] allOfParameterTypes = method.getParameterTypes();
        if(allOfParameterTypes.length==0) return null;
        //service方法的第一个入参必须是领域对象，即继承Entity类
        Class<?> firstOfParameterType = allOfParameterTypes[0];

        if(!EntityUtils.isEntity(firstOfParameterType)) return null;
        return EntityUtils.createEntity((Class<Entity>)firstOfParameterType, json);
    }

    /**
     * 组装所有的入参数组
     * The first thing is put the value object to the first argument, if it's available.
     * Then put the other arguments in order.
     * @param method
     * @param json
     * @param vo
     * @return the arguments
     */
    public Object[] getArguments(Method method, Map<String, Object> json, Object vo) {
        int length = method.getParameterCount();
        if(length==0) return null;
        int index = 0;
        List<Object> args = new ArrayList<Object>();

        //add the value object to the first argument, if it's available.
        if(vo!=null) {
            args.add(vo);
            index++;
        }

        //add the other arguments in order.
        Parameter[] allOfParameters = method.getParameters();
        for( ; index<length; index++) {
            Parameter parameter = allOfParameters[index];
            String name = parameter.getName();
            Object value = BeanUtils.bind(parameter.getParameterizedType(), json.get(name));
            args.add(value);
        }
        return args.toArray();
    }

    /**
     * 反射调用方法
     * */
    public Object invoke(Object service, Method method, Object[] args) {
        try {
            if(args==null) return method.invoke(service);
            else return method.invoke(service, args);
        } catch (IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException e) {
            throw new RuntimeException("error when invoking the service by reflect [service: "
                    +service+", method: "+method+", args: "+args+"]", e);
        }
    }
}
