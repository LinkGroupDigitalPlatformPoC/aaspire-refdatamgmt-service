package com.ibm.aaspire.poc.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.ibm.aaspire.poc.services.DiscussionTopicService;

@RestController
public class RefDataController {

	private static final Set<String> SUPPORTED_REF_TYPE = new HashSet<>(Arrays.asList("discussion-topics", "sample"));

	@Autowired
	DiscussionTopicService discussionTopicService;

	@GetMapping("/referencedata")
	public Set<String> getRefDataTypes() {
		return SUPPORTED_REF_TYPE;
	}

	@GetMapping("/referencedata/{refDataType}")
	public Iterable<?> getMembers(@PathVariable(value = "refDataType") String refDataType) {
		String errFormat = "Usupported reference data type: %s. Supported types are: %s";
		Preconditions.checkArgument(SUPPORTED_REF_TYPE.contains(refDataType),
				String.format(errFormat, refDataType, SUPPORTED_REF_TYPE.toString()));

		switch (refDataType) {
		case "discussion-topics":
			return discussionTopicService.getAllDiscussionTopics();

		default:
			return Arrays.asList("Come back later.");
		}
	}
}
