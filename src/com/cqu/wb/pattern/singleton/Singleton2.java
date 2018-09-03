/**
 * @description 懒汉式单例模式(线程安全)
 */
package com.cqu.wb.pattern.singleton;

//保证了延迟加载与线程安全，但创建唯一实例后仍需同步锁进行验证，因此大多数情况下不需要同步，所以效率很低
public class Singleton2 {
	private static Singleton2 instance = null;
	
	private Singleton2() {
		
	}
	
	//同步锁（类锁）确保线程安全
	public static synchronized Singleton2 getInstance() {
		//或者synchronized(Singleton2.class){}均可
		if(instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}
