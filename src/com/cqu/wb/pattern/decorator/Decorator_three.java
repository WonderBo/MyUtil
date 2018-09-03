/**
 * @description 装饰者三
 */
package com.cqu.wb.pattern.decorator;

public class Decorator_three extends Decorator {

	public Decorator_three(Human human) {
		super(human);
		// TODO Auto-generated constructor stub
	}

	public void findClothes() {
		System.out.println("穿衣：找到衣服~~~~");
	}
	
	public void findPlace() {
		System.out.println("出行：找到地方----");
	}

	@Override
	public void wearClothes() {
		// TODO Auto-generated method stub
		super.wearClothes();
		findClothes();
	}

	@Override
	public void walkToWhere() {
		// TODO Auto-generated method stub
		super.walkToWhere();
		findPlace();
	}
	
	
}
