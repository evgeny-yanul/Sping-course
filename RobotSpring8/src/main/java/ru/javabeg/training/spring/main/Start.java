package ru.javabeg.training.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.javabeg.training.spring.impls.robot.ModelT1000;

public class Start {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

		Object obj = context.getBean("T1000");

		if (obj instanceof ModelT1000) {

			// ModelT1000 t1000 = (ModelT1000) obj;
			// System.out.println(t1000.getHand());
			// t1000.action();
		}

	}
}
