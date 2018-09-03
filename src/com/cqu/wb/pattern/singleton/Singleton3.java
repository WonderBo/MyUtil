/**
 * @description 懒汉式单例模式(双重检查锁定)
 */
package com.cqu.wb.pattern.singleton;

//保证了延迟加载，线程安全与效率
public class Singleton3 {
	private static volatile Singleton3 instance = null;

	private Singleton3() {

	}

	public static Singleton3 getInstance() {
		//外部判断检查：是否进行加锁（提升效率）
		if(instance == null){
			synchronized(Singleton3.class) {
				//内部判断检查：是否已经实例化（在多线程的情况下确保线程安全）
				if(instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	} 
}
