package ru.javabegin.training.spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.aop.objects.FileManager;
import ru.javabegin.training.spring.aop.objects.FileManager2;

public class Start {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileUtil = (FileManager) context.getBean("fileManager");
		FileManager2 fileUtil2 = (FileManager2) context.getBean("fileManager2");
		String folder = "c:\\Windows\\System32";
		fileUtil.getExtensionCount(folder);
		fileUtil2.getExtensionCount(folder);
		// fileUtil.getExtensionList(folder);
		// fileUtil.getExtensionCount("c:\\Windows\\");
	}

}
