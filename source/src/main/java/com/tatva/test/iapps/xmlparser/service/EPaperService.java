package com.tatva.test.iapps.xmlparser.service;

import java.net.URL;
import java.util.Optional;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.tatva.test.iapps.xmlparser.dto.EPaperDTO;
import com.tatva.test.iapps.xmlparser.dto.ResponseListDTO;
import com.tatva.test.iapps.xmlparser.entity.EPaper;
import com.tatva.test.iapps.xmlparser.mapper.IEPaperMapper;
import com.tatva.test.iapps.xmlparser.repo.IEPaperRepository;

@Service
public class EPaperService {

	@Autowired
	private IEPaperRepository epaperRepository;
	
	private IEPaperMapper epaperMapper = IEPaperMapper.INSTANCE;
	
	public ResponseEntity<EPaperDTO> save(MultipartFile file) {
		
		try {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            // Create schema from xsd to validate xml
            URL url = getClass().getClassLoader().getResource("data/xsd/EPaperValidation.xsd");
            Schema schema = sf.newSchema(url);

            Validator validator = schema.newValidator();
            // Validate XML with XSD
            validator.validate(new StreamSource(file.getInputStream()));

            JAXBContext context = JAXBContext.newInstance(EPaperDTO.class);
            Unmarshaller un = context.createUnmarshaller();
            un.setSchema(schema);
            
            EPaperDTO dto = (EPaperDTO) un.unmarshal(file.getInputStream());
            EPaper epaper = epaperMapper.toEntity(dto);
            epaper.setFileName(file.getOriginalFilename());

            epaper = epaperRepository.save(epaper);
            return ResponseEntity.ok(epaperMapper.toDTO(epaper));
		}catch (SAXException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(EPaperDTO.error("ERR-001", ex.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(EPaperDTO.error("ERR-002", e.getMessage()));
        }
	}

	public ResponseListDTO findAll(String sortColumn, String sortOrder, Integer pageNo, String search) {
		Sort sorting = sortOrder != null && sortOrder.equalsIgnoreCase("DESC") ? Sort.by(sortColumn).descending() : Sort.by(sortColumn);
		Pageable paging = 
				  PageRequest.of(pageNo-1, 10, sorting);
		
		final Page<EPaper> lst = this.epaperRepository.findAll(this.findByString("newsPaperName", search), paging);
		return new ResponseListDTO(epaperMapper.toDTOList(lst.getContent()), lst.getTotalElements());
	}

	public EPaperDTO findById(final Long id) {
		final Optional<EPaper> opt = this.epaperRepository.findById(id);
		final EPaper epaper = opt.isPresent() ? opt.get() : null;
		return epaperMapper.toDTO(epaper);
	}
	
	private Specification<EPaper> findByString(final String column, final String value) {
		return (root, query, builder) -> builder.like(root.get(column), "%"+value+"%");
	}

}
