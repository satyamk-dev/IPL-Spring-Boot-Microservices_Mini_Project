package com.nt.iservice;

import java.util.List;
import java.util.Map;

import com.nt.dto.PlayerDto;
import com.nt.dto.PlayerReciveDto;

public interface IPlayerService {

	public String registerPlayer(PlayerDto playerDto);
	
	public String bulkRegisterPlayer(List<PlayerDto> listPlayerDto);	

	public PlayerReciveDto getPlayer(int id);

	public String deletePlayer(Integer id);

	public String updatePlayer(PlayerDto player);

	public String updateName(String name, Integer playerId);

	public String updateRoll(String roll, Integer playerId);

	public List<String> getAllPlayerName();

	public Map<Integer, String> getAllIdAndPlayer();

	public List<PlayerReciveDto> getAllPlayer();

}
