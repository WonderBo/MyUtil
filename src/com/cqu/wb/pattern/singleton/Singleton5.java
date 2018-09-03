/**
 * @description 静态内部类
 */
package com.cqu.wb.pattern.singleton;

/*同样利用了ClassLoder的机制来保证初始化instance时只有一个线程，但是这种方式是Singleton5类被装载了，
  instance不一定被初始化。因为LazyHolder类没有被主动使用，只有显示通过调用getInstance方法时，
    才会显示装载LazyHolder类，从而实例化instance，保证了延迟加载。*/
public class Singleton5 {
	private Singleton5() {
		
	}
	
	private static class LazyHolder {
		private static final Singleton5 INSTANCCE = new Singleton5();
	}
	
	public static final Singleton5 getInstance() {
		return LazyHolder.INSTANCCE;
	}
	
	//测试
	public static void main(String[] args) {
		Singleton5 instance1 = Singleton5.getInstance();
		Singleton5 instance2 = Singleton5.getInstance();
		
		System.out.println(instance1.toString());
		System.out.println(instance2.toString());
		
		if(instance1 == instance2) {
			System.out.println("创建的是同一个实例");
		} else {
			System.out.println("创建的不是同一个实例");
		}
	}
}
