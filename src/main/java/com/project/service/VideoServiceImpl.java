package com.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.Topic;
import com.project.entity.Videos;
import com.project.repository.TopicRepository;
import com.project.repository.VideosRepository;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired TopicRepository topicrepo;
	@Autowired VideosRepository videorepo;
	
	private static String UPLOADED_FOLDER = "A://temp//";
	
	@Override
	public Videos addVideo(VideoVO videovo, MultipartFile file, RedirectAttributes redirectAttributes) {
		Topic topic = topicrepo.findOne(videovo.getTopicId());
		Videos video = new Videos();
		video.setVideoName(videovo.getVideoName());
		Set<Videos> videos = topic.getVideos();
		for(Videos vid : videos){
			if(vid.getVideoName().equals(videovo.getVideoName())){
				System.out.println("Video Already Exist");
				throw new EntityExistsException();
			}
		}
		Path path = singleFileUpload(file, redirectAttributes);
		String pathstr = path.toString();
		video.setVideoPath(pathstr);
		video.setTopics(topic);
		if(videovo.getVideoName()=="")
			throw new NullPointerException();
		videorepo.save(video);
		return video;
	}

	public Path singleFileUpload(MultipartFile file,
            RedirectAttributes redirectAttributes) {
		
		try {
		
		// Get the file and save it somewhere
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		
		Files.write(path, bytes);
		
		redirectAttributes.addFlashAttribute("message",
		"You successfully uploaded '" + file.getOriginalFilename() + "'");
		
		} catch (IOException e) {
		e.printStackTrace();
		}
		Path pathvalue = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		return pathvalue;
	}
	
	@Override
	public List<Videos> findAllVideos() {
		List<Videos> videos = videorepo.findAll();
		return videos;
	}

	@Override
	public Videos findByName(String videoName) {
		Videos video = videorepo.findByVideoName(videoName);
		return video;
	}

	@Override
	public List<Videos> findVideoByTopicId(long topicId) {
		List<Videos> videos = videorepo.findByTopicsTopicId(topicId);
		return videos;
	}

	@Override
	public Videos findByVideoId(Long videoId) {
		Videos video = videorepo.findOne(videoId);
		return video;
	}

	@Override
	public void deletevideo(Long videoId) throws IOException {
		Videos video = videorepo.findOne(videoId);
		video.setTopics(null);
		Path path = Paths.get(video.getVideoPath());
		Files.delete(path);
		videorepo.delete(videoId);
	}

	@Override
	public Videos updatevideo(VideoVO videovo) {
		Videos video = videorepo.findOne(videovo.getVideoId());
		video.setVideoId(videovo.getVideoId());
		video.setVideoName(videovo.getVideoName());
		return video;
	}
	
	

}
