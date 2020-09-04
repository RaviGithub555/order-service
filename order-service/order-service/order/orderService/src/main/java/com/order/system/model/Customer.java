package com.order.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@Entity
@Document("customers")
public class Customer {
	
	@Id
	private String mongoId;
	
	@Field("CUSTOMER_ID")
	private int Id;
	
	@Field("CUSTOMER_NAME")
	private String customerName;
	
	@Field("CONTACT_NAME")
	private String contactName;
	
	@Field("ADDRESS")
	private String address;
	
	@Field("CITY")
	private String city;
	
	@Field("COUNTRY")
	private String country;
	
	@Field("POSTAL_CODE")	
	private String postalCode;

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotBlank
//    private String customerName;
//
//    @NotBlank
//    private String contactName;
//
//    @NotBlank
//    private String address;
//
//    @NotBlank
//    private String city;
//
//    @NotBlank
//    private String postalCode;
//
//    @NotBlank
//    private String country;
//
//	public Object getId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}