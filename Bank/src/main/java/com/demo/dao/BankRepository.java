package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.demo.entity.Bank;
import java.net.URL;



public interface BankRepository extends JpaRepository<Bank,Integer>{
	
	@Query("select b from Bank b where b.name = :name")
	List<Bank> findByName(@Param("name") String name);
	
	@Query("select b.bankId as bankId, b.name as bankName, c.name as customerName, c.id as customerId from Bank b join Customer c on b.bankId = c.bankId")
	List<BankCustomerCustomized> findBankWithCustomers();
	
	List<Bank> findByWebsiteUrl(URL websiteUrl);
	
	int totalBanks();
}
