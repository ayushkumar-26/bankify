package com.demo.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.bo.CustomerBo;
import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.CustomerCustomized;
import com.demo.entity.Customer;
import com.demo.exception.CustomerException;
import com.demo.response.ResponseObject;
import com.example.demo.BankApplication;


@Component
public class CustomerService {
	final static Logger LOG = Logger.getLogger(BankApplication.class);
	
	@Autowired
	CustomerBo bo;
	
	public ResponseObject addingCustomer(Customer c) {
		ResponseObject ro = new ResponseObject();
		LOG.info("Insert function called from Customer Service with Name: "+ c.getName() );
		try {
			Customer c1 = new Customer();
			c1 = bo.addingCustomer(c);
			ro.setC(c1);
			ro.setSuccessMsg("Customer is added Successfully with id: "+ c.getId());
			LOG.info("Customer is added Successfully.");
		} catch (CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
	public ResponseObject updateCustomer(Customer c) {
		ResponseObject ro = new ResponseObject();
		LOG.info("Update function called from Customer Service with Name: "+ c.getName() );
		try {
			Customer c1 = new Customer();
			c1 = bo.updateCustomer(c);
			ro.setC(c1);
			ro.setSuccessMsg("Customer is updated Successfully with Name: "+ c.getName());
			LOG.info("Customer is updated Successfully.");
		} catch (CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
//	public ResponseObject deleteById(int id) {
//		LOG.info("DeleteById is called from service with id:" +id);
//		ResponseObject ro = new ResponseObject();
//		try {
//			bo.deleteById(id);
//			ro.setSuccessMsg("Customer with the id: "+id+" is deleted.");
//			LOG.info("Customer with the given id is deleted.");
//		} catch (CustomerException e) {
//			ro.setFailureMsg(e.getMessage());
//			LOG.error(e.getMessage());
//		}
//		catch (Exception e) {
//			ro.setFailureMsg(e.getMessage());
//			LOG.error(e.getMessage());
//		}
//		return ro;
//	}
	public ResponseObject findCustomerById(int id) {
		LOG.info("FindCustomerbyId is called from service with id:" +id);
		ResponseObject ro = new ResponseObject();
		try {
			Customer c1 = new Customer();
			c1  = bo.findCustomerById(id);
			ro.setSuccessMsg("Customer with the id: "+id+" is " + c1.getName());
			LOG.info("Customer with the given id is fetched.");
			ro.setC(c1);
		} catch (CustomerException e) {
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
		LOG.info("Find all Customers is called from service layer.");
		ResponseObject ro = new ResponseObject();
		try {
			List<Customer> l = bo.findAll();
			ro.setListCustomer(l);
			ro.setSuccessMsg("All Customers are displayed Successfully");
			LOG.info("All Customers are fetched.");
		} catch (CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro; 
	}
	
	public ResponseObject findCustomersWithGreaterId(int id){
		LOG.info("Customers with Id Greater than "+ id+" is called in Service.");
		ResponseObject ro = new ResponseObject();
		try {
			List<Customer> c1 = bo.findCustomersWithGreaterId(id);
			ro.setListCustomer(c1);
			ro.setSuccessMsg("Customers with id Greater than "+id+" is fetched.");
			LOG.info("findCustomersWithGreaterId is called Successfully");
		} catch(CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}  catch(Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
	public ResponseObject findCustomerWithName(String name){
		name = name.trim();
		LOG.info("Customers with the name "+name+" is called in Service.");
		ResponseObject ro = new ResponseObject();
		try {
			List<BankCustomerCustomized> c1 = bo.findCustomerWithName(name);
			ro.setListBankCustomerCustomized(c1);
			ro.setSuccessMsg("Customers with name "+name+" is fetched.");
			LOG.info("findCustomerWithName is called Successfully");
		} catch(CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}  catch(Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
	
	public ResponseObject findAllInDescendingOrder(){
		LOG.info("Customers in Descending Order of their name is called in Service.");
		ResponseObject ro = new ResponseObject();
		try {
			List<Customer> c1 = bo.findAllInDescendingOrder();
			ro.setListCustomer(c1);
			ro.setSuccessMsg("Customers in Descending Order of their name is fetched.");
			LOG.info("findAllInDescendingOrder is called Successfully");
		} catch(CustomerException e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}  catch(Exception e) {
			ro.setFailureMsg(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ro;
	}
}
