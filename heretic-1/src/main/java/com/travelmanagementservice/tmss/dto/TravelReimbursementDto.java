package com.travelmanagementservice.tmss.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TravelReimbursementDto {
	private long id;
	private Double amount;
	private String description;
	private LocalDate dateOfExpense;
	private String status;
	private Long requestedByUserId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateOfExpense() {
		return dateOfExpense;
	}
	public void setDateOfExpense(LocalDate dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
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
