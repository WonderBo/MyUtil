/**
 * @description 统计词频
 */
package com.cqu.wb.tfidf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class TermFrequency {

	/**
	 * 
	 * @param wordList 词元列表
	 * @return (词元，出现次数)哈希表
	 * @description 根据分词后的词列表，统计词元出现次数，得到对应哈希表
	 */
	public static HashMap<String, Integer> normalTF(List<String> wordList) {
		//输入验证
		if(wordList == null || wordList.size() == 0) {
			System.out.println("该文件对应词元列表为空");
			return null;
		}

		HashMap<String, Integer> TermWithTimesHashMap = new HashMap<String, Integer>();

		System.out.println("----词元名称：----");
		//类似于计数器进行词数统计
		for(String word : wordList) {
			if(TermWithTimesHashMap.get(word) == null) {
				TermWithTimesHashMap.put(word, 1);
				System.out.println(word);
			} else {
				TermWithTimesHashMap.put(word, TermWithTimesHashMap.get(word) + 1);
			}
		}

		return TermWithTimesHashMap;
	}

	/**
	 * 
	 * @param wordList 词元列表
	 * @return (词元，TF)哈希表
	 * @description 根据分词后的词列表，统计词元出现频率，得到对应哈希表
	 */
	public static HashMap<String, Float> tf(List<String> wordList) {

		HashMap<String, Float> tfHashMap = new HashMap<String, Float>();
		HashMap<String, Integer> termWithTimesHashMap = TermFrequency.normalTF(wordList);
		int wordNum = wordList.size();

		System.out.println("----TF数据(词元=tf)：----");
		Iterator<Entry<String, Integer>> termWithTimesIterator = termWithTimesHashMap.entrySet().iterator();
		while(termWithTimesIterator.hasNext()) {
			Entry<String, Integer> entry = termWithTimesIterator.next();
			String term = entry.getKey();
			//类型转换问题：int/int为int，int/float或者float/int为float
			Float tf = (float)entry.getValue() / wordNum;
			tfHashMap.put(term, tf);
			
			System.out.println(term + " = " + tf);
		}

		return tfHashMap;
	}

	/**
	 * 
	 * @param dirPath 文件目录
	 * @return [文件名，(词元，出现次数)]哈希表
	 * @description 根据文件目录获取子文件名列表，根据文件名对文件内容进行分词，得到指定文件的(词元，出现次数)哈希表，最后汇总为对应二维哈希表
	 */
	public static HashMap<String, HashMap<String, Integer>> normalTFOfAllFiles(String dirPath) {
		HashMap<String, HashMap<String, Integer>> TermWithTimesHashMapofAllFiles = new HashMap<String, HashMap<String,Integer>>();

		List<String> fileList = ReadFiles.readDir(dirPath);
		for(String filePath : fileList) {
			List<String> wordList = WordSegmentation.cutWords(filePath);
			HashMap<String, Integer> termWithTimesHashMap = TermFrequency.normalTF(wordList);
			TermWithTimesHashMapofAllFiles.put(filePath, termWithTimesHashMap);
		}

		return TermWithTimesHashMapofAllFiles;
	}

	/**
	 * 
	 * @param dirPath 文件目录
	 * @return [文件名，(词元，TF)]二维哈希表
	 * @description 根据文件目录获取子文件名列表，根据文件名对文件内容进行分词，得到指定文件的(词元，TF)哈希表，最后汇总为对应二维哈希表
	 */
	public static HashMap<String, HashMap<String, Float>> tfOfAllFiles(String dirPath) {
		HashMap<String, HashMap<String, Float>> tfHashMapofAllFiles = new HashMap<String, HashMap<String,Float>>();

		List<String> fileList = ReadFiles.readDir(dirPath);
		for(String filePath : fileList) {
			List<String> wordList = WordSegmentation.cutWords(filePath);
			HashMap<String, Float> tfHashMap = TermFrequency.tf(wordList);
			tfHashMapofAllFiles.put(filePath, tfHashMap);
		}

		return tfHashMapofAllFiles;
	}
}
