/**
 * @description 饿汉式单例模式
 */
package com.cqu.wb.pattern.singleton;

//基于ClassLoder机制避免了多线程的同步问题，使instance在类装载时就实例化，没有达到lazy loading的效果
public class Singleton4 {
	private static Singleton4 instance = new Singleton4();
	
	private Singleton4() {
		
	}
	
	public static Singleton4 getInstance() {
		return instance;
	}
}
