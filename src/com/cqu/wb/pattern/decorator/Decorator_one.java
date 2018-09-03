/**
 * @description 装饰者一
 */
package com.cqu.wb.pattern.decorator;

public class Decorator_one extends Decorator {

	public Decorator_one(Human human) {
		super(human);
		// TODO Auto-generated constructor stub
	}
	
	public void goHome() {
		System.out.println("穿衣：进屋~~~~");
	}

	public void findMap() {
		System.out.println("出行：找地图----");
	}
	
	@Override
	public void wearClothes() {
		// TODO Auto-generated method stub
		super.wearClothes();
		goHome();
	}

	@Override
	public void walkToWhere() {
		// TODO Auto-generated method stub
		super.walkToWhere();
		findMap();
	}
}
