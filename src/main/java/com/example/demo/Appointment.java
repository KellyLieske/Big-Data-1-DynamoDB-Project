package com.example.demo;

import java.util.Comparator;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Appointment")
public  class Appointment implements Comparable<Appointment> {
	

	String date;
	String time;
	String reasonForVisit;
	Patient patient = new Patient();
	
	public Appointment() {
		
		
	}
	
	public Appointment(String date, String time, String reasonForVisit) {

		this.date = date;
		this.time = time;
		this.reasonForVisit = reasonForVisit;
		
	}
	
	
	@DynamoDBHashKey(attributeName = "Date")
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@DynamoDBRangeKey(attributeName = "Time")
	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}


	@DynamoDBAttribute(attributeName = "Reason for Visit")
	public String getReasonForVisit() {
		return reasonForVisit;
	}



	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}


	@DynamoDBAttribute(attributeName = "Person")
	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	@Override
	public String toString() {
		return "Appointment [date=" + date + ", time=" + time + ", reasonForVisit=" + reasonForVisit + ", patient="
				+ patient + "]";
	}

	@Override
	public int compareTo(Appointment o) {
		
		if(this.date.equals( o.getDate()) && this.time.equals(o.getTime()))
			return 0;
		if(this.date.equals( o.getDate()))
			return this.time.compareTo(o.getTime());
		
		else
			return this.date.compareTo(o.getDate());
	}
	public static  Comparator<Appointment> appComp = new Comparator<Appointment>() {
		
		public int compare(Appointment app1, Appointment app2) {
		if(app1.date.equals( app2.getDate()) && app1.time.equals(app2.getTime()))
			return 0;
		if(app1.date.equals( app2.getDate()))
			return app1.time.compareTo(app2.getTime());
		
		else
			return app1.date.compareTo(app2.getDate());
	}
	};
}


		
	

