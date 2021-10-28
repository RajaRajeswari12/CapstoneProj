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
import org.springframework.web.servlet.ModelAndView;

import com.ICINBank.ICINbanking.IciNbankingApplication;
import com.ICINBank.ICINbanking.model.ChequeBook;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class ChequeBookControllerTest {
	
	@Autowired
	private ChequeBookController chequeBookController;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testRaiseChequeRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		ChequeBook chequeBook = new ChequeBook();
		chequeBook.setAccountType("savings");
		chequeBook.setDescription("Savings Account ChequesBook");
		ModelAndView actualResult = chequeBookController.raiseChequeRequest(chequeBook, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "homePage");	
	}
	
	@Test
	public void testPaginateViewChequeBookReqList() {
		Model model =new  ExtendedModelMap();
		String actualResult = chequeBookController.paginateViewChequeBookReqList(1,model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewChequeBkRequestList");	
	}
	
	@Test
	public void testApproveChequeBookRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR123");
		Model model =new  ExtendedModelMap();
		String actualResult = chequeBookController.approveChequeBookRequest(99,1,model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewChequeBkRequestList");	
	}
	
	 

}
