/**
 * @description 自定义注解及默认值（水果颜色）---注解声明
 */
package com.cqu.wb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
											//@Inherited元注解是一个标记注解，阐述了某个被标注的Annotation类型是可以被继承的。即如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类
@Target(ElementType.FIELD)					//@Target用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
@Retention(RetentionPolicy.RUNTIME)			//@Retention表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
@Documented									//@Documented表示将注释包含在JavaDoc中，即注解可以被例如JavaDoc此类的工具文档化。Documented是一个标记注解，没有成员
public @interface FruitColor {				//public @interface 注解名 {定义体}
											//@interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）,可以通过default来声明参数的默认值
	/**										
	 * @description 颜色枚举
	 * @author 汪波
	 *
	 */
	public enum Color{BULE, RED, GREEN};	
	
	/**
	 * @description 颜色属性
	 * @return
	 */
											//Annotation的成员在Annotation类型中以无参数的方法的形式被声明
	Color fruitColor() default Color.GREEN;	
}
