package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentItemId;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.ItemService;
import com.cafe24.security.AuthUser;

@Controller
public class MainController {
    @Autowired
    private ItemService itemService; 

	@RequestMapping( { "", "/main" } )
	public String index(
	        @RequestParam(value="page", required=false, defaultValue="0")Integer page,
	        @RequestParam(value="kwd", required=false, defaultValue="")String keyword,
	        Model model ) {
//	    itemService.initData();
	    model.addAttribute("itemList", itemService.getItemList(page, keyword));
	    model.addAttribute("totalCount", itemService.getTotalCount());
	    model.addAttribute("totalPage", itemService.getTotalPage());
	    model.addAttribute("page", page);
	    model.addAttribute("kwd", keyword);
		return "main/index"; 
	}
	
	@RequestMapping( "/rent" )
	public String rent( 
	        @AuthUser User authUser,
	        @RequestParam(value="itemNo", required=true) Long itemNo,
	        Model model ) {
	    if(authUser == null) {
	        return "redirect:/user/login";
	    }
	    
	    RentItemId rentItemId = new RentItemId();
	    rentItemId.setUser(authUser.getNo());
	    rentItemId.setItem(itemNo);
	    
	    boolean rentalResult = itemService.rent(rentItemId);
	    model.addAttribute("rentalResult", rentalResult);
	    
		return "main/rent";
	}

	@RequestMapping( "/reserve" )
    public String reserve( 
            @AuthUser User authUser,
            @RequestParam(value="itemNo", required=true) Long itemNo,
            Model model ) {
        if(authUser == null) {
            return "redirect:/user/login";
        }
        
        RentItemId rentItemId = new RentItemId();
        rentItemId.setUser(authUser.getNo());
        rentItemId.setItem(itemNo);
        
        boolean rentalResult = itemService.reserve(rentItemId);
        model.addAttribute("reserveResult", rentalResult);
        
        return "main/reserve";
    }
}
