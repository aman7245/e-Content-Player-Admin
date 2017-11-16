package com.project.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.project.entity.Login;
import com.project.service.LoginService;

@Controller
public class LoginController extends WebMvcConfigurerAdapter{
	
	@Autowired LoginService loginserv;
	
	@GetMapping("/")
	public String home(Model model){
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("/login")
	public String allowLogin(@Valid @ModelAttribute Login login, BindingResult result){
		try {
				loginserv.checkLogin(login);
			}catch (EntityNotFoundException enf){
				enf.printStackTrace();
				return "login";
			} catch (NullPointerException ne){
				ne.printStackTrace();
				return "login";
		} 
		return "redirect:/findpackage";
	}
	
}
