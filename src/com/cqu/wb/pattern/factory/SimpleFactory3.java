/**
 * @description 简单工厂模式（利用多方法封装实现）
 * 				支持源码：java.util.concurrent.Executors类便是一个生成Executor 的工厂 ，其采用的便是 多方法（静态）工厂模式
 * 				优点：方便创建 同种类型的 复杂参数 对象（参数数量、类型均可改变）。
 */
package com.cqu.wb.pattern.factory;

interface Shape3 {
	public void draw();
}

class Circle3 implements Shape3 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画圆...");
	}
}

class Rectangle3 implements Shape3 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画长方形...");
	}
}

class Square3 implements Shape3 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画正方形...");
	}
}

class ShapeFactory3 {  
	public Shape3 getCircle() {
		return new Circle3();
	}
	
	public Shape3 getRectangle() {
		return new Rectangle3();
	}
	
	public Shape3 getSquare() {
		return new Square3();
	}
} 

public class SimpleFactory3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory3 shapeFactory = new ShapeFactory3();
		shapeFactory.getCircle().draw();
		shapeFactory.getRectangle().draw();
		shapeFactory.getSquare().draw();
	}

}
