package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.TeamDto;
import com.nt.entity.Team;
import com.nt.iservice.IService;
import com.nt.repository.TeamRepository;

@Service
public class ServiceImpl implements IService {

	@Autowired
	private TeamRepository teamRepo;


	@Override
	public String saveTeam(TeamDto dto) {
		Team team = new Team();
		BeanUtils.copyProperties(dto, team);
		Team save = teamRepo.save(team);
		return "Team Registered Successfully! [" + save.getTeamName()+"]";
	}

	@Override
	public TeamDto getTeamById(Integer id) {
		TeamDto teamDto = new TeamDto();
		Team team = teamRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		BeanUtils.copyProperties(team, teamDto);
		return teamDto;
	}

	@Override
	public String updateTeam(TeamDto dto) {
		Team team = teamRepo.findById(dto.getTeamId()).orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		BeanUtils.copyProperties(dto, team);
		Team save = teamRepo.save(team);
		return "Team Update Successfully! [" + save.getTeamName()+"]";
	}

	@Override
	public String updateTeamName(Integer id, String name) {
		Team team = teamRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		team.setTeamName(name);
		return "Team Name Updated! [" + teamRepo.save(team).getTeamName()+"]";
	}

	@Override
	public String deleteTeam(Integer id) {
		Team team = teamRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		teamRepo.deleteById(team.getTeamId());
		return "Team Deleleted Successfully! ["+team.getTeamName()+"]";
	}

}
