package com.ICINBank.ICINbanking.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NamedQueries(value= { @NamedQuery(name = "Customer.getCustomerByUserName", query = "select cust from Customer cust where cust.user.userName = ?1 ") ,
})
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	@Length(min = 10,max=10)
	private String phoneNum;
	
	private String email;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(targetEntity = CurrentAccount.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "currentActNo")
	private CurrentAccount currentAccount;
	
	@OneToOne(targetEntity = SavingsAccount.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "savingActNo")
	private SavingsAccount savingsAccount;
	
	
	

}
