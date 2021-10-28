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
import com.ICINBank.ICINbanking.POJO.TransferDetailPOJO;
import com.ICINBank.ICINbanking.model.DepositOrWithdraw;
import com.ICINBank.ICINbanking.repository.DepositOrWithdrawRepository;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class FundsTransferControllerTest {
	
	@Autowired
	private FundsTransferController fundsTransferController;
	
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testVerifyAndTransferFundToPrimaryPositive() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		 TransferDetailPOJO transferDetailPOJO = new TransferDetailPOJO();
		 transferDetailPOJO.setFromAccType("savings");
		 transferDetailPOJO.setToAccType("primary");
		 transferDetailPOJO.setAmountToBeTranfered(100);
		 transferDetailPOJO.setToAccNo(1923);

		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		ModelAndView actualResult = fundsTransferController.verifyAndTransferFund(transferDetailPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "homePage");	
	}
	
	@Test
	public void testVerifyAndTransferFundToPrimaryNegative() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		 TransferDetailPOJO transferDetailPOJO = new TransferDetailPOJO();
		 transferDetailPOJO.setFromAccType("savings");
		 transferDetailPOJO.setToAccType("primary");
		 transferDetailPOJO.setAmountToBeTranfered(100);
		 transferDetailPOJO.setToAccNo(1923);

		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		ModelAndView actualResult = fundsTransferController.verifyAndTransferFund(transferDetailPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "FundTransfer");	
	}
	@Test
	public void testVerifyAndTransferFundSavings() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		 TransferDetailPOJO transferDetailPOJO = new TransferDetailPOJO();
		 transferDetailPOJO.setFromAccType("primary");
		 transferDetailPOJO.setToAccType("savings");
		 transferDetailPOJO.setAmountToBeTranfered(100);
		 transferDetailPOJO.setToAccNo(923);

		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		ModelAndView actualResult = fundsTransferController.verifyAndTransferFund(transferDetailPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "homePage");	
	}
	@Test
	public void testVerifyAndTransferFundToSavingsNegative() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		 HttpSession session = request.getSession();
		 session.setAttribute("UserName", "RR1234");
		 TransferDetailPOJO transferDetailPOJO = new TransferDetailPOJO();
		 transferDetailPOJO.setFromAccType("primary");
		 transferDetailPOJO.setToAccType("savings");
		 transferDetailPOJO.setAmountToBeTranfered(100);
		 transferDetailPOJO.setToAccNo(1923);

		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		ModelAndView actualResult = fundsTransferController.verifyAndTransferFund(transferDetailPOJO,result, request);		
		logger.info("Actual Page "+actualResult.getViewName());		
		assertEquals(actualResult.getViewName(), "FundTransfer");	
	}
	
	

}
