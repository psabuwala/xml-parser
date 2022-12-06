package com.tatva.test.iapps.xmlparser.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseListDTO {
	
	@SuppressWarnings("rawtypes")
	private List data;
	private Long totalCount;
}
