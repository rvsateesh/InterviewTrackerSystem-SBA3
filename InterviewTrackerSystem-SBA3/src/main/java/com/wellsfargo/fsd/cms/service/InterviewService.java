package com.wellsfargo.fsd.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.cms.dao.AttendeesRepository;
import com.wellsfargo.fsd.cms.dao.InterviewRepository;
import com.wellsfargo.fsd.cms.entity.Interview;
import com.wellsfargo.fsd.cms.exception.InterviewException;

@Service
public class InterviewService {
	
	@Autowired
	private InterviewRepository interviewRepo;
	
	@Autowired
	private AttendeesRepository attendeesRepo;

	
	@Transactional
	public Interview addInterview(Interview interview) throws InterviewException {
		
		if(interview != null) {
			if(interviewRepo.existsById(interview.getInterviewId())) {
				throw new InterviewException("Interview ID already in use");
			}
			if(interviewRepo.existsByInterviewName(interview.getInterviewName())) {
				throw new InterviewException("Interview Name cannot be duplicate");
			}
			
			interviewRepo.save(interview);
		}
		return interview;
	}
	
	@Transactional
	public boolean deleteInterview(int interviewId) throws InterviewException {
		
		if(!(interviewRepo.existsById(interviewId))) {
			throw new InterviewException("No such Interview ID defined");
		}
		
		interviewRepo.deleteById(interviewId);
		
		List<Integer> currentIntList = attendeesRepo.findByInterviewId(interviewId);
		if(!(currentIntList.isEmpty())) {
			for (Integer i : currentIntList) {
				attendeesRepo.deleteById(i);
			} 

		}
		return true;
	}
	
	@Transactional
	public Interview updateInterviewStatus(int interviewId, String status) throws InterviewException {
		Interview interview = new Interview();
		interview = interviewRepo.getById(interviewId);
		if(interviewRepo.existsById(interviewId)) {
			interview.setInterviewStatus(status);
		}else {
			throw new InterviewException("No such interview ID defined");
		}
		return interview;
	}
	
	@Transactional
	public Interview getByInterviewName(String interviewName) throws InterviewException {
				
		return interviewRepo.findbyInterviewName(interviewName);
	}
	
	@Transactional
	public List<Interview> getByInterviewerName(String interviewerName) throws InterviewException {
				
		return interviewRepo.findbyInterviewerName(interviewerName);
	}

	@Transactional
	public int countOfInterviews() throws InterviewException {
		
		return interviewRepo.getCountOfInterviews();
	}
	
	@Transactional
	public List<Interview> getAllInterviews() throws InterviewException {
		
		return interviewRepo.findAll();
	}
	
	@Transactional
	public Interview getbyId(int interviewId) throws InterviewException {
		return interviewRepo.getById(interviewId);
	}
}
