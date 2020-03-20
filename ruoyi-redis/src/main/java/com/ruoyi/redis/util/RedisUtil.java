package com.ruoyi.redis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RedisUtil {

    public static final String SYS_CACHE = "sysCache";
    public static final String USER_CACHE = "userCache";
    public static final String DEFAULT_CACHE = "defaultCache";

    public static String getSysInfo(String key) {
        return RedisUtil.getStringValue(SYS_CACHE+"_"+key);
    }
    public static String getDefaultInfo(String key) {
        return RedisUtil.getStringValue(DEFAULT_CACHE+"_"+key);
    }
    public static String getUserInfo(String key,String yhid) {
        return RedisUtil.getStringValue(USER_CACHE+"_"+key+"_"+yhid);
    }

    public static void putSysInfo(String key, Object value) {
        RedisUtil.setStringValue(SYS_CACHE+"_"+key,JSON.toJSONString(value));
    }
    public static void putDefaultInfo(String key, Object value) {

        RedisUtil.setStringValue(DEFAULT_CACHE+"_"+key,JSON.toJSONString(value));
    }
    public static void putUserInfo(String key,String yhid, Object value) {
        RedisUtil.setStringValue(USER_CACHE+"_"+key+"_"+yhid,JSON.toJSONString(value));
    }

    public static void removeSysInfo(String key) {
        RedisUtil.del(SYS_CACHE+"_"+key);
    }
    public static void removeDefaultInfo(String key) {
        RedisUtil.del(DEFAULT_CACHE+"_"+key);
    }
    public static void removeUserInfo(String key,String yhid) {
        RedisUtil.del(USER_CACHE+"_"+key+"_"+yhid);
    }
    public static void clearUserInfo(String yhid){
        List<String> keys=RedisUtil.keys(USER_CACHE+"_*_"+yhid);
        keys.forEach(s->{
            RedisUtil.del(s);
        });
    }
    public static void clearUserCache(){
        List<String> keys=RedisUtil.keys(USER_CACHE+"_*");
        keys.forEach(s->{
            RedisUtil.del(s);
        });
    }
    public static void clearSysCache(){
        List<String> keys=RedisUtil.keys(SYS_CACHE+"_*");
        keys.forEach(s->{
            RedisUtil.del(s);
        });
    }
    public static void clearDefaultCache(){
        List<String> keys=RedisUtil.keys(DEFAULT_CACHE+"_*");
        keys.forEach(s->{
            RedisUtil.del(s);
        });
    }
    //--------------------------------------------------

    public static String setStringValue(String key, String value) {
        return InitRedisUtil.setStringValue(key,value);
    }

    public static String type(String key) {
        return InitRedisUtil.type(key);
    }
    public static long ttl(String key) {
        return InitRedisUtil.ttl(key);
    }
    /**
     * Set String
     *
     * @param key
     * @param value
     * @param seconds 存活时间,单位/秒
     * @return
     */
    public static String setStringValue(String key, String value, int seconds) {
        return InitRedisUtil.setStringValue(key,value,seconds);
    }

    /**
     * Set Object
     * @param key
     * @param obj
     * @param seconds 存活时间,单位/秒
     */
    public static String setObjectValue(String key, Object obj, int seconds) {
        return InitRedisUtil.setObjectValue(key,obj,seconds);
    }

    /**
     * Get String
     *
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        return InitRedisUtil.getStringValue(key);
    }

    /**
     * Get Object
     *
     * @param key
     * @return
     */
    public static Object getObjectValue(String key) {
        return InitRedisUtil.getObjectValue(key);
    }
    public static Object getObjectValue(String key,Class cls) {
        return InitRedisUtil.getObjectValue(key,cls);
    }
    /**
     * Delete key
     *
     * @param key
     * @return Integer reply, specifically:
     * an integer greater than 0 if one or more keys were removed
     * 0 if none of the specified key existed
     */
    public static Long del(String key) {
        return InitRedisUtil.del(key);
    }

    /**
     * incrBy i(+i)
     *
     * @param key
     * @param i
     * @return new value after incr
     */
    public static Long incrBy(String key, int i) {
        return InitRedisUtil.incrBy(key,i);
    }

    /**
     * exists valid
     *
     * @param key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    public static boolean exists(String key) {
        return InitRedisUtil.exists(key);
    }

    /**
     * expire reset
     * @param key
     * @param seconds 存活时间,单位/秒
     * @return Integer reply, specifically:
     * 1: the timeout was set.
     * 0: the timeout was not set since the key already has an associated timeout (versions lt 2.1.3), or the key does not exist.
     */
    public static long expire(String key, int seconds) {
        return InitRedisUtil.expireAt(key,seconds);
    }

    /**
     * expire at unixTime
     * @param key
     * @param unixTime
     * @return
     */
    public static long expireAt(String key, long unixTime) {
        return InitRedisUtil.expireAt(key,unixTime);
    }

    /**
     * 实现redis keys 模糊查询
     * @author hq
     * @param pattern
     * @return
     */
    public static List<String> keys(String pattern){
        return InitRedisUtil.keys(pattern);
    }

    public static void main(String[] args) {

    }
}
