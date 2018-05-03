package com.cafe24.lms.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.lms.dto.JSONResult;
import com.cafe24.lms.service.UserService;


@Controller(value="userAPIController")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value="/checkemail", method=RequestMethod.POST)
    public JSONResult checkMail(
            @RequestParam(value = "email", required = true, defaultValue = "") String email) {
        System.out.println(email);
        return JSONResult.success((userService.isExist(email))?"exist":"not exist");
    }
}
