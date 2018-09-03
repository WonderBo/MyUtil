/**
 * @description 抽象工厂模式
 * 				缺点：将工厂也抽象后，有个显著问题，就是类爆炸，当增加产品系时，需要修改抽象工厂类，因此所有的具体工厂子类都被牵连，需要同步被修改
 */
package com.cqu.wb.pattern.factory;

// 简单工厂模式和工厂方法模式都是单产品系的。抽象工厂是多产品系 /产品族
interface Shape5 {
	public void draw();
}

interface Color5 {
	public void fill();
}

interface MyAbstractFactory {
	// 获取形状产品
	public Shape5 getShape();
	// 获取颜色产品
	public Color5 getColor();
}

class Circle5 implements Shape5 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画圆...");
	}
}

class Rectangle5 implements Shape5 {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("画长方形...");
	}
}

class Red5 implements Color5 {

	@Override
	public void fill() {
		// TODO Auto-generated method stub
		System.out.println("填充红色......");
	}
}

class Blue5 implements Color5 {

	@Override
	public void fill() {
		// TODO Auto-generated method stub
		System.out.println("填充蓝色......");
	}
}

class Factory1 implements MyAbstractFactory {

	@Override
	public Shape5 getShape() {
		// TODO Auto-generated method stub
		return new Circle5();
	}

	@Override
	public Color5 getColor() {
		// TODO Auto-generated method stub
		return new Red5();
	}
}

class Factory2 implements MyAbstractFactory {

	@Override
	public Shape5 getShape() {
		// TODO Auto-generated method stub
		return new Rectangle5();
	}

	@Override
	public Color5 getColor() {
		// TODO Auto-generated method stub
		return new Blue5();
	}
}

public class AbstractFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyAbstractFactory factory1 = new Factory1();
		factory1.getShape().draw();
		factory1.getColor().fill();
		
		MyAbstractFactory factory2 = new Factory2();
		factory2.getShape().draw();
		factory2.getColor().fill();
	}

}
