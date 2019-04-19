package com.saptris.erp;

public class DataType {
	public static String validatePhone(String phone) throws IllegalArgumentException {
		if(phone.matches("\\d{10}"))
			return phone;
		else
			throw new IllegalArgumentException("Invalid value for phone");
	}
	public static String validateEmail(String email) throws IllegalArgumentException {
		//if(email.contains("@") && email.contains(".") && email.indexOf("@")!=0 && email.lastIndexOf("@")!=email.length()-2 && email.lastIndexOf(".")!=0 && email.indexOf(".")!=email.length()-2)
		//if(email.matches("^[\\\\w!#$%&'*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$"))
		if(email.matches(".+[@].+\\..+"))
			return email;
		else
			throw new IllegalArgumentException("Invalid value for email");
	}
	public static String validateGender(String gender) throws IllegalArgumentException {
		if(gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("Female"))
			return gender;
		else
			throw new IllegalArgumentException("Invalid value for gender");
	}
	public static String validateIfsc(String ifsc) throws IllegalArgumentException {
		if(ifsc.matches("^[A-Za-z]{4}0[A-Z0-9a-z]{6}$"))
			return ifsc;
		else
			throw new IllegalArgumentException("Invalid value for ifsc");
	}
}