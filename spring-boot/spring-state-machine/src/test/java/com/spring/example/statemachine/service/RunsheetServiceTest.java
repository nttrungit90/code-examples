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

	@Test
	void checkoutRunsheetSuccessTest() {
		Long runsheetId = 1L;
		runsheetService.checkoutRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CHECKED_OUT, dbRunsheet.getStatus());
	}

	@Test
	void checkoutRunsheetFailTest() {
		Long runsheetId = 3L;
		runsheetService.checkoutRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CHECKED_OUT, dbRunsheet.getStatus());
	}

	@Test
	void cancelRunsheetSuccessTest() {
		Long runsheetId = 1L;
		runsheetService.cancelRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CANCELED, dbRunsheet.getStatus());
	}

	@Test
	void cancelRunsheetFailTest() {
		Long runsheetId = 3L;
		runsheetService.cancelRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CANCELED, dbRunsheet.getStatus());
	}

	@Test
	void cancelRunsheetNotAllowedByGuardTest() {
		Long runsheetId = 1L;
		runsheetService.cancelRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CANCELED, dbRunsheet.getStatus());
	}

	@Test
	void cancelRunsheetAllowedByGuardTest() {
		Long runsheetId = 6L;
		runsheetService.cancelRunsheet(runsheetId);
		Runsheet dbRunsheet = runsheetRepository.findById(runsheetId).orElse(null);
		Assert.assertEquals(RunsheetStatus.CANCELED, dbRunsheet.getStatus());
	}



}
