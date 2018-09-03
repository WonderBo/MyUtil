/**
 * @description 简单工厂模式（利用反射机制实现）
 * 				原理：利用Class.forName(clazz.getName()).newInstance()，与简单的new一个对象类似
 * 				特点：工厂也是一个具体的类，非接口 抽象类。但它的获取产品的方法是利用反射机制生成对象返回，好处是增加一种产品时，不需要修改获取产品方法的代码。
 * 				缺点：1.Class.forName(clazz.getName()).newInstance()调用的是无参构造函数生成对象。而工厂方法应该用于复杂对象的初始化 ，
 * 					      当需要调用有参的构造函数时便无能为力了，这样像为了工厂而工厂。
 * 					 2.不同的产品需要不同额外参数的时候不支持。
 */
package com.cqu.wb.pattern.factory;

interface Shape2 {
	public void draw();
}

class Circle2 implements Shape2 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画圆...");
	}
}

class Rectangle2 implements Shape2 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画长方形...");
	}
}

class Square2 implements Shape2 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画正方形...");
	}
}

class ShapeFactory2 {
	@SuppressWarnings("unchecked")
	public <T extends Shape> T getShape(Class<T> clazz) {
		T result = null;
		try {
			result = (T) Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}

public class SimpleFactory2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory2 shapeFactory = new ShapeFactory2();
		shapeFactory.getShape(Circle.class).draw();
		shapeFactory.getShape(Rectangle.class).draw();
		shapeFactory.getShape(Square.class).draw();
	}

}
