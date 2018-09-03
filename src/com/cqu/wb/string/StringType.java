/**
 * @description 字符串类型及原理
 */
package com.cqu.wb.string;

public class StringType {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/** 
         * 情景一：字符串池 (方法区 -> 常量区)
         * JAVA虚拟机(JVM)中存在着一个字符串池，其中保存着很多String对象; 
         * 并且可以被共享使用，因此它提高了效率。 
         * 由于String类是final的，它的值一经创建就不可改变。 
         * 字符串池由String类维护，我们可以调用intern()方法来访问字符串池。  
         */  
		//在字符串池创建了一个对象
        String s1 = "abc";     
        //字符串pool已经存在对象“abc”(共享),所以创建0个对象，累计创建一个对象  
        String s2 = "abc";     
        /*对于 ==，如果作用于基本数据类型（byte、short、char、int、long、float、double、boolean）的变量,则比较的是其存储的“值”是否相等；
                    如果作用与引用类型的变量，则比较其所指向的对象的地址是否相同（即是否同一个对象）。在Java中，String是引用类型。*/
        System.out.println("s1 == s2 : " + (s1 == s2));    					//true  指向同一个对象，  
        /*String的 equals 方法继承自Java中的超级父类Object，Object的equals方法用来比较两个对象的引用是否相等（即是否同一个对象）。
                    但是，String的equals方法不仅是简单地继承，而是进行了重写(Override)，用来比较两个String对象所存储的字符序列值是否相等。*/
        System.out.println("s1.equals(s2) : " + (s1.equals(s2)));    	//true  值相等  
        //------------------------------------------------------over  
        
        /** 
         * 情景二：关于new String("") 
         *  
         */  
        //创建了两个对象，一个存放在字符串池中，一个存在与堆区中；  还有一个对象引用s3存放在栈中  
        String s3 = new String("abc");  
        //字符串池中已经存在“abc”对象，所以只在堆中创建了一个对象  
        String s4 = new String("abc");  
        System.out.println("s3 == s4 : " + (s3 == s4));  				//false   s3和s4栈区的地址不同，指向堆区的不同地址；  
        System.out.println("s3.equals(s4) : " + (s3.equals(s4)));  	//true    s3和s4的值相同  
        System.out.println("s1 == s3 : " + (s1 == s3));  				//false   存放的地区多不同，一个栈区，一个堆区  
        System.out.println("s1.equals(s3) : " + (s1.equals(s3)));  	//true    值相同  
        //------------------------------------------------------over  
        
        /** 
         * 情景三：  
         * Java编译器在编译源码时，对于编译时就可以确定的字符常量，包括字符序列和final字符变量，会自动进行拼接优化。
         * 由于常量的值在编译的时候就被确定(优化)了。 
         * 在这里，"ab"和"cd"都是常量，因此变量str1的值在编译时就可以确定。 
         * 这行代码编译后的效果等同于： String str1 = "abcd"; 
         */  
        String str1 = "ab" + "cd";  //1个对象  
        String str11 = "abcd";   
        System.out.println("str1 = str11 : " + (str1 == str11));  	//true
        //------------------------------------------------------over  
        
        /** 
         * 情景四：  
         * 局部变量str2,str3存储的是存储两个拘留字符串对象(intern字符串对象)的地址。 
         *  
         * 第三行代码原理(str2+str3)： 
         * 运行期JVM首先会在堆中创建一个StringBuilder类， 
         * 同时用str2指向的拘留字符串对象完成初始化， 
         * 然后调用append方法完成对str3所指向的拘留字符串的合并， 
         * 接着调用StringBuilder的toString()方法在堆中创建一个String对象， 
         * 最后将刚生成的String对象的堆地址存放在局部变量str4中。 
         *  
         * 而str5存储的是字符串池中"abcd"所对应的拘留字符串对象的地址。 
         * str4与str5地址当然不一样了。 
         *  
         * 内存中实际上有五个字符串对象： 三个拘留字符串对象、一个String对象和一个StringBuilder对象。 
         */  
        String str2 = "ab";  //1个对象  
        String str3 = "cd";  //1个对象                                         
        String str4 = str2+str3;                                        
        String str5 = "abcd";
        System.out.println("str4 = str5 : " + (str4==str5)); // false  
        //------------------------------------------------------over  
        
        /** 
         * 情景五： 
         *  JAVA编译器对String + 基本数据类型/常量 是当成常量表达式直接求值来优化的。 
         *  运行期的两个String相加，会产生新的对象的，存储在堆(heap)中 
         */  
        String str6 = "b";  
        //str6为变量，在运行期才会被解析。
        String str7 = "a" + str6;  
        String str67 = "ab";  
        System.out.println("str7 = str67 : " + (str7 == str67));  //false  
        final String str8 = "b";  
        //str8为常量变量，编译期会被优化  
        String str9 = "a" + str8;  
        String str89 = "ab";  
        System.out.println("str9 = str89 : " + (str9 == str89));  //true
        //------------------------------------------------------over  
        
        /**
         * 情景六：intern方法
         * 无论是字符串常量区中的String对象，还是堆内存中的String对象，它们的intern方法都是去JVM中的字符串常量区获取相等字符序列的String对象返回
         */
        String s61 = "kvill"; 
        String s62 = new String("kvill"); 
        String s63 = new String("kvill"); 
        System.out.println("str61 = str62 : " + (s61 == s62)); 			//false
        //虽然执行了s1.intern(),但它的返回值没有赋给s62
        s62.intern(); 
        //把常量池中"kvill"的引用赋给s63 
        s63 = s63.intern(); 
        System.out.println("str61 = str62 : " + (s61 == s62)); 			//flase
        System.out.println("str61 = str62 : " + (s61 == s62.intern())); 	//true	说明s1.intern()返回的是常量池中"kvill"的引用
        System.out.println("str61 = str63 : " + (s61 == s63)); 			//true
        
        /**
         * 情景七：作用域与值类型/引用类型
         * 值类型表示复制一个当前变量传给方法，当你在这个方法中改变这个变量的值时，最初生命的变量的值不会变；
         * 引用类型表示你操作的数据是同一个，也就是说当你传一个参数给另一个方法时，你在另一个方法中改变这个变量的值，那么调用这个方法传入的变量的值也将改变
         */
        String str = "hello";
		modifyString(str);		//引用类型将对象地址传递给方法形参，全局参数与局部参数共同指向对象，然后方法内部局部参数重新指向内容为‘world’的String新对象
		System.out.println(str);		//hello		此处调用的是全局参数，依旧指向“hello”对象，但是如果局部参数只是修改指向的对象而未重定向，那么此处将改变
	}

	public static void modifyString(String str) {
		str = "world";			//String不可修改，只会重新生成“world”对象，并将局部参数指向对象
	}
}
