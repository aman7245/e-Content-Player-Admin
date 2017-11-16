package com.project.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Topic")
public class Topic {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long topicId;
	
	@NotNull
	@Column(name="TopicName")
	private String topicName;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "PackageId")
	private Package packages;
	
	@OneToMany(mappedBy = "topics",cascade={CascadeType.MERGE,CascadeType.REMOVE})
	private Set<Videos> videos = new HashSet<>();
	
	public Topic(){}
	
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Package getPackages() {
		return packages;
	}
	public void setPackages(Package packages) {
		this.packages = packages;
	}
	public Set<Videos> getVideos() {
		return videos;
	}
	public void setVideos(Set<Videos> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", packages=" + packages + "]";
	}


	
	

}
