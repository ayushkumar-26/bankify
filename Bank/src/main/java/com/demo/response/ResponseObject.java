package com.demo.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.BankCustomized;
import com.demo.dao.CustomerCustomized;
import com.demo.entity.Bank;
import com.demo.entity.Customer;

@Component
public class ResponseObject {
	 String successMsg;
	 String failureMsg;
	 Bank b;
	 Customer c;
	 List<Bank> listBank;
	 List<Customer> listCustomer;
	 List<BankCustomerCustomized> listBankCustomerCustomized;
	 List<BankCustomized> listBankCustomized;
	 List<CustomerCustomized> listCustomerCustomized;
	
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getFailureMsg() {
		return failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	public Bank getB() {
		return b;
	}
	public void setB(Bank b) {
		this.b = b;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public List<Bank> getListBank() {
		return listBank;
	}
	public void setListBank(List<Bank> listBank) {
		this.listBank = listBank;
	}
	public List<Customer> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
	public List<BankCustomerCustomized> getListBankCustomerCustomized() {
		return listBankCustomerCustomized;
	}
	public void setListBankCustomerCustomized(List<BankCustomerCustomized> listBankCustomerCustomized) {
		this.listBankCustomerCustomized = listBankCustomerCustomized;
	}
	public List<BankCustomized> getListBankCustomized() {
		return listBankCustomized;
	}
	public void setListBankCustomized(List<BankCustomized> listBankCustomized) {
		this.listBankCustomized = listBankCustomized;
	}
	public List<CustomerCustomized> getListCustomerCustomized() {
		return listCustomerCustomized;
	}
	public void setListCustomerCustomized(List<CustomerCustomized> listCustomerCustomized) {
		this.listCustomerCustomized = listCustomerCustomized;
	}
		
}
