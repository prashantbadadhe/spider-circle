package com.circle.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.circle.model.Taluka;
import com.circle.service.TalukaService;
import com.circle.util.CommonConstant;

@RestController
public class TalukaController implements CommonConstant {

	@Autowired
	TalukaService talukaService;

	@RequestMapping(value = "/taluka/", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Taluka>> listAllTaluka() {
		List<Taluka> taluka = talukaService.findAllTalukas();
		if (taluka.isEmpty()) {
			return new ResponseEntity<List<Taluka>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Taluka>>(taluka, HttpStatus.OK);
	}

	@RequestMapping(value = "/taluka/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Taluka> getTaluka(@PathVariable("id") long id) {
		System.out.println("Fetching Taluka with id " + id);
		Taluka taluka = talukaService.findById(id);
		if (taluka == null) {
			System.out.println("Taluka with id " + id + " not found");
			return new ResponseEntity<Taluka>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Taluka>(taluka, HttpStatus.OK);
	}

	@RequestMapping(value = "/taluka/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTaluka(@RequestBody Taluka taluka, UriComponentsBuilder ucBuilder) {
		if (taluka.getName() == null || talukaService.isTalukaExist(taluka)) {
			System.out.println("A Taluka with name " + taluka.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		taluka.setStatus('1');
		talukaService.saveTaluka(taluka);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/taluka/{id}").buildAndExpand(taluka.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/taluka/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Taluka> updateTaluka(@PathVariable("id") long id, @RequestBody Taluka taluka) {
		System.out.println("Updating Taluka " + id);

		Taluka currentTaluka = talukaService.findById(id);

		if (currentTaluka == null) {
			System.out.println("Taluka with id " + id + " not found");
			return new ResponseEntity<Taluka>(HttpStatus.NOT_FOUND);
		}

		currentTaluka.setName(taluka.getName());
		currentTaluka.setCode(taluka.getCode());
		currentTaluka.setDistrict(taluka.getDistrict());
		currentTaluka.setDescr(taluka.getDescr());
		currentTaluka.setLatitude(taluka.getLatitude());
		currentTaluka.setLongitude(taluka.getLongitude());
		currentTaluka.setCity(taluka.isCity());
		currentTaluka.setCreatedAt(new Date());
		currentTaluka.setUpdatedAt(new Date());
		currentTaluka.setCreatedBy("Abhishek");
		currentTaluka.setUpdatedBy("Abhishek");

		talukaService.updateTaluka(currentTaluka);
		return new ResponseEntity<Taluka>(currentTaluka, HttpStatus.OK);
	}

	@RequestMapping(value = "/taluka/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Taluka> deleteTaluka(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Taluka with id " + id);

		Taluka taluka = talukaService.findById(id);
		if (taluka == null) {
			System.out.println("Unable to delete. Taluka with id " + id + " not found");
			return new ResponseEntity<Taluka>(HttpStatus.NOT_FOUND);
		}
		  int deleted =   talukaService.deleteTalukaById(id);
	        if(deleted>0){
	            return new ResponseEntity<Taluka>(HttpStatus.OK);
	        }
		return new ResponseEntity<Taluka>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/taluka/", method = RequestMethod.DELETE)
	public ResponseEntity<Taluka> deleteAllTalukas() {
		System.out.println("Deleting All Talukas");

		talukaService.deleteAllTalukas();
		return new ResponseEntity<Taluka>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/taluka/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Taluka>> listAllTaluka(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,
			@PathVariable("searchKeyword") String searchKeyword) {
		HttpHeaders headers = new HttpHeaders();

		List<Taluka> taluka = talukaService.listAllTaluka(noOfRecord, pageNumber, searchKeyword);
		taluka.removeIf(Objects::isNull);
		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + talukaService.countRecord(searchKeyword));

		if (taluka.isEmpty()) {
			return new ResponseEntity<List<Taluka>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Taluka>>(taluka, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "taluka/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Taluka>> filteredTaluka(@PathVariable String searchKey, @PathVariable String itemsPerPage) {
		HttpHeaders headers = new HttpHeaders();
		List<Taluka> taluka = talukaService.filteredTaluka(searchKey, itemsPerPage);
		List<Taluka> filterStrings = taluka.stream().filter(p -> p != null).collect(Collectors.toList());

		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + filterStrings.size());
		
		
			if (taluka.isEmpty()) {
			return new ResponseEntity<List<Taluka>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Taluka>>(taluka, headers, HttpStatus.OK);
	}

}