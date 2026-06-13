package com.nt.ifeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.dto.TeamDto;
import com.nt.utility.ResponseMessage;

@FeignClient("IPL-TEAM")
public interface IfeignServcie {

	@PostMapping("/team/saveteam")
	public ResponseEntity<ResponseMessage> saveTeamController(@RequestBody TeamDto teamDto);

	@GetMapping("/team/getallteam")
	public ResponseEntity<ResponseMessage> getAllTeamContorller();

	@GetMapping("/team/getbyid/{id}")
	public ResponseEntity<ResponseMessage> getTeamByIdController(@PathVariable int id);

	@PutMapping("/team/updateteam")
	public ResponseEntity<ResponseMessage> updateFullTeamController(@RequestBody TeamDto teamDto);

	@PatchMapping("/team/updateteamname")
	public ResponseEntity<ResponseMessage> updateTeamNameController(@RequestParam Integer id,
			@RequestParam String name);

	@DeleteMapping("/team/deleteteam")
	public ResponseEntity<ResponseMessage> deleteTeamByIdController(@RequestParam Integer id);

	@DeleteMapping("/team/deleteallteam")
	public ResponseEntity<ResponseMessage> deleteAllTeamController();
}
