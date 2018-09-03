/**
 * @description 被装饰者
 */
package com.cqu.wb.pattern.decorator;

public class Person implements Human {

	@Override
	public void wearClothes() {
		// TODO Auto-generated method stub
		System.out.println("今天穿什么衣服好呢？");
	}

	@Override
	public void walkToWhere() {
		// TODO Auto-generated method stub
		System.out.println("今天去什么地方好呢？");
	}

}
