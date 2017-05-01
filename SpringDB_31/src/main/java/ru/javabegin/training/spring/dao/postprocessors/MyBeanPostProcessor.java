package ru.javabegin.training.spring.dao.postprocessors;

import org.springframework.beans.BeansException;

public class MyBeanPostProcessor {

	public Object postProcessAfterInitialization(Object object, String name) throws BeansException {

		return object;
	}

	public Object postProcessBeforeInitialization(Object object, String name) throws BeansException {
		System.out.println(object + " - postProcessBeforeInitialization()");
		return object;
	}

}
