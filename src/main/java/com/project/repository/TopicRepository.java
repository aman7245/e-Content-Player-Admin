package com.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Topic;

@Repository
@Transactional
public interface TopicRepository extends JpaRepository<Topic, Long>{
	
	public Topic findByTopicName(String topicName);
	public List<Topic> findByPackagesId(long id);
	public Topic findByTopicNameAndPackagesPackageName(String topicName,String packageName);
	
	@Transactional
	public void deleteByTopicId(Long id);

}
