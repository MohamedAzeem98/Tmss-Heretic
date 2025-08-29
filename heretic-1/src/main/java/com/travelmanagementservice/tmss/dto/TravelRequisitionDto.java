package com.travelmanagementservice.tmss.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TravelRequisitionDto {
	private Long id;
	
	@NotBlank(message="purpose is required")
	private String purpose;
	
	@NotNull(message="Start date is required")
	@Future(message="Start date must be in future")
	private LocalDate startDate;
	
	@NotNull(message="End date is required")
	@Future(message="End date must be in the future")
	private LocalDate endDate;
	
	
	@NotNull(message = "Estimated cost is required")
    private Double estimatedCost;

    private String status;

    private Long requestedByUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRequestedByUserId() {
		return requestedByUserId;
	}

	public void setRequestedByUserId(Long requestedByUserId) {
		this.requestedByUserId = requestedByUserId;
	}
    
    
	

}
