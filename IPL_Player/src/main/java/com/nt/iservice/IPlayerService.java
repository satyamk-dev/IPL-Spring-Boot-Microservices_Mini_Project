package com.nt.iservice;

import java.util.List;
import java.util.Map;

import com.nt.dto.PlayerDto;
import com.nt.dto.TeamDto;

public interface IPlayerService {

	public String registerPlayer(PlayerDto player);

	public PlayerDto getPlayer(Integer id);

	public String deletePlayer(Integer id);

	public String updatePlayer(PlayerDto player);

	public String updateName(String name, Integer playerId);

	public String updateRoll(String roll, Integer playerId);

	public List<String> getAllPlayerName(List<Integer> list);

	public Map<Integer, String> getAllIdAndPlayer(List<Integer> list);

	public List<PlayerDto> getAllPlayer();

}
