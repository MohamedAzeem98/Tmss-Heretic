package com.travelmanagementservice.tmss.service;

import java.util.List;

import com.travelmanagementservice.tmss.dto.TravelRequisitionDto;

public interface TravelRequisitionService {
	
	TravelRequisitionDto submitRequisition(TravelRequisitionDto dto,String username);
	
	List<TravelRequisitionDto> getUserRequisitions(String username);
	
	List<TravelRequisitionDto> getpendingRequisitionsForApproval();
	
	void approveRequisition(Long requisitionId,String approverUsername);
	
	void declineRequisition(Long requisitionId,String approverUsername);

}
