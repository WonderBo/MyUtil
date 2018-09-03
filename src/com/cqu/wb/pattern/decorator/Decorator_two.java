/**
 * @description 装饰者二
 */
package com.cqu.wb.pattern.decorator;

public class Decorator_two extends Decorator {

	public Decorator_two(Human human) {
		super(human);
		// TODO Auto-generated constructor stub
	}
	
	public void goClothespress() {
		System.out.println("穿衣：在衣柜找找~~~~");
	}
	
	public void findPlaceOnMap() {
		System.out.println("出行：在地图找找----");
	}

	@Override
	public void wearClothes() {
		// TODO Auto-generated method stub
		super.wearClothes();
		goClothespress();
	}

	@Override
	public void walkToWhere() {
		// TODO Auto-generated method stub
		super.walkToWhere();
		findPlaceOnMap();
	}
}
