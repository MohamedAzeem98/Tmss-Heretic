package com.travelmanagementservice.tmss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagementservice.tmss.entity.TravelReimbursement;

public interface TravelReimbursementRepository extends JpaRepository<TravelReimbursement,Long> {

	List<TravelReimbursement> findByRequestedById(Long id);
	
	List<TravelReimbursement> findByStatus(String status);

}
