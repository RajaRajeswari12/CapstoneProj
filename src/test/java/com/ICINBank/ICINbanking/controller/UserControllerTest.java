package com.ICINBank.ICINbanking.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ICINBank.ICINbanking.IciNbankingApplication;
import com.ICINBank.ICINbanking.model.User;

@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class UserControllerTest {
	
	@Autowired
	private UserController userController;

	@Test
	public void validateAdminUser_Test_wrongPassword() {
		User params = new User();
		params.setUserName("admin");
		params.setPassword("password");
		 BindingResult result = mock(BindingResult.class);
		    when(result.hasErrors()).thenReturn(true);
		 MockHttpServletRequest request = new MockHttpServletRequest();
		
		String pageName =  userController.adminLogin(params,result,request);
		assertNotNull(result);
		assertEquals(pageName, "adminLogin");
	}
	
	@Test
	public void validateAdminUser_Test_Positive() {
		User params = new User();
		params.setUserName("admin");
		params.setPassword("12345678");
		 BindingResult result = mock(BindingResult.class);
		   when(result.hasErrors()).thenReturn(false);
		 MockHttpServletRequest request = new MockHttpServletRequest();
	
		String pageName =  userController.adminLogin(params,result,request);
		assertNotNull(result);
		assertEquals(pageName, "adminHomePage");
	}
	
	
	@Test
	public void validateProfilePage_Test_Positive() {
	
		 MockHttpServletRequest request = new MockHttpServletRequest();
		String expectedPage = "ViewCustomerDetail";
	
		ModelAndView pageName =  userController.goToProfilePage(request);
		assertNotNull(pageName);
		assertEquals(pageName.getViewName(), expectedPage);
	}

	
	@Test
	public void validateAdminHomePage_Test_Positive() {
	
		 MockHttpServletRequest request = new MockHttpServletRequest();
		String expectedPage = "adminHomePage";
	
		ModelAndView pageName =  userController.goToAdminHomePage(request);
		assertNotNull(pageName);
		assertEquals(pageName.getViewName(), expectedPage);
	}
	
	@Test
	public void validateUserHomePage_Test_Positive() {
	
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 request.setAttribute("UserName", "RR123");
		String expectedPage = "homePage";
	
		ModelAndView pageName =  userController.goToHomePage(request);
		assertNotNull(pageName);
		assertEquals(pageName.getViewName(), expectedPage);
	}
	
	@Test
	public void validateUser_Test_wrongPassword() {
		User params = new User();
		params.setUserName("RR123");
		params.setPassword("password");
		 BindingResult result = mock(BindingResult.class);
		    when(result.hasErrors()).thenReturn(true);
		 MockHttpServletRequest request = new MockHttpServletRequest();
		
		ModelAndView pageName =  userController.userLogin(params,result,request);
		assertNotNull(result);
		assertEquals(pageName.getViewName(), "userLogin");
	}
	
	@Test
	public void validateUser_Test_Positive() {
		User params = new User();
		params.setUserName("RR123");
		params.setPassword("12345678");
		 BindingResult result = mock(BindingResult.class);
		   when(result.hasErrors()).thenReturn(false);
		 MockHttpServletRequest request = new MockHttpServletRequest();
	
		ModelAndView pageName =  userController.userLogin(params,result,request);
		assertNotNull(result);
		assertEquals(pageName.getViewName(), "homePage");
	}
	


}
