/**
 * @description 自定义注解及默认值（水果名字）---注解声明
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
public @interface FruitName{			
	/**
	 * @description 水果名称
	 * @return
	 */
	String value() default "";			//如果只有一个参数成员,最好把参数名称设为"value",后加小括号.
										//当annotation只有单一成员，并成员命名为"value="。这时可以省去"value="
}


										//附：JDK内置系统注解：
										//@Override：用于修饰此方法覆盖了父类的方法;
										//@Deprecated：用于修饰已经过时的方法;
										//@SuppressWarnnings: 用于通知java编译器禁止特定的编译警告。
