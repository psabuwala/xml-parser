package com.tatva.test.iapps.xmlparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tatva.test.iapps.xmlparser.dto.EPaperDTO;
import com.tatva.test.iapps.xmlparser.dto.ResponseListDTO;
import com.tatva.test.iapps.xmlparser.service.EPaperService;

@RestController
@RequestMapping("epaper")
public class EPaperController {

	@Autowired
	private EPaperService epaperService;

	@PostMapping
	public ResponseEntity<EPaperDTO> save(@RequestParam("xmlFile") MultipartFile file) {
		return epaperService.save(file);
	}

	@GetMapping
	public ResponseEntity<ResponseListDTO> getAllEPapers(@RequestParam(required = false, defaultValue = "newsPaperName") String sortColumn,
			@RequestParam(required = false, defaultValue = "ASC") String sortOrder, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false, defaultValue = "") String search) {
		if (pageNo == null) {
			pageNo = 1;
		}
		ResponseListDTO epaperList = epaperService.findAll(sortColumn, sortOrder, pageNo, search);
		return ResponseEntity.ok(epaperList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EPaperDTO> getEPapersById(@PathVariable Long id) {
		EPaperDTO epaper = epaperService.findById(id);
		return ResponseEntity.ok(epaper);
	}
}
