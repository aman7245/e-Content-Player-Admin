package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long>{
	
	public Videos findByVideoName(String videoName);
	public List<Videos> findByTopicsTopicId(long id);
}
