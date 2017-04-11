package com.ibm.aaspire.poc.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static java.lang.String.format;
import static com.google.common.base.Preconditions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.aaspire.poc.entities.DiscussionTopic;
import com.ibm.aaspire.poc.services.DiscussionTopicService;

@RestController
public class RefDataController {

	private static final Set<String> SUPPORTED_REFS = new HashSet<>(Arrays.asList("discussion-topics", "sample"));

	@Autowired
	private DiscussionTopicService discussionTopicService;

	@GetMapping("/referencedata")
	public Set<String> getRefDataTypes() {
		return SUPPORTED_REFS;
	}

	@GetMapping("/referencedata/{refDataType}")
	public Iterable<?> getRefData(@PathVariable(value = "refDataType") String refDataType) {
		String errFormat = "Usupported reference data type: %s. Supported types are: %s";
		checkArgument(SUPPORTED_REFS.contains(refDataType), format(errFormat, refDataType, SUPPORTED_REFS.toString()));

		switch (refDataType) {
			case "discussion-topics":
				return discussionTopicService.getAllDiscussionTopics();
			default:
				return Arrays.asList("Come back later.");
		}
	}
	
	@RequestMapping(value = "/referencedata/{refDataType}", method = RequestMethod.POST)
	public @ResponseBody Object addRefData(@PathVariable(value = "refDataType") String refDataType, @RequestBody Map<String, Object> payload) {
		String errFormat = "Usupported reference data type: %s. Supported types are: %s";
		checkArgument(SUPPORTED_REFS.contains(refDataType), format(errFormat, refDataType, SUPPORTED_REFS.toString()));

		final ObjectMapper mapper = new ObjectMapper();
		
		switch (refDataType) {
			case "discussion-topics":
				// Parse to discussion topic ...
				DiscussionTopic topic = mapper.convertValue(payload, DiscussionTopic.class);
				return discussionTopicService.save(topic);
			default:
				return Arrays.asList("Come back later.");
		}
	}
	
	@RequestMapping(value = "/referencedata/{refDataType}/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateRefData(@PathVariable(value = "refDataType") String refDataType, @PathVariable(value = "id") String id, @RequestBody Map<String, Object> payload) {
		String errFormat = "Usupported reference data type: %s. Supported types are: %s";
		checkArgument(SUPPORTED_REFS.contains(refDataType), format(errFormat, refDataType, SUPPORTED_REFS.toString()));

		final ObjectMapper mapper = new ObjectMapper();
		
		switch (refDataType) {
			case "discussion-topics":
				// Parse to discussion topic ...
				DiscussionTopic topic = mapper.convertValue(payload, DiscussionTopic.class);
				topic.setId(id);
				checkNotNull(discussionTopicService.findOne(id), "Use POST if an entity with the Id exists.");

				return discussionTopicService.save(topic);				
			default:
				return Arrays.asList("Come back later.");
		}
	}
}
