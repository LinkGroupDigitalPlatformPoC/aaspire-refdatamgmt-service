package com.ibm.aaspire.poc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.aaspire.poc.entities.DiscussionTopic;

public interface DiscussionTopicRepository extends PagingAndSortingRepository<DiscussionTopic, String> {
	
}
