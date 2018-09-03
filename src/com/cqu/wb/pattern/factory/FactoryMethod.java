/**
 * @description 工厂方法模式（产品类与工厂类一一对应，利用多个工厂类封装实现）
 * 				特点：工厂方法模式就是把简单工厂模式中具体的工厂类划分成两层：抽象工厂层+具体的工厂子类层。（一般->特殊）
 * 				区别：1.工厂方法模式特点是不仅仅产品要抽象， 工厂也应该需要抽象；
 * 					 2.工厂方法模式使一个产品类的实例化延迟到其具体工厂子类；
 * 					 3.工厂方法的好处就是更拥抱变化，当需求变化，只需要增删相应的类，不需要修改已有的类；
 * 					      而简单工厂需要修改工厂类的获取产品的方法，多方法（静态/简单）工厂模式需要增加一个静态方法。
 * 				缺点：引入抽象工厂层后，每次新增一个具体产品类，也要同时新增一个具体工厂类。
 * 					
 */
package com.cqu.wb.pattern.factory;

interface Shape4 {
	public void draw();
}

interface ShapeFactory4 {
	public Shape4 getShape();
}

class Circle4 implements Shape4 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画圆...");
	}
}

class CircleFactory implements ShapeFactory4 {

	@Override
	public Shape4 getShape() {
		// TODO Auto-generated method stub
		return new Circle4();
	}
}

class Rectangle4 implements Shape4 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画长方形...");
	}
}

class RectangleFactory implements ShapeFactory4 {

	@Override
	public Shape4 getShape() {
		// TODO Auto-generated method stub
		return new Rectangle4();
	}
}

class Square4 implements Shape4 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画正方形...");
	}
}

class SquareFactory implements ShapeFactory4 {

	@Override
	public Shape4 getShape() {
		// TODO Auto-generated method stub
		return new Square4();
	}
}

public class FactoryMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory4 circleFactory = new CircleFactory();
		circleFactory.getShape().draw();
		ShapeFactory4 rectangleFactory = new RectangleFactory();
		rectangleFactory.getShape().draw();
		ShapeFactory4 squareFactory = new SquareFactory();
		squareFactory.getShape().draw();
	}

}
