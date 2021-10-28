package com.ICINBank.ICINbanking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.ICINBank.ICINbanking.IciNbankingApplication;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class AdminControllerTest {
	
	@Autowired
	AdminController admincontroller;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void TestEnableOrDisableCustomer() {
		Model model =new  ExtendedModelMap();
		String actualResult = admincontroller.enableOrDisableUserAccount(2, 1, model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewListUserDetail");		
	}
	
	@Test
	public void TestPaginateViewUserDetail() {
		Model model =new  ExtendedModelMap();
		String actualResult = admincontroller.paginateViewUserDetail(1, model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewListUserDetail");		
	}

}
