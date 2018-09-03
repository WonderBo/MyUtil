/**
 * @description 统计逆向文件频率
 */
package com.cqu.wb.tfidf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class InverseDocumentFrequency {
	
	/**
	 * 
	 * @param tfHashMapofAllFiles [文件名，(词元，TF)]二维哈希表
	 * @return (词元，IDF)哈希表
	 * @description 利用双重循环解析二维哈希表得到(词元，出现文档数)哈希表，再解析该哈希表计算得到(词元，IDF)哈希表
	 */
	public static HashMap<String, Float> idf(HashMap<String, HashMap<String, Float>> tfHashMapofAllFiles) {
		HashMap<String, Float> idfHashMap = new HashMap<String, Float>();
		HashMap<String, Integer> termWithDocNumHashMap = new HashMap<String, Integer>();

		int totalDocNum = tfHashMapofAllFiles.size();
		//哈希表的遍历方式：HashMap -> Iterator -> Entry
		Iterator<Entry<String, HashMap<String, Float>>> tfOfAllFilesIterator = tfHashMapofAllFiles.entrySet().iterator();
		while(tfOfAllFilesIterator.hasNext()) {
			Entry<String, HashMap<String, Float>> fileNameWithEntry = tfOfAllFilesIterator.next();
			HashMap<String, Float> tfHashMap = fileNameWithEntry.getValue();
			
			Iterator<Entry<String, Float>> tfIterator = tfHashMap.entrySet().iterator();
			while(tfIterator.hasNext()) {
				Entry<String, Float> entry = tfIterator.next();
				String term = entry.getKey();
				if(termWithDocNumHashMap.get(term) == null) {
					termWithDocNumHashMap.put(term, 1);
				} else {
					termWithDocNumHashMap.put(term, termWithDocNumHashMap.get(term) + 1);
				}
			}
		}
		
		System.out.println("----IDF数据(词元=idf)：----");
		Iterator<Entry<String, Integer>> termWithDocNumIterator = termWithDocNumHashMap.entrySet().iterator();
		while(termWithDocNumIterator.hasNext()) {
			Entry<String, Integer> entry = termWithDocNumIterator.next();
			String term = entry.getKey();
			int docNum = entry.getValue();
			//注意数据类型：double = Math.log(double)
			float idf =  (float) Math.log((double)totalDocNum / docNum);
			idfHashMap.put(term, idf);
			
			System.out.println(term + " = " + idf);
		}
		
		return idfHashMap;
	}
}
