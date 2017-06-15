package ru.javabegin.training.springwebflow.objects;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

@Component
public class UserService {

	private ArrayList<User> userList = new ArrayList<User>();

	public UserService() {
		userList.add(new User("user", "pass"));
	}

	public boolean userExist(User user, RequestContext context) {		
//		System.out.println(context.getFlashScope().asMap());
//		System.out.println(context.getRequestScope().asMap());
//		System.out.println(context.getConversationScope().asMap());
//		System.out.println(context.getViewScope().asMap());
		System.out.println(context.getFlowScope().asMap());
		
		if (userList.contains(user)) {
			return true;
		} else {
			return false;
		}
	}

	public  void createUser(User user){
		userList.add(user);
	}
	
	private boolean usernameExist(String username){
		for (User user : userList) {
			if (user.getName().equals(username)){
				return true;
			}
		}
		
		return false;
	}
}
