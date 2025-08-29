package org.example.model;

public class Customer {
	private long custID;
	private double custTotal;
	private String custName;
	public Customer() {
		custID = 0;
		custTotal = 0.0;
		custName = "";
	}

	public Customer(long custID, double custTotal, String custName) {
		this.custID = custID;
		this.custTotal = custTotal;
		this.custName = custName;
	}

	// Getters
	public long getCustID() { 
		return custID; 
		}
	public double getCustTotal() {
		 return custTotal; 
		 }
	public String getCustName() {
		 return custName; 
		 }

	// Setters
	public void setCustID(long custID) {
		this.custID = custID; 
		}
	public void setCustTotal(double custTotal) { 
		this.custTotal = custTotal; 
		}
	public void addToCustTotal(double value) { 
		this.custTotal += value; 
		}
	public void setCustName(String custName) { 
		this.custName = custName; 
		}
}
