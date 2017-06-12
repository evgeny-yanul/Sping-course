package ru.javabegin.training.springwebflow.objects;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class UserService {

	private ArrayList<User> userList = new ArrayList<User>();

	public UserService() {
		userList.add(new User("user", "pass"));
	}

	public boolean userExist(User user) {

		if (userList.contains(user)) {
			return true;
		} else {
			return false;
		}
	}

	public  void createUser(User user){
		userList.add(user);
	}
}
