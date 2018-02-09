package com.wdzsj.cmn.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 数组操作工具类
 *
 * @author qy
 * @since 1.0
 */
public class ArrayUtil {

    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 连接数组
     */
    public static Object[] concat(Object[] array1, Object[] array2) {
        return ArrayUtils.addAll(array1, array2);
    }

    /**
     * 判断对象是否在数组中
     */
    public static <T> boolean contains(T[] array, T obj) {
        return ArrayUtils.contains(array, obj);
    }
    
    /**
     * 获取entity集合的id
     * 
     * @param entityList
     * @return
     */
    public static <T> Long[] getIds(List<T> entityList, String property) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Long> ids = new ArrayList<Long>();
        for (T entity : entityList) {
            Field field = ReflectionUtils.findField(entity.getClass(), property);
            field.setAccessible(true);
            ids.add(Long.valueOf(ReflectionUtils.getField(field, entity).toString()));
        }
        Long[] idArr = new Long[entityList.size()];
        ids.toArray(idArr);
        return idArr;
    }

    /**
     * 获取entity集合对象中，某个属性值
     * 
     * @param entityList
     * @return
     */
    public static <T, V> V[] getPropertyList(List<T> entityList, String property, Class<V> type) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<V> propertyList = new ArrayList<V>();
        for (T entity : entityList) {
            Field field = ReflectionUtils.findField(entity.getClass(), property, type);
            field.setAccessible(true);
            propertyList.add((V) ReflectionUtils.getField(field, entity));
        }
        V[] idArr = (V[]) propertyList.toArray();
        return idArr;
    }

}
