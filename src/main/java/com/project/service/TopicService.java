package com.project.service;

import java.util.List;

import com.project.entity.Topic;

public interface TopicService {

	public Topic addTopic(TopicVO topicvo);
	public List<Topic> findAllTopic();
	public Topic findByName(String topicName);
	public List<Topic> findTopicByPackageId(long id);
	public Topic findByTopicId(Long topicId);
	public Topic deletetopic(Long id);
	public Topic updateTopic(TopicVO topicvo);
}
