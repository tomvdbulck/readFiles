package be.ordina.readfiles.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonRecord implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = 6768529907437592854L;
	
	private String name;
	private String address;
	private String postCode;
	private Long credit;
	private Date date;
	
	
	public PersonRecord(String name, String address, String postCode, Long credit, Date date) {
		this.name = name;
		this.address = address;
		this.postCode = postCode;
		this.credit = credit;
		this.date = date;
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
	public Long getCredit() {
		return credit;
	}
	public Date getDate() {
		return date;
	}
	
	

}
