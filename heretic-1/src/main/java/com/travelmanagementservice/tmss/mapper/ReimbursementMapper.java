package com.travelmanagementservice.tmss.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travelmanagementservice.tmss.dto.TravelReimbursementDto;
import com.travelmanagementservice.tmss.entity.TravelReimbursement;

@Component
public class ReimbursementMapper {
	
//	private final ModelMapper modelMapper=new ModelMapper();
//	
//	public TravelReimbursementDto toDto(TravelReimbursement entity) {
//		TravelReimbursementDto dto =modelMapper.map(entity,TravelReimbursementDto.class);
//		if(entity.getRequestedBy()!=null) {
//			dto.setRequestedByUserId(entity.getRequestedBy().getId());
//		}
//		return dto;
//	}
//		
//	public TravelReimbursement toEntity(TravelReimbursementDto dto) {
//		return modelMapper.map(dto,TravelReimbursement.class);
//	}
//	
//	
//	
	
	public TravelReimbursementDto toDto(TravelReimbursement entity) {
		if(entity==null) {
			return null;
		}
		
		TravelReimbursementDto dto=new TravelReimbursementDto();
		dto.setId(entity.getId());
		dto.setAmount(entity.getAmount());
		dto.setDescription(entity.getDescription());
		dto.setDateOfExpense(entity.getDateOfExpense());
	    dto.setStatus(entity.getStatus());
	    
	    if(entity.getRequestedBy()!=null) {
	    	dto.setRequestedByUserId(entity.getRequestedBy().getId());
	    }
	    
	    return dto;
		}
	
	 
	
	public TravelReimbursement toEntity(TravelReimbursementDto dto) {
        if (dto == null) {
            return null;
        }
        
        TravelReimbursement entity = new TravelReimbursement();
        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
        entity.setDateOfExpense(dto.getDateOfExpense());
        // Note: Don't set id, status, or requestedBy here - they're set in service
        
        return entity;

}}
