package ru.javabeg.training.spring.impls.pool;

import java.util.Map;

import ru.javabegin.training.spring.interfaces.Robot;
import ru.javabegin.training.spring.interfaces.RobotPool;

public class T1000Pool implements RobotPool {

	private Map<String, Robot> robotCollection;

	public T1000Pool(Map<String, Robot> robotCollection) {
		super();
		this.robotCollection = robotCollection;
	}

	public Map<String, Robot> getRobotCollection() {
		return robotCollection;
	}

	public void action() {
		for (Map.Entry<String, Robot> robot : robotCollection.entrySet()) {
			System.out.println(robot.getKey());
			robot.getValue().action();
		}
	}

}
