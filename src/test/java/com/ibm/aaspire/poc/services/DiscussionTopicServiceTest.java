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

	@Before
	public void populateDb() {
		repo.deleteAll();
		repo.save(new DiscussionTopic("uuid1", "Middle", "doesn't matter"));
		repo.save(new DiscussionTopic("uuid2", "AStart", "doesn't matter"));
		repo.save(new DiscussionTopic("uuid3", "ZEnd", "doesn't matter"));	
	}
	
	@Test
	public void testGetAll() {
		List<DiscussionTopic> loadedTopics = newArrayList(service.getAllDiscussionTopics());
		
		assertEquals(3, loadedTopics.size());
		assertEquals("uuid2", loadedTopics.get(0).getId());
		assertEquals("uuid1", loadedTopics.get(1).getId());
		assertEquals("uuid3", loadedTopics.get(2).getId());
	}
}
