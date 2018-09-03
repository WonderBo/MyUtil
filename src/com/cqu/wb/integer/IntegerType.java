/**
 * @description 整型的IntegerCache、拆箱、装箱、equals()方法
 */
package com.cqu.wb.integer;

/**
 * @author 汪波
 *
 */
public class IntegerType {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		
		// 自动装箱操作：Integer a=1；Java在编译的时候会执行将代码封装成Integer a=Integer.valueOf(1)
		/* Integer.valueOf()中有个内部类IntegerCache（类似于一个常量数组，也叫对象池），它维护了一个Integer数组cache（-128到127），
		 如果有直接返回该对象的引用；如果没有，则使用New keyword创建一个对象，并返回该对象的引用地址。*/
		System.out.println("c=d:" + (c == d));
		System.out.println("e=f:" + (e == f));
		
		// Java的数学计算是在内存栈里操作的，Java会对a,b进行拆箱操作，其实比较的是基本数据类型
		// 注意“==”运算在不遇到算数运算的情况下不会自动拆箱
		System.out.println("c=a+b:" + (c == (a + b)));
		System.out.println("c.equals(a+b):" + (c.equals(a + b)));
		
		// equals()方法不处理数据转型的关系，而“==”支持数据自动转型
		System.out.println("g=a+b:" + (g == (a + b)));
		System.out.println("g.equals(a+b):" + (g.equals(a + b)));
	}

}
