package com.circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.circle.model.UserModel;
import com.circle.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@RequestMapping(value = "/testLoginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from JournalDEV successfully.");
		}

		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = { "/testLoginPage" }, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public String valildateUserLogin(@ModelAttribute UserModel user) {
		user = userService.findUserFromCredentials(user);
		if (user == null) {
			return "dashboard";
		}
		return "dashboard";
	}


}