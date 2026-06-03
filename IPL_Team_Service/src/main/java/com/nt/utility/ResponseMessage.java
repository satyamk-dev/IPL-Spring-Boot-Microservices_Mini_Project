package com.nt.utility;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.nt.dto.TeamDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ResponseMessage {
	
	private String message;
	
	private String status;
	
	private HttpStatus statusCode;
	
	private Object object;
	
	private List<TeamDto> list;
	

}
