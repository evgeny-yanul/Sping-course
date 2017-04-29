package ru.javabegin.training.spring.aop.loggins;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class MyLogger {

	public Object watchTime(ProceedingJoinPoint joinpoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("method begin: " + joinpoint.getSignature().toShortString());
		Object output = null;
		try {
			output = joinpoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms");

		return output;
	}

}
