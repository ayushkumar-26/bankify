package com.demo.service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.demo.bo.BankBo;
import com.demo.bo.CustomerBo;
import com.demo.entity.Bank;
import com.demo.entity.Customer;
import com.demo.exception.BankException;
import com.demo.exception.CustomerException;
import com.demo.response.ResponseObject;
import com.example.demo.BankApplication;

@Component
public class BankService {
	final static Logger LOG = Logger.getLogger(BankApplication.class);
	
	@Autowired
	BankBo bo;
	
	@Autowired
	CustomerBo co;
	
	
	public ResponseObject addingBank(Bank b) {
		ResponseObject ro = new ResponseObject();
		
		LOG.info("Insert function called from Bank Service with Id: "+b.getName() );
		
		try {
			Bank b1  = new Bank();
			b1 = bo.addingBank(b);
			LOG.info("Bank is inserted Successfully with Id: "+b.getBankId());
			ro.setSuccessMsg("Bank Added Successfully! with id: "+b.getBankId());
			ro.setB(b1);
		} catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch(Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
	public ResponseObject updateBank(Bank b) {
		ResponseObject ro = new ResponseObject();
		
		LOG.info("Update function called from Bank Service with Name: "+b.getName() );
		
		try {
			Bank b1  = new Bank();
			b1 = bo.updateBank(b);
			LOG.info("Bank is updated Successfully with Id: "+b.getBankId());
			ro.setSuccessMsg("Bank updated Successfully!");
			ro.setB(b1);
		} catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch(Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	public ResponseObject findBankById(int id) {
		LOG.info("FindBankById function is called from service layer with Id: " + id);
		ResponseObject ro = new ResponseObject();
		try {
			Bank b1 = new Bank();
			b1 = bo.findBankById(id);
			ro.setSuccessMsg("Bank with the id: "+id+" is " + b1.getName());
			LOG.info("Bank with the given id is fetched.");
			ro.setB(b1);
		} catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	public ResponseObject findAll() {
		LOG.info("Find all banks is called from service layer.");
		ResponseObject ro = new ResponseObject();
		try {
			ro.setListBank(bo.findAll());
			ro.setSuccessMsg("All Banks are displayed Successfully");
			LOG.info("All banks are fetched.");
		} catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro; 
	}
	
	public ResponseObject findBankByName(String name) {
		name = name.trim();
		LOG.info("FindBankByName is called from service with name: "+name);
		ResponseObject ro = new ResponseObject();
		try {
			ro.setListBank(bo.findByName(name));
			ro.setSuccessMsg("Bank with name: "+name+" is fetched.");
			LOG.info("Bank with name: "+name+" is fetched.");
		} catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
	public  int totalbanks() {
		LOG.info("Count total number of Teams function is called in service.");
		return bo.totalBanks();
	}
	
	public ResponseObject findBankWithCustomers(){
		LOG.info("find Bank with CUstomers is called from Service.");
		ResponseObject ro = new ResponseObject();
		
		try {
			ro.setListBankCustomerCustomized(bo.findBankWithCustomers());
			LOG.info("Bank with Customers is successfully called in service.");
			ro.setSuccessMsg("Bank with Customers are fetched Successfully.");
		}  catch (BankException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
			
		return ro;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertBankAndCustomer(Bank b, Customer c) throws BankException, CustomerException{
		LOG.info("Transaction function called from Bank Service");
		System.out.println("Before Adding Bank:");
		bo.addingBank(b);
		System.out.println("After Adding Bank:");
		System.out.println("Before Adding Customer:");
		co.addingCustomer(c);;
		System.out.println("After Adding Customer:");
	}
}
