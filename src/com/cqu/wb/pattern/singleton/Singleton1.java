/**
 * @description 懒汉式单例模式(线程不安全)
 * 				线程安全：如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。
 * 						 如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，
 * 						 就是线程安全的。
 * 				单例模式特点：1、单例类只能有一个实例。
 *　							2、单例类必须自己创建自己的唯一实例。
 *　							3、单例类必须给所有其他对象提供这一实例。
 *					     目的： 选择单例模式就是为了避免不一致状态，避免政出多头
 */
package com.cqu.wb.pattern.singleton;

//保证了延迟加载，但没有考虑线程安全问题，它是线程不安全的，并发环境下很可能出现多个Singleton实例
public class Singleton1 {
	//实例必须为静态的，因为其唯一性（唯一实例）
	private static Singleton1 instance = null;
	
	//将构造方法限定为private避免了类在外部被实例化
	private Singleton1() {
		
	}
	
	//唯一实例只能通过getInstance()方法访问，由于类不能在外部被实例化，因此此方法必须为static
	public static Singleton1 getInstance() {
		if(instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}
