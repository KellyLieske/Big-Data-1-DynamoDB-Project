package com.example.demo;
	

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
	
@DynamoDBDocument
public class Patient {
	
	private String name;
	private String birthday;
	private String address;
	private int Age;
	//unused model in final code, fully functional just unused
	public Patient() {
		
	}
	
	public Patient(String name, String birtday, String address, int age) {
		super();
		this.name = name;
		this.birthday = birtday;
		this.address = address;
		Age = age;
	}
	
	
	@DynamoDBAttribute(attributeName = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@DynamoDBAttribute(attributeName = "Birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birtday) {
		this.birthday = birtday;
	}
	@DynamoDBAttribute(attributeName = "Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@DynamoDBAttribute(attributeName = "Age")
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}

	
	
	
	

}
