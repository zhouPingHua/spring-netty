package com.zph.cralwer.netty.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zph  on 2018/8/6
 */
public class MapUtils {
    /**
     * 将map转变成Class
     * @param map
     * @return
     * @throws Exception
     */
    public static <T> T toBean(Class<T> clazz, Map map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (InvocationTargetException e) {
                        System.out.println("字段映射失败");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("实例化 JavaBean 失败");
        } catch (IntrospectionException e) {
            System.out.println("分析类属性失败");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("映射错误");
        } catch (InstantiationException e) {
            System.out.println("实例化 JavaBean 失败");
        }
        return (T) obj;


    }

    /**
     * 将Map转变成JSON
     * @param map
     * @return
     */
//    public static JSONObject changeMap2JSON(Map<String, Object> map){
//        return JSONObject..toJSONString(requestMap);
//    }

}
