package com.travelmanagementservice.tmss.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagementservice.tmss.dto.TravelRequisitionDto;
import com.travelmanagementservice.tmss.entity.TravelRequisition;
import com.travelmanagementservice.tmss.entity.User;
import com.travelmanagementservice.tmss.exceptions.UserNotFoundException;
import com.travelmanagementservice.tmss.mapper.TravelRequisitionMapper;
import com.travelmanagementservice.tmss.repository.TravelRequsitionRepository;
import com.travelmanagementservice.tmss.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TravelRequisitionServiceImpl implements TravelRequisitionService{

	@Autowired private TravelRequsitionRepository repo;
	@Autowired private UserRepository userRepo;
	@Autowired private TravelRequisitionMapper mapper;
	
	@Autowired private EmailService emailService;
	
	@Override
	public TravelRequisitionDto submitRequisition(TravelRequisitionDto dto, String username) {
		User user=userRepo.findByUsername(username)
			  .orElseThrow(()->new UserNotFoundException("user not found"));
		
		TravelRequisition entity=mapper.toEntity(dto);
		entity.setRequestedBy(user);
		entity.setStatus("PENDING");
		TravelRequisition saved=repo.save(entity);
		return mapper.toDto(saved);
	
	}

	@Override
	public List<TravelRequisitionDto> getUserRequisitions(String username) {
		User user=userRepo.findByUsername(username)
				.orElseThrow(()-> new UserNotFoundException("user not found"));
		List<TravelRequisition> list=repo.findByRequestedById(user.getId());
		return list.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TravelRequisitionDto> getpendingRequisitionsForApproval() {
		List<TravelRequisition> list=repo.findByStatus("PENDING");
		return list.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void approveRequisition(Long requisitionId, String approverUsername) {
		TravelRequisition requisition = repo.findById(requisitionId)
				.orElseThrow(()-> new RuntimeException("Requisition not found"));
		requisition.setStatus("APPROVED");
		repo.save(requisition);
		//----Email config codes -----//
		String toEmail=requisition.getRequestedBy().getUsername();
		String subject="Travel Requisition Approved";
		String body="Your travel requisition #"+requisitionId+" has been approved";
	}

	@Override
	public void declineRequisition(Long requisitionId, String approverUsername) {
		TravelRequisition requisition = repo.findById(requisitionId)
				.orElseThrow(()-> new RuntimeException("Requisition not found"));
		requisition.setStatus("DECLINED");
		repo.save(requisition);
		String toEmail=requisition.getRequestedBy().getUsername();
		String subject="Sorry , Your Travel Requisition Declined";
		String body="Your travel requisition #"+requisitionId+"has been declined";
	}
	
}
