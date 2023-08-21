package com.demo.dto;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.cglib.core.Local;

public class BankDTO implements Serializable{
	private int bankId;
	private String name;
	public LocalDate getDateEstablished() {
		return dateEstablished;
	}
	public void setDateEstablished(LocalDate dateEstablished) {
		this.dateEstablished = dateEstablished;
	}
	public int getTotalBranches() {
		return totalBranches;
	}
	public void setTotalBranches(int totalBranches) {
		this.totalBranches = totalBranches;
	}
	public URL getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(URL websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	private Date createdAt;
	private Date modifiedAt;
	private LocalDate dateEstablished;
	private int totalBranches;
	private URL websiteUrl;
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
}
