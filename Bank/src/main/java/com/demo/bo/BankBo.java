package com.demo.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.BankRepository;
import com.demo.entity.Bank;
import com.demo.exception.BankException;
import com.demo.exception.CustomerException;


@Component
public class BankBo {
	@Autowired
	BankRepository bankdao;
	public Bank addingBank(Bank b) throws BankException {
		if(b.getName()==null) throw new BankException("Name cant be null");
		if(b.getDateEstablished()==null) throw new BankException("Date Established cant be null");
		if(b.getWebsiteUrl()==null) throw new BankException("Url cant be null");
		
		b.setName(b.getName().trim());
		if(b.getDateEstablished() == null) throw new BankException("Please provide valid Date.");
		LocalDate currentDate = LocalDate.now();
		if(b.getDateEstablished().isAfter(currentDate)) throw new BankException("Invalid Date of Establish.");
		else if(b.getTotalBranches()<0) throw new BankException("Bank Branches can't be negetive.");
		else if(b.getBankId()<0) { 
			throw new BankException("Please enter Positive BankID");
		}else if(b.getName().isEmpty() || !(b.getName() instanceof String) || b.getName().isBlank() ) {
			throw new BankException("Please provide valid Bank Name.");
		}else if(!(bankdao.findByName(b.getName()).isEmpty())) {
			throw new BankException("Bank already Present.");
		}else if(!bankdao.findByWebsiteUrl(b.getWebsiteUrl()).isEmpty()) {
			throw new BankException("URL already Present.");
		}
		return bankdao.save(b);
	}
	
	public Bank updateBank(Bank b) throws BankException {
		if(b.getName()==null) throw new BankException("Name cant be null");
		if(b.getDateEstablished()==null) throw new BankException("Date Established cant be null");
		if(b.getWebsiteUrl()==null) throw new BankException("Url cant be null");
		
		b.setName(b.getName().trim());
		if(b.getDateEstablished() == null) throw new BankException("Please provide valid Date.");
		LocalDate currentDate = LocalDate.now();
		if(b.getDateEstablished().isAfter(currentDate)) throw new BankException("Invalid Date of Establish.");
		else if(b.getTotalBranches()<0) throw new BankException("Bank Branches can't be negetive.");
		else if(b.getBankId()<0) { 
			throw new BankException("Please enter Positive BankID");
		}else if(b.getName().isEmpty() || !(b.getName() instanceof String) || b.getName().isBlank() ) {
			throw new BankException("Please provide valid Bank Name.");
		}
		List<Bank> l = bankdao.findByName(b.getName());
		if(!(l.isEmpty()) && l.get(0).getBankId()!=b.getBankId()) {
			throw new BankException("Bank already Present.");
		}
		l = bankdao.findByWebsiteUrl(b.getWebsiteUrl());
		if(!l.isEmpty() && l.get(0).getBankId()!=b.getBankId()) {
			throw new BankException("URL already Present.");
		}
		return bankdao.save(b);
	}
	
	public Bank findBankById(int id) throws BankException {
		if(id<=0) {
			throw new BankException("Please enter Positive BankID");
		}
		Optional<Bank>b =  bankdao.findById(id);
		if(!b.isPresent()) {
			throw new BankException("Bank is not present with Id: "+id);
		}
		return b.get();
	}
	public List<Bank> findAll() throws BankException{
		List<Bank> b = bankdao.findAll();
		if(b.isEmpty()) {
			throw new BankException("No Bank is Present.");
		}
		return b;
	}
	
	//query1
	public List<Bank> findByName(String name) throws BankException{
		name = name.trim();
		List<Bank> l = bankdao.findByName(name);
		if(name.isEmpty() || name.isBlank()) { 
			throw new BankException("Please provide valid Bank Name.");
		}
		else if(l.isEmpty()) {
			throw new BankException("No record found with name:"+name);
		}
		return bankdao.findByName(name);
	}
	
	//query2
	public int totalBanks() {
		return bankdao.totalBanks();
	}
	//query3
	public List<BankCustomerCustomized> findBankWithCustomers() throws BankException{
		List<BankCustomerCustomized>l = bankdao.findBankWithCustomers();
		if(l.isEmpty()) throw new BankException("No Bank with customers is found.");
		return bankdao.findBankWithCustomers();
	}
}
