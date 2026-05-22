package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nt.dto.PlayerDto;
import com.nt.dto.TeamDto;
import com.nt.entity.Player;
import com.nt.ifeign.IfeignServcie;
import com.nt.iservice.IPlayerService;
import com.nt.repository.PlayerRepository;
import com.nt.repository.TeamRepository;

public class PlayerService implements IPlayerService{
	
	private PlayerRepository playerRepository;
	
	private TeamRepository teamRepository;
	
	@Autowired
	private IfeignServcie feignService;
	
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Autowired
	public void setTeamRepository(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public String registerPlayer(PlayerDto playerDto) {
		
		Player player = new Player();
		
		player.setPlayerName(playerDto.getPlayerName());
		player.setAge(playerDto.getAge());
		player.setRoll(playerDto.getRoll());
		player.setTeam(playerDto.getTeam());
		
		Player save = playerRepository.save(player);
		
		return "Player Registered Successfully! "+save.toString();
	}

	@Override
	public PlayerDto getPlayer(Integer id) {
		Optional<Player> byId = playerRepository.findById(id);
		
		PlayerDto playerDto = null;
		
		if(byId.isPresent()) {
			playerDto = new PlayerDto();
			Player player = byId.get();	
			BeanUtils.copyProperties(player, playerDto);
		}
		return playerDto;
	}

	@Override
	public String deletePlayer(Integer id) {
		
		Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
		
		playerRepository.deleteById(player.getPlayerId());
	
		return "Player is Deleted Successfully! ";
	}

	@Override
	public String updatePlayer(PlayerDto player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRoll(String roll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTeam(TeamDto team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllPlayerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getAllIdAndPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDto> getAllPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
