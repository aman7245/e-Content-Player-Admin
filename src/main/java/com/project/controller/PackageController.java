package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.entity.Package;
import com.project.service.PackageService;

@Controller
public class PackageController {

	@Autowired PackageService packageserv;
	
	@GetMapping("/addpack")
	public String start(Model model){
		model.addAttribute("packages", new Package());
		return "addpackage";
	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	@PostMapping("/addpack")
	public String createPackage(@ModelAttribute Package packages){
		try{
				packageserv.createPackage(packages);
		} catch(EntityExistsException ee){	ee.printStackTrace();  }
		  catch(NullPointerException ne){  ne.printStackTrace();  }
		return "redirect:/findpackage";
	}
	@RequestMapping(value="/findpackage",method=RequestMethod.GET)
	public String findPackage(Model model){
		model.addAttribute("list",packageserv.findAllPackages());
		return "packagelist";
	}
	@RequestMapping(value = "/deletepackage/{id}", method = RequestMethod.GET)
	public String deletePackage(@PathVariable Long id, Model model) {
		packageserv.deletePackage(id);
		model.addAttribute("list",packageserv.findAllPackages());
		return "packagelist";
	}
	@RequestMapping(value = "/updatepackage/{id}", method = RequestMethod.GET)
	public String updatePackage(@PathVariable Long id, Model model) {
		model.addAttribute("packages",packageserv.findByPackageId(id));
		return "updatepackage";
	}
	@RequestMapping(value = "/updatepackage", method = RequestMethod.POST)
	public String updatedPackage(@ModelAttribute Package packages, Model model) {
		packageserv.updatePackage(packages);
		return "redirect:/findpackage";
	}

}
