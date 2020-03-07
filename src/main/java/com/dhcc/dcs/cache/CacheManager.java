package com.dhcc.dcs.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存
 * 仅存储当前企业的磅秤采集数据和司机扫描二维码获取到的信息
 * 用于实时获取数据，并交给运营人员读取
 * @author zhanglei
 *
 */
@SuppressWarnings(value = { "all" })
public class CacheManager {
	
	/**
	 * 存储的数据
	 */
	private static Map<String,CacheData> CACHE_DATA = new ConcurrentHashMap<>();
	
	/**
	 * 数据装载器
	 * 如果缓存中没有数据或者已经过期
	 * 则调用数据装载器加载最新的数据并且加入缓存，并返回
	 * @param key
	 * @param load
	 * @param expire
	 * @return
	 * @author zhanglei
	 */
    public static <T> T getData(String key,Load<T> load,int expire){
        T data = getData(key);
        if(data == null && load != null){
            data = load.load();
            if(data != null){
                setData(key,data,expire);
            }
        }
        return data;
    }
    
    /**
     * 根据key值获取数据
     * @param key
     * @return
     * @author zhanglei
     */
    public static <T> T getData(String key){
        CacheData<T> data = CACHE_DATA.get(key);
        if(data != null && (data.getExpire() <= 0 || data.getSaveTime() >= new Date().getTime())){
            return data.getData();
        }
        return null;
    }
    
    public static <T> void setData(String key,T data,int expire){
        CACHE_DATA.put(key,new CacheData(data,expire));
    }
    public static void clear(String key){
        CACHE_DATA.remove(key);
    }
    public static void clearAll(){
        CACHE_DATA.clear();
    }
    public interface Load<T>{
        T load();
    }
    public static boolean check(String key) {
    	return CACHE_DATA.containsKey(key);
    }
    
    
    private static class CacheData<T>{
        CacheData(T t,int expire){
            this.data = t;
            this.expire = expire <= 0 ? 0 : expire * 1000;
            this.saveTime = new Date().getTime() + this.expire;
        }
        private T data;
        private long saveTime; // 存活时间
        private long expire;   // 过期时间 小于等于0标识永久存活
        
        public T getData() {
            return data;
        }
        
        public long getExpire() {
            return expire;
        }
        
        public long getSaveTime() {
            return saveTime;
        }
        
    }
}
