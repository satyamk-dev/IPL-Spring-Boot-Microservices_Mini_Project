package com.nt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {

	private Integer teamId;
	
	private String teamName;
	
	private String teamOwner;
	
}
