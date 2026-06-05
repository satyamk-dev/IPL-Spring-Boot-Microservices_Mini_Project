package com.nt.ifeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.dto.TeamDto;
import com.nt.utility.ResponseMessage;

@FeignClient("IPL-TEAM")
public interface IfeignServcie {
	
	@GetMapping("/team/getbyid/{id}")
	public ResponseEntity<ResponseMessage> getTeamByIdController(@PathVariable Integer id);

}
