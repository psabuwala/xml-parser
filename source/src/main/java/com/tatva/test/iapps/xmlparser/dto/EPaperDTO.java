package com.tatva.test.iapps.xmlparser.dto;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections4.map.HashedMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "epaperRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class EPaperDTO extends AbstractDTO {

	private Long id;

	@XmlElement
	private DeviceInfoDTO deviceInfo;

	@XmlElement
	private GetPagesDTO getPages;
	
	public static EPaperDTO error(String errorCode, String errorMsg) {
		EPaperDTO dto = new EPaperDTO();
		dto.isError = true;
		final Map<String, String> errors = new HashedMap<>();
		errors.put(errorCode, errorMsg);
		dto.errors = errors;
		return dto;
	}
}
