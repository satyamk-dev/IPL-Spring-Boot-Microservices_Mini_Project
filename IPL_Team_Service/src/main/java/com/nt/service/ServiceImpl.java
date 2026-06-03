package com.nt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.TeamDto;
import com.nt.entity.Team;
import com.nt.exception.TeamNotFoundException;
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
		return "Team Registered Successfully! [" + save.getTeamName() + "]";
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
		return "Team Update Successfully! [" + save.getTeamName() + "]";
	}

	@Override
	public String updateTeamName(Integer id, String name) {
		Team team= null;
		try {
			team = teamRepo.findById(id).orElseThrow(() -> new TeamNotFoundException("Invalid Input"));
			team.setTeamName(name);
			
		} catch (TeamNotFoundException e) {
			e.printStackTrace();
		}
		return "Team Name Updated! [" + teamRepo.save(team).getTeamName() + "]";
	}

	@Override
	public String deleteTeam(Integer id) {
		Team team = teamRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		teamRepo.deleteById(team.getTeamId());
		return "Team Deleleted Successfully! [" + team.getTeamName() + "]";
	}

	@Override
	public List<TeamDto> getAllTeamService() {
		List<TeamDto> teamDto = new ArrayList<>();
		List<Team> all = teamRepo.findAll();
		Iterator<Team> iterator = all.listIterator();
		while (iterator.hasNext()) {
			TeamDto dto = new TeamDto();
			Team next = iterator.next();
			BeanUtils.copyProperties(next, dto);
			teamDto.add(dto);
		}
		return teamDto;
	}

	@Override
	public String deleteAllTeam() {
		teamRepo.deleteAll();
		return "All Team Deleted! ";
	}

}
