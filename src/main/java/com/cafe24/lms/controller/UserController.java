package com.cafe24.lms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;

@Controller
@RequestMapping( "/user" )
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute User user){
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join1(@ModelAttribute @Valid User user, BindingResult result) {
	    System.out.println(user);
	    if(result.hasErrors()) {
            return "user/join";
        }
	    userService.join(user);
	    return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}
}
