package ru.javabegin.training;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import ru.javabegin.training.objects.User;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute User user, HttpSession session) {
		user.setName("usernamevalue");
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public String checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, ModelMap modelMap) {

		System.out.println(model);
		System.out.println(modelMap);

		if (bindingResult.hasErrors()) {
			return "login";
		}

		model.addAttribute("user", user);

		return "main";
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed() {
		return new ModelAndView("login-failed", "message", "Login failed!");
	}

	@RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
		User user = new User();
		user.setName(name);
		user.setAdmin(admin);
		return user;
	}

	@RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> setJsonUser(@RequestBody User user) {
		logger.info(user.getName());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
