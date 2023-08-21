package com.demo.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.dto.BankDTO;
import com.demo.dto.CustomerDTO;
import com.demo.entity.Bank;
import com.demo.entity.Customer;
import com.demo.response.ResponseObject;
import com.demo.service.BankService;
import com.demo.service.CustomerService;

@RestController
//@RequestMapping("bank")
public class ControllerLayer {
	@Autowired
	BankService bank;
	
	@Autowired
	CustomerService customer;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@RequestMapping("/sayHello")
	public String sayHello() {
		return "Hello!";
	}
	
	@RequestMapping("/sayHelloUser")
	public String sayHelloUser(@RequestParam(value  = "name" , defaultValue = "Ayush") String name ,
			@RequestParam(value = "phone" , required = true) long phone){
		return "Hello "+ name +" and "+ phone+" is your number.";
	}
	
	@RequestMapping("/pathVarialbleExample/{empId}")
	public String pathVariableExample(@PathVariable("empId") int id) {
		return "Employee id is "+ id;
	}
	
	@RequestMapping("/greetingUser")
	public GreetingMessage greetingUser() {
		GreetingMessage msg = new GreetingMessage();
		msg.setMessage("Good Morning!");
		msg.setName("Shyam");
		return msg;
	}
	
	@RequestMapping("/greetingUserName")
	public GreetingMessage greetingUserName(@RequestParam(value = "name", defaultValue = "Ayush") String name, 
			@RequestParam(value = "message") String message) {
		GreetingMessage msg = new GreetingMessage();
		msg.setMessage(message);
		msg.setName(name);
		return msg;
	}
	
	@RequestMapping("/greetingUserUpperCase")
	public GreetingMessage greetingUserUpperCase(GreetingMessage msg) {
		msg.setName(msg.getName().toUpperCase());
		msg.setMessage(msg.getMessage().toUpperCase());
		return msg;
	}
	
	@RequestMapping("/userMap")
	public Map<String, Users> userMap(){
		Map<String,Users> mp = new HashMap<String,Users>();
		
		Users u1 = new Users();
		u1.setAge(11);
		u1.setName("Tanya");
		
		Users u2 = new Users();
		u2.setAge(14);
		u2.setName("Samyak");
		
		mp.put(u1.getName(), u1);
		mp.put(u2.getName(), u2);
		return mp;
	}
	
	@PostMapping("/greetingMessagePost")
	public GreetingMessage greetingMessagePost(@RequestBody GreetingMessage msg) {
		System.out.println("Message :" + msg.getMessage());
		System.err.println("Name :"+ msg.getName());
		return msg;
	}
	
	
	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
	public ResponseObject addBank(@RequestBody BankDTO dto) {
		
		ResponseObject ro = new ResponseObject();
		Bank b = new Bank();
		b.setName(dto.getName());
		b.setDateEstablished(dto.getDateEstablished());
		b.setTotalBranches(dto.getTotalBranches());
		b.setWebsiteUrl(dto.getWebsiteUrl());
		ro = bank.addingBank(b);
		return ro;
	}
	
	@RequestMapping(value = "/updateBank", method = RequestMethod.POST)
	public ResponseObject updateBank(@RequestBody BankDTO dto) {
		ResponseObject ro = new ResponseObject();
		Bank b = new Bank();
		b.setBankId(dto.getBankId());
		b.setName(dto.getName());
		b.setDateEstablished(dto.getDateEstablished());
		b.setTotalBranches(dto.getTotalBranches());
		b.setWebsiteUrl(dto.getWebsiteUrl());
		ro = bank.updateBank(b);
		return ro;
	}
	
	@GetMapping("/fetchBankById")
	public ResponseObject fetchBankById(@RequestParam(value="id" , defaultValue = "0") int id) {
		ResponseObject ro = new ResponseObject();
		
		ro = bank.findBankById(id);
		return ro;
	}
	
	@PostMapping("/addCustomer")
	public ResponseObject addCustomer(@RequestBody CustomerDTO dto) {	
		Bank b = new Bank();
		b.setBankId(dto.getBankId());
		Customer c  = new Customer();
//		c.setBankId(dto.getBankId());
		c.setBank(b);
		c.setCity(dto.getCity());
		c.setName(dto.getName());
		c.setAccountNumber(dto.getAccountNumber());
		c.setContactNumber(dto.getContactNumber());
		c.setDob(dto.getDob());
		c.setGender(dto.getGender());
		c.setEmail(dto.getEmail());
		ResponseObject rr = customer.addingCustomer(c);
		return rr;
	}
	
	@PostMapping("/updateCustomer")
	public ResponseObject updateCustomer(@RequestBody CustomerDTO dto) {	
		Bank b = new Bank();
		b.setBankId(dto.getBankId());
		Customer c  = new Customer();
		c.setId(dto.getId());
		c.setBankId(dto.getBankId());
		c.setBank(b);
		c.setCity(dto.getCity());
		c.setName(dto.getName());
		c.setAccountNumber(dto.getAccountNumber());
		c.setContactNumber(dto.getContactNumber());
		c.setDob(dto.getDob());
		c.setGender(dto.getGender());
		c.setEmail(dto.getEmail());
		ResponseObject rr = customer.updateCustomer(c);
		return rr;
	}
	
	@GetMapping("/fetchCustomerById")
	public ResponseObject fetchCustomerById(@RequestParam(value="id", defaultValue ="-1") int id) {
		ResponseObject ro = customer.findCustomerById(id);
		return ro;
	}
	
//	@DeleteMapping("/deleteCustomerById/{id}")
//	public ResponseObject deleteCustomerById(@PathVariable("id") int id) {
//		System.out.println("Delete function called for id: "+id);
//		ResponseObject ro = customer.deleteById(id);
//		return ro;
//	}
	
	@GetMapping("/findCustomerWithName")
	public ResponseObject findCustomerWithName(@RequestParam(value="name") String name){
		
		ResponseObject ro = customer.findCustomerWithName(name);
		return ro;
	}
	
	@GetMapping ("/findCustomersWithGreaterId")
	public ResponseObject findCustomersWithGreaterId(@RequestParam(value ="id", defaultValue ="0") int id){
	
		ResponseObject ro = new ResponseObject();
		ro = customer.findCustomersWithGreaterId(id);
		System.out.println(ro.getListCustomer());
		return ro;
	}
	
	@RequestMapping(value = "/findAllCustomers", method = RequestMethod.GET )
	public ResponseObject findAllCustomers() {
		ResponseObject ro = customer.findAll();
		return ro;
	}
	
	@GetMapping("/findAllCustomersInDescendingOrder")
	public ResponseObject findAllCustomersInDescendingOrder() {
		ResponseObject ro = new ResponseObject();
		ro = customer.findAllInDescendingOrder();
		return ro;
	}
	
	@GetMapping("/findAllBanks")
	public ResponseObject findAllBanks() {
		ResponseObject ro = new ResponseObject();
		ro = bank.findAll();
		List<Bank>l = new ArrayList<Bank>();
		Bank b = new Bank();
		Bank b2 = new Bank();
		l.add(b);
		l.add(b2);
//		for(Bank it:ro.getListBank()) {
//			l.add(it);
//		}
		return ro;
	}
	
	@GetMapping("/findBankByName")
	public ResponseObject findBankByName(@RequestParam(value="name") String name){
		ResponseObject ro = bank.findBankByName(name);
		return ro;
	}
	
	@GetMapping("/totalBanks")
	public int totalBanks() {
		int count = bank.totalbanks();
		return count;
	}
	@PostMapping ("/findBankWithCustomers")
	public ResponseObject findBankWithCustomers(){
		ResponseObject ro = new ResponseObject();
		ro = bank.findBankWithCustomers();
		return ro;
	}
	
//	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
//	public BankDTO addBank(@RequestBody BankDTO dto) {
//		Bank b = new Bank();
//		b.setName(dto.getName());
//		Bank b1 = bank.addingBank(b);
//		dto.setBankId(b1.getBankId());
//		dto.setCreatedAt(b1.getCreatedAt());
//		dto.setModifiedAt(b1.getModifiedAt());
//		return dto;
//	}
	
	
//	@GetMapping("/fetchBankById")
//	public BankDTO fetchBankById(@RequestParam int id) {
//		Bank b = bank.findBankById(id);
//		BankDTO dto = new BankDTO();
//		dto.setBankId(b.getBankId());
//		dto.setCreatedAt(b.getCreatedAt());
//		dto.setModifiedAt(b.getModifiedAt());
//		dto.setName(b.getName());
//		return dto;
//	}
	
//	@PostMapping("/addCustomer")
//	public CustomerDTO addCustomer(@RequestBody CustomerDTO dto) {
//		Bank b = bank.findBankById(dto.getBankId());
//		Customer c  = new Customer();
//		c.setBank(b);
//		c.setCity(dto.getCity());
//		c.setName(dto.getName());
//		c = customer.addingCustomer(c);
//		dto.setCreatedAt(c.getCreatedAt());
//		dto.setId(c.getId());
//		dto.setModifiedAt(c.getModifiedAt());
//		return dto;
//	}
	
//	@PostMapping("/fetchCustomerById")
//	public Customer fetchCustomerById(@RequestBody Integer id) {
//		Customer c = customer.FindCustomerById(id);
//		return c;
//	}
	
//	@GetMapping("/findCustomerWithName")
//	public List<CustomerDTO> findCustomerWithName(@RequestParam(value="name") String name){
//		
//		List<CustomerCustomized> l = customer.findCustomerWithName(name);
//		List<CustomerDTO> dtoList = new ArrayList<CustomerDTO>();
//		for(CustomerCustomized i:l) {
//			CustomerDTO dto = new CustomerDTO();
//			dto.setId(i.getCustomerId());
//			dto.setCity(i.getCity());
//			dtoList.add(dto);
//		}
//		return dtoList;
//	}
//	@RequestMapping ("/findCustomersWithGreaterId")
//	public List<CustomerDTO> findCustomersWithGreaterId(int id){
//		List<CustomerDTO> dtoList  = new ArrayList<CustomerDTO>();
//		List<Customer> l = customer.findCustomersWithGreaterId(id);
//		for(Customer i:l) {
//			CustomerDTO dto = new CustomerDTO();
//			dto.setId(i.getId());
//			dto.setBankId(i.getBankid());
//			dto.setCity(i.getCity());
//			dto.setCreatedAt(i.getCreatedAt());
//			dto.setModifiedAt(i.getModifiedAt());
//			dto.setName(i.getName());
//			dtoList.add(dto);
//		}
//		return dtoList;
//	}
	
//	@PostMapping ("/findBankWithCustomers")
//	public List<BankCustomerDTO> findBankWithCustomers(){
//		List<BankCustomerDTO> dtoList = new ArrayList<BankCustomerDTO>();
//		List<BankCustomerCustomized> l = bank.findBankWithCustomers();
//		for(BankCustomerCustomized i : l) {
//			BankCustomerDTO dto = new BankCustomerDTO();
//			dto.setBankId(i.getBankId());
//			dto.setBankName(i.getBankName());
//			dto.setCity(i.getCity());
//			dto.setCustomerId(i.getCustomerId());
//			dto.setCustomerName(i.getCustomerName());
//			dtoList.add(dto);
//		}
//		return dtoList;
//	}
	
	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("http://localhost:3000") // Allow requests from your React app
	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowCredentials(true);
	    }
	}
}