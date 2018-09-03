/**
 * @description LinkedHashMap实现LRU（最近最少使用）算法
 * 				根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。
 */
package com.cqu.wb.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheByLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cacheSize;
	
	public LRUCacheByLinkedHashMap(int cacheSize) {
		// 链表的顺序是插入的顺序，而不是访问的顺序。但是，构造函数提供了一个选项，设置为true可以使用访问的顺序
		super(16, 0.75f, true);
		this.cacheSize = cacheSize;
	}
	
	/**
	 * @description 判断是否执行回收策略
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		// 回收条件
		boolean isRemove = size() > cacheSize;
		
		// 输出清除数据，便于测试
		if(isRemove) {
			System.out.println("清除缓存key：" + eldest.getKey());
		}
		
		return isRemove;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 测试
		LRUCacheByLinkedHashMap<String, String> lruCache = new LRUCacheByLinkedHashMap<String, String>(5);
		lruCache.put("1", "1");
		lruCache.put("2", "2");
		lruCache.put("3", "3");
		lruCache.put("4", "4");
		lruCache.put("5", "5");
		
		System.out.println("初始化：");
		System.out.println(lruCache.keySet());
		System.out.println("访问3：");
		lruCache.get("3");
		System.out.println(lruCache.keySet());
		System.out.println("访问2：");
		lruCache.get("2");
		System.out.println(lruCache.keySet());
		System.out.println("访问数据6，7：");
		lruCache.put("6", "6");
		lruCache.put("7", "7");
		System.out.println(lruCache.keySet());
	}

}
