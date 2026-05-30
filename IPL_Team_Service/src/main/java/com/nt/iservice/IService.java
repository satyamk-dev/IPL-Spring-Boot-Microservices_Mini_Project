package com.nt.iservice;

import com.nt.dto.TeamDto;

public interface IService {
	
	public String saveTeam(TeamDto dto);
	
	public TeamDto getTeamById(Integer id);
	
	public String updateTeam(TeamDto dto);
	
	public String updateTeamName(Integer id, String name);
	
	public String deleteTeam(Integer id);
	
}
