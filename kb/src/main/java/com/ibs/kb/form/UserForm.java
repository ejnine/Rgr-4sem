package com.ibs.kb.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserForm {

	@Email(message = "Email not correct")
	private String email;
	
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;
	
	@NotBlank
	@Size(min = 1)
	private String password;

	private int balance;


	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean pay(int price){
		if (balance - price < 0){
			return false;
		}
		balance -= price;
		return true;
	}
}
