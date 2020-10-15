package com.wellsfargo.fsd.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.cms.entity.Interview;
import com.wellsfargo.fsd.cms.exception.InterviewException;
import com.wellsfargo.fsd.cms.service.InterviewService;

@RestController
@RequestMapping("/interviews")
public class InterviewRestController {

	@Autowired
	private InterviewService intService;
	
	@GetMapping
	public ResponseEntity<List<Interview>> displayAllInterviews() throws InterviewException {
		return new ResponseEntity<List<Interview>>(intService.getAllInterviews(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Interview> addInterview(@RequestBody Interview interview) throws InterviewException {
		
		return new ResponseEntity<Interview>(intService.addInterview(interview),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{interviewId}")
	public ResponseEntity<Void> deleteInterview(@PathVariable("interviewId") int interviewId) throws InterviewException {
		intService.deleteInterview(interviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/update/{interviewId}/{status}")
	public ResponseEntity<Interview> updateInterviewStatus(@PathVariable("interviewId") int interviewId, @PathVariable("status") String status) throws InterviewException {
		intService.updateInterviewStatus(interviewId, status);
		return new ResponseEntity<Interview>(intService.getbyId(interviewId), HttpStatus.OK);
	}
	
	@GetMapping("/interview/{interviewname}")
	public ResponseEntity<Interview> getByInterivewName(@PathVariable("interviewname") String interviewname) throws InterviewException {
		return new ResponseEntity<Interview>(intService.getByInterviewName(interviewname), HttpStatus.OK);
	}
	
	@GetMapping("/interviewer/{interviewerName}")
	public ResponseEntity<List<Interview>> getListByInterviewerName(@PathVariable("interviewerName") String interviewerName) throws InterviewException {
		return new ResponseEntity<List<Interview>>(intService.getByInterviewerName(interviewerName), HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getCountOfInterviews() throws InterviewException {
		return new ResponseEntity<Integer>(intService.countOfInterviews(), HttpStatus.OK);
	}
	
}
