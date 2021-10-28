package com.ICINBank.ICINbanking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ICINBank.ICINbanking.IciNbankingApplication;
import com.ICINBank.ICINbanking.POJO.DepositOrWithdrawPOJO;
import com.ICINBank.ICINbanking.model.DepositOrWithdraw;
import com.ICINBank.ICINbanking.repository.DepositOrWithdrawRepository;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class DepositOrWithdrawControllerTest {
	
	@Autowired
	private DepositOrWithdrawController depositOrWithdrawController;
	
	@Autowired
	private DepositOrWithdrawRepository depositOrWithdrawRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testDepositOrWithdrawPagePositive() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		DepositOrWithdrawPOJO depositOrWithdrawPOJO = new DepositOrWithdrawPOJO();
		depositOrWithdrawPOJO.setAccountType("savings");
		depositOrWithdrawPOJO.setActionType("Deposit");
		depositOrWithdrawPOJO.setAmount(10000);
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		ModelAndView actualResult = depositOrWithdrawController.depositOrWithdrawPage(depositOrWithdrawPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "homePage");	
	}
	@Test
	public void testDepositOrWithdrawPageNegative() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		DepositOrWithdrawPOJO depositOrWithdrawPOJO = new DepositOrWithdrawPOJO();
		depositOrWithdrawPOJO.setAccountType("primary");
		depositOrWithdrawPOJO.setActionType("Deposit");
		depositOrWithdrawPOJO.setAmount(10000);
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		ModelAndView actualResult = depositOrWithdrawController.depositOrWithdrawPage(depositOrWithdrawPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), depositOrWithdrawPOJO.getActionType());	
	}
	@Test
	public void testPaginateViewDepositWithdrawReqList() {
		Model model =new  ExtendedModelMap();
		String actualResult = depositOrWithdrawController.paginateViewDepositWithdrawReqList(1,model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewMoneyTransferRequest");	
	}
	
	@Test
	public void testDepositOrWithdrawAmount() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		Model model =new  ExtendedModelMap();
		String actualResult = depositOrWithdrawController.depositOrWithdrawAmount(16,1,model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewMoneyTransferRequest");	
		DepositOrWithdraw depositOrWithdraw = depositOrWithdrawRepository.findById(16).get();
		assertEquals(depositOrWithdraw.getStatus(), "Approved");
		
	}
	
	@Test
	public void testDepositOrWithdrawAmountDisapprove() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		Model model =new  ExtendedModelMap();
		String actualResult = depositOrWithdrawController.depositOrWithdrawAmountDisapprove(38,1,model);		
		logger.info("Actual Page "+actualResult);		
		assertEquals(actualResult, "viewMoneyTransferRequest");	
		DepositOrWithdraw depositOrWithdraw = depositOrWithdrawRepository.findById(38).get();
		assertEquals(depositOrWithdraw.getStatus(), "Rejected");
		
	}
	
	
	 

}
