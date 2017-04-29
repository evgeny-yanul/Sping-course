package ru.javabegin.training.spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabegin.training.spring.aop.objects.FileManager;

public class Start {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileUtil = (FileManager) context.getBean("fileManager");
		String folder = "c:\\Windows\\System32";
		fileUtil.getExtensionCount(folder);
		fileUtil.getExtensionCount("c:\\Windows\\");
	}

}
