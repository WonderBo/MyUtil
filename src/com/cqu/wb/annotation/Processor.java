/**
 * @description 测试类  ---注解处理器
 */
package com.cqu.wb.annotation;

import java.lang.reflect.Field;

public class Processor {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("---不使用注解处理器获取程序元素信息---");
		Apple apple = new Apple();					//基本原则：Annotation不能影响程序代码的执行，无论增加、删除 Annotation，代码都始终如一的执行
		System.out.println(apple.getAppleName());	//根据测试得到annotation的加载并不影响class的执行，因为Annotation与Class在使用上是被分离的
		System.out.println(apple.getAppleColor());
		System.out.println(apple.getAppleProvider());
		
		System.out.println("---使用注解处理器通过程序元素获取注解配置元素信息---");
		getFruitInfo(Apple.class);
	}
	
	//流程：程序通过反射机制获取了某个类的AnnotatedElement对象之后，程序就可以调用该对象的四个方法来访问Annotation对象信息，然后通过Annotation对象来获取注解里面的元数据
	//Java使用Annotation接口来代表程序元素前面的注解，该接口是所有Annotation类型的父接口
	//而AnnotatedElement接口代表程序中可以接受注解的程序元素，是所有程序元素（Class、Method和Constructor）的父接口
	public static void getFruitInfo(Class<?> clazz){
		String strFruitName = " 水果名称：";
		String strFruitColor = " 水果颜色：";
		String strFruitProvider = " 水果供应商：";
		
		Field[] fields = clazz.getDeclaredFields();									//获取所有被注解的程序元素
		
		for(Field field : fields){
			if(field.isAnnotationPresent(FruitName.class)){							//判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false
				FruitName fruitName = field.getAnnotation(FruitName.class);			//获取该程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null
				strFruitName = strFruitName + fruitName.value();					//获取注解对象里面的元数据
				System.out.println(strFruitName);
			}else if(field.isAnnotationPresent(FruitColor.class)){
				FruitColor fruitColor = field.getAnnotation(FruitColor.class);
				strFruitColor = strFruitColor + fruitColor.fruitColor();
				System.out.println(strFruitColor);
			}else if(field.isAnnotationPresent(FruitProvider.class)){
				FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
				strFruitProvider = strFruitProvider + " 供应商编号：" + fruitProvider.id() + " 供应商名称：" 
						+ fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
				System.out.println(strFruitProvider);
			}
		}
	}
}
