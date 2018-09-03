/**
 * @description 自定义注解及默认值（水果供应商）---注解声明
 */
package com.cqu.wb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
	
	/**
	 * @description 供应商编号
	 * @return
	 */
	public int id() default -1;		//注解元素必须有确定的值，要么在定义注解的默认值中指定，要么在使用注解时指定，非基本类型的注解元素的值不可为null
									//这个约束使得处理器很难表现一个元素的存在或缺失的状态，因为每个注解的声明中，所有元素都存在，并且都具有相应的值，
									//为了绕开这个约束，我们只能定义一些特殊的值，例如空字符串或者负数，以此表示某个元素不存在，在定义注解时，这已经成为一个习惯用法。
	
	/**
	 * @description 供应商名称
	 * @return
	 */
	public String name() default "";
	
	/**
	 * @description 供应商地址
	 * @return
	 */
	public String address() default "";
}
