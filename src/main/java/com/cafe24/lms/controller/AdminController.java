package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.enumeration.Role;
import com.cafe24.lms.service.AdminService;
import com.cafe24.security.Auth;

@Auth(role=Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
    @Autowired
    private AdminService adminService;
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String main( 
	        @RequestParam(value="page", required=false, defaultValue="0") Integer page,
	        Model model ) {
	    model.addAttribute("itemList", adminService.getRentItemList(page));
        model.addAttribute("totalCount", adminService.getTotalCount());
        model.addAttribute("totalPage", adminService.getTotalPage());
        model.addAttribute("page", page);
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String board(
	        @RequestParam(value="page", required=false, defaultValue="0") Integer page,
            Model model) {
	    model.addAttribute("itemList", adminService.getReserveItemList(page));
        model.addAttribute("totalCount", adminService.getTotalCount());
        model.addAttribute("totalPage", adminService.getTotalPage());
        model.addAttribute("page", page);
		return "admin/reserve";
	}
	
}
