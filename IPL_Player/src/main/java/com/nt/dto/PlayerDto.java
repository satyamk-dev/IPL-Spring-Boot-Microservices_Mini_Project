package com.nt.dto;

import com.nt.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
	
	private Integer playerId;
	
	private String playerName;
	
	private Integer age;
	
	private String roll;
	
	private Team team;

}
