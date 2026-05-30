package com.nt.ifeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.dto.TeamDto;

@FeignClient("IPL_Team_Service")
public interface IfeignServcie {
	
	@GetMapping("/ipl-team/getTeamById/{id}")
	public ResponseEntity<TeamDto> getTeamByIdController(@PathVariable Integer id);

}
