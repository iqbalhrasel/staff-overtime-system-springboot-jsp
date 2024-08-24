package com.pxc.ot_system.ot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pxc.ot_system.ot.security.UserInfo;

@Controller
public class HomeController {
	
	@Autowired
	private UserInfo userInfo;
	
	@ModelAttribute
	public void navBarUsername(ModelMap modelMap) {
		modelMap.put("username", userInfo.getLoggedInUsername());
	}
	
	
	@GetMapping("/")
	public String homePage() {
		if(userInfo.getUserRole().contains("USER")) {
			return "redirect:/user/";
		}
		
		return "redirect:/admin/";
	}
	
	@GetMapping("/user/")
	public String homePageUser() {
		return "home";
	}
	
	@GetMapping("/admin/")
	public String homePageAdmin() {
		return "admin/homeAdmin";
	}
	
	@GetMapping("/user/logout")
    public String userLogout() {
        return "redirect:/login?logout";
    }

	@GetMapping("/admin/logout")
    public String adminLogout() {
        return "redirect:/login?logout";
    }

}
