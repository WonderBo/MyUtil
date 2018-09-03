/**
 * @description 装饰者抽象类（为装饰者更为具体或者说进一步的模板）
 * 实现Human接口：实现构造方法参数的多态，表现出一种装饰、链式的类似于过滤的行为
 * 持有Human变量：方法全部委托给该接口调用，实际是交给该接口的实现类即装饰者子类（多态）进行调用
 */
package com.cqu.wb.pattern.decorator;

public abstract class Decorator implements Human {
	private Human human;
	
	public Decorator(Human human) {
		this.human = human;
	}
	
	public void wearClothes() {
		human.wearClothes();
	}
	
	public void walkToWhere() {
		human.walkToWhere();
	}
}
