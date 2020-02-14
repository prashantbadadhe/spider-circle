package com.circle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.circle.service.UrlService;

@RestController
public class UrlController {

	@Autowired
	UrlService urlService;

	@RequestMapping(value = "/url/", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Object>> listAllUrl() {
		Map<Object, Object> outputMap = new HashMap<Object, Object>();
		List<Object> groupUrlList = urlService.findUrlAndGroupUrlWithRoleAccess(5);
		outputMap.put("groupUrlList", groupUrlList);
		if (groupUrlList.isEmpty()) {
			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Object>>(groupUrlList, HttpStatus.OK);
	}

}