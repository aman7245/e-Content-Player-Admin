package com.project.controller;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.entity.Package;
import com.project.entity.Topic;
import com.project.service.PackageService;
import com.project.service.TopicService;
import com.project.service.TopicVO;

@Controller
@RequestMapping("topic")
public class TopicController {

	@Autowired TopicService topicservice;
	@Autowired PackageService packageserv;
	
	@GetMapping("/addtopic/{id}")
	public String start(@PathVariable Long id, Model model){
		Package packages = packageserv.findByPackageId(id);
		TopicVO topicvo = new TopicVO();
		
		model.addAttribute("topicvo", topicvo);
		model.addAttribute("topicList", topicservice.findTopicByPackageId(id));
		topicvo.setPackageId(packages.getPackageId());
		topicvo.setPackageName(packages.getPackageName());
		topicvo.setpId(id);
		
		return "addtopic";
	}
	@PostMapping("/addnewtopic")
	public String createTopic(@ModelAttribute TopicVO topicvo,BindingResult bindingResult,Model model){
		try{
			model.addAttribute(topicservice.addTopic(topicvo));
		} catch(EntityExistsException ee){	ee.printStackTrace();  }
			
		return "redirect:/topic/addtopic/"+topicvo.getpId();	
	}
	
	@RequestMapping(value="/findtopic/{id}",method=RequestMethod.GET)
	public String findTopics(@PathVariable Long id, Model model){
		model.addAttribute("topiclist", topicservice.findAllTopic());
		return "addtopic";
	}
	
	@RequestMapping(value = "/deletetopic/{topicId}", method = RequestMethod.GET)
	public String deletetopic(@PathVariable Long topicId, Model model) {
		Topic topic = topicservice.findByTopicId(topicId);
		long id = topic.getPackages().getId();
		topicservice.deletetopic(topicId);
		return "redirect:/topic/addtopic/"+id;
	}
	
	@RequestMapping(value = "/updatetopic/{topicId}", method = RequestMethod.GET)
	public String updateTopic(@PathVariable Long topicId, Model model) {

		Topic topic = topicservice.findByTopicId(topicId);
		TopicVO topicvo = new TopicVO();
		topicvo.setPackageId(topic.getPackages().getPackageId());
		topicvo.setPackageName(topic.getPackages().getPackageName());
		topicvo.setTopicName(topic.getTopicName());
		topicvo.setTopicId(topicId);
		model.addAttribute("topicvo", topicvo);
		
		return "updatetopic";
	}
	
	@RequestMapping(value = "/updatetopic", method = RequestMethod.POST)
	public String updatedTopic(@ModelAttribute TopicVO topicvo, Model model) {
		Topic topic = topicservice.updateTopic(topicvo);
		long id = topic.getPackages().getId();
		
		return "redirect:/topic/addtopic/"+id;
	}
}
