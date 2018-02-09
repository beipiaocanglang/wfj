package com.wdzsj.cmn.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统缓存类
 */
public class CacheHelper {

    /**
     * 缓存容器
     */
    private static Map<String,Object> cacheMap = new HashMap<String,Object>();

    /**
     * 加入缓存
     * @param key
     * @param cacheObject
     */
    public static synchronized void put(String key,Object cacheObject) {
        cacheMap.put(key,cacheObject);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return cacheMap.get(key);
    }

    /**
     * 清除缓存
     * @param key
     * @return
     */
    public static Object remove(String key) {
        return cacheMap.remove(key);
    }
}
