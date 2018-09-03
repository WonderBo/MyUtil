/**
 * @description 测试类
 */
package com.cqu.wb.pattern.decorator;

public class Test {
	public static void main(String[] args) {
		Human person = new Person();
		Decorator decorator = new Decorator_three(new Decorator_two(new Decorator_one(person)));
		decorator.wearClothes();
		decorator.walkToWhere();
	}
}
