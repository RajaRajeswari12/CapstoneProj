package com.ICINBank.ICINbanking.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.ICINBank.ICINbanking.IciNbankingApplication;
import com.ICINBank.ICINbanking.model.Customer;
import com.ICINBank.ICINbanking.model.User;


@RunWith(SpringRunner.class)

@SpringBootTest(classes=IciNbankingApplication.class) 
public class CustomerControllerTest {
	
	@Autowired
	private CustomerController customerController;
	@Test
	public void validateUserRegistration_Test_Positive() {
	
			User user = new User(16,"PaulMark","12345678",true,"User");
			
			Customer customer = new Customer();
			customer.setFirstName("Paul");
			customer.setLastName("MArtin");
			customer.setEmail("peterPaul@gmail.com");
			customer.setPhoneNum("9087654321");
			customer.setUser(user);
			BindingResult result = mock(BindingResult.class);
		    when(result.hasErrors()).thenReturn(false);
		
		String pageName =  customerController.saveAdminUser(customer,result);
		assertNotNull(result);
		assertEquals(pageName, "welcome");
	}

	
	@Test
	public void validateUserRegistration_Test_Negative() {
	
			User user = new User(16,"PeterMartqn","12345678",true,"User");
			
			Customer customer = new Customer();
			customer.setFirstName("Peter");
			customer.setLastName("Martin");
			customer.setEmail("peterMartin@gmail.com");
			customer.setPhoneNum("908765432");
			customer.setUser(user);
			BindingResult result = mock(BindingResult.class);
		    when(result.hasErrors()).thenReturn(true);
//		 MockHttpServletRequest request = new MockHttpServletRequest();
		
		String pageName =  customerController.saveAdminUser(customer,result);
		assertNotNull(result);
		assertEquals(pageName, "userRegistration");
	}

	

}
