package com.nt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.dto.PlayerDto;
import com.nt.dto.PlayerReciveDto;
import com.nt.dto.TeamDto;
import com.nt.entity.Player;
import com.nt.entity.Team;
import com.nt.exception.PlayerAlradyExistsException;
import com.nt.ifeign.IfeignServcie;
import com.nt.iservice.IPlayerService;
import com.nt.repository.PlayerRepository;
import com.nt.repository.TeamRepository;
import com.nt.utility.ResponseMessage;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerService implements IPlayerService {

	private final PlayerRepository playerRepository;

	private final TeamRepository teamRepository;

	private final IfeignServcie feignService;

	private final ObjectMapper objectMapper;

	@Override
	public String registerPlayer(PlayerDto playerDto) {

		if (playerRepository.existsByPlayerName(playerDto.getPlayerName())) {
			throw new PlayerAlradyExistsException("Player Alrady Exists ");
		}

		Player player = new Player();
		BeanUtils.copyProperties(playerDto, player);
		Player save = playerRepository.save(player);

		return "Player Registered Successfully! [" + save.getPlayerName() + "]";
	}

	@Override
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "BreakedServiceCommunication")
	public PlayerReciveDto getPlayer(int id) {

		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Player Id"));

		ResponseEntity<ResponseMessage> teambyId = feignService.getTeamByIdController(player.getTeam().getTeamId());

		@Nullable
		ResponseMessage body = teambyId.getBody();

		Team team = objectMapper.convertValue(body.getObject(), Team.class);

		TeamDto teamDto = new TeamDto();

		BeanUtils.copyProperties(team, teamDto);

		PlayerReciveDto playerDto = new PlayerReciveDto();

		playerDto.setPlayerId(player.getPlayerId());
		playerDto.setPlayerName(player.getPlayerName());
		playerDto.setAge(player.getAge());
		playerDto.setRoll(player.getRoll());
		playerDto.setTeamDto(teamDto);

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

		if (!player.getPlayerName().equalsIgnoreCase(playerDto.getPlayerName())
				&& playerRepository.existsByPlayerName(playerDto.getPlayerName())) {
			throw new PlayerAlradyExistsException("Player is not exists in DataBase");
		}

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

		return "Player old Roll: " + oldRoll + " Player New Roll: " + playerRepository.save(player).getPlayerName();
	}

	@Override
	public List<String> getAllPlayerName() {

		List<String> nameList = new ArrayList<>();

		List<Player> all = playerRepository.findAll();

		Iterator<Player> ilist = all.listIterator();

		while (ilist.hasNext()) {

			Player next = ilist.next();

			nameList.add(next.getPlayerName());
		}
		return nameList;
	}

	@Override
	public Map<Integer, String> getAllIdAndPlayer() {
		Map<Integer, String> map = new HashMap<>();

		List<Player> all = playerRepository.findAll();

		Iterator<Player> iterator = all.iterator();

		while (iterator.hasNext()) {

			Player next = iterator.next();

			map.put(next.getPlayerId(), next.getPlayerName());
		}
		return map;
	}

	@Override
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "getAllPlayerFallBack")
	public List<PlayerReciveDto> getAllPlayer() {
		List<PlayerReciveDto> playerDto = new ArrayList<>();
		List<Player> all = playerRepository.findAll();
		for (Player player : all) {
			System.out.println(player.getTeam().getTeamId());
			ResponseEntity<ResponseMessage> teamByFeign = feignService
					.getTeamByIdController(player.getTeam().getTeamId());

			Team team = objectMapper.convertValue(teamByFeign.getBody().getObject(), Team.class);
			TeamDto teamDto = new TeamDto();
			System.out.println(team);

			BeanUtils.copyProperties(team, teamDto);

			System.out.println(teamDto);

			PlayerReciveDto playerO = new PlayerReciveDto();
			BeanUtils.copyProperties(player, playerO);
			playerO.setTeamDto(teamDto);
			playerDto.add(playerO);
		}
		return playerDto;
	}

	public PlayerReciveDto BreakedServiceCommunication(int id, Exception e) {
		PlayerReciveDto dto = new PlayerReciveDto();
		dto.setPlayerId(id);
		dto.setPlayerName("Service Unavailable");
		dto.setRoll("Service Unavailable");
		dto.setAge(0);
		dto.setTeamDto(null);

		return dto;
	}

	public List<PlayerReciveDto> getAllPlayerFallBack(Exception e) {
		PlayerReciveDto dto = new PlayerReciveDto();
		dto.setPlayerId(0);
		dto.setPlayerName("Service Unavailable");
		dto.setRoll("Service Unavailable");
		dto.setAge(0);
		dto.setTeamDto(null);

		return List.of(dto);
	}

	@Override
	public String bulkRegisterPlayer(List<PlayerDto> listPlayerDto) {

		ListIterator<PlayerDto> listIterator = listPlayerDto.listIterator();

		while (listIterator.hasNext()) {
			Player player = new Player();
			PlayerDto next = listIterator.next();
			if (playerRepository.existsByPlayerName(next.getPlayerName())) {
				throw new PlayerAlradyExistsException("Player Alrady Exists "+next.getPlayerName());
			}
			BeanUtils.copyProperties(next, player);
			playerRepository.save(player);
		}

		return "All Players Save Successfully!";

	}

}
