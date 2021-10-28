package com.ICINBank.ICINbanking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.ICINBank.ICINbanking.IciNbankingApplication;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class AccountTransactionControllerTest {
	
	@Autowired
	private AccountTransactionController accountTranCtrl;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testGetSavingsAccountTrans() {
		Model model =new  ExtendedModelMap();
		String actualResult = accountTranCtrl.getSavingsAccountTrans(model, 923);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewTransactionDetails");	
	}
	
	@Test
	public void testGetCurrentAccountTrans() {
		Model model =new  ExtendedModelMap();
		String actualResult = accountTranCtrl.getSavingsAccountTrans(model, 923);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewTransactionDetails");	
	}
	
	@Test
	public void testGetAccountTransaction() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR123");
		Model model =new  ExtendedModelMap();
		String actualResult = accountTranCtrl.getAccountTransaction(model, "savings",request);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewTransactionDetails");	
	}
	
	 

}
