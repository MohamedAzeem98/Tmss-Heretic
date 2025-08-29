package com.travelmanagementservice.tmss.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.travelmanagementservice.tmss.dto.TravelRequisitionDto;
import com.travelmanagementservice.tmss.entity.TravelRequisition;

@Component
public class TravelRequisitionMapper {
	
	private final ModelMapper modelMapper=new ModelMapper();
	
	public TravelRequisitionDto toDto(TravelRequisition entity) {
		TravelRequisitionDto dto=modelMapper.map(entity, TravelRequisitionDto.class);
		
		if(entity.getRequestedBy()!=null) {
			dto.setRequestedByUserId(entity.getRequestedBy().getId());
		}
		return dto;
	}
	
	public TravelRequisition toEntity(TravelRequisitionDto dto) {
		return modelMapper.map(dto,TravelRequisition.class);
	}
}
