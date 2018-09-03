/**
 * @description 读取文件夹下的文件内容
 */
package com.cqu.wb.tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {
	private static List<String> fileList = new ArrayList<String>();
	/**
	 * 
	 * @param filePath 文件目录
	 * @return 文件名列表
	 * @description 获取指定目录(包括子目录)下的文件名列表
	 */
	public static List<String> readDir(String dirPath) {
		//输入验证
		if(dirPath == null || dirPath.equals("")) {
			System.out.println("文件路径为空");
			return null;
		}
		
		try {
			File file = new File(dirPath);

			if(!file.isDirectory()) {		//文件时添加
				fileList.add(file.getAbsolutePath());
			} else {						//目录时递归
				String[] fileNameArray = file.list();
				
				for(int i=0; i<fileNameArray.length; i++) {
					//文件分割符：在Windows系统中的值为"\"(需要进行转义),而在Linux系统中的值为"/"
					readDir(dirPath + "\\" + fileNameArray[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return fileList;
	}
	
	/**
	 * 
	 * @param filePath 文件路径
	 * @return 文件内容
	 * @description 读取文件内容
	 */
	@SuppressWarnings("resource")
	public static String readFile(String filePath) {
		//输入验证
		if(filePath == null || filePath.equals("")) {
			System.out.println("文件路径为空");
			return null;
		}
		
		//String为常量不可修改，StringBuffer可以进行修改
		StringBuffer stringBuffer = new StringBuffer();			
		
		try {
			//StreamReader：字节流转换为字符流，便于处理中文文本
			//组合流过滤器：添加多重功能
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath), "gbk");	
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String fileLine = bufferedReader.readLine();
			while(fileLine != null) {
				//Windows系统的行结束符为“\r\n”，UNIX系统为“\n”，通过System.getProperty("line.separator")获取
				stringBuffer.append(fileLine).append("\r\n");	
				fileLine = bufferedReader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
}
