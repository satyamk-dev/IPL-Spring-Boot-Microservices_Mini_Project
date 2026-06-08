package com.nt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerReciveDto {
	
	private Integer playerId;
	
	private String playerName;
	
	private Integer age;
	
	private String roll;
	
	private TeamDto teamDto;
	
}
