/**
 * @description 数组实现LRU（最近最少使用）算法
 * 				选择最后一次访问时间距离当前时间最长的一页并淘汰之--即淘汰最长时间没有使用的页。
 */
package com.cqu.wb.lru;

public class LRUCacheByArray {
	private int[] cacheArray;
	private int endIndex;
	private int currentSize;
	private int maxSize = 5;
	
	public LRUCacheByArray() {
		cacheArray = new int[maxSize];
		endIndex = 0;
		currentSize = 0;
	}
	
	/**
	 * 
	 * @param k 访问元素
	 * @description 遍历缓冲区，查看待访问元素是否命中缓存区中的元素，若命中则将命中元素删除并将后面的元素前移一格（以便后面将命中元素移动到缓冲区末尾），没命中则不变
	 */
	public void beHit(int k) {
		for(int i = 0; i < currentSize; i++) {
			// 命中则前移，并更新指针
			if(cacheArray[i] == k) {
				for(int j = i; j < cacheArray.length - 1; j++) {
					cacheArray[j] = cacheArray[j + 1];
				}
				endIndex --;
				currentSize --;
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param k 访问元素
	 * @description 如果缓冲区未满，则直接添加到缓冲区末尾；如果缓冲区已满，则将缓冲区所有元素前移一格并将访问元素添加至缓冲区末尾
	 */
	public void accessData(int k) {
		beHit(k);
		if(currentSize < maxSize) {		// 缓冲区未满
			cacheArray[endIndex] = k;
			endIndex ++;
			currentSize ++;
		} else {						// 缓冲区已满
			for(int i = 0; i < maxSize - 1; i++) {
				cacheArray[i] = cacheArray[i + 1];
			}
			cacheArray[maxSize - 1] = k;
		}
		
		// 测试cache元素
		System.out.print("缓冲区中元素：");
		for(int i = 0; i < currentSize; i++) {
			System.out.print(cacheArray[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCacheByArray lruCacheByArray = new LRUCacheByArray();
		lruCacheByArray.accessData(4);
		lruCacheByArray.accessData(7);
		lruCacheByArray.accessData(0);
		lruCacheByArray.accessData(7);
		lruCacheByArray.accessData(1);
		lruCacheByArray.accessData(0);
		lruCacheByArray.accessData(1);
		lruCacheByArray.accessData(2);
		lruCacheByArray.accessData(1);
		lruCacheByArray.accessData(2);
		lruCacheByArray.accessData(6);
	}

}
