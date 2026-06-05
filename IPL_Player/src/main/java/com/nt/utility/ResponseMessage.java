package com.nt.utility;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ResponseMessage {
	
	private String message;
	
	private String status;
	
	private HttpStatusCode statusCode;
	
	private Object object;
	
	private List<?> list;
	
	private Map<?,?> map;
	
	

}
