
package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.PlayerDto;
import com.nt.ifeign.IfeignServcie;
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
		return new String("GetMapping calling : Application Running");
	}

	@GetMapping("/getallplayer")
	public ResponseEntity<ResponseMessage> getAllPlayerController() {
		List<PlayerDto> allPlayer = service.getAllPlayer();
		ResponseMessage response = ResponseMessage.builder().message("All Player List").status(Constants.SUCCESS)
				.statusCode(HttpStatus.OK).list(allPlayer).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping("saveplayer")
	public ResponseEntity<ResponseMessage> savePlayerWithTeam(@RequestBody PlayerDto dto) {

		String registerPlayer = service.registerPlayer(dto);

		ResponseMessage response = ResponseMessage.builder().message(registerPlayer).status(Constants.SAVE_SUCCESS)
				.statusCode(HttpStatus.ACCEPTED).object(dto).build();

		return ResponseEntity.ok(response);

	}

}
