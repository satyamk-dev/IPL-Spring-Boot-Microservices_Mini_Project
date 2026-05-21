package com.nt.utility;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ResponseMessage {
	
	private String message;
	
	private String status;
	
	private String statusCode;
	
	private Object object;
	
	private List list;
	
	

}
