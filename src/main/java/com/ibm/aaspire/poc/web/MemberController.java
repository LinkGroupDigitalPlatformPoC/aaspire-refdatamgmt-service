package com.ibm.aaspire.poc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.aaspire.poc.entities.DiscussionTopic;
import com.ibm.aaspire.poc.services.DiscussionTopicService;

@RestController
public class MemberController {

	@Autowired
	DiscussionTopicService discussionTopicService;

	@GetMapping("/discussion-topics")
	public Iterable<DiscussionTopic> getMembers() {
		return discussionTopicService.getAllDiscussionTopics();
	}
}
