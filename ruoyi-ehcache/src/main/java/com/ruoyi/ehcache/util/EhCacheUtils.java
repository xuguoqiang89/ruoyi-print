/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ruoyi.ehcache.util;

import com.ruoyi.common.utils.spring.SpringUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


import java.util.Iterator;
import java.util.List;

/**
 * Cache工具类
 * @author wujiyue
 */
public class EhCacheUtils {

	private static CacheManager cacheManager =SpringUtils.getBean("ehCacheManager");
	public static final String SYS_CACHE = "sysCache";
	public static final String USER_CACHE = "userCache";
	public static final String DICT_CACHE = "dictCache";
	public static final String DEFAULT_CACHE = "defaultCache";
	/**
	 * 根据缓存键获取系统缓存的缓存值
	 * @param key
	 * @return
	 */
	public static Object getSysInfo(String key) {
		return get(SYS_CACHE, key);
	}
	public static Object getDefaultInfo(String key) {
		return get(DEFAULT_CACHE, key);
	}
	public static Object getSysInfo(String prefix,String key) {
		return get(SYS_CACHE, prefix+"_"+key);
	}
	public static Object getUserInfo(String key,String yhid) {
		return get(USER_CACHE, key+"_"+yhid);
	}
	public static Object getUserInfo(String key) {
		return get(USER_CACHE, key);
	}
	public static Object getDictInfo(String dicType,String key) {
		return get(DICT_CACHE, dicType+"_"+key);
	}
	/**
	 * 写入SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static void putSysInfo(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	public static void putDefaultInfo(String key, Object value) {
		put(DEFAULT_CACHE, key, value);
	}
	public static void putSysInfo(String prefix,String key, Object value) {
		put(SYS_CACHE, prefix+"_"+key, value);
	}
	public static void putUserInfo(String key,String yhid, Object value) {
		put(USER_CACHE, key+"_"+yhid, value);
	}
	public static void putUserInfo(String key, Object value) {
		put(USER_CACHE, key, value);
	}

	public static void putDictInfo(String dicType, String key, Object value) {
		put(DICT_CACHE, dicType+"_"+key, value);
	}
	/**
	 * 从SYS_CACHE缓存中移除
	 * @param key
	 * @return
	 */
	public static void removeSysInfo(String key) {
		remove(SYS_CACHE, key);
	}
	public static void removeSysInfo(String prefix,String key) {
		remove(SYS_CACHE, prefix+"_"+key);
	}
	public static void removeUserInfo(String key,String yhid) {
		remove(USER_CACHE, key+"_"+yhid);
	}
	public static void clearUserInfo(String yhid){
		Cache cache=getCache(USER_CACHE);
		List list = cache.getKeys();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			if(key.toString().indexOf("_"+yhid)>=0){
				cache.remove(key);
			}

		}
	}

	public static void removeDictInfo(String dictType, String key) {
		remove(DICT_CACHE, dictType+"_"+key);
	}
	/**
	 * 清空缓存
	 * @param cacheName
	 */
	public static void clear(String cacheName){
		Cache cache=getCache(cacheName);

		List list = cache.getKeys();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
				cache.remove(key);
		}
	}

	/**
	 * 获取缓存值
	 * @param cacheName 缓存的名称
	 * @param key 缓存的键
	 * @return
	 */
	private static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element==null?null:element.getObjectValue();
	}

	/**
	 * 写入缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	private static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	private static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 获得一个Cache，没有则创建一个。
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

}
