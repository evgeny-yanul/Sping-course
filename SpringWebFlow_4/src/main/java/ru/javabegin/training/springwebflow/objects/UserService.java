package ru.javabegin.training.springwebflow.objects;

import org.springframework.stereotype.Component;

@Component
public class UserService {

	public String checkUser(User user) {

		if (user.getName() != null && user.getName().equals("user") && user.getPassword() != null && user.getPassword().equals("pass")) {
			return "success";
		} else {
			return "failed";
		}

	}
}
