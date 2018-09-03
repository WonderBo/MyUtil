/**
 * @description 测试
 */
package com.cqu.wb.tfidf;

import java.util.HashMap;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "D:\\资料\\程序\\Java\\tfidf所需插件和测试文件\\testfiles";
		
		HashMap<String,HashMap<String, Float>> tfHashMapofAllFiles = TermFrequency.tfOfAllFiles(filePath);
		System.out.println();
		HashMap<String, Float> idfHashMap = InverseDocumentFrequency.idf(tfHashMapofAllFiles);
		System.out.println();
		HashMap<String, HashMap<String, Float>> tfIdfHashMapofAllFiles = TfIdf.tfIdfOfAllFiles(tfHashMapofAllFiles, idfHashMap);
		TfIdf.displayTfIdf(tfIdfHashMapofAllFiles);
	}

}
