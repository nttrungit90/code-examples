package com.example.spring.statemachine.service;

import com.example.spring.statemachine.repository.RunsheetRepository;
import com.example.spring.statemachine.domain.Runsheet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/data.sql"})
class RunsheetServiceTest {

	@Autowired
	RunsheetService runsheetService;

	@Autowired
	RunsheetRepository runsheetRepository;

	@Test
	void dispatchRunsheetTest() {
		Long courierId = 6L;
		Runsheet runsheet = runsheetService.dispatchRunsheet(courierId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheet.getId()).orElse(null);
		Assert.assertNotNull(dbRunsheet);
	}

}
