/**
 * @description synchronized用法(对象锁与类锁)
 * 				1.内置锁：每个java对象都可以用做一个实现同步的锁，这些锁成为java的内置锁
 * 				2.对象锁：对象锁是用于对象实例方法，或者一个对象实例上的
 * 				3.类锁：类锁是用于类的静态方法或者一个类的class对象上的
 * 				4.注意：类锁只是一个概念上的东西，并不是真实存在的，它只是用来帮助我们理解锁定实例方法和静态方法的区别的
 * 					类的对象实例可以有很多个，但是每个类只有一个class对象，所以不同对象实例的对象锁是互不干扰的，
 * 				但是每个类只有一个类锁，所以属于同一个类的不同对象实例的类锁是相互干扰的。
 * 					当获取到与对象关联的内置锁时，并不能阻止其他线程访问该对象，当某个线程获得对象的锁之后，
 * 				只能阻止其他线程获得同一个锁。之所以每个对象都有一个内置锁，是为了免去显式地创建锁对象。
 * 					因此synchronized只是一个内置锁的加锁机制，当某个方法加上synchronized关键字后，
 * 				就表明要获得该内置锁才能执行，并不能阻止其他线程访问不需要获得该内置锁的方法。
 * 					实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
 */
package com.cqu.wb.thread;

public class TestSynchronized {
	/**
	 * @description 作用于代码块的对象锁
	 */
	public void testObjectLock1() {
		synchronized(this) {
			int i = 5;
			//i-- > 0相当于 j = i-- > 0，是先进行 > 比较，再进行 -- 操作，注意运算符的优先级
			while(i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @description 作用于方法的对象锁
	 */
	public synchronized void testObjectLock2() {
		int i = 5;
		while(i-- > 0) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @description 作用于代码块的类锁
	 */
	public void testClassLock1() {
		synchronized(TestSynchronized.class) {
			int i = 5;
			while(i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @description 作用于方法的类锁
	 */
	public static synchronized void testClassLock2() {
		int i = 5;
		while(i-- > 0) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final TestSynchronized test = new TestSynchronized();
		
		//匿名内部类
		/*4个线程的new Runnable(){...}明明创建多个对象，而不是相同对象，却可以实现对象锁的互斥，
		    这是因为与该对象锁关联的对象是test对象（由this与成员方法可见，因此是同一对象），而不是匿名内部类的对象*/
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.testObjectLock1();
			}
		}, "对象锁-代码块-线程");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.testObjectLock2();
			}
		}, "对象锁-方法-线程");
		
		Thread thread3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.testClassLock1();
			}
		}, "类锁-代码块-线程");
		
		Thread thread4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TestSynchronized.testClassLock2();
			}
		}, "类锁-方法-线程");
		
		/*注意：java编译器在编译成字节码的时候，会对代码进行一个重排序，也就是说，编译器会根据实际情况对代码进行一个
		合理的排序，编译前代码写在前面，在编译后的字节码不一定排在前面，因此thread间的运行顺序不一定是代码顺序*/
		
		//线程获取或者等待对象锁
		thread1.start();
		thread2.start();
		//线程获取或者等待类锁
		thread3.start();
		thread4.start();
		
		//运行结果：对象锁(针对相同对象)间互斥，类锁间互斥，但是对象锁和类锁间不互斥
		//解释原因： 1.对象锁(针对相同对象)间访问的临界资源是相同的，都位于堆区(对象)，此时需要互斥获得与释放同步锁；
		//		   2.类锁间访问的临界资源是相同的，静态方法位于静态存储区(注:代码块的类锁与静态方法的类锁是类似的)，此时也需要互斥获得与释放同步锁；
		//		   3.对象锁与类锁间访问的临界资源位于不同位置，此时不需要进行互斥（即类锁和对象锁是两个不一样的锁，控制着不同的区域，它们是互不干扰的）
	}
}
