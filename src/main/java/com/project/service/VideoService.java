package com.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.Videos;

public interface VideoService {

	public Videos addVideo(VideoVO videovo, MultipartFile file, RedirectAttributes redirectAttributes);
	public List<Videos> findAllVideos();
	public Videos findByName(String videoName);
	public List<Videos> findVideoByTopicId(long topicId);
	public Videos findByVideoId(Long videoId);
	public void deletevideo(Long videoId) throws IOException;
	public Videos updatevideo(VideoVO videovo);
}
