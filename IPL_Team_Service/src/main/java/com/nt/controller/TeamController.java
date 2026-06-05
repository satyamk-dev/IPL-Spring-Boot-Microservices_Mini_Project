package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.nt.dto.TeamDto;
import com.nt.iservice.IService;
import com.nt.utility.Constants;
import com.nt.utility.ResponseMessage;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private IService service;

	@GetMapping("/test")
	public String testing() {
		return new String(" Team Service ✔: Application Running");
	}

	@PostMapping("/saveteam")
	public ResponseEntity<ResponseMessage> saveTeamController(@RequestBody TeamDto teamDto) {
		String saveTeam = service.saveTeam(teamDto);
		ResponseMessage response = ResponseMessage.builder().message(saveTeam).status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).build();
		System.out.println(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getallteam")
	public ResponseEntity<ResponseMessage> getAllTeamContorller() {
		List<TeamDto> allTeamService = service.getAllTeamService();
		ResponseMessage response = ResponseMessage.builder().message("List of All Team's").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).list(allTeamService).build();
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<ResponseMessage> getTeamByIdController(@PathVariable int id) {
		TeamDto teamById = service.getTeamById(id);
		ResponseMessage response = ResponseMessage.builder().message("Get Team By Id Service").status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).object(teamById).build();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateteam")
	public ResponseEntity<ResponseMessage> updateFullTeamController(@RequestBody TeamDto teamDto) {

		String updateTeam = service.updateTeam(teamDto);
		ResponseMessage response = ResponseMessage.builder().message(updateTeam).status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_CREATED).object(teamDto).build();
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/updateteamname")
	public ResponseEntity<ResponseMessage> updateTeamNameController(@RequestParam Integer id,
			@RequestParam String name) {

		String updateTeamName = service.updateTeamName(id, name);

		ResponseMessage response = ResponseMessage.builder().message(updateTeamName).status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_CREATED).build();

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteteam")
	public ResponseEntity<ResponseMessage> deleteTeamByIdController(@RequestParam Integer id) {

		String deleteTeam = service.deleteTeam(id);
		ResponseMessage response = ResponseMessage.builder().message(deleteTeam).status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).build();

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteallteam")
	public ResponseEntity<ResponseMessage> deleteAllTeamController() {
		String deleteAllTeam = service.deleteAllTeam();

		ResponseMessage response = ResponseMessage.builder().message(deleteAllTeam).status(Constants.SUCCESS)
				.statusCode(Constants.STATUS_OK).build();

		return ResponseEntity.ok(response);

	}

}
