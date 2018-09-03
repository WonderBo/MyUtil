/**
 * @description 被装饰者接口（也可以是抽象类，为装饰者与被装饰者共同的模板）
 * 装饰者模式：动态给一个对象添加一些额外的职责（方法里的功能扩展）,就像在墙上刷油漆.
 * 			  使用Decorator模式相比用生成子类方式达到功能的扩充显得更为灵活。
 * 设计原则为：对扩展开放、对修改关闭
 */
package com.cqu.wb.pattern.decorator;

public interface Human {
	public void wearClothes();
	
	public void walkToWhere();
}
