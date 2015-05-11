package be.ordina.readfiles.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PersonRecord implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = 6768529907437592854L;
	
	private String name;
	private String address;
	private String postCode;
	private String phone;
	private BigDecimal creditLimit;
	private LocalDate birthday;
	
	
	public PersonRecord(String name, String address, String postCode, String phone, BigDecimal creditLimit, LocalDate date) {
		this.name = name;
		this.address = address;
		this.postCode = postCode;
		this.phone = phone;
		this.creditLimit = creditLimit;
		this.birthday = date;
	}
	
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getPhone() {
		return phone;
	}
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	
	

}
