package com.nt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.dto.PlayerDto;
import com.nt.entity.Player;
import com.nt.entity.Team;
import com.nt.ifeign.IfeignServcie;
import com.nt.iservice.IPlayerService;
import com.nt.repository.PlayerRepository;
import com.nt.repository.TeamRepository;
import com.nt.utility.ResponseMessage;

@Service
public class PlayerService implements IPlayerService {

	private PlayerRepository playerRepository;

	private TeamRepository teamRepository;

	@Autowired
	private IfeignServcie feignService;

	@Autowired
	private ObjectMapper objectMapper;

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
		BeanUtils.copyProperties(playerDto, player);
		Player save = playerRepository.save(player);

		return "Player Registered Successfully! " + save.toString();
	}

	@Override
	public PlayerDto getPlayer(Integer id) {

		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Player Id"));

		ResponseEntity<ResponseMessage> teambyId = feignService.getTeamByIdController(player.getTeam().getTeamId());

		@Nullable
		ResponseMessage body = teambyId.getBody();

		Team team = objectMapper.convertValue(body.getObject(), Team.class);

		PlayerDto playerDto = new PlayerDto();

		playerDto.setPlayerId(player.getPlayerId());
		playerDto.setPlayerName(player.getPlayerName());
		playerDto.setAge(player.getAge());
		playerDto.setRoll(player.getRoll());
		playerDto.setTeam(team);

		return playerDto;
	}

	@Override
	public String deletePlayer(Integer id) {

		Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));

		playerRepository.deleteById(player.getPlayerId());

		return "Player is Deleted Successfully! ";
	}

	@Override
	public String updatePlayer(PlayerDto playerDto) {

		Player player = playerRepository.findById(playerDto.getPlayerId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		BeanUtils.copyProperties(playerDto, player);
		Player save = playerRepository.save(player);
		return "Player Updated!  Player Name: " + save.getPlayerName();
	}

	@Override
	public String updateName(String name, Integer playerId) {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
		String oldName = player.getPlayerName();
		player.setPlayerName(name);
		return "Player Old Name: " + oldName + " Player New Name: " + playerRepository.save(player).getPlayerName();
	}

	@Override
	public String updateRoll(String roll, Integer playerId) {

		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id"));

		String oldRoll = player.getRoll();

		player.setRoll(roll);

		return "Player old Roll: " + oldRoll + " Player New Roll: " + playerRepository.save(player);
	}

	@Override
	public List<String> getAllPlayerName() {
		List<String> nameList = new ArrayList<>();
		List<Player> all = playerRepository.findAll();
		Iterator<Player> ilist = all.listIterator();
		while (ilist.hasNext()) {
			PlayerDto dto = new PlayerDto();
			Player next = ilist.next();
			BeanUtils.copyProperties(next, dto);
			nameList.add(dto.getPlayerName());
		}
		return nameList;
	}

	@Override
	public Map<Integer, String> getAllIdAndPlayer() {
		Map<Integer, String> map = new HashMap<>();

		List<Player> all = playerRepository.findAll();

		Iterator<Player> iterator = all.iterator();

		while (iterator.hasNext()) {
			PlayerDto dto = new PlayerDto();
			Player next = iterator.next();
			BeanUtils.copyProperties(next, dto);
			map.put(dto.getPlayerId(), dto.getPlayerName());
		}

		return map;
	}

	@Override
	public List<PlayerDto> getAllPlayer() {
		List<PlayerDto> playerDto = new ArrayList<>();
		List<Player> all = playerRepository.findAll();
		for (Player player : all) {
			PlayerDto playerO = new PlayerDto();
			BeanUtils.copyProperties(player, playerO);
			playerDto.add(playerO);
		}
		return playerDto;
	}

}
