package com.travelmanagementservice.tmss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelmanagementservice.tmss.dto.TravelReimbursementDto;
import com.travelmanagementservice.tmss.entity.ApiResponse;
import com.travelmanagementservice.tmss.service.TravelReimbursementService;

@RestController
@RequestMapping("/reimbursement")
public class TravelReimbursementController {
	@Autowired private TravelReimbursementService service;
//	@Autowired private ApiResponse ApiResponse;
	
	@PostMapping("/submit")
	public ResponseEntity<ApiResponse<TravelReimbursementDto>> submitReimbusrement(@RequestBody 
			     TravelReimbursementDto dto,Authentication authentication){
		TravelReimbursementDto travel=service.submitReimbursement(dto,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"TravelReimbursement submitted successfully",travel));
		
	}
	
	@GetMapping("/myRequest")
	public ResponseEntity<ApiResponse<List<TravelReimbursementDto>>> getUserReimbursement(Authentication authentication){
		List<TravelReimbursementDto> list=service.getUserReimbursement(authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"Requests retreived",list));
	}
	
	
	@GetMapping("/pending")
	public ResponseEntity<ApiResponse<List<TravelReimbursementDto>>> getPendingReimbursement(){
	    List<TravelReimbursementDto> list=service.getPendingReimbursement();
		return ResponseEntity.ok(new ApiResponse<>(true,"Pending TravelReimbursement reteived",list));
	}
	
	@GetMapping("/approve/{id}")
	public ResponseEntity<ApiResponse<Void>> getApprovalReimbursement(@PathVariable Long id,Authentication authentication){
	    service.approvalReimbursement(id,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"Approved TravelReimbursement request",null));
	}
	
	@GetMapping("/decline/{id}")
	public ResponseEntity<ApiResponse<Void>> getDeclineReimbursement(@PathVariable Long id,Authentication authentication){
	    service.declineReimbursement(id,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"Declined TravelReimbursement request",null));
	}

}
