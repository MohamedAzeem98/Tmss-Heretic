package com.travelmanagementservice.tmss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelmanagementservice.tmss.entity.TravelRequisition;

public interface TravelRequsitionRepository extends JpaRepository<TravelRequisition,Long> {
	
	List<TravelRequisition> findByRequestedById(Long id);
	
	List<TravelRequisition> findByStatus(String status);
}
