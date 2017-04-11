package com.ibm.aaspire.poc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ibm.aaspire.poc.entities.DiscussionTopic;
import com.ibm.aaspire.poc.repository.DiscussionTopicRepository;

@Component
public class DiscussionTopicService {

	@Autowired
	private DiscussionTopicRepository repo;
	
	public Iterable<DiscussionTopic> getAllDiscussionTopics() {
		return repo.findAll(new Sort(Sort.Direction.ASC, "description"));
	}
	
	public DiscussionTopic save(DiscussionTopic topic) {
		return repo.save(topic);
	}
	
	public DiscussionTopic findOne(String id) {
		return repo.findOne(id);
	}
}
