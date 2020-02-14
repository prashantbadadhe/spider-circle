package com.circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.circle.model.UserModel;
import com.circle.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/userManagement" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "userManagement";
	}

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String productsPage(ModelMap model) {
		return "dashboard";
	}

	@RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {
		return "contactus";
	}

	@RequestMapping(value = { "/country" }, method = RequestMethod.GET)
	public String country(ModelMap model) {
		model.addAttribute("select2", "static/metronic/global/plugins/select2/select2.css");
		return "country";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}

	@RequestMapping(value = { "/homePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");
		return model;
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

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

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public String test(ModelMap model) {
		return "test";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public String valildateUserLogin(@ModelAttribute UserModel user) {
		user = userService.findUserFromCredentials(user);
		if (user == null) {
			return "login";
		}
		return "dashboard";
	}

	@RequestMapping(value = { "/state" }, method = RequestMethod.GET)
	public String state(ModelMap model) {
		return "state";
	}

	@RequestMapping(value = { "/district" }, method = RequestMethod.GET)
	public String district(ModelMap model) {
		return "district";
	}

	@RequestMapping(value = { "/taluka" }, method = RequestMethod.GET)
	public String taluka(ModelMap model) {
		return "taluka";
	}

	@RequestMapping(value = { "/ward" }, method = RequestMethod.GET)
	public String ward(ModelMap model) {
		return "ward";
	}

	@RequestMapping(value = { "/accesscontroller" }, method = RequestMethod.GET)
	public String accessContrller() {
		return "accessController";
	}

	@RequestMapping(value = { "/village" }, method = RequestMethod.GET)
	public String village(ModelMap model) {
		return "village";
	}

	@RequestMapping(value = { "/aboutus" }, method = RequestMethod.GET)
	public String aboutus(ModelMap model) {
		return "aboutus";
	}

	@RequestMapping(value = { "/setting" }, method = RequestMethod.GET)
	public String setting(ModelMap model) {
		return "setting";
	}

	@RequestMapping(value = { "/domain" }, method = RequestMethod.GET)
	public String domain(ModelMap model) {
		return "domain";
	}
	@RequestMapping(value = { "/contactDetail" }, method = RequestMethod.GET)
	public String contactDetail(ModelMap model) {
		return "contactDetail";
	}
	

	@RequestMapping(value = { "/designation" }, method = RequestMethod.GET)
	public String designation(ModelMap model) {
		model.addAttribute("select2", "static/metronic/global/plugins/select2/select2.css");
		return "designation";
	}
}