/**
 * @description 枚举类
 * 				优点：不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
 * 				序列化问题：Java规范中规定，每一个枚举类型极其定义的枚举变量在JVM中都是唯一的，因此在枚举类型的序列化和反序列化上，Java做了特殊的规定。
 * 						    在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法
 * 						    来根据名字查找枚举对象，因此反序列化后的实例也会和之前被序列化的对象实例相同。由此可知，枚举天生保证序列化单例。 		    
 */
package com.cqu.wb.pattern.singleton;

class Singleton6{}

// 枚举类型
public enum SingletonEnmu {
	// 枚举对象
	Singleton;

	private Singleton6 singleton;

	//枚举类的构造方法在类加载时被实例化 
	private SingletonEnmu() {
		singleton = new Singleton6();
	}
	public Singleton6 getInstance() {
		return singleton;
	}

	//测试
	public static void main(String[] args) {
		Singleton6 instance1 = SingletonEnmu.Singleton.getInstance();
		Singleton6 instance2 = SingletonEnmu.Singleton.getInstance();

		System.out.println(instance1.toString());
		System.out.println(instance2.toString());

		if(instance1 == instance2) {
			System.out.println("创建的是同一个实例");
		} else {
			System.out.println("创建的不是同一个实例");
		}
	}
}