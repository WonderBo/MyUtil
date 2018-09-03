/**
 * @description 对文件内容进行分词
 */
package com.cqu.wb.tfidf;

import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.lucene.IKAnalyzer;

public class WordSegmentation {
	/**
	 * 
	 * @param filePath 文件路径
	 * @return 分词后的词元列表
	 * @description 对文件内容进行分词
	 */
	@SuppressWarnings("resource")
	public static List<String> cutWords(String filePath) {
		List<String> wordList = new ArrayList<String>();
		//IKAnalyzer：基于Lucene的第三方中文分词技术
		IKAnalyzer ikAnalyzer = new IKAnalyzer();

		String content = ReadFiles.readFile(filePath);
		//文件内容为空判断
		if(content != null) {
			try {
				wordList = ikAnalyzer.split(content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(filePath + "文件内容为空");
		}

		return wordList;
	} 
}
