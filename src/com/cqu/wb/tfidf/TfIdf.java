/**
 * 
 */
package com.cqu.wb.tfidf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TfIdf {

	/**
	 * 
	 * @param tfHashMapofAllFiles [文件名，(词元，TF)]二维哈希表
	 * @param idfHashMap (词元，IDF)哈希表
	 * @return [文件名，(词元，TF-IDF)]二维哈希表
	 * @description 利用双重循环解析二维哈希表得到term与TF，再根据term在哈希表idfHashMap中得到IDF，最后计算出TF*IDF并封装成为[文件名，(词元，TF-IDF)]二维哈希表
	 */
	public static HashMap<String, HashMap<String, Float>> tfIdfOfAllFiles(HashMap<String,HashMap<String, Float>> tfHashMapofAllFiles, HashMap<String, Float> idfHashMap) {
		HashMap<String, HashMap<String, Float>> tfIdfHashMapofAllFiles = new HashMap<String, HashMap<String,Float>>();

		Iterator<Entry<String, HashMap<String, Float>>> tfOfAllFilesIterator = tfHashMapofAllFiles.entrySet().iterator();
		while(tfOfAllFilesIterator.hasNext()) {
			Entry<String, HashMap<String, Float>> fileNameWithEntry = tfOfAllFilesIterator.next();
			String fileName = fileNameWithEntry.getKey();
			HashMap<String, Float> tfHashMap = fileNameWithEntry.getValue();

			HashMap<String, Float> tfIdfHashMap = new HashMap<String, Float>();	
			Iterator<Entry<String, Float>> tfIterator = tfHashMap.entrySet().iterator();
			while(tfIterator.hasNext()) {
				Entry<String, Float> entry = tfIterator.next();
				String term = entry.getKey();
				float tf = entry.getValue();
				
				float idf = idfHashMap.get(term);
				float tfIdf = tf * idf;
				tfIdfHashMap.put(term, tfIdf);
			}
			tfIdfHashMapofAllFiles.put(fileName, tfIdfHashMap);
		}

		return tfIdfHashMapofAllFiles;
	}
	
	/**
	 * 
	 * @param tfIdfHashMapofAllFiles [文件名，(词元，TF-IDF)]二维哈希表
	 * @description 利用双重循环解析二维哈希表，并且根据格式：文件名{词元=tfIdf} 展示其信息
	 */
	public static void displayTfIdf(HashMap<String, HashMap<String, Float>> tfIdfHashMapofAllFiles) {
		System.out.println("----TF-IDF数据(文件名{词元=tf-idf)}：----");
		Iterator<Entry<String, HashMap<String, Float>>> tfIdfofAllFilesIterator = tfIdfHashMapofAllFiles.entrySet().iterator();
		while(tfIdfofAllFilesIterator.hasNext()) {
			Entry<String, HashMap<String, Float>> fileNameWithEntry = tfIdfofAllFilesIterator.next();
			String fileName = fileNameWithEntry.getKey();
			HashMap<String, Float> tfIdfHashMap = fileNameWithEntry.getValue();
			
			System.out.println("文件名：" + fileName + "\n" + "{");
			Iterator<Entry<String, Float>> tfIdfTterator = tfIdfHashMap.entrySet().iterator();
			while(tfIdfTterator.hasNext()) {
				Entry<String, Float> entry = tfIdfTterator.next();
				String term = entry.getKey();
				float tfIdf = entry.getValue();
				
				System.out.println("\t" + term + " = " + tfIdf);
			}
			System.out.println("}");
		}
	}
}
