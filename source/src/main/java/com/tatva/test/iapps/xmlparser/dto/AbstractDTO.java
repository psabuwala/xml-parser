package com.tatva.test.iapps.xmlparser.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO {

	protected boolean isError;
	protected Map<String, String> errors;
	
}
