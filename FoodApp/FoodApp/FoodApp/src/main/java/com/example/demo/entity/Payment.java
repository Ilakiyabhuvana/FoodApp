package com.example.demo.entity;

import aj.org.objectweb.asm.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Payment", schema="arusuvai")
public class Payment {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String Address;
	private String city;
   private String state;
   private long pincode;
   private long number;
   public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public long getPincode() {
	return pincode;
}
public void setPincode(long pincode) {
	this.pincode = pincode;
}
public long getNumber() {
	return number;
}
public void setNumber(long number) {
	this.number = number;
}
   
   
}
