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

import com.travelmanagementservice.tmss.dto.TravelRequisitionDto;
import com.travelmanagementservice.tmss.entity.ApiResponse;
import com.travelmanagementservice.tmss.service.TravelRequisitionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/travel")
public class TravelRequisitionController {
	@Autowired TravelRequisitionService service;
	
	@PostMapping("/submit")
	public ResponseEntity<ApiResponse<TravelRequisitionDto>> submitRequisition(@Valid @RequestBody TravelRequisitionDto dto,Authentication authentication){
		TravelRequisitionDto saved=service.submitRequisition(dto,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"TravelRequisition submitted successfully",saved));
	}
	
	@GetMapping("/myrequests")
	public ResponseEntity<ApiResponse<List<TravelRequisitionDto>>> getMyRequests(Authentication authentication){
		List<TravelRequisitionDto> list=service.getUserRequisitions(authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"requisitions retrieved",list));
	}
	
	@GetMapping("/pending")
	public ResponseEntity<ApiResponse<List<TravelRequisitionDto>>> getPendingRequests(){
		List<TravelRequisitionDto> list=service.getpendingRequisitionsForApproval();
		return ResponseEntity.ok(new ApiResponse<>(true,"pending requisitions retrieved ",list));
	}
	
	
	@GetMapping("/approve/{id}")
	public ResponseEntity<ApiResponse<Void>> approveRequisition(@PathVariable Long id,Authentication authentication){
		service.approveRequisition(id,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"Requisition approved successfully",null));
	}
	
	
	@GetMapping("/decline/{id}")
	public ResponseEntity<ApiResponse<Void>> declineRequisition(@PathVariable Long id,Authentication authentication){
		service.declineRequisition(id,authentication.getName());
		return ResponseEntity.ok(new ApiResponse<>(true,"Requisition declined successfully",null));
	}
	
	
	
	
	
	
	

}
