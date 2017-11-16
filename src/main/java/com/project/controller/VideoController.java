package com.project.controller;

import java.io.IOException;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.Topic;
import com.project.entity.Videos;
import com.project.service.TopicService;
import com.project.service.VideoService;
import com.project.service.VideoVO;

@Controller
@RequestMapping("video")
public class VideoController {
	
	@Autowired VideoService videoserv;
	@Autowired TopicService topicservice;

    @GetMapping("/addvideo/{topicId}")
    public String index(@PathVariable Long topicId,Model model) {
    	System.out.println("#######");
    	System.out.println("$$$$"+topicId);
    	Topic topic = topicservice.findByTopicId(topicId);
    	VideoVO videovo = new VideoVO();
    	videovo.setTopicId(topicId);
		videovo.setTopicName(topic.getTopicName());
    	model.addAttribute("videovo", videovo);
		model.addAttribute("videoList", videoserv.findVideoByTopicId(topicId));
    	
        return "addvideo";
    }

    @PostMapping("/upload") 
    public String singleFileUpload(@ModelAttribute VideoVO videovo,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,Model model) {
    	try{
    			model.addAttribute(videoserv.addVideo(videovo, file, redirectAttributes));
    	} catch(EntityExistsException ee){	ee.printStackTrace();  }
		  catch(NullPointerException ne){  ne.printStackTrace();  }
        return "redirect:/video/addvideo/"+videovo.getTopicId();
    }

    @RequestMapping(value="/findvideo/{topicId}",method=RequestMethod.GET)
	public String findVideos(@PathVariable Long topicId, Model model){
		model.addAttribute("videoList", videoserv.findAllVideos());
		return "addvideo";
    }
    
    @RequestMapping(value = "/deletevideo/{videoId}", method = RequestMethod.GET)
	public String deletetopic(@PathVariable Long videoId, Model model) {
		Videos video = videoserv.findByVideoId(videoId);
		long id = video.getTopics().getTopicId();
		try{
			videoserv.deletevideo(videoId);
		} catch(IOException ioe){	ioe.printStackTrace();   }
		return "redirect:/video/addvideo/"+id;
	}
    
    @RequestMapping(value = "/updatevideo/{videoId}", method = RequestMethod.GET)
	public String updateTopic(@PathVariable Long videoId, Model model) {

		Videos video = videoserv.findByVideoId(videoId);
		VideoVO videovo = new VideoVO();
		videovo.setTopicId(video.getTopics().getTopicId());
		videovo.setTopicName(video.getTopics().getTopicName());
		videovo.setVideoName(video.getVideoName());
		videovo.setVideoId(videoId);
		videovo.setVideoPath(video.getVideoPath());
		model.addAttribute("videovo", videovo);
		return "updatevideo";
	}
	
    @RequestMapping(value = "/updatevideo", method = RequestMethod.POST)
	public String updatedTopic(@ModelAttribute VideoVO videovo, Model model) {
		Videos video = videoserv.updatevideo(videovo);
		long id = video.getTopics().getTopicId();
		return "redirect:/video/addvideo/"+id;
	}

}
