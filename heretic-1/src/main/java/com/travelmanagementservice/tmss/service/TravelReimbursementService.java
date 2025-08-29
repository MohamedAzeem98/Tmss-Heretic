package com.travelmanagementservice.tmss.service;

import java.util.List;

import com.travelmanagementservice.tmss.dto.TravelReimbursementDto;

public interface TravelReimbursementService {
	
	TravelReimbursementDto submitReimbursement(TravelReimbursementDto dto,String username);
	
	List<TravelReimbursementDto> getUserReimbursement(String username);
	
	List<TravelReimbursementDto> getPendingReimbursement();
	
	void approvalReimbursement(Long TravelReimbursementId,String approvalUsername);
	
	void declineReimbursement(Long TravelReimbursementId,String approvalUsername);

}
