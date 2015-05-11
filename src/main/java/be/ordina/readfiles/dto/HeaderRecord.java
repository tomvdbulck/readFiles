package be.ordina.readfiles.dto;

import java.io.Serializable;

public class HeaderRecord implements Serializable {
	

	
	/**
	 */
	private static final long serialVersionUID = -2502334733585866659L;
	
	
	private String nameLabel;
	private String addressLabel;
	private String postcodeLabel;
	private String phoneLabel;
	private String creditLimitLabel;
	private String birthdayLabel;
	public String getNameLabel() {
		return nameLabel;
	}
	public void setNameLabel(String nameLabel) {
		this.nameLabel = nameLabel;
	}
	public String getAddressLabel() {
		return addressLabel;
	}
	public void setAddressLabel(String addressLabel) {
		this.addressLabel = addressLabel;
	}
	public String getPostcodeLabel() {
		return postcodeLabel;
	}
	public void setPostcodeLabel(String postcodeLabel) {
		this.postcodeLabel = postcodeLabel;
	}
	public String getPhoneLabel() {
		return phoneLabel;
	}
	public void setPhoneLabel(String phoneLabel) {
		this.phoneLabel = phoneLabel;
	}
	public String getCreditLimitLabel() {
		return creditLimitLabel;
	}
	public void setCreditLimitLabel(String creditLimitLabel) {
		this.creditLimitLabel = creditLimitLabel;
	}
	public String getBirthdayLabel() {
		return birthdayLabel;
	}
	public void setBirthdayLabel(String birthdayLabel) {
		this.birthdayLabel = birthdayLabel;
	}
	
	

}
