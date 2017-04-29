package ru.javabeg.training.spring.impls.robot;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import ru.javabegin.training.spring.interfaces.Hand;
import ru.javabegin.training.spring.interfaces.Head;
import ru.javabegin.training.spring.interfaces.Leg;
import ru.javabegin.training.spring.interfaces.Robot;

public class ModelT1000 extends BaseModel implements Robot, InitializingBean, DisposableBean {

	private String color;
	private int year;
	private boolean soundEnable;

	public ModelT1000() {

	}

	public ModelT1000(Hand hand, Leg leg, Head head) {
		super(hand, leg, head);
	}

	public ModelT1000(String color, int year, boolean soundEnable) {
		super();
		this.color = color;
		this.year = year;
		this.soundEnable = soundEnable;
	}

	public ModelT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnable) {
		super(hand, leg, head);
		this.color = color;
		this.year = year;
		this.soundEnable = soundEnable;
	}

	@Override
	public void action() {
		getHead().calc();
		getHand().catchSomething();
		getLeg().go();
		System.out.println("color: " + color);
		System.out.println("year: " + year);
		System.out.println("can play cound: " + soundEnable);
	}

	@Override
	public void dance() {
		System.out.println("T1000 is dancing!");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isSoundEnable() {
		return soundEnable;
	}

	public void setSoundEnable(boolean soundEnable) {
		this.soundEnable = soundEnable;
	}

	public void initObject() {
		System.out.println("init");
	}

	public void destroyObject() {
		System.out.println("destroy");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(this + " - method destroy()");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this + " - method init()");

	}

}
