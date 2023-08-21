package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	@Query("select c from Customer c where c.id > :id1")
	List<Customer> findCustomersWithGreaterId(@Param("id1") int id);
	
	@Query("select c.name as customerName, c.id as customerId, c.bankId as bankId, c.accountNumber as accountNumber, b.name as bankName from Customer as c join Bank as b on b.bankId = c.bankId where c.name = :name1")
	List<BankCustomerCustomized> findCustomerWithName(@Param("name1") String name);
	
	List<Customer>findByAccountNumber(String accountNumber);
	List<Customer> findByContactNumber(String contactNumber);
	List<Customer> findByEmail(String email);
	//void deleteById(int id);
	//NamedQuery
	List<Customer> findAllInDescendingOrder();
}
