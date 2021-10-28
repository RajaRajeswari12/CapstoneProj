package com.ICINBank.ICINbanking.serviceImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.ICINBank.ICINbanking.model.Customer;
import com.ICINBank.ICINbanking.model.User;
import com.ICINBank.ICINbanking.repository.CustomerRepository;
import com.ICINBank.ICINbanking.service.CurrentAccountService;
import com.ICINBank.ICINbanking.service.CustomerService;
import com.ICINBank.ICINbanking.service.SavingsAccountService;
import com.ICINBank.ICINbanking.service.UserService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private CurrentAccountService currentAccService;
	
	@Autowired 
	private SavingsAccountService savingsAccService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Customer registerNewCustomer(Customer customer) {
//		User user = customer.getUser();
		
		log.info("User Info" + customer.getUser().toString());
		User user = userService.createNewUser(customer.getUser());
		customer.setUser(user);
		customer.setSavingsAccount(savingsAccService.createNewSavingsAccount());
		customer.setCurrentAccount(currentAccService.createNewCurrentAccount());
		
		return customerRepo.save(customer);
	}

	@Override
	public Customer findByUserName(String userName) {
		
		return customerRepo.getCustomerByUserName(userName);
	}

	@Override
	public Customer getCustomerBySessionVar(HttpServletRequest request) {			
			HttpSession session = request.getSession(false);
			Customer cust = null;
			log.info("Session value"+session);
			if(session != null) {
				String username = (String) session.getAttribute("UserName"); 
				cust = findByUserName(username);
			}
			return cust;	
			
		}

	@Override
	public Page<Customer> findAllCustomer(int pageNo, int userCount) {
		String sortField = "user.userName";
		 Sort sort = Sort.by(sortField).ascending();
		Pageable pageable = PageRequest.of(pageNo-1, userCount, sort);
		return customerRepo.findAll(pageable);
	}

	@Override
	public Customer enableDisableUserAcc(int userId) {
		Customer customer = customerRepo.findById(userId).get();
		customer.getUser().setActive(!customer.getUser().isActive());
		
		return customerRepo.save(customer);
	}
	
	


}
