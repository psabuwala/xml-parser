package com.tatva.test.iapps.xmlparser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tatva.test.iapps.xmlparser.entity.EPaper;

@Repository
public interface IEPaperRepository extends JpaRepository<EPaper, Long>, JpaSpecificationExecutor<EPaper>{

}
