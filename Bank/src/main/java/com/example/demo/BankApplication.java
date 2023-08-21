package com.example.demo;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.CustomerCustomized;
import com.demo.entity.Bank;
import com.demo.entity.Customer;
import com.demo.response.ResponseObject;
import com.demo.service.BankService;
import com.demo.service.CustomerService;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.demo")
@EntityScan(basePackages = { "com.demo.entity" })
@EnableJpaRepositories("com.demo.dao")
public class BankApplication {
	final static Logger LOG = Logger.getLogger(BankApplication.class);

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BankApplication.class, args);
		LOG.info("The System has Started");

		BankService bankManager = ctx.getBean(BankService.class);
		CustomerService customerManager = ctx.getBean(CustomerService.class);

		Scanner sc = new Scanner(System.in);

//		System.out.println(menu);
		while (true) {
			
			System.out.println("Menu :");
			System.out.println("1. ADD BANK");
			System.out.println("2. UPDATE BANK");
			System.out.println("3. FETCH BANK BY ID");
			System.out.println("4. FETCH ALL BANKS");
			System.out.println("5. ADD CUSTOMER");
			System.out.println("6. UPDATE CUSTOMER");
			System.out.println("7. FETCH CUSTOMER BY ID");
			System.out.println("8. FETCH ALL CUSTOMERS");
			System.out.println("9. FETCH BANK BY NAME");
			System.out.println("10.TOTAL BANKS");
			System.out.println("11.BANK WITH CUSTOMERS");
			System.out.println("12.FETCH CUSTOMERS WITH  GREATER ID");
			System.out.println("13.FETCH CUSTOMERS WITH NAME");
			System.out.println("14.FETCH CUSTOMERS IN DESC ORDER");        
			System.out.println("15.EXIT");
			String val = sc.nextLine().trim();
			while(val.isBlank() || isNumeric(val)==false) {
				if(!val.isBlank()) System.out.println("Please enter a number from : 1 - 15");
				val = sc.nextLine().trim();
			}
			int menu = Integer.parseInt(val);
			
			switch (menu) {
			case 1:
				LOG.info("Add bank function is called from main.");
				Bank b1 = new Bank();
				System.out.println("Enter the Name of Bank:");
				String name1 = sc.nextLine();
				b1.setName(name1);
				ResponseObject ro1 = new ResponseObject();
				ro1 = bankManager.addingBank(b1);
				if (ro1.getSuccessMsg() != null) {
					System.out.println(ro1.getSuccessMsg());
				} else {
					System.out.println(ro1.getFailureMsg());
				}
				break;

			case 2:
				LOG.info("Update bank function is called from main.");
				Bank b2 = new Bank();
				System.out.println("Enter the Bank Id:");
				int id2 = 0;  
				try {
					id2 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				b2.setBankId(id2);
				System.out.println("Enter the Name of Bank:");
				String name2 = sc.nextLine();
				b2.setName(name2);
				ResponseObject ro2 = new ResponseObject();
				ro2 = bankManager.addingBank(b2);
				if (ro2.getSuccessMsg() != null) {
					System.out.println(ro2.getSuccessMsg());
				} else {
					System.out.println(ro2.getFailureMsg());
				}
				break;

			case 3:
				LOG.info("Find Bank by Id is called from Main.");
				System.out.println("Enter the Bank Id:");
				int id3 = 0;
				try {
					id3 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				ResponseObject ro3 = new ResponseObject();
				ro3 = bankManager.findBankById(id3);
				if (ro3.getSuccessMsg() != null) {
					System.out.println(ro3.getSuccessMsg());
					Bank b3 = ro3.getB();
					System.out.println("============================================================");
					System.out.printf("%-5s%-10s%-30s%-20s\n", "BankID", "Name", "CreatedAt", "ModifiedAt");
					System.out.println("============================================================");
					System.out.printf("%-5s%-10s%-30s%-20s\n", b3.getBankId(), b3.getName(), b3.getCreatedAt(),
							b3.getModifiedAt());
				} else {
					System.out.println(ro3.getFailureMsg());
				}
				break;

			case 4:
				LOG.info("Fetch all banks called from main.");
				ResponseObject ro4 = new ResponseObject();
				ro4 = bankManager.findAll();
				List<Bank> l4 = ro4.getListBank();

				if (ro4.getSuccessMsg() != null) {
					System.out.println(ro4.getSuccessMsg());
					System.out.println("===================================================================================");
					System.out.printf("%-10s%-20s%-30s%-20s\n", "BankID", "Name", "CreatedAt", "ModifiedAt");
					System.out.println("===================================================================================");
					for (Bank vo : l4)
						System.out.printf("%-10s%-20s%-30s%-20s\n", vo.getBankId(), vo.getName(), vo.getCreatedAt(),
								vo.getModifiedAt());
				} else {
					System.out.println(ro4.getFailureMsg());
				}
				break;

			case 5:
				LOG.info("Add Customer is called from main.");
				Bank b5 = new Bank();
				ResponseObject ro5 = new ResponseObject();
				Customer c5 = new Customer();

				System.out.println("Enter the Name of Customer:");
				String name5 = sc.nextLine();
				c5.setName(name5);

				System.out.println("Enter the City:");
				String city5 = sc.nextLine();
				c5.setCity(city5);

				System.out.println("Enter the Bank Id:");
				int id5 = 0;
				try {
					id5 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				b5.setBankId(id5);
				c5.setBank(b5);
				ro5 = customerManager.addingCustomer(c5);

				if (ro5.getSuccessMsg() != null) {
					System.out.println(ro5.getSuccessMsg());
				} else {
					System.out.println(ro5.getFailureMsg());
				}
				break;

			case 6:
				LOG.info("Update Customer function is called from main.");
				ResponseObject ro6 = new ResponseObject();
				Bank b6 = new Bank();
				Customer c6 = new Customer();
				System.out.println("Enter the Customer Id:");
				int cid6 = 0;
				try {
					cid6 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				c6.setId(cid6);

				System.out.println("Enter the Name of Customer:");
				String name6 = sc.nextLine();
				c6.setName(name6);

				System.out.println("Enter the City:");
				String city6 = sc.nextLine();
				c6.setCity(city6);

				System.out.println("Enter the Bank Id:");
				int id6 = 0;
				
				try {
					id6 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				
				b6.setBankId(id6);
				c6.setBank(b6);
				ro6 = customerManager.addingCustomer(c6);

				if (ro6.getSuccessMsg() != null) {
					System.out.println(ro6.getSuccessMsg());
				} else {
					System.out.println(ro6.getFailureMsg());
				}
				break;

			case 7:
				LOG.info("Fetch Customer by Id is called from main.");
				System.out.println("Enter the Customer Id:");
				int id7 = 0;
				try {
					id7 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				ResponseObject ro7 = new ResponseObject();
				ro7 = customerManager.findCustomerById(id7);

				if (ro7.getSuccessMsg() != null) {
					System.out.println(ro7.getSuccessMsg());
					Customer c7 = ro7.getC();
					System.out.println(
							"==============================================================================================");
					System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", "ID", "Name", "City", "Bank_Id", "CreatedAt",
							"ModifiedAt");
					System.out.println(
							"==============================================================================================");
					System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", c7.getId(), c7.getName(), c7.getCity(),
							c7.getBankId(), c7.getCreatedAt(), c7.getModifiedAt());
				} else {
					System.out.println(ro7.getFailureMsg());
				}
				break;

			case 8:
				LOG.info("Fetch all Customers called from main.");
				ResponseObject ro8 = new ResponseObject();
				ro8 = customerManager.findAll();

				if (ro8.getSuccessMsg() != null) {
					System.out.println(ro8.getSuccessMsg());
					List<Customer> c8 = ro8.getListCustomer();
					System.out.println(
							"==============================================================================================");
					System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", "ID", "Name", "City", "Bank_Id", "CreatedAt",
							"ModifiedAt");
					System.out.println(
							"==============================================================================================");
					for (Customer vo : c8) {
						System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", vo.getId(), vo.getName(), vo.getCity(),
								vo.getBankId(), vo.getCreatedAt(), vo.getModifiedAt());
					}
				} else {
					System.out.println(ro8.getFailureMsg());
				}
				break;
				
			case 9:
				LOG.info("Fetch Bank with Name is called from main.");
				ResponseObject ro9 = new ResponseObject();
				System.out.println("Enter Bank Name:");
				String name9 = sc.nextLine();
				ro9 = bankManager.findBankByName(name9);
				if (ro9.getSuccessMsg() != null) {
					System.out.println(ro9.getSuccessMsg());
					List<Bank> b9 = ro9.getListBank();
					System.out.println("=========================================================================");
					System.out.printf("%-10s%-10s%-30s%-20s\n", "BankID", "Name", "CreatedAt", "ModifiedAt");
					System.out.println("=========================================================================");
					for (Bank vo : b9)
						System.out.printf("%-10s%-10s%-30s%-20s\n", vo.getBankId(), vo.getName(), vo.getCreatedAt(),vo.getModifiedAt());
					
				} else {
					System.out.println(ro9.getFailureMsg());
				}
				break;
			
			case 10:
				LOG.info("Total Banks function is called from main.");
				int count = bankManager.totalbanks();
				System.out.println("Total Number of Banks is "+ count);
				break;
			
			case 11:
				LOG.info("Banks with Customers function is called from main.");
				ResponseObject ro11 = new ResponseObject();
				ro11 = bankManager.findBankWithCustomers();
				if (ro11.getSuccessMsg() != null) {
					System.out.println(ro11.getSuccessMsg());
					List<BankCustomerCustomized> bc11 = ro11.getListBankCustomerCustomized();
					System.out.println("=========================================================================");
					System.out.printf("%-10s%-15s%-10s%-15s\n", "BankID", "BankName", "CustomerID", "CustomerName");
					System.out.println("=========================================================================");
					for (BankCustomerCustomized vo : bc11)
						System.out.printf("%-10s%-15s%-10s%-15s\n", vo.getBankId(), vo.getBankName(), vo.getCustomerId(),vo.getCustomerName());
					
				} else {
					System.out.println(ro11.getFailureMsg());
				}
				break;
				
			case 12:
				LOG.info("findCustomersWithGreaterId is called from main.");
				ResponseObject ro12 = new ResponseObject();
				System.out.println("Enter the Customer Id:");
				int id12 = 0;
				try {
					id12 = Integer.parseInt(sc.nextLine());
				}
				catch(NumberFormatException e){
					System.out.println("Please enter numeric value");
					break;
				}
				
				
				
				ro12 = customerManager.findCustomersWithGreaterId(id12);
				if (ro12.getSuccessMsg() != null) {
					System.out.println(ro12.getSuccessMsg());
					List<Customer> c12 = ro12.getListCustomer();
					System.out.println(
							"==============================================================================================");
					System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", "ID", "Name", "City", "Bank_Id", "CreatedAt",
							"ModifiedAt");
					System.out.println(
							"==============================================================================================");
					for (Customer vo : c12) {
						System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", vo.getId(), vo.getName(), vo.getCity(),
								vo.getBankId(), vo.getCreatedAt(), vo.getModifiedAt());
					}
				} else {
					System.out.println(ro12.getFailureMsg());
				}
				break;
				
			case 13:
				LOG.info("findCustomersWithName is called from main.");
				ResponseObject ro13 = new ResponseObject();
				System.out.println("Enter the Customer Name:");
				String name13 = sc.nextLine();
				ro13 = customerManager.findCustomerWithName(name13);
				if (ro13.getSuccessMsg() != null) {
					System.out.println(ro13.getSuccessMsg());
					List<BankCustomerCustomized> c13 = ro13.getListBankCustomerCustomized();
					System.out.println(
							"=============================================================================================");
					System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Name", "CustomerID", "BankID","BankName", "AccountNumber");
					System.out.println(
							"=============================================================================================");
					for (BankCustomerCustomized vo : c13) {
						System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", vo.getCustomerName(),vo.getCustomerId(),vo.getBankId(), vo.getBankName(),vo.getAccountNumber());
					}
				} else {
					System.out.println(ro13.getFailureMsg());
				}
				break;
			
			case 14:
				LOG.info("Customers in desc order function is called from main.");
				ResponseObject ro14 = new ResponseObject();
				ro14 = customerManager.findAllInDescendingOrder();
				if (ro14.getSuccessMsg() != null) {
					System.out.println(ro14.getSuccessMsg());
					List<Customer> c14 = ro14.getListCustomer();
					System.out.println(
							"==============================================================================================");
					System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", "ID", "Name", "City", "Bank_Id", "CreatedAt",
							"ModifiedAt");
					System.out.println(
							"==============================================================================================");
					for (Customer vo : c14) {
						System.out.printf("%-5s%-15s%-15s%-10s%-30s%-20s\n", vo.getId(), vo.getName(), vo.getCity(),
								vo.getBankId(), vo.getCreatedAt(), vo.getModifiedAt());
					}
				} else {
					System.out.println(ro14.getFailureMsg());
				}
				break;
				
			case 15:
				System.out.println("Exiting...");
				System.out.println("Thank You!");
				System.exit(0);
				break;
	
			default:
				System.out.println("Please select valid Option!");
			}
			
		}
//		Bank b1 = new Bank();
//		b1.setName("HDFC");
//		b1.setBankId(1);
//		BankManager.addingBank(b1);
//		System.out.println(BankManager.FindAll());

//		Customer c1 = new Customer();
//		c1.setName("AyushKumar");
//		c1.setId(101);
//		c1.setBank(b1);
//		customerManager.addingCustomer(c1);

//		Bank b2 = new Bank();
//		b2.setName("SBI");
//		
//		Customer c2 = new Customer();
//		c2.setName("Raj Sharma");
//		
//		Customer c3 = new Customer();
//		c3.setName("Priya Gupta");
//		
//		c2.setBank(b2);
//		c3.setBank(b2);
//		
//		List<Customer> l1 = new ArrayList<Customer>();
//		l1.add(c2);
//		l1.add(c3);
//		b2.setCustomers(l1);
//		
//		BankManager.addingBank(b2);

//		Bank b3 = new Bank();
//		b3.setName("BOI");
//		
//		Customer c4 = new Customer();
//		c4.setName("Dev Jain");
//		c4.setBank(b3);
//		
//		BankManager.insertBankAndCustomer(b3, c4);

//		Bank b4=BankManager.FindBankById(7);
//		b4.setName("Axis");
//		b4.setBankId(3);
//		BankManager.addingBank(b4);

//		// Query
//		List<Customer> clist = customerManager.findCustomersWithGreaterId(2);
//		System.out.println("List of Customers with id greter than 2 : ->" + clist);
//		
//		//Customized Query
//		List<CustomerCustomized> clist1 = customerManager.findCustomerWithName("AyushKumar");
//		for(CustomerCustomized i:clist1) {
//			System.out.println("City -> "+ i.getCity() +" id -> "+i.getCustomerId());
//		}
//		
//		//NamedQuery
//		List<Customer> clist2 = customerManager.findAllInDescendingOrder();
//		System.out.println(clist2);

//		//AggregateFunction
//		int total = BankManager.totalbanks();
//		System.out.println("Total no. of banks are -> "+total);

//		//CustomQueryJoin
//		List<BankCustomerCustomized> l = BankManager.FindBankWithCustomers();
//		System.out.println("size: " + l.size());
//		for(BankCustomerCustomized i: l) {
//			System.out.println("Bank Id: "+ i.getBankId() +" Bank Name: "
//		+i.getBankName()+" Customer Id: "+ i.getCustomerId()+ " Customer Name: "+i.getCustomerName());
//		}
//		
//		Customer c4 = new Customer();
//		Bank b4 = BankManager.FindBankById(1);
//		c4.setName("Ravi Bansal");
//		c4.setCity("Mumbai");
//		c4.setBank(b4);
//		customerManager.addingCustomer(c4);

	}
	
	static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}

