package com.zhy.java.jdk8.features;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * jdk1.8新特性测试
 * @author yang.zhang3
 *
 */
public class TestNewFeatures {
	/**
	 * Lambda表达式和函数式接口
	 */
	@Test
	public void testNewFeatures1(){
		System.out.println("**************");
		Arrays.asList("1", "2", "3").forEach(e -> {System.out.println(e);});
		System.out.println("**************");
		Arrays.asList("4", "5", "6").forEach((String e)->{System.out.println(e);});
		System.out.println("**************");
		String seq = ",";//Lambda表达式可以引用类成员和局部变量，会将这些变量隐式得转换成final的
		Arrays.asList("7", "8", "9").forEach(e->{System.out.print(e + seq);});
		System.out.println("");
		System.out.println("**************");
		List<String> list = Arrays.asList("d","b", "c","a");
		list.sort((e1, e2)->e1.compareTo(e2));
		list.forEach(e->{System.out.println(e);});
	}
}
