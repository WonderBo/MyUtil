/**
 * 工厂模式：方便创建 同种产品类型的 复杂参数 对象。
 *    说明： 工厂模式重点就是适用于 构建同产品类型（同一个接口 基类）的不同具体产品时，这些具体产品new很复杂，需要很多的参数，
 * 	 	       而这些参数中大部分都是固定的，因此懒惰的开发人员便用工厂模式封装并隐藏具体的创建细节。
 * 	               为了适应程序的扩展性，拥抱变化，便衍生出了 工厂方法模式、抽象工厂等模式。
 * 		       无论是简单工厂模式，工厂方法模式，还是抽象工厂模式，他们都属于工厂模式，在形式和特点上也是极为相似的，他们的最终目的都是为了解耦。
 * 使用场景：在任何需要生成复杂对象的地方，都可以使用工厂模式。直接用new可以完成的不需要用工厂模式。
 * 
 * @description 简单工厂模式（利用条件判断语句实现）		
 * 				特点：工厂是一个具体的类，非接口 抽象类。产品可以利用接口或抽象类。获取产品的方法是利用if或者 switch创建产品并返回。
 * 				缺点：1.扩展性差（增加一种产品，除了新增一个产品类，还需要修改工厂类方法）
 * 					 2.不同的产品需要不同额外参数的时候不支持。
 */
package com.cqu.wb.pattern.factory;

interface Shape {
	public void draw();
}

class Circle implements Shape {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画圆...");
	}
}

class Rectangle implements Shape {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画长方形...");
	}
}

class Square implements Shape {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画正方形...");
	}
}

class ShapeFactory {  
	public Shape getShape(String type) {  
		if ("circle".equals(type)) {  
			return new Circle();  
		} else if ("rectangle".equals(type)) {  
			return new Rectangle();  
		} else if ("square".equals(type)) {  
			return new Square();  
		} else {  
			return null;  
		}  
	}        
}  


public class SimpleFactory1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory shapeFactory = new ShapeFactory();
		shapeFactory.getShape("circle").draw();
		shapeFactory.getShape("rectangle").draw();
		shapeFactory.getShape("square").draw();
	}
}
