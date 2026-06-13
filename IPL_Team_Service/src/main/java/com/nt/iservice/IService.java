package com.nt.iservice;

import java.util.List;

import com.nt.dto.TeamDto;

public interface IService {

	public String saveTeam(TeamDto dto);

	public String saveBulkTeam(List<TeamDto> dto);

	public List<TeamDto> getAllTeamService();

	public TeamDto getTeamById(Integer id);

	public String updateTeam(TeamDto dto);

	public String updateTeamName(Integer id, String name);

	public String deleteTeam(Integer id);

	public String deleteAllTeam();

}
