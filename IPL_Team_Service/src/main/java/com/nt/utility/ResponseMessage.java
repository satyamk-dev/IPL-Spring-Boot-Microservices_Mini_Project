package com.nt.utility;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ResponseMessage {

	private String message;

	private String status;

	private Integer statusCode;

	private Object object;

	private List<?> list;

	private Map<?, ?> map;

}
