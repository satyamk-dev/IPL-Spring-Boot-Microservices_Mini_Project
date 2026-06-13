
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.PlayerDto;
import com.nt.dto.PlayerReciveDto;
import com.nt.dto.TeamDto;
import com.nt.ifeign.IfeignServcie;
import com.nt.iservice.IPlayerService;
import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private IPlayerService service;

	@Autowired
	private IfeignServcie feignService;

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

	@GetMapping("/getallplayeridandname")
	public ResponseEntity<ResponseMessage> getAllIdAndName() {

		Map<Integer, String> allIdAndPlayer = service.getAllIdAndPlayer();

		ResponseMessage response = ResponseMessage.builder().message("All Id And  Name List").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).map(allIdAndPlayer).build();

		return ResponseEntity.ok(response);

	}

	@PostMapping("/register-all")
	public ResponseEntity<ResponseMessage> registerAllPlayer(@RequestBody List<PlayerDto> listPlayerDto) {
		String bulkRegisterPlayer = service.bulkRegisterPlayer(listPlayerDto);
		ResponseMessage response = ResponseMessage.builder().message(bulkRegisterPlayer).status(Constants.SAVE_SUCCESS)
				.statusCode(Constants.STATUS_CREATED).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/team/getbyid/{id}")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "getTeamByIdFallBack")
	public ResponseEntity<ResponseMessage> getTeamById(@PathVariable int id) {
		return feignService.getTeamByIdController(id);
	}

	@PostMapping("/team/saveteam")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "postSaveTeamFallBack")
	public ResponseEntity<ResponseMessage> postSaveTeam(@RequestBody TeamDto teamDto) {
		return feignService.saveTeamController(teamDto);
	}

	@GetMapping("/team/getallteam")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "getAllTeamFallBack")
	public ResponseEntity<ResponseMessage> getAllTeam() {
		return feignService.getAllTeamContorller();
	}

	@PutMapping("/team/updateteam")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "updateTeamFallBack")
	public ResponseEntity<ResponseMessage> updateTeam(@RequestBody TeamDto teamDto) {
		return feignService.updateFullTeamController(teamDto);
	}

	@PatchMapping("/team/updateteamname")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "updateTeamNameFallBack")
	public ResponseEntity<ResponseMessage> updateTeamName(@RequestParam int id, @RequestParam String name) {
		return feignService.updateTeamNameController(id, name);
	}

	@DeleteMapping("/team/deleteteam")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "deleteTeamByIdFallBack")
	public ResponseEntity<ResponseMessage> deleteTeamById(@RequestParam int id) {
		return feignService.deleteTeamByIdController(id);
	}

	@DeleteMapping("/team/deleteallteam")
	@CircuitBreaker(name = "IPL-TEAM", fallbackMethod = "deleteAllTeamFallBack")
	public ResponseEntity<ResponseMessage> deleteAllTeam() {
		return feignService.deleteAllTeamController();
	}

	public ResponseEntity<ResponseMessage> getAllTeamFallBack(Exception ex) {
		ResponseMessage response = ResponseMessage.builder().message(
				"upstream connect error or disconnect/reset before headers. retried and the latest reset reason: connection timeout")
				.build();
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> deleteAllTeamFallBack(Exception ex) {
		ResponseMessage response = ResponseMessage.builder().message(
				"upstream connect error or disconnect/reset before headers. retried and the latest reset reason: connection timeout")
				.build();
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> updateTeamNameFallBack(int id, String name, Exception ex) {

		ResponseMessage response = ResponseMessage.builder().message("Updatation Communication Failed!").build();

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> getTeamByIdFallBack(int id, Exception ex) {

		ResponseMessage response = ResponseMessage.builder().message("Communication Failed!").build();

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> postSaveTeamFallBack(TeamDto dto, Exception ex) {

		ResponseMessage response = ResponseMessage.builder().message("Communication Failed!").build();

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> updateTeamFallBack(TeamDto dto, Exception ex) {

		ResponseMessage response = ResponseMessage.builder().message("Communication Failed!").build();

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<ResponseMessage> deleteTeamByIdFallBack(int id, Exception ex) {

		ResponseMessage response = ResponseMessage.builder().message("Communication Failed!").build();

		return ResponseEntity.ok(response);
	}

}
