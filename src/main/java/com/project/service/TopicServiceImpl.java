package com.project.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Package;
import com.project.entity.Topic;
import com.project.repository.PackageRepository;
import com.project.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService{

	@Autowired PackageRepository packagerepo;
	@Autowired TopicRepository topicrepo;
	
	@Override
	public Topic addTopic(TopicVO topicvo) {
		Package packages = packagerepo.findByPackageName(topicvo.getPackageName());
		Topic topic = new Topic();
		topic.setTopicName(topicvo.getTopicName());
		Set<Topic> topics = packages.getTopics();
		for(Topic topc : topics){
			if(topc.getTopicName().equals(topicvo.getTopicName())){
				System.out.println("Topic alresdy Exist");
				throw new EntityExistsException();
			}
				
		}
		if(topicvo.getTopicName()=="")
			throw new NullPointerException();
		topic.setPackages(packages);
		topicrepo.save(topic);
		return topic;
	}

	@Override
	public List<Topic> findAllTopic() {
		List<Topic> topics = topicrepo.findAll();
		return topics;
	}

	@Override
	public Topic findByName(String topicName) {
		Topic topic = topicrepo.findByTopicName(topicName);
		return topic;
	}

	@Override
	public List<Topic> findTopicByPackageId(long id) {
		List<Topic> topics = topicrepo.findByPackagesId(id);
		return topics;
	}

	@Override
	public Topic findByTopicId(Long topicId) {
		Topic topic = topicrepo.findOne(topicId);
		return topic;
	}

	@Override
	public Topic deletetopic(Long id) {
		Topic topic = topicrepo.findOne(id);
		topic.setPackages(null);
		topicrepo.delete(id);
		return topic;
	}

	@Override
	public Topic updateTopic(TopicVO topicvo) {
		
		Topic topic = topicrepo.findOne(topicvo.getTopicId());
		topic.setTopicName(topicvo.getTopicName());
		
		topicrepo.save(topic);
		return topic;
	}

	

}
