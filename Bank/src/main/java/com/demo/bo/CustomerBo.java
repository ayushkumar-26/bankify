package com.demo.bo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.BankRepository;
import com.demo.dao.CustomerCustomized;
import com.demo.dao.CustomerRepository;
import com.demo.entity.Bank;
import com.demo.entity.Customer;
import com.demo.exception.BankException;
import com.demo.exception.CustomerException;

@Component
public class CustomerBo {
	@Autowired
	CustomerRepository dao;
	@Autowired 
	BankRepository bankdao;
	public Customer addingCustomer(Customer c) throws CustomerException{
		
		if(c.getAccountNumber()==null) throw new CustomerException("Acc Number cant be null.");
		if(c.getContactNumber()==null) throw new CustomerException("Contact Number cant be null.");
		if(c.getGender()==null) throw new CustomerException("Gender cnat be null");
		if(c.getDob()==null) throw new CustomerException("Please enter DoB.");
		if(c.getName()==null) throw new CustomerException("Please enter Name.");
		if(c.getCity()==null) throw new CustomerException("Please enter City.");
		
		c.setName(c.getName().trim());
		c.setCity(c.getCity().trim());
		if(c.getBank()==null) throw new CustomerException("Please provide valid Bank.");
		Optional<Bank> b1 = bankdao.findById(c.getBank().getBankId());
		
		String emailPattern = "^\\S+@gmail\\.com$";
		boolean isEmailValid = false;
		String phoneNumberPattern = "\\d{10}";
		boolean isPhoneNumberValid = false;
		String accountNumberPattern  = "\\d{10,15}";
		boolean isAccountNumberValid = false;
		isEmailValid = c.getEmail().matches(emailPattern) ? true: false;
		isPhoneNumberValid = c.getContactNumber().matches(phoneNumberPattern) ? true: false;
		isAccountNumberValid = c.getAccountNumber().matches(accountNumberPattern) ? true:false;
		if(!(c.getGender().equals("M") || c.getGender().equals("F") || c.getGender().equals("O")) ) throw new CustomerException("Enter valid Gender.");
		if(isEmailValid==false) throw new CustomerException("Please enter valid Email.");
		if(isPhoneNumberValid==false) throw new CustomerException("Please enter valid Phone Number.");
		if(isAccountNumberValid==false) throw new CustomerException("Please enter valid Account Number.");
		
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(c.getDob(), currentDate).getYears();
		if(age<18) throw new CustomerException("Age should be greater that 18.");
		if(c.getId()< 0) { 
			throw new CustomerException("Please enter valid Customer Id");
		}
		else if(c.getName().isEmpty() || c.getName().isBlank()) {
			throw new CustomerException("Please enter valid Customer Name.");
		}
		else if(c.getCity().isEmpty() || !(c.getCity() instanceof String) || c.getCity().isBlank()) {
			throw new CustomerException("Please enter valid City Name.");
		}
		else if(!dao.findByAccountNumber(c.getAccountNumber()).isEmpty()) throw new CustomerException("Account Number already Present.");
		else if(!dao.findByContactNumber(c.getContactNumber()).isEmpty()) throw new CustomerException("Contact Number already Present.");
		else if(!dao.findByEmail(c.getEmail()).isEmpty()) throw new CustomerException("Email already present.");
		if(!b1.isPresent()) {
			throw new CustomerException("Bank is not present with id: "+ c.getBank().getBankId());
		}
		return dao.save(c);
	}
	
public Customer updateCustomer(Customer c) throws CustomerException{
		
	if(c.getAccountNumber()==null) throw new CustomerException("Acc Number cant be null.");
	if(c.getContactNumber()==null) throw new CustomerException("Contact Number cant be null.");
	if(c.getGender()==null) throw new CustomerException("Gender cnat be null");
	if(c.getDob()==null) throw new CustomerException("Please enter DoB.");
	if(c.getName()==null) throw new CustomerException("Please enter Name.");
	if(c.getCity()==null) throw new CustomerException("Please enter City.");
	
	c.setName(c.getName().trim());
	c.setCity(c.getCity().trim());
	if(c.getBank()==null) throw new CustomerException("Please provide valid Bank.");
	Optional<Bank> b1 = bankdao.findById(c.getBank().getBankId());
	if(!b1.isPresent()) {
		throw new CustomerException("Bank is not present with id: "+ c.getBank().getBankId());
	}
	
	
	String emailPattern = "^\\S+@gmail\\.com$";
	boolean isEmailValid = false;
	String phoneNumberPattern = "\\d{10}";
	boolean isPhoneNumberValid = false;
	String accountNumberPattern  = "\\d{10,15}";
	boolean isAccountNumberValid = false;
	isEmailValid = c.getEmail().matches(emailPattern) ? true: false;
	isPhoneNumberValid = c.getContactNumber().matches(phoneNumberPattern) ? true: false;
	isAccountNumberValid = c.getAccountNumber().matches(accountNumberPattern) ? true:false;
	if(!(c.getGender().equals("M") || c.getGender().equals("F") || c.getGender().equals("O")) ) throw new CustomerException("Enter valid Gender.");
	if(isEmailValid==false) throw new CustomerException("Please enter valid Email.");
	if(isPhoneNumberValid==false) throw new CustomerException("Please enter valid Phone Number.");
	if(isAccountNumberValid==false) throw new CustomerException("Please enter valid Account Number.");
	
	LocalDate currentDate = LocalDate.now();
	int age = Period.between(c.getDob(), currentDate).getYears();
	if(age<18) throw new CustomerException("Age should be greater that 18.");
	if(c.getId()< 0) { 
		throw new CustomerException("Please enter valid Customer Id");
	}
	else if(c.getName().isEmpty() || c.getName().isBlank()) {
		throw new CustomerException("Please enter valid Customer Name.");
	}
	else if(c.getCity().isEmpty() || !(c.getCity() instanceof String) || c.getCity().isBlank()) {
		throw new CustomerException("Please enter valid City Name.");
	}
	List<Customer> l = dao.findByAccountNumber(c.getAccountNumber());
	if(!l.isEmpty() && l.get(0).getId()!=c.getId()) throw new CustomerException("Account Number already Present.");
	
	l = dao.findByContactNumber(c.getContactNumber());
	if(!l.isEmpty() && l.get(0).getId()!=c.getId()) throw new CustomerException("Contact Number already Present.");
	
	l = dao.findByEmail(c.getEmail());
	if(!l.isEmpty() && l.get(0).getId()!=c.getId()) throw new CustomerException("Email already present.");
	
	
	return dao.save(c);
	}
	
//	public void deleteById(int id) throws CustomerException{
//		if(id<0) {
//			throw new CustomerException("Enter a positive id.");
//		}
//		Optional<Customer>c =  dao.findById(id);
//		if(!c.isPresent()) {
//			throw new CustomerException("Customer is not present with id: "+id);
//		}
//		Customer c1 = c.get();
//		dao.delete(c1);
//		//dao.deleteById(id);
//		return;
//	}
	
	
	public Customer findCustomerById(int id) throws CustomerException{
		if(id<0) {
			throw new CustomerException("Enter a positive id.");
		}
		Optional<Customer>c =  dao.findById(id);
		if(!c.isPresent()) {
			throw new CustomerException("Customer is not present with id: "+id);
		}
		return c.get();
	}
	public List<Customer> findAll() throws CustomerException{
		List<Customer> l = dao.findAll();
		if(l.isEmpty()) {
			throw new CustomerException("No Customer found.");
		}
		return l;
	}
	public List<Customer> findCustomersWithGreaterId(int id) throws CustomerException{
		if(id<0) {
			throw new CustomerException("Please enter positive Id");
		}
		
		List<Customer> l = dao.findCustomersWithGreaterId(id);
		if(l.isEmpty()) {
			throw new CustomerException("No record found for findCustomersWithGreaterId");
		}
		return l;
	}
	public List<BankCustomerCustomized> findCustomerWithName(String name) throws CustomerException{
		name = name.trim();
		List<BankCustomerCustomized> l = dao.findCustomerWithName(name);
		if(l.isEmpty()) {
			throw new CustomerException("No Customer is found with the name: " +name);
		}
		return l;
	}
	public List<Customer> findAllInDescendingOrder() throws CustomerException{
		
		List<Customer> l = dao.findAllInDescendingOrder();
		if(l.isEmpty()) {
			throw new CustomerException("No Customer found.");
		}
		return l;
	}

}
