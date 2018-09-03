/**
 * @description Java程序初始化顺序
 * 				实例化之前需要先进行类加载工作，其中包括了变量和代码块的初始化工作
 * 				优先级（递减）：类>对象，静态>非静态，父类>子类，变量>代码块
 */
package com.cqu.wb.initialization;

public class InitializationSequence {
	
	/**
	 * @param args
	 * @description main方法栈 用于测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一次实例化先在静态存储区进行类加载，后在堆进行对象实例化
		System.out.println("第一次加载：");
		new Sub();
		// 第二次乃至后面就只需在堆进行对象实例化
		System.out.println("\n第二次加载：");
		new Sub();
	}

}

/**
 * 
 * @author 汪波
 * @description 基类
 */
class Base {
	static {
		System.out.println("--基类静态代码块");
	}
	
	{
		System.out.println("--基类非静态代码块");
	}
	
	public Base() {
		System.out.println("--基类构造函数");
	}
}

/**
 * 
 * @author 汪波
 * @description 子类
 */
class Sub extends Base {
	static {
		System.out.println("++子类静态代码块");
	}
	
	{
		System.out.println("++子类非静态代码块");
	}
	
	public Sub() {
		System.out.println("++子类构造函数");
	}
}
