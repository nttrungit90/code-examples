package com.spring.example.statemachine.service;

import com.spring.example.statemachine.domain.Runsheet;
import com.spring.example.statemachine.domain.RunsheetStatus;
import com.spring.example.statemachine.repository.RunsheetRepository;
import com.spring.example.statemachine.service.RunsheetService;
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
