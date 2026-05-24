package com.nt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.PlayerDto;
import com.nt.dto.TeamDto;
import com.nt.entity.Player;
import com.nt.ifeign.IfeignServcie;
import com.nt.iservice.IPlayerService;
import com.nt.repository.PlayerRepository;
import com.nt.repository.TeamRepository;

@Service
public class PlayerService implements IPlayerService {

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

		return "Player Registered Successfully! " + save.toString();
	}

	@Override
	public PlayerDto getPlayer(Integer id) {
		Optional<Player> byId = playerRepository.findById(id);

		PlayerDto playerDto = null;

		if (byId.isPresent()) {
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
	public List<String> getAllPlayerName(List<Integer> playerIds) {
		List<String> nameList = new ArrayList<>();
		Iterator<Integer> ilist = playerIds.listIterator();
		while (ilist.hasNext()) {
			Integer next = ilist.next();
			Player player = playerRepository.findById(next)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
			nameList.add(player.getPlayerName());
		}
		return nameList;
	}

	@Override
	public Map<Integer, String> getAllIdAndPlayer(List<Integer> list) {
		Map<Integer, String> map = new HashMap<>();
		for (Integer i : list) {
			Player player = playerRepository.findById(i)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Input"));
			map.put(player.getPlayerId(), player.getPlayerName());
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
