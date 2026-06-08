
package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.PlayerDto;
import com.nt.dto.PlayerReciveDto;
import com.nt.iservice.IPlayerService;
import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private IPlayerService service;

	@GetMapping("/test")
	public String testing() {
		return new String("Player Service  : Application Running ✔");
	}

	@GetMapping("/getallplayer")
	public ResponseEntity<ResponseMessage> getAllPlayerController() {
		List<PlayerReciveDto> allPlayer = service.getAllPlayer();
		ResponseMessage response = ResponseMessage.builder().message("All Player List ✔").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).list(allPlayer).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping("/saveplayer")
	public ResponseEntity<ResponseMessage> savePlayerWithTeam(@RequestBody PlayerDto dto) {

		String registerPlayer = service.registerPlayer(dto);

		ResponseMessage response = ResponseMessage.builder()
				.message(registerPlayer + " Team Id : " + dto.getTeam().getTeamId()).status(Constants.SAVE_SUCCESS)
				.statusCode(Constants.STATUS_OK).build();

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getplayerbyid/{id}")
	public ResponseEntity<ResponseMessage> getPlayerByIdController(@PathVariable int id) {
		PlayerReciveDto player = service.getPlayer(id);
		ResponseMessage response = ResponseMessage.builder().message("Player got By Id").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).object(player).build();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateplayer")
	public ResponseEntity<ResponseMessage> updatePlayerController(@RequestBody PlayerDto dto) {
		String updatePlayer = service.updatePlayer(dto);
		ResponseMessage response = ResponseMessage.builder().message(updatePlayer).status(Constants.UPDATE_SUCCESS)
				.statusCode(Constants.STATUS_CREATED).build();
		return ResponseEntity.ok(response);

	}

	@PatchMapping("/updatename/{name}/{id}")
	public ResponseEntity<ResponseMessage> updateNameController(@PathVariable String name, @PathVariable int id) {

		String updateName = service.updateName(name, id);

		ResponseMessage response = ResponseMessage.builder().message(updateName).status(Constants.UPDATE_SUCCESS)
				.statusCode(Constants.STATUS_CREATED).build();

		return ResponseEntity.ok(response);

	}

	@PatchMapping("/updateroll/{roll}/{id}")
	public ResponseEntity<ResponseMessage> updateRollController(@PathVariable String roll, @PathVariable int id) {

		String updateRoll = service.updateRoll(roll, id);

		ResponseMessage response = ResponseMessage.builder().message(updateRoll).status(Constants.UPDATE_SUCCESS)
				.statusCode(Constants.STATUS_CREATED).build();

		return ResponseEntity.ok(response);

	}

	@DeleteMapping("/deleteplayer/{id}")
	public ResponseEntity<ResponseMessage> deletePlayerController(@PathVariable int id) {
		String deletePlayer = service.deletePlayer(id);

		ResponseMessage response = ResponseMessage.builder().message(deletePlayer).status(Constants.DELETE_SUCCESS)
				.statusCode(Constants.STATUS_OK).build();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getplayernamelist")
	public ResponseEntity<ResponseMessage> getPlayerNamesController() {

		List<String> allPlayerName = service.getAllPlayerName();

		ResponseMessage response = ResponseMessage.builder().message("All Player Name List").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).list(allPlayerName).build();

		return ResponseEntity.ok(response);
	}

	@GetMapping("getallplayeridandname")
	public ResponseEntity<ResponseMessage> getAllIdAndName() {

		Map<Integer, String> allIdAndPlayer = service.getAllIdAndPlayer();

		ResponseMessage response = ResponseMessage.builder().message("All Id And  Name List").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).map(allIdAndPlayer).build();

		return ResponseEntity.ok(response);

	}

}
