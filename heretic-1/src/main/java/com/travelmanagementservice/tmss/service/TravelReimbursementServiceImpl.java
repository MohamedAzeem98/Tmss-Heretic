package com.travelmanagementservice.tmss.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmanagementservice.tmss.dto.TravelReimbursementDto;
import com.travelmanagementservice.tmss.entity.TravelReimbursement;
import com.travelmanagementservice.tmss.entity.User;
import com.travelmanagementservice.tmss.exceptions.UserNotFoundException;
import com.travelmanagementservice.tmss.mapper.ReimbursementMapper;
import com.travelmanagementservice.tmss.repository.TravelReimbursementRepository;
import com.travelmanagementservice.tmss.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TravelReimbursementServiceImpl implements TravelReimbursementService {

	@Autowired private TravelReimbursementRepository repo;
	@Autowired private UserRepository uRepo;
	@Autowired private ReimbursementMapper mapper;
	@Autowired private EmailService emailService;
	
	
	@Override
	public TravelReimbursementDto submitReimbursement(TravelReimbursementDto dto,String username) {
		User user=uRepo.findByUsername(username)
				.orElseThrow(()-> new UserNotFoundException("User not found"));
		TravelReimbursement entity=mapper.toEntity(dto);
		entity.setRequestedBy(user);
		entity.setStatus("PENDING");
		TravelReimbursement saved=repo.save(entity);
		return mapper.toDto(saved);
		
	}

	@Override
	public List<TravelReimbursementDto> getUserReimbursement(String username) {
		User user=uRepo.findByUsername(username)
				.orElseThrow(()-> new UserNotFoundException("user not found"));
		List<TravelReimbursement> all=repo.findByRequestedById(user.getId());
		return all.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TravelReimbursementDto> getPendingReimbursement() {
		List<TravelReimbursement> list=repo.findByStatus("PENDING");
		return list.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void approvalReimbursement(Long TravelReimbursementId, String approvalUsername) {
		TravelReimbursement travel=repo.findById(TravelReimbursementId)
				.orElseThrow(()->new RuntimeException("Requisitions not found"));
		travel.setStatus("APPROVED");
		repo.save(travel);
	
		String toEmail=travel.getRequestedBy().getUsername();
		String subject="Travel Reimbursement Approved";
		String body="Your travel requisition #"+TravelReimbursementId+" has been approved";
		emailService.sendEmailMessage(toEmail, subject, body);
	}

	@Override
	public void declineReimbursement(Long TravelReimbursementId, String approvalUsername) {
		TravelReimbursement travel=repo.findById(TravelReimbursementId)
				.orElseThrow(()->new RuntimeException("Requsitions not found"));
		travel.setStatus("DECLINED");
		repo.save(travel);
		
		String toEmail=travel.getRequestedBy().getUsername();
		String subject="Travel Reimbursement Declined";
		String body="Your travel reimbursement #"+TravelReimbursementId+" has been declined ";
		emailService.sendEmailMessage(toEmail, subject, body);
	}

}
