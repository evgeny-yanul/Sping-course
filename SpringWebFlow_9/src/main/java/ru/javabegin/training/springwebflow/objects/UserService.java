package ru.javabegin.training.springwebflow.objects;

import java.util.ArrayList;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ru.javabegin.training.springwebflow.exceptions.UserExistException;

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

	public String createUser(User user, RequestContext context) throws UserExistException{
		if (usernameExist(user.getName())){		
			throw new UserExistException("User exist!");
		}

		userList.add(user);
		
		context.getMessageContext().addMessage(new MessageBuilder().code("user_created").build());
		context.getMessageContext().addMessage(new MessageBuilder().code("enter").build());
		
		return "success";
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
