package ru.javabegin.beans;

import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.javabegin.objects.CarInterface;

@Component
@SessionScoped
public class MainBean {

	@Autowired
	private CarInterface carImpl;

	public String getMessage() {

		// Рабочий, но нежелательный вариант
		// ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		// SpringInterface springObj = ctx.getBean("springObj", SpringInterface.class);

		return carImpl.getSound();
	}

}
