/**
 * @description 字符串常用操作
 */
package com.cqu.wb.string;

public class StringOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string="good morning everyone";
		String sub="morning";
		System.out.println("字符串的长度：\t" + "morning:" + sub.length() + "; good morning everyone:" + string.length());
		int a=string.indexOf(sub);
		if(a>=0){
			System.out.println("morning在字符串中的位置:"+a);
			String ss1=string.substring(0,a+sub.length());
			String ss2=string.substring(a+sub.length(),string.length());
			System.out.println("你需要的结果是:"+ss1);
			System.out.println("删掉的字符是:"+ss2);
		}else{
			System.out.println("不存在");
		}
	}

}
