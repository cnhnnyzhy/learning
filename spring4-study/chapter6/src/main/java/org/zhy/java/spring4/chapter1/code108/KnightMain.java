package org.zhy.java.spring4.chapter1.code108;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zhy.java.spring4.chapter1.code102.Knight;

public class KnightMain {

	public static void main(String[] args) {
		//����Spring������
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/zhy/java/spring4/chapter1/code106/knights.xml");
		//��ȡknight bean
		Knight knight = context.getBean(Knight.class);
		//ʹ��knight
		knight.embarkOnQuest();
		context.close();
	}

}
