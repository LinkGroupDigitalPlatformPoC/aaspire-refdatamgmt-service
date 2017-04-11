package com.ibm.aaspire.poc.services;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.collect.Lists.*;
import com.ibm.aaspire.poc.entities.DiscussionTopic;

import com.ibm.aaspire.poc.repository.DiscussionTopicRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussionTopicServiceTest {

	@Autowired
	private DiscussionTopicService service;

	@Autowired
	private DiscussionTopicRepository repo;
	
	private DiscussionTopic topic1;	
	private DiscussionTopic topic2;	
	private DiscussionTopic topic3;

	@Before
	public void populateDb() {
		repo.deleteAll();
		topic1 = repo.save(new DiscussionTopic("Middle", "doesn't matter"));
		topic2 = repo.save(new DiscussionTopic("AStart", "doesn't matter"));
		topic3 = repo.save(new DiscussionTopic("ZEnd", "doesn't matter"));	
	}
	
	@Test
	public void testGetAll() {
		List<DiscussionTopic> loadedTopics = newArrayList(service.getAllDiscussionTopics());
		
		assertEquals(3, loadedTopics.size());
		assertEquals(topic2.getId(), loadedTopics.get(0).getId());
		assertEquals(topic1.getId(), loadedTopics.get(1).getId());
		assertEquals(topic3.getId(), loadedTopics.get(2).getId());
	}
}
